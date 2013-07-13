package com.sumavision.itv.engine;

import com.sumavision.itv.controller.BindByCodeController;
import com.sumavision.itv.controller.ChannelDetailController;
import com.sumavision.itv.controller.DlnaCotroller;
import com.sumavision.itv.controller.LoadingController;
import com.sumavision.itv.controller.MainTvController;
import com.sumavision.itv.controller.MovieToTvController;
import com.sumavision.itv.dlna.GetMutParser;
import com.sumavision.itv.dlna.GetPositonInfoParser;
import com.sumavision.itv.dlna.GetProductInfoParser;
import com.sumavision.itv.dlna.GetTransportInfoParser;
import com.sumavision.itv.dlna.GetVolumeParser;
import com.sumavision.itv.net.AllLiveProgramRequest;
import com.sumavision.itv.net.ChannelProgramRequest;
import com.sumavision.itv.net.CtAddFavoriteRequest;
import com.sumavision.itv.net.CtBindRequest;
import com.sumavision.itv.net.CtCheckVersionRequest;
import com.sumavision.itv.net.CtClientRegistRequest;
import com.sumavision.itv.net.CtFastRegisterRequest;
import com.sumavision.itv.net.CtTopicCommentListRequest;

import java.io.ByteArrayInputStream;

import com.sumavision.itv.application.Launcher;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 
 * @author yanzhidan
 * @createTime 2013-4-15
 * 
 */
public class ItvEngine
{

	private static Context context = Launcher.baseContext;
	public static final int EVENT_ERROR = -1;
	public static final int EVENT_OK = 0;

	public static final int EVENT_BASE = 0;
	public static final int EVENT_BIND_BY_CODE = EVENT_BASE + 1;// code绑定机顶盒
	public static final int EVENT_DLNA_ACTION_PLAY = EVENT_BASE + 2;// dlna播放
	public static final int EVENT_DLNA_ACTION_PAUSE = EVENT_BASE + 3;// dlna暂停
	public static final int EVENT_DLNA_ACTION_STOP = EVENT_BASE + 4;// dlna停止
	public static final int EVENT_DLNA_ACTION_SETTRANSPORTURL = EVENT_BASE + 5;// setTransportUrl
	public static final int EVENT_DLNA_ACTION_SETMUTE = EVENT_BASE + 6;// 设置静音
	public static final int EVENT_DLNA_ACTION_SETVOLMUE = EVENT_BASE + 7;// 设置音量
	public static final int EVENT_DLNA_ACTION_SEEK = EVENT_BASE + 8;// 播放进度
	public static final int EVENT_DLNA_ACTION_ORDER = EVENT_BASE + 9;// 订购信息
	public static final int EVENT_DLNA_ACTION_GETMUTE = EVENT_BASE + 10;// 静音状态
	public static final int EVENT_DLNA_ACTION_GETPOSITIONINFO = EVENT_BASE + 11;// 播放位置
	public static final int EVENT_DLNA_ACTION_GETTRANSPORINFO = EVENT_BASE + 12;// getTransportUrl
	public static final int EVENT_DLNA_ACTION_GETVOLUME = EVENT_BASE + 13;// getVolume
	public static final int EVENT_DLNA_ACTION_GETPRODUCTINFO = EVENT_BASE + 14;// getProductInfo

	public static final int EVENT_GET_TOPIC_COMMENT = EVENT_BASE + 15;// 获取评论内容
	public static final int EVENT_ADD_FAVORITE = EVENT_BASE + 16;// 添加收藏
	public static final int EVENT_CLIENT_REGIST = EVENT_BASE + 17;// 注册
	public static final int EVENT_CHECK_VERSION = EVENT_BASE + 18; // 检查版本
	public static final int EVENT_FAST_REGIST = EVENT_BASE + 19; // 快速注册
	public static final int EVENT_ALL_LIVE_PROGRAM = EVENT_BASE + 20; // 获取电视页频道数据
	public static final int EVENT_GET_CHANNEL_PROGRAM = EVENT_BASE + 21; // 48小时节目单主数据

	public static void serviceBindByCode()
	{

		new RequestTask(EVENT_BIND_BY_CODE).execute(context, new CtBindRequest());
	}

	public static void serviceDlnaSetUrl(String currentUri)
	{

		DlnaManager.dlnaSetTransportUrl(context, currentUri);
	}

	public static void serviceDlnaPlay(int speed)
	{

		DlnaManager.dlnaPlay(context, speed);
	}

	public static void serviceDlnaPause()
	{

		DlnaManager.dlnaPause(context);
	}

	public static void serviceDlnaStop()
	{

		DlnaManager.dlnaStop(context);
	}

	public static void serviceDlnaSetMute(int mute)
	{

		DlnaManager.dlnaSetMute(context, mute);
	}

	public static void serviceDlnaSetVolmue(int vol)
	{

		DlnaManager.dlnaSetVolmue(context, vol);
	}

	public static void serviceDlnaSeek(String seekTime)
	{

		DlnaManager.dlnaSeek(context, seekTime);
	}

	public static void serviceDlnaOrder(int contentType)
	{

		DlnaManager.dlnaOrder(context, contentType);
	}

	public static void serviceDlnaGetMute()
	{

		DlnaManager.dlnaGetMute();
	}

	public static void serviceDlnaGetPositionInfo()
	{

		DlnaManager.dlnaGetPositionInfo();
	}

	public static void serviceDlnaGetProductInfo(String currentUri)
	{

		DlnaManager.dlnaGetProductInfo(context, currentUri);
	}

	public static void serviceDlnaGetTransportInfo()
	{

		DlnaManager.dlnaGetTranSportInfo();
	}

