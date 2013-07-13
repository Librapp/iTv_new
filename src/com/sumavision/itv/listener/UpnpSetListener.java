package com.sumavision.itv.listener;

import org.cybergarage.upnp.CtUpnpListener;

import com.sumavision.itv.engine.ItvEngine;

public class UpnpSetListener implements CtUpnpListener
{

	private int action_id = -1;
	public UpnpSetListener(int actionId)
	{

		this.action_id = actionId;
	}

	@Override
	public void getUpnpDevice()
	{

	}

	@Override
	public void onUpnpError(String errMsg)
	{
		ItvEngine.sendMessage(action_id, ItvEngine.EVENT_ERROR, errMsg);
	}

	@Override
	public void onUpnpResponse()
	{
		ItvEngine.sendMessage(action_id, ItvEngine.EVENT_OK, "");
	}

}
