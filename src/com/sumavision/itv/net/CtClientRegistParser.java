package com.sumavision.itv.net;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.JSONMessageType;
import com.sumavision.itv.engine.ItvEngine;

public class CtClientRegistParser extends JSONParser
{

	@Override
	public int parse(String s)
	{

		JSONObject jAData = null;
		String msg = "";
		int errCode = -1;
		jsonErrCode = ItvEngine.EVENT_OK;

		if (s != null && !s.equals("") )
		{
			try
			{
				jAData = new JSONObject(s);
				msg = jAData.getString("msg");
				errCode = jAData.getInt("errorcode");
				if (errCode == JSONMessageType.SERVER_CODE_OK || errCode == 300)
				{
					CtUserInfo.isTimeOut = false;
					CtUserInfo.errMsg = "";
					jsonErrCode = ItvEngine.EVENT_OK;
					JSONArray array = jAData.getJSONArray("stbIds");
					// 可以绑定多个机顶盒 目前绑定一个
					if (array.length() > 0)
					{
						String id = array.getString(0);
						CtUserInfo.stbId = id;
					}
					else
					{
						CtUserInfo.stbId = "";
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
						else
						{
							CtUserInfo.stbId = "";
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
						else
						{
							CtUserInfo.stbId = "";
						}
					}

					if (jAData.has("account"))
						CtUserInfo.stbAccounts = jAData.getString("account");

				}
				else
				{
					CtUserInfo.isTimeOut = true;
					CtUserInfo.errMsg = msg;
					jsonErrCode = ItvEngine.EVENT_ERROR;
				}
			}
			catch (Exception e)
			{
				CtUserInfo.isTimeOut = true;
				jsonErrCode = ItvEngine.EVENT_ERROR;
				CtUserInfo.errMsg = JSONMessageType.SERVER_NETFAIL;
				e.printStackTrace();
			}
			return jsonErrCode;
		}
		return jsonErrCode;
	}

}
