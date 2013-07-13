package com.sumavision.itv.listener;


public interface OnDlnaEventListener
{
	//set
	public void eventDlnaSetTransportUrl(boolean isOk, String result);
	public void eventDlnaPlay(boolean isOk, String result);
	public void eventDlnaPause(boolean isOk, String result);
	public void eventDlnaStop(boolean isOk, String result);
	public void eventDlnaSetMute(boolean isOk, String result);
	public void eventDlnaSetVolmue(boolean isOk, String result);
	public void eventDlnaSeek(boolean isOk, String result);
	public void eventDlnaOrder(boolean isOk, String result);
	
	//get
	public void eventDlnaGetMute(boolean isOk, String result);
	public void eventDlnaGetPositionInfo(boolean isOk, String result);
	public void eventDlnaGetProductInfo(boolean isOk, String result);
	public void eventDlnaGetTransportInfo(boolean isOk, String result);
	public void eventDlnaGetVolume(boolean isOk, String result);

}
