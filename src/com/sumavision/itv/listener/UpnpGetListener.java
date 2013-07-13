package com.sumavision.itv.listener;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.cybergarage.http.HTTPNetListener;

import com.sumavision.itv.engine.ItvEngine;



import android.util.Log;

public class UpnpGetListener implements HTTPNetListener
{
	private int action_id = -1;
	public UpnpGetListener(int actionId)
	{
		this.action_id = actionId;
	}

	@Override
	public void onDLNAGetBegin()
	{

	}

	@Override
	public void onDLNAGetError(String errMsg)
	{
		ItvEngine.sendMessage(action_id, ItvEngine.EVENT_ERROR, errMsg);
	}

	@Override
	public void onDLNAGetResponse(StatusLine statusLine, Header[] header, HttpEntity entity)
	{
		try
		{
			Log.e("View", action_id + "  --- " +  EntityUtils.toString(entity));
			ItvEngine.sendMessage(action_id, ItvEngine.EVENT_OK, EntityUtils.toString(entity));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ItvEngine.sendMessage(action_id, ItvEngine.EVENT_ERROR, "entity to string failed");
		}
	}

}
