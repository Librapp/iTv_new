package com.sumavision.itv.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.sumavision.itv.R;
import com.sumavision.itv.controller.LoadingController;
import com.sumavision.itv.data.CtProgram;
import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.DLNAData;
import com.sumavision.itv.data.JSONMessageType;
import com.sumavision.itv.data.OtherCacheData;
import com.sumavision.itv.data.UpdateData;
import com.sumavision.itv.listener.OnLoadingListener;
import com.sumavision.itv.service.LocateMySelfService;
import com.sumavision.itv.utils.AppUtil;
import com.sumavision.itv.utils.BitmapUtils;
import com.sumavision.itv.utils.NetUtil;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoadingActivity extends BaseActivity implements OnLoadingListener
{

	private String sessionId;
	private final String CONFIGFILE = "userSetting";
	private final int DELAY = 2000;
	private TextView loadingTips;
	// 自动更新程序，下载最新版
	private ProgressBar progressBar;
	private AlertDialog tips_progress;
	private String folderPath = "";
	private final int OPEN_MAIN = 10;
	private final int DOWN_OVER = 11;
	private final int DOWN_START = 12;
	private final int DOWN_STOP = 13;
	private TextView txt_progress;
	private Intent intent;
	private HttpURLConnection conn;
	private InputStream is;
	private FileOutputStream fos;
	private int downFileSize = 0;
	private int fileSize;
	private boolean isCancelDownload = false;
	private final String fileName = "iTV+update.apk";
	private final int REQUESTCODE_UPDATE = 12;
	private Thread tDownload;
	private ProgressBar tipsPb;
	private Button ok;
	private Button cancel;
	private TextView txtTips;
	private ImageView allBg;
	private RelativeLayout dialog;
	private SharedPreferences bindInfo;

	private Handler handler = new Handler()
	{

		public void handleMessage(android.os.Message msg)
		{

			switch (msg.what)
			{
				case OPEN_MAIN:
					switchViews(R.id.activity_main_tab, null, null);
					finish();
					break;

				case DOWN_OVER:
					allBg.setVisibility(View.GONE);
					dialog.setVisibility(View.GONE);
					Intent intent = new Intent(LoadingActivity.this, MainTabActivity.class);
					startActivity(intent);
					finish();
					LoadingActivity.this.overridePendingTransition(R.anim.enteralpha, R.anim.exitalpha);
					openFile(new File(folderPath + fileName));
					downFileSize = 0;

					break;
				case DOWN_START:

					Bundle b = msg.getData();
					int p = b.getInt("progress");
					int d = b.getInt("downloaded");
					int s = b.getInt("allsize");
					double f = d / (1024);
					double ff = s / (1024);
					txt_progress.setText(f + "/" + ff + " (KB)");
					progressBar.setProgress(p);

					break;
				case DOWN_STOP:
					if (tips_progress != null)
					{
						tips_progress.dismiss();
					}
					conn.disconnect();
					break;
			}
		};
	};

	@Override
	public void doInitFindView()
	{

		allBg = (ImageView) findViewById(R.id.a_pr_anim_wait_all_bg);
		allBg.setOnClickListener(null);
		dialog = (RelativeLayout) findViewById(R.id.a_pct_custom_dialog_all);
		progressBar = (ProgressBar) findViewById(R.id.utips_progress);
		txt_progress = (TextView) findViewById(R.id.utips_txt_progress);
		tipsPb = (ProgressBar) findViewById(R.id.progressBar_loading);
		ok = (Button) findViewById(R.id.a_pcustom_dialog_ok);
		cancel = (Button) findViewById(R.id.a_pcustom_dialog_cancel);
		txtTips = (TextView) findViewById(R.id.a_pcustom_dialog_txt);
	}

	@Override
	public void doInit(Bundle bundle)
	{
		LoadingController.setOnLoadingListener(this);
		SharedPreferences spW = getSharedPreferences("Dlna_data", 0);
		Editor ea = spW.edit();
		ea.putBoolean("hasPlayingOnTV", false);
		ea.commit();

		SharedPreferences sp = getSharedPreferences(CONFIGFILE, 0);
		String ip = sp.getString("serverIp", "");

		String username = sp.getString("username", Build.MODEL);
		String sessionId = sp.getString("sessionId", "");

		CtUserInfo.name = username;
		if (sessionId.equals(""))
		{
			CtUserInfo.sessionId = sessionId;
		}

		CtUserInfo.searchResultP = null;
		DLNAData.current().prevProgramID = 0;
		CtProgram.current().currentEpisode = 1;
		CtProgram.current().isGetDetailReturnProgram = false;

		if (ip.equals(""))
		{
			CtUserInfo.myServerAddress = NetUtil.BASE_URL + "JsonServlet.do";
		}
		else
		{
			CtUserInfo.myServerAddress = ip;
		}

		boolean sdExists = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

		if (sdExists)
		{
			int first = sp.getInt("firstRun", 0);
			String descPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + JSONMessageType.LOGO_SDCARD_FOLDER_SHORT;
			Editor e = sp.edit();
			switch (first)
			{
				case 0:
					CopyAssets("file:///android_asset/pic_demo.jpg", descPath);

					e.putInt("firstRun", 1);
					break;
				default:
					String picFileName = "pic_demo.jpg";
					if (!BitmapUtils.isFileExist(descPath, picFileName))
					{
						CopyAssets("file:///android_asset/pic_demo.jpg", descPath);
					}
					break;
			}

			e.commit();
		}
		else
		{}

		loadingTips = (TextView) findViewById(R.id.ct_loading_tips);

		startFastRegist();
		DLNAData.current().hasSetTransportURI = false;

		if (!isSystemUpdateServiceRunning(this, getPackageName(this) + ".services.LocateMySelfService"))
		{
			startLoactionService();
		}
	}

	@Override
	protected int getContentView()
	{

		return R.layout.activity_loading;
	}

	private void showUnBindAlert(String txt, int type)
	{

		progressBar.setVisibility(View.VISIBLE);
		txt_progress.setVisibility(View.VISIBLE);
		switch (type)
		{
			case 0:
				progressBar.setVisibility(View.GONE);
				txt_progress.setVisibility(View.GONE);
				ok.setVisibility(View.VISIBLE);
				ok.setText("立即升级");
				ok.setOnClickListener(new android.view.View.OnClickListener()
				{

					@Override
					public void onClick(View v)
					{

						if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
						{
							folderPath = Environment.getExternalStorageDirectory() + File.separator + "iTV+" + File.separator;

							tDownload = new Thread(new Runnable()
							{

								@Override
								public void run()
								{

									downLoadFile(UpdateData.current().appDownURL);

								}
							});
							tDownload.start();
							showTipsProgress();

						}
						else
						{
							Toast.makeText(LoadingActivity.this, "SD卡不存在，无法更新程序，请插入SD卡后重试", Toast.LENGTH_LONG).show();
						}
					}
				});

				cancel.setOnClickListener(new android.view.View.OnClickListener()
				{

					@Override
					public void onClick(View v)
					{

						allBg.setVisibility(View.GONE);
						dialog.setVisibility(View.GONE);
						if (UpdateData.current().mustUpdate == 0)
						{
							Intent intent = new Intent(LoadingActivity.this, MainTabActivity.class);
							startActivity(intent);
							LoadingActivity.this.overridePendingTransition(R.anim.enteralpha, R.anim.exitalpha);
						}
						finish();
					}
				});
				break;
			case 1:
				ok.setVisibility(View.GONE);
				progressBar.setVisibility(View.VISIBLE);
				txt_progress.setVisibility(View.VISIBLE);

				cancel.setOnClickListener(new android.view.View.OnClickListener()
				{

					@Override
					public void onClick(View v)
					{

						allBg.setVisibility(View.GONE);
						dialog.setVisibility(View.GONE);

						conn.disconnect();
						conn = null;
						downFileSize = 0;

						tDownload.interrupt();
						tDownload = null;

						if (is != null)
						{
							try
							{
								is.close();
								is = null;
							}
							catch (IOException e)
							{
								e.printStackTrace();
							}
						}
						if (fos != null)
						{
							try
							{
								fos.close();
								fos = null;
							}
							catch (IOException e)
							{
								e.printStackTrace();
							}
						}
						if (UpdateData.current().mustUpdate == 0)
						{
							Intent intent = new Intent(LoadingActivity.this, MainTabActivity.class);
							startActivity(intent);
							LoadingActivity.this.overridePendingTransition(R.anim.enteralpha, R.anim.exitalpha);
						}
						else
							finish();
					}
				});
				break;
		}
		txtTips.setText(txt);
		allBg.setVisibility(View.VISIBLE);
		dialog.setVisibility(View.VISIBLE);
	}

	private void saveBindPreference(String info)
	{

		bindInfo = getSharedPreferences("BINDINFO", Context.MODE_PRIVATE);
		Editor editor = bindInfo.edit();
		editor.putString("stbId", info);
		if (info.equals(""))
		{
			editor.putBoolean("hasBinded", false);
		}
		else
		{
			editor.putBoolean("hasBinded", true);
		}
		editor.commit();
	}

	private void openCommunicationPage()
	{

		new Thread(new Runnable()
		{

			public void run()
			{

				try
				{
					Thread.sleep(DELAY);

					finish();
					LoadingActivity.this.overridePendingTransition(R.anim.enteralpha, R.anim.exitalpha);

				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}).start();

	}

	private void detectNewVersion()
	{

		if (AppUtil.isServiceRunning(this, AppUtil.getPackageName(this) + ".dlna.services.AppUpdateService"))
		{
			Toast.makeText(getApplicationContext(), "正在下载新版本", Toast.LENGTH_SHORT).show();
			openMainPage();
		}
		else
		{
			LoadingController.checkVersion();
		}
	}

	private void openMainPage()
	{
		Message msg = handler.obtainMessage();
		msg.what = OPEN_MAIN;
		handler.sendMessageDelayed(msg, DELAY);
	}

	private void startFastRegist()
	{

		SharedPreferences sp = getSharedPreferences(CONFIGFILE, 0);

		sessionId = "";
		int id = sp.getInt("userId", 0);
		CtUserInfo.sessionId = sessionId;
		CtUserInfo.id = id;
		CtUserInfo.equipName = sp.getString("equipName", "");
		CtUserInfo.mac = sp.getString("Mac", "");
		if (sessionId.equals(""))
		{
			LoadingController.fastRegist();
		}
		else
		{
			openCommunicationPage();
		}
	}

	private void CopyAssets(String assetDir, String dir)
	{

		String[] files;
		try
		{
			files = this.getResources().getAssets().list(assetDir);
		}
		catch (IOException e1)
		{
			return;
		}
		File mWorkingPath = new File(dir);
		if (!mWorkingPath.exists())
		{
			if (!mWorkingPath.mkdirs())
			{

			}
		}

		for (int i = 0; i < files.length; i++)
		{
			try
			{
				String fileName = files[i];
				if (!fileName.contains("."))
				{
					if (0 == assetDir.length())
					{
						CopyAssets(fileName, dir + fileName + "/");
					}
					else
					{
						CopyAssets(assetDir + "/" + fileName, dir + fileName + "/");
					}
					continue;
				}
				File outFile = new File(mWorkingPath, fileName);
				if (outFile.exists())
					outFile.delete();
				InputStream in = null;
				if (0 != assetDir.length())
					in = getAssets().open(assetDir + "/" + fileName);
				else
					in = getAssets().open(fileName);
				OutputStream out = new FileOutputStream(outFile);

				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0)
				{
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void showNewVersionDialog(Context context, String title, String msg)
	{

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton("现在更新", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{

				starAppDownloadService();
				Toast.makeText(getApplicationContext(), "新版本已经开始下载，您可在通知栏观看下载进度", Toast.LENGTH_SHORT).show();
				if (UpdateData.current().mustUpdate == 0)
				{
					openMainPage();// 打开主页
				}
				finish();
			}

		});
		builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{

				dialog.dismiss();
				if (UpdateData.current().mustUpdate == 0)
				{
					openMainPage();// 打开主页
				}
				finish();
			}
		});
		builder.setCancelable(false).create().show();
	}

	public void starAppDownloadService()
	{

		Intent intent = new Intent(this, com.sumavision.itv.service.AppUpdateService.class);
		intent.putExtra("url", UpdateData.current().appDownURL);
		startService(intent);
	}

	protected File downLoadFile(String httpUrl)
	{

		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist)
		{
			folderPath = Environment.getExternalStorageDirectory() + File.separator + "iTV+/update" + File.separator;

		}
		else
		{
			tDownload.interrupt();
			Toast.makeText(LoadingActivity.this, "SD卡不存在，无法更新程序，请插入SD卡后重试", Toast.LENGTH_SHORT).show();

			return null;
		}

		int progress = 0;

		File tmpFile = new File(folderPath);
		if (!tmpFile.exists())
		{
			tmpFile.mkdir();
		}
		final File file = new File(folderPath + fileName);

		try
		{
			URL url = new URL(httpUrl);
			try
			{
				conn = (HttpURLConnection) url.openConnection();
				fileSize = conn.getContentLength();
				progressBar.setMax(fileSize);
				is = conn.getInputStream();
				fos = new FileOutputStream(file);
				byte[] buf = new byte[1024];
				conn.setReadTimeout(5000);
				conn.setConnectTimeout(5000);
				conn.connect();
				double count = 0;
				int tempProgress = -1;

				if (conn.getResponseCode() >= 400)
				{
					Toast.makeText(LoadingActivity.this, "连接超时", Toast.LENGTH_SHORT).show();

				}
				else
				{
					while (count <= 100)
					{
						if (is != null)
						{
							if (!isCancelDownload)
							{
								int numRead = is.read(buf);
								if (numRead <= 0)
								{
									break;
								}
								else
								{

									downFileSize = downFileSize + numRead;
									progress = downFileSize;

									if (fos != null)
									{
										fos.write(buf, 0, numRead);
									}

									synchronized (this)
									{
										if (downFileSize == fileSize)
										{

											Message msg = new Message();
											msg.what = DOWN_OVER;
											handler.sendMessage(msg);

										}
										else if (tempProgress != progress)
										{
											Message msg = new Message();
											msg.what = DOWN_START;

											Bundle b = new Bundle();
											b.putInt("progress", progress);
											b.putInt("downloaded", downFileSize);
											b.putInt("allsize", fileSize);
											msg.setData(b);
											handler.sendMessage(msg);

											tempProgress = progress;
										}
									}

								}
							}

						}
						else
						{
							break;
						}

					}
				}
				conn.disconnect();
				fos.close();
				is.close();

			}
			catch (Exception e)
			{

				e.printStackTrace();
			}
		}
		catch (MalformedURLException e)
		{

			e.printStackTrace();
		}
		return file;
	}

	private void openFile(File file)
	{

		SharedPreferences sp = getSharedPreferences("otherInfo", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
		intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		startActivityForResult(intent, REQUESTCODE_UPDATE);
	}

	private void showTipsProgress()
	{

		String txt = "新版本下载中，下载完成将自动安装，请稍后...";
		showUnBindAlert(txt, 1);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{

		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if (dialog.isShown())
			{
				return true;
			}
			else
			{
				return super.onKeyDown(keyCode, event);
			}
		}
		else
		{
			return super.onKeyDown(keyCode, event);
		}
	}

	public static boolean isSystemUpdateServiceRunning(Context context, String serviceName)
	{

		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> serviceInfo = activityManager.getRunningServices(100);
		for (RunningServiceInfo info : serviceInfo)
		{
			String name = info.service.getClassName();
			if (name.equals(serviceName))
			{
				return true;
			}
		}
		return false;

	}

	public static String getPackageName(Context context)
	{

		PackageManager packageManager = context.getPackageManager();
		PackageInfo packInfo;
		try
		{
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packInfo.packageName;
		}
		catch (NameNotFoundException e)
		{
			return null;
		}
	}

	private void startLoactionService()
	{

		Intent intent = new Intent(this, LocateMySelfService.class);
		startService(intent);
	}

	@Override
	public void eventClientRegist(boolean isOk)
	{

		if (isOk)
		{
			OtherCacheData.current().dontHasNet = false;
			loadingTips.setText("检测新版本...");
			if (CtUserInfo.stbId == null)
			{
				saveBindPreference("");
			}
			else
			{
				saveBindPreference(CtUserInfo.stbId);
			}
			detectNewVersion();
		}
		else
		{
			loadingTips.setText("注册客户端...");
			LoadingController.checkVersion();
		}

	}

	@Override
	public void eventCheckVersion(boolean isOk)
	{

		if (isOk)
		{
			OtherCacheData.current().dontHasNet = false;
			if (UpdateData.current().isNeedUpdateApp)
			{
				tipsPb.setVisibility(View.GONE);
				loadingTips.setText("发现新版本");
				String txt = "发现新版本" + " 米花TV，版本号v" + UpdateData.current().versionCodeServer + "\n" + "更新细节：" + "\n" + UpdateData.current().updateDetail + "\n" + "是否现在进行更新?";
				showNewVersionDialog(LoadingActivity.this, "itv新版本", txt);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "已是最新版!", Toast.LENGTH_SHORT).show();
				openMainPage();
			}
		}
		else
		{
			OtherCacheData.current().dontHasNet = true;
			loadingTips.setText("检测新版本...");
			try
			{
				Thread.sleep(3000);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				Toast.makeText(LoadingActivity.this, "网络初始化出错，请检查您的网络后重新打开程序...", Toast.LENGTH_LONG).show();
				finish();
			}
		}

	}

	@Override
	public void eventFastRegist(boolean isOk)
	{

		if (isOk)
		{
			OtherCacheData.current().dontHasNet = false;
			loadingTips.setText("注册客户端...");
			LoadingController.clientRegist();
		}
		else
		{
			LoadingController.clientRegist();
			loadingTips.setText("初始化失败...");
		}
	}

	@Override
	protected void onPause()
	{

		LoadingController.setOnLoadingListener(null);
		super.onPause();
	}

}
