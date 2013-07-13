package com.sumavision.itv.engine;

import com.sumavision.itv.application.Launcher;
import com.sumavision.itv.net.AllLiveProgramParser;
import com.sumavision.itv.net.ChannelProgramParser;
import com.sumavision.itv.net.CtAddFavoriteParser;
import com.sumavision.itv.net.CtBindParser;
import com.sumavision.itv.net.CtCheckVersionParser;
import com.sumavision.itv.net.CtClientRegistParser;
import com.sumavision.itv.net.CtFastRegisterParser;
import com.sumavision.itv.net.CtTopicCommentListParser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ParserTask extends AsyncTask<Object, Void, Integer>
{
	private static Context context = Launcher.baseContext;
	private int eventId;

	public ParserTask(int eventId)
	{

		this.eventId = eventId;
	}

	@Override
	protected void onPreExecute()
	{

	}

	@Override
	protected Integer doInBackground(Object... params)
	{

		String result = (String) params[0];
		try
		{
			switch (eventId)
			{
				case ItvEngine.EVENT_BIND_BY_CODE:
					CtBindParser paser = new CtBindParser();
					return paser.parse(result);

//				case ItvEngine.EVENT_DLNA_ACTION_GETMUTE:
//
//					GetMutParser.parse(new ByteArrayInputStream(((String) msg.obj).getBytes()));
//					DlnaCotroller.eventDlnaGetMute(msg.arg1, "");
//					break;
//
//				case ItvEngine.EVENT_DLNA_ACTION_GETPOSITIONINFO:
//					GetPositonInfoParser.parse(new ByteArrayInputStream(((String) msg.obj).getBytes()));
//					// 后期改为返回最终数据
//					DlnaCotroller.eventDlnaGetPositionInfo(msg.arg1, "");
//					break;
//
//				case ItvEngine.EVENT_DLNA_ACTION_GETPRODUCTINFO:
//					GetProductInfoParser.parseNew(new ByteArrayInputStream(((String) msg.obj).getBytes()));
//					DlnaCotroller.eventDlnaGetProductInfo(msg.arg1, "");
//					break;
//				case ItvEngine.EVENT_DLNA_ACTION_GETTRANSPORINFO:
//					GetTransportInfoParser.parse(new ByteArrayInputStream(((String) msg.obj).getBytes()));
//					DlnaCotroller.eventDlnaGetTransportInfo(msg.arg1, "");
//					break;
//				case ItvEngine.EVENT_DLNA_ACTION_GETVOLUME:
//					GetVolumeParser.parse(new ByteArrayInputStream(((String) msg.obj).getBytes()));
//					DlnaCotroller.eventDlnaGetVolume(msg.arg1, "");
//					break;

				case ItvEngine.EVENT_ADD_FAVORITE:
					CtAddFavoriteParser favoriteParser = new CtAddFavoriteParser();
					return favoriteParser.parse(result);
				case ItvEngine.EVENT_GET_TOPIC_COMMENT:
					CtTopicCommentListParser commentParser = new CtTopicCommentListParser();
					return commentParser.parse(result);
				case ItvEngine.EVENT_CLIENT_REGIST:
					CtClientRegistParser clientRegistParser = new CtClientRegistParser();
					return clientRegistParser.parse(result);
				case ItvEngine.EVENT_CHECK_VERSION:
					CtCheckVersionParser checkVersionParser = new CtCheckVersionParser();
					return checkVersionParser.parse(result);
				case ItvEngine.EVENT_FAST_REGIST:
					CtFastRegisterParser fastRegisterParser = new CtFastRegisterParser(context);
					return fastRegisterParser.parse(result);
					
				case ItvEngine.EVENT_ALL_LIVE_PROGRAM:
					AllLiveProgramParser liveProgramParser = new AllLiveProgramParser();
					return liveProgramParser.parse(result);
					
				case ItvEngine.EVENT_GET_CHANNEL_PROGRAM:
					ChannelProgramParser channelProgramParser = new ChannelProgramParser();
					return channelProgramParser.parse(result);
					
				default:
					return ItvEngine.EVENT_ERROR;
			}
		}
		catch (Exception e)
		{
			return ItvEngine.EVENT_ERROR;
		}
	}

	@Override
	protected void onCancelled()
	{

	}

	@Override
	protected void onPostExecute(Integer result)
	{
		Log.e("ParserTask", "eventId: " + eventId);
		ItvEngine.sendMessage(eventId, result, null);
	}
}
