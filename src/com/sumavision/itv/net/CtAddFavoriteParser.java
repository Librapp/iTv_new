package com.sumavision.itv.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.CtProgram;
import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.JSONMessageType;
import com.sumavision.itv.engine.ItvEngine;

public class CtAddFavoriteParser extends JSONParser
{

	@Override
	public int parse(String s)
	{

		JSONObject jAData = null;
		int errCode = -1;
		jsonErrCode = -1;

		if (!s.equals("") && s != null)
		{
			try
			{
				jAData = new JSONObject(s);
				errCode = jAData.getInt("errorcode");

				if (errCode == JSONMessageType.SERVER_CODE_OK)
				{
					CtUserInfo.isTimeOut = false;
					jsonErrCode = ItvEngine.EVENT_OK;
					CtProgram.current().isFavorited = true;
					if (jAData.has("favCount"))
					{
						CtProgram.current().favCount += 1;
					}
				}
				else
				{
					jsonErrCode = ItvEngine.EVENT_ERROR;
					CtUserInfo.isTimeOut = true;
				}
			}
			catch (JSONException e)
			{
				CtUserInfo.isTimeOut = true;
				jsonErrCode = ItvEngine.EVENT_ERROR;
				e.printStackTrace();
			}
			return jsonErrCode;
		}
		return jsonErrCode;
	}
}