	public static void serviceDlnaGetVolume()
	{

		DlnaManager.dlnaGetVolume();
	}

	public static void serviceAddFavorite(boolean bool)
	{

		new RequestTask(EVENT_ADD_FAVORITE).execute(context, new CtAddFavoriteRequest(bool));
	}

	public static void serviceGetTopicComment(boolean bool)
	{

		new RequestTask(EVENT_GET_TOPIC_COMMENT).execute(context, new CtTopicCommentListRequest(bool));
	}

	public static void serviceClientRegist()
	{

		new RequestTask(EVENT_CLIENT_REGIST).execute(context, new CtClientRegistRequest(context));
	}

	public static void serviceCheckVersion()
	{

		new RequestTask(EVENT_CHECK_VERSION).execute(context, new CtCheckVersionRequest());
	}

	public static void serviceFastRegist()
	{

		new RequestTask(EVENT_FAST_REGIST).execute(context, new CtFastRegisterRequest(context));
	}

	public static void serviceAllLiveProgramGet()
	{

		new RequestTask(EVENT_ALL_LIVE_PROGRAM).execute(context, new AllLiveProgramRequest());
	}

	public static void serviceChannelProgramGet(int id)
	{

		new RequestTask(EVENT_GET_CHANNEL_PROGRAM).execute(context, new ChannelProgramRequest(id));
	}

	public static void sendMessage(int eventId, int arg, Object obj)
	{

		Message message = new Message();
		message.what = eventId;
		message.arg1 = arg;
		message.obj = obj;
		handler.sendMessage(message);
	}

	private static Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{

			try
			{
				switch (msg.what)
				{
					case EVENT_BIND_BY_CODE:
						BindByCodeController.eventBindByCode(msg.arg1);
						break;
					case EVENT_DLNA_ACTION_SETTRANSPORTURL:
						DlnaCotroller.eventDlnaSetUrl(msg.arg1, (String) msg.obj);
						break;

					case EVENT_DLNA_ACTION_PLAY:
						DlnaCotroller.eventDlnaPlay(msg.arg1, (String) msg.obj);
						break;

					case EVENT_DLNA_ACTION_PAUSE:
						DlnaCotroller.eventDlnaPause(msg.arg1, (String) msg.obj);
						break;

					case EVENT_DLNA_ACTION_STOP:
						DlnaCotroller.eventDlnaStop(msg.arg1, (String) msg.obj);
						break;

					case EVENT_DLNA_ACTION_SETMUTE:
						DlnaCotroller.eventDlnaSetMute(msg.arg1, (String) msg.obj);
						break;

					case EVENT_DLNA_ACTION_SETVOLMUE:
						DlnaCotroller.eventDlnaSetVolmue(msg.arg1, (String) msg.obj);
						break;

					case EVENT_DLNA_ACTION_SEEK:
						DlnaCotroller.eventDlnaSeek(msg.arg1, (String) msg.obj);
						break;

					case EVENT_DLNA_ACTION_ORDER:
						DlnaCotroller.eventDlnaOrder(msg.arg1, (String) msg.obj);
						break;

					case EVENT_DLNA_ACTION_GETMUTE:

						GetMutParser.parse(new ByteArrayInputStream(((String) msg.obj).getBytes()));
						DlnaCotroller.eventDlnaGetMute(msg.arg1, "");
						break;

					case EVENT_DLNA_ACTION_GETPOSITIONINFO:
						GetPositonInfoParser.parse(new ByteArrayInputStream(((String) msg.obj).getBytes()));
						// 后期改为返回最终数据
						DlnaCotroller.eventDlnaGetPositionInfo(msg.arg1, "");
						break;

					case EVENT_DLNA_ACTION_GETPRODUCTINFO:
						GetProductInfoParser.parseNew(new ByteArrayInputStream(((String) msg.obj).getBytes()));
						DlnaCotroller.eventDlnaGetProductInfo(msg.arg1, "");
						break;
					case EVENT_DLNA_ACTION_GETTRANSPORINFO:
						GetTransportInfoParser.parse(new ByteArrayInputStream(((String) msg.obj).getBytes()));
						DlnaCotroller.eventDlnaGetTransportInfo(msg.arg1, "");
						break;
					case EVENT_DLNA_ACTION_GETVOLUME:
						GetVolumeParser.parse(new ByteArrayInputStream(((String) msg.obj).getBytes()));
						DlnaCotroller.eventDlnaGetVolume(msg.arg1, "");
						break;

					case EVENT_ADD_FAVORITE:
						MovieToTvController.onMovieToTvEvent(msg.what, msg.arg1);
						break;

					case EVENT_GET_TOPIC_COMMENT:
						MovieToTvController.onMovieToTvEvent(msg.what, msg.arg1);
						break;

					case EVENT_CLIENT_REGIST:
						LoadingController.onLoadingEvent(msg.what, msg.arg1);
						break;
					case EVENT_CHECK_VERSION:
						LoadingController.onLoadingEvent(msg.what, msg.arg1);
						break;
					case EVENT_FAST_REGIST:
						LoadingController.onLoadingEvent(msg.what, msg.arg1);
						break;

					case EVENT_ALL_LIVE_PROGRAM:
						MainTvController.eventAllLiveProgramGet(msg.arg1);
						break;

					case EVENT_GET_CHANNEL_PROGRAM:
						ChannelDetailController.eventGetChannelProgram(msg.arg1);
						break;
					default:
						break;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Log.e("engine", "errorCode:  " + msg.what + " ****** " + e.getMessage());
			}
		}

	};

}
