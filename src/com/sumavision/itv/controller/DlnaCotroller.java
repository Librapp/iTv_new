package com.sumavision.itv.controller;



import com.sumavision.itv.engine.ItvEngine;
import com.sumavision.itv.listener.OnDlnaEventListener;

public class DlnaCotroller
{
	private static OnDlnaEventListener dlnaEventListener;
	
	public static void setDLNAEventListener(OnDlnaEventListener listener)
	{
		dlnaEventListener = listener;
	}
	
	public static void setTransportUrl(String currentUri)
	{
		ItvEngine.serviceDlnaSetUrl(currentUri);
	}
	
	public static void play(int speed)
	{
		ItvEngine.serviceDlnaPlay(speed);
	}
	
	public static void pause()
	{
		ItvEngine.serviceDlnaPause();
	}

	public static void stop()
	{
		ItvEngine.serviceDlnaStop();
	}
	
	public static void setMute(int mute)
	{
		ItvEngine.serviceDlnaSetMute(mute);
	}
	
	public static void setVolmue(int vol)
	{
		ItvEngine.serviceDlnaSetVolmue(vol);
	}
	
	public static void seek(String seekTime)
	{
		ItvEngine.serviceDlnaSeek(seekTime);
	}
	
	public static void order(int contentType)
	{
		ItvEngine.serviceDlnaOrder(contentType);
	}
	
	public static void getMute()
	{
		ItvEngine.serviceDlnaGetMute();
	}
	
	public static void getPositionInfo()
	{
		ItvEngine.serviceDlnaGetPositionInfo();
	}
	
	public static void getProductInfo(String currentUri)
	{
		ItvEngine.serviceDlnaGetProductInfo(currentUri);
	}
	
	public static void getTransportInfo()
	{
		ItvEngine.serviceDlnaGetTransportInfo();
	}
	
	public static void getVolume()
	{
		ItvEngine.serviceDlnaGetVolume();
	}
	
	
	public static void eventDlnaSetUrl(int errCode, String result)
	{
		
		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaSetTransportUrl(isOk, result);
	}
	
	public static void eventDlnaPlay(int errCode, String result)
	{
		
		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaPlay(isOk, result);
	}

	public static void eventDlnaPause(int errCode, String result)
	{
		
		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaPause(isOk, result);
	}

	public static void eventDlnaStop(int errCode, String result)
	{
		
		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaStop(isOk, result);
	}

	public static void eventDlnaSetMute(int errCode, String result)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaSetMute(isOk, result);
	}
	
	public static void eventDlnaSetVolmue(int errCode, String result)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaSetVolmue(isOk, result);
	}
	
	public static void eventDlnaSeek(int errCode, String result)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaSeek(isOk, result);
	}
	
	public static void eventDlnaOrder(int errCode, String result)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaOrder(isOk, result);
	}
	
	public static void eventDlnaGetMute(int errCode, String result)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaGetMute(isOk, result);
	}
	
	public static void eventDlnaGetPositionInfo(int errCode, String result)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaGetPositionInfo(isOk, result);
	}
	
	public static void eventDlnaGetProductInfo(int errCode, String result)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaGetProductInfo(isOk, result);
	}
	
	public static void eventDlnaGetTransportInfo(int errCode, String result)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaGetTransportInfo(isOk, result);
	}
	
	public static void eventDlnaGetVolume(int errCode, String result)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (dlnaEventListener != null)
			dlnaEventListener.eventDlnaGetVolume(isOk, result);
	}
	
}
