package com.sumavision.itv.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.JSONMessageType;
import com.sumavision.itv.engine.ItvEngine;


public class CtBindParser extends JSONParser
{

	@Override
	public int parse(String s)
	{

		JSONObject jAData = null;
		int errCode = -1;

		if (!s.equals("") && s != null)
		{
			try
			{
				jAData = new JSONObject(s);
				errCode = jAData.getInt("errorcode");
				if (errCode == JSONMessageType.SERVER_CODE_OK || errCode == 300)
				{
					CtUserInfo.isTimeOut = false;
					jsonErrCode = ItvEngine.EVENT_OK;
					JSONArray array = jAData.getJSONArray("stbIds");
					// 可以绑定多个机顶盒 目前绑定一个
					if (array.length() > 0)
					{
						String id = array.getString(0);
						CtUserInfo.stbId = id;
					}
					// 机顶盒类型列表
					if (jAData.has("stbTypes"))
					{
						JSONArray arrayType = jAData.getJSONArray("stbTypes");
						// 可以绑定多个机顶盒 目前绑定一个
						if (arrayType.length() > 0)
						{
							int id = arrayType.getInt(0);
							CtUserInfo.STBType = id;
						}
					}
					// 机顶盒UA，用于友盟统计
					if (jAData.has("stbUas"))
					{
						JSONArray arrayType = jAData.getJSONArray("stbUas");
						// 可以绑定多个机顶盒 目前绑定一个
						if (arrayType.length() > 0)
						{
							String ua = arrayType.getString(0);
							CtUserInfo.STBUserAgent = ua;
						}
					}

				}
				else
				{
					CtUserInfo.isTimeOut = true;
					jsonErrCode = ItvEngine.EVENT_ERROR;
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
