package com.sumavision.itv.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class AppUtil
{

	public static boolean isServiceRunning(Context context, String serviceName)
	{

		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> serviceInfo = activityManager.getRunningServices(100);
		for (RunningServiceInfo info : serviceInfo)
		{
			String name = info.service.getClassName();
			if (name.equals(serviceName))
			{
				return true;
			}
		}
		return false;

	}

	public static String getPackageName(Context context)
	{

		PackageManager packageManager = context.getPackageManager();
		PackageInfo packInfo;
		try
		{
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packInfo.packageName;
		}
		catch (NameNotFoundException e)
		{
			return null;
		}
	}

	public static boolean isEmail(String strEmail)
	{

		String strPattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

	public static boolean isUserName(String userName)
	{

		String strPattern = "^([u4e00-u9fa5]|[ufe30-uffa0]|[a-zA-Z0-9_]){3,12}$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(userName);
		return m.matches();
	}
}
