package com.sumavision.itv.controller;

import com.sumavision.itv.engine.ItvEngine;
import com.sumavision.itv.listener.OnMainTvEventListener;


public class MainTvController
{
	private static OnMainTvEventListener tvEventListener;
	
	public static void setOnMainTvEventListener(OnMainTvEventListener listener)
	{
		tvEventListener = listener;
	}
	
	public static void getAllLiveProgram()
	{
		ItvEngine.serviceAllLiveProgramGet();
	}
	
	public static void eventAllLiveProgramGet(int errCode)
	{
		
		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (tvEventListener != null)
			tvEventListener.eventAllLiveProgramGet(isOk);
	}

}
