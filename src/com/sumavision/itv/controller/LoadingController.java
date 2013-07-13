package com.sumavision.itv.controller;

import com.sumavision.itv.engine.ItvEngine;
import com.sumavision.itv.listener.OnLoadingListener;

public class LoadingController
{

	private static OnLoadingListener onLoadingListener;

	public static void setOnLoadingListener(OnLoadingListener linListener)
	{

		onLoadingListener = linListener;
	}

	public static void clientRegist()
	{

		ItvEngine.serviceClientRegist();
	}

	public static void checkVersion()
	{

		ItvEngine.serviceCheckVersion();
	}

	public static void fastRegist()
	{
		ItvEngine.serviceFastRegist();
	}

	public static void onLoadingEvent(int eventId, int errCode)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (onLoadingListener != null)
		{
			switch (eventId)
			{
				case ItvEngine.EVENT_CLIENT_REGIST:
					onLoadingListener.eventClientRegist(isOk);
					break;

				case ItvEngine.EVENT_CHECK_VERSION:
					onLoadingListener.eventCheckVersion(isOk);
					break;

				case ItvEngine.EVENT_FAST_REGIST:
					onLoadingListener.eventFastRegist(isOk);
					break;

				default:
					break;
			}
		}
	}
}
