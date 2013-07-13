package com.sumavision.itv.application;

import com.sumavision.itv.data.C;
import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.utils.ImageLoaderHelper;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

public class Launcher extends Application
{

	public static Context baseContext;

	@Override
	public void onCreate()
	{

		super.onCreate();
		Log.e("Launcher", "onCreate");
		baseContext = getApplicationContext();
		if (baseContext != null)
		{
			ImageLoaderHelper.initImageLoader(getApplicationContext());
		}
		refreshEquipmac();
	}

	private void refreshEquipmac()
	{

		String Mac = null;
		SharedPreferences sp = baseContext.getSharedPreferences(C.str.setting, 0);
		String mac = sp.getString(C.str.mac, "");

		if (mac.equals(""))
		{
			WifiManager wm = (WifiManager) baseContext.getSystemService(Service.WIFI_SERVICE);
			Mac = wm.getConnectionInfo().getMacAddress();

			Editor spEd = sp.edit();
			spEd.putString(C.str.mac, Mac);
			spEd.putString(C.str.equipName, Build.MODEL);
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
	

}
