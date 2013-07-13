package com.sumavision.itv.controller;

import com.sumavision.itv.engine.ItvEngine;
import com.sumavision.itv.listener.OnDlnaBindListener;

public class BindByCodeController
{

	private static OnDlnaBindListener dlnaEventListener;

	public static void setDlnaEventListener(OnDlnaBindListener listener)
	{

		dlnaEventListener = listener;
	}

	public static void bindByCode()
	{
		ItvEngine.serviceBindByCode();
	}

	public static void eventBindByCode(int errCode)
	{
		
		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
		{
			dlnaEventListener.onBindByCode(isOk);
		}
	}

}
