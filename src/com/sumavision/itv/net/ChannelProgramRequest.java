package com.sumavision.itv.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.utils.Lunar;

public class ChannelProgramRequest extends JSONRequest
{

	private int channelId;

	public ChannelProgramRequest(int channelId)
	{

		this.channelId = channelId;
	}

	@Override
	public String make()
	{

		JSONObject jsonObject = new JSONObject();
		try
		{
			jsonObject.put("method", "ChannelLiveProgramList");
			jsonObject.put("channelId", channelId);
			jsonObject.put("starttime", Lunar.getStartDate());
			jsonObject.put("endtime", Lunar.getEndDate());

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
