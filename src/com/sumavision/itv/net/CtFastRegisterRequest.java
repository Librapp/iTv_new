package com.sumavision.itv.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.JSONMessageType;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.telephony.TelephonyManager;

public class CtFastRegisterRequest extends JSONRequest
{

	private String Mac = null;
	private final String CONFIGFILE = "userSetting";

	public CtFastRegisterRequest(Context context)
	{

		SharedPreferences sp = context.getSharedPreferences(CONFIGFILE, 0);
		String mac = sp.getString("Mac", "");

		if (mac.equals(""))
		{
			TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			Mac = manager.getDeviceId();

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
		CtUserInfo.nameNew = Build.MODEL;
		CtUserInfo.equipName = Build.MODEL;
	}

	@Override
	public String make()
	{

		JSONObject holder = new JSONObject();
		try
		{

			holder.put("method", JSONMessageType.FASTREGISTER);
			holder.put("equipName", Build.MODEL);
			holder.put("equipMac", Mac);

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return holder.toString();
	}

}
