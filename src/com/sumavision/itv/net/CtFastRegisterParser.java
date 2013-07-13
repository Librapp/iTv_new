package com.sumavision.itv.net;

import org.json.JSONObject;

import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.JSONMessageType;
import com.sumavision.itv.engine.ItvEngine;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;

public class CtFastRegisterParser extends JSONParser
{

	private Context context;
	private final String CONFIGFILE = "userSetting";

	public CtFastRegisterParser(Context context)
	{

		this.context = context;
	}

	@Override
	public int parse(String s)
	{

		JSONObject jAData = null;

		String msg = "";
		String sessionId = "";
		int userId = 0;
		int errCode = -1;
		try
		{

			jAData = new JSONObject(s);
			errCode = Integer.parseInt(jAData.getString("errorcode"));

			if (errCode == JSONMessageType.SERVER_CODE_OK)
			{
				sessionId = jAData.getString("sessionId");
				userId = Integer.parseInt(jAData.getString("userId"));

				SharedPreferences sp = context.getSharedPreferences(CONFIGFILE, 0);
				Editor spEd = sp.edit();
				spEd.putString("username", Build.MODEL);
				spEd.putInt("userId", userId);
				spEd.putString("sessionId", sessionId);

				spEd.commit();

				if (jAData.has("headPhoto"))
				{
					CtUserInfo.iconURL = JSONMessageType.URL_TITLE_SERVER + jAData.getString("headPhoto") + ".jpg";
				}
				else
				{
					CtUserInfo.iconURL = "";
				}
				CtUserInfo.nickName = jAData.getString("nickname");
				CtUserInfo.equipName = Build.MODEL;
				CtUserInfo.sessionId = sessionId;
				CtUserInfo.id = userId;
				CtUserInfo.isTimeOut = false;
				CtUserInfo.errMsg = "";
				jsonErrCode = ItvEngine.EVENT_OK;

				return jsonErrCode;

			}
			else
			{
				CtUserInfo.errMsg = msg;
				jsonErrCode = ItvEngine.EVENT_ERROR;
				CtUserInfo.isTimeOut = false;
				return jsonErrCode;
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
			jsonErrCode = ItvEngine.EVENT_ERROR;
			CtUserInfo.errMsg = JSONMessageType.SERVER_NETFAIL;
			CtUserInfo.isTimeOut = false;
		}
		return jsonErrCode;
	}
}
