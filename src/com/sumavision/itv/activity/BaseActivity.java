package com.sumavision.itv.activity;

import com.sumavision.itv.R;
import com.sumavision.itv.data.C;
import com.sumavision.itv.data.main.iChannel;
import com.sumavision.itv.data.main.iProgram;
import com.sumavision.itv.listener.OnItemBodyThrowListener.onItemThrowedListener;
import com.sumavision.itv.utils.ImageLoaderHelper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 
 * @author yanzhidan
 * 
 * @createTime 2013-04-08
 * 
 * @description 基类
 * 
 */
public abstract class BaseActivity extends Activity implements onItemThrowedListener
{

	public abstract void doInitFindView();

	public abstract void doInit(Bundle bundle);

	protected abstract int getContentView();

	protected static Dialog dialog;
	public ImageLoaderHelper imageLoaderHelper;
	public LayoutInflater inflater;
	public static boolean DEBUG_MODE = false;

	private String TAG;
	public final String i = "log_i";
	public final String e = "log_e";
	public final String d = "log_d";

	public Display display;
	public RelativeLayout progress;
	public final int SHOW_WAIT_DIALOG = 1;
	public final int HIDE_WAIT_DIALOG = 2;
	public Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{

			switch (msg.what)
			{
				case SHOW_WAIT_DIALOG:
				{
					progress.setVisibility(View.VISIBLE);
				}
					break;
				case HIDE_WAIT_DIALOG:
				{
					progress.setVisibility(View.GONE);
				}
					break;
				default:
					break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		DEBUG_MODE = (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		if (getContentView() != -1)
		{
			setContentView(getContentView());
		}
		TAG = getClass().getName().replace(getPackageName() + ".activity.", " ");
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		display = getWindowManager().getDefaultDisplay();
		imageLoaderHelper = new ImageLoaderHelper();
		new Task(savedInstanceState).execute();
	}

	private class Task extends AsyncTask<Void, Void, String[]>
	{

		Bundle bundle;

		public Task(Bundle savedInstanceState)
		{

			this.bundle = savedInstanceState;
		}

		protected String[] doInBackground(Void... params)
		{

			try
			{
				doInitFindView();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String[] result)
		{

			super.onPostExecute(result);
			doInit(bundle);
		}
	}

	public void switchViews(int viewId, Object data, String[] strs)
	{

		Intent intent = new Intent();

		switch (viewId)
		{
			case R.id.activity_main_tab:
			{
				intent.setClass(this, MainTabActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
				break;
			}

			case R.id.activity_channel_detail:
			{

				if (data != null && data instanceof iChannel)
				{
					iChannel channel = (iChannel) data;
					intent.putExtra(C.str.channelId, channel.channelId);
					intent.putExtra(C.str.channelName, channel.channelName);
				}
				intent.setClass(this, ChannelDetailActivity.class);
				break;
			}
			
			case R.id.activity_program_detail:
			{
				if (data != null && data instanceof iProgram)
				{
					iProgram program = (iProgram) data;
					intent.putExtra(C.str.programId, program.liveProgramId);
					intent.putExtra(C.str.programName, program.programName);
				}
				intent.setClass(this, ProgramDetailActivity.class);
				break;
			}
			

			default:
				break;

		}

		startActivity(intent);
	}

	public void makeToast(String content)
	{

		Toast.makeText(getApplicationContext(), content, 0).show();
	}

	public void log(String content)
	{

		log(i, content);
	}

	public void log(int content)
	{

		log(i, "" + content);
	}

	public void log(String tag, int content)
	{

		log(tag, "" + content);
	}

	public void log(String tag, String content)
	{

		if (!DEBUG_MODE)
			return;

		if (tag.equals(i))
		{
			Log.i(TAG, content);
		}
		else if (tag.equals(e))
		{
			Log.e(TAG, content);
		}
		else if (tag.equals(d))
		{
			Log.d(TAG, content);
		}
	}

	public boolean isEmpty(String str)
	{

		if (str == null || (str != null) && str.trim().length() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Dialog createSmallProgressBar(final Context context, boolean canCancel)
	{

		View view = inflater.inflate(R.layout.alert_dialog_small, null);
		dialog = new Dialog(context, R.style.MyDialog);
		dialog.setContentView(view);
		dialog.setCancelable(canCancel);
		return dialog;
	}

	@Override
	protected Dialog onCreateDialog(int id)
	{

		switch (id)
		{
			case R.id.dialog_progress_small:
				return createSmallProgressBar(this, false);

			default:
				return null;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args)
	{
		return super.onCreateDialog(id, args);
	}
	
	@Override
	public void onItemThrowed(int selection)
	{
	
	}
}
