package com.sumavision.itv.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.JSONMessageType;


import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiManager;
import android.os.Build;

public class CtClientRegistRequest extends JSONRequest
{

	private String Mac = null;
	private final String CONFIGFILE = "userSetting";

	public CtClientRegistRequest(Context context)
	{

		SharedPreferences sp = context.getSharedPreferences(CONFIGFILE, 0);
		String mac = sp.getString("Mac", "");

		if (mac.equals(""))
		{
			WifiManager wm = (WifiManager) context.getSystemService(Service.WIFI_SERVICE);
			Mac = wm.getConnectionInfo().getMacAddress();

			Editor spEd = sp.edit();
			spEd.putString("Mac", Mac);
			spEd.putString("equipName", Build.MODEL);
			spEd.commit();
		}
		else
		{
			Mac = mac;
		}
		CtUserInfo.mac = Mac;
		CtUserInfo.equipName = Build.MODEL;
	}

	@Override
	public String make()
	{

		JSONObject holder = new JSONObject();
		try
		{

			holder.put("method", JSONMessageType.CLIENT_REGIST);
			holder.put("equipMac", CtUserInfo.mac);
			holder.put("equipName", CtUserInfo.equipName);
			holder.put("userIp", "127.0.0.1");

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		return holder.toString();
	}

}
