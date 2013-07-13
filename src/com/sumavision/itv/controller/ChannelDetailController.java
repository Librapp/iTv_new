package com.sumavision.itv.controller;

import com.sumavision.itv.engine.ItvEngine;
import com.sumavision.itv.listener.OnChannelDetailListener;


public class ChannelDetailController
{
	private static OnChannelDetailListener detailListener;
	public static void setOnChannelDetailListener(OnChannelDetailListener listener)
	{
		detailListener = listener;
	}
	
	public static void getChannleProgram(int channelId)
	{
		ItvEngine.serviceChannelProgramGet(channelId);
	}
	
	public static void eventGetChannelProgram(int errCode)
	{
		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if(detailListener != null)
			detailListener.eventGetChannelProgram(isOk);
	}

}
