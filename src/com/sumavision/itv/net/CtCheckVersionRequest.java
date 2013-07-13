package com.sumavision.itv.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.JSONMessageType;

public class CtCheckVersionRequest extends JSONRequest
{

	@Override
	public String make()
	{

		JSONObject holder = new JSONObject();
		try
		{

			holder.put("method", JSONMessageType.CHECKVERSION);
			holder.put("macType", 2);
			holder.put("versionNum", JSONMessageType.APP_VERSION);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		return holder.toString();
	}
}
