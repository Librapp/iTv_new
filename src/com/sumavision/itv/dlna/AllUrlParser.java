package com.sumavision.itv.dlna;

import com.sumavision.itv.data.CtUserInfo;


public class AllUrlParser
{

	@SuppressWarnings("unused")
	private static int SOAP_Port = 0;
	@SuppressWarnings("unused")
	private static String SOAP_Address = "8.8.8.8";
	@SuppressWarnings("unused")
	private static String SOAP_PREFIX = "/prefix";
	private static boolean needUUID = false;
	private static boolean isForServiceSCPDURL = false;

	// 主方法，传入相应字符串即可
	@SuppressWarnings("unused")
	public static String checkDeviceURL(String str, boolean b)
	{

		isForServiceSCPDURL = b;
		String resultStr = "";
		int lastColon = str.lastIndexOf(":");

		if (str.contains("uuid:"))
		{
			needUUID = true;

			if (str.contains("http:"))
			{
				if (checkDoubleColon(str, true))
				{
					int p = checkThreeDash(str);
					int l = str.length();
					String r = "";
					if (p == 0 || p == l)
					{
						r = "/";
					}
					else
					{
						r = str.substring(p, l);
					}
					filterDoubleLine();
					processHasPortUrlForUUID(str);
					resultStr = CtUserInfo.SOAP_Address;
				}
				else
				{
					processNoPortUrl(str);
					resultStr = CtUserInfo.SOAP_Address;
				}

			}
			else
			{
				if (str.startsWith("/"))
				{
					CtUserInfo.SOAP_Address = str;
					resultStr = str;
				}
				else
				{

					if (!CtUserInfo.SOAP_PREFIX.endsWith("/"))
					{
						CtUserInfo.SOAP_PREFIX = CtUserInfo.SOAP_PREFIX + "/";
						CtUserInfo.SOAP_Address = CtUserInfo.SOAP_PREFIX + str;
						resultStr = CtUserInfo.SOAP_Address;

					}
					else
					{
						CtUserInfo.SOAP_Address = CtUserInfo.SOAP_PREFIX + str;
						resultStr = CtUserInfo.SOAP_Address;
					}
				}
			}

		}
		else if (str.contains("http:") && (lastColon > 5))
		{
			needUUID = false;
			str = deleteParamenter(str);
			processHasPortUrl(str, str.substring(str.lastIndexOf(":") + 1));
			resultStr = CtUserInfo.SOAP_Address;
		}
		else if (str.contains("http:") && (lastColon < 5))
		{
			needUUID = false;
			str = deleteParamenter(str);
			processNoPortUrl(str);
			resultStr = CtUserInfo.SOAP_Address;
		}

		else
		{
			needUUID = false;
			if (str.startsWith("/"))
			{
				resultStr = str;
				CtUserInfo.SOAP_Address = resultStr;
			}
			else
			{

				if (!CtUserInfo.SOAP_PREFIX.endsWith("/"))
				{
					if (isFileType(CtUserInfo.SOAP_PREFIX))
					{
						resultStr = "/" + str;
						CtUserInfo.SOAP_Address = resultStr;
					}
					else
					{
						CtUserInfo.SOAP_PREFIX = CtUserInfo.SOAP_PREFIX + "/";
						CtUserInfo.SOAP_Address = CtUserInfo.SOAP_PREFIX + str;
						resultStr = CtUserInfo.SOAP_Address;
					}

				}
				else
				{
					CtUserInfo.SOAP_Address = CtUserInfo.SOAP_PREFIX + str;
					resultStr = CtUserInfo.SOAP_Address;
				}
			}
		}

		if (!needUUID)
		{
			formatAdressDeletePot();
		}
		return resultStr;
	}

	public static boolean isFileType(String s)
	{

		s = deleteParamenter(s);
		int l = s.length();
		boolean result = false;
		String t = "";

		for (int i = 100; i > -1; i--)
		{
			if (l > i)
			{
				t = s.substring(l - i, l);
				if (t.contains("."))
				{
					return true;
				}
			}
		}
		return result;
	}

	public static boolean formatPrefixDeletePot()
	{

		String s = CtUserInfo.SOAP_PREFIX;
		int p = 0;

		if (isFileType(s))
		{
			if (s.contains("."))
			{
				p = s.lastIndexOf("");
				s = s.substring(0, p);
				char[] strs = s.toCharArray();
				for (int i = strs.length - 1; i > 0; i--)
				{

					if (strs[i] == '/')
					{
						p = i;
						CtUserInfo.SOAP_PREFIX = s.substring(0, p);
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean formatAdressDeletePot()
	{

		String s = CtUserInfo.SOAP_Address;
		int p = 0;

		if (isFileType(s) && !isForServiceSCPDURL)
		{
			if (s.contains("."))
			{
				p = s.lastIndexOf("");
				s = s.substring(0, p);
				char[] strs = s.toCharArray();
				for (int i = strs.length - 1; i > 0; i--)
				{

					if (strs[i] == '/')
					{
						p = i;
						// 2012-10-23 后面实际使用中出问题，打开注释
						// CtUserInfo.SOAP_Address = s.substring(0, p);
						CtUserInfo.SOAP_PREFIX = s.substring(0, p);
						return true;
					}
				}
			}
		}
		else if (isForServiceSCPDURL)
		{
			CtUserInfo.SOAP_Address = deleteParamenter(s);
		}
		return false;
	}

	public static String deleteParamenter(String s)
	{

		String result = "";
		if (s.contains("?"))
		{
			int p = s.indexOf("?");
			result = s.substring(0, p);
		}
		else
		{
			result = s;
		}

		// Log.e("deleteParamenter", result);
		return result;
	}

	public static boolean IsNum(String str)
	{

		for (int i = 0; i < str.length(); i++)
		{
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}

	public static int checkPortPosition(String str)
	{

		int p = 0;
		int a = str.lastIndexOf(":");
		int l = str.length();
		char[] strs = str.substring(a, l).toCharArray();

		for (int i = 0; i < strs.length; i++)
		{
			if (strs[i] == '/')
			{
				return i + a + 1;
			}
		}
		return p;
	}

	public static String getPortFroHasUUID(String str)
	{

		int p = 0;
		int q = 0;
		int start = 0;
		int end = 0;
		char[] strs = str.toCharArray();

		for (int i = 0; i < strs.length; i++)
		{
			if (strs[i] == ':')
			{
				p++;
				if (p == 2)
				{
					start = i;
				}
			}
			else if (strs[i] == '/')
			{
				q++;
				if (q == 3)
				{
					end = i;
				}
			}
		}

		String port = "";
		port = str.substring(start + 1, end);
		if (!port.equals(""))
		{
			if (IsNum(port))
			{
				CtUserInfo.SOAP_Port = Integer.parseInt(port);
			}
		}
		CtUserInfo.SOAP_Address = str.substring(end, str.length());

		return str.substring(start, end);
	}

	public static void filterDoubleLine()
	{

		String tmp = CtUserInfo.SOAP_Address;
		char[] tmps = tmp.toCharArray();
		if (tmps.length > 1)
		{
			if (tmps[0] == '/' && tmps[1] == '/')
			{
				CtUserInfo.SOAP_Address = tmp.substring(1);
			}
		}
	}

	public static boolean checkDoubleColon(String str, boolean hasUUID)
	{

		int i = 0;
		boolean result = false;
		char[] strs = str.toCharArray();
		for (int j = 0; j < strs.length; j++)
		{
			if (strs[j] == ':')
			{
				i++;
			}
		}
		switch (i)
		{
			case 0:
				result = false;
				break;
			case 1:
				result = false;
				break;
			case 2:
				if (hasUUID)
				{
					result = false;
				}
				else
				{
					result = true;
				}
				break;
			case 3:
				result = checkColonJustForHasUUID(str);
				break;
			default:
				result = checkColonJustForHasUUID(str);
				break;
		}
		return result;
	}

	public static boolean checkColonJustForHasUUID(String str)
	{

		int p = 0;
		int end = 0;
		char[] strs = str.toCharArray();
		for (int i = 0; i < strs.length; i++)
		{
			if (strs[i] == '/')
			{
				p++;
				if (p == 3)
				{
					end = i;
				}
			}
		}
		int q = 0;
		String prefixStr = str.substring(0, end);
		char[] prefixStrs = prefixStr.toCharArray();
		for (int i = 0; i < prefixStrs.length; i++)
		{
			if (strs[i] == ':')
			{
				q++;
				if (q == 2)
				{
					return true;
				}
			}
		}

		return false;
	}

	private static void processHasPortUrl(String all, String address)
	{

		if (all.charAt(all.length() - 1) == '/')
		{

			String tmp = address.substring(0, address.indexOf("/"));
			if (IsNum(tmp))
			{
				CtUserInfo.SOAP_Port = Integer.parseInt(tmp);
			}

			int s = checkPortPosition(all);
			int l = all.length();
			if (s == 0 || s == l)
			{
				CtUserInfo.SOAP_Address = address.substring(address.indexOf("/"));
			}
			else
			{
				CtUserInfo.SOAP_Address = all.substring(s - 1, l);
			}
		}
		else
		{
			String tmp;
			if (address.endsWith("/"))
			{
				tmp = address.substring(0, address.indexOf("/"));
			}
			else
			{
				tmp = address;
			}
			if (IsNum(tmp))
			{
				CtUserInfo.SOAP_Port = Integer.parseInt(tmp);
			}
			else
			{
				int p = checkAddressPort(tmp);
				if (p != 0)
				{
					CtUserInfo.SOAP_Port = Integer.parseInt(tmp.substring(0, p));
				}
				else
				{
				}
			}
			int s = checkPortPosition(all);
			if (s == 0)
			{
				CtUserInfo.SOAP_Address = "/";
			}
			else
			{
				CtUserInfo.SOAP_Address = all.substring(s - 1, all.length());
			}
		}

		filterDoubleLine();
		CtUserInfo.SOAP_PREFIX = CtUserInfo.SOAP_Address;
		formatPrefixDeletePot();
	}

	private static void processHasPortUrlForUUID(String all)
	{

		getPortFroHasUUID(all);
		filterDoubleLine();
		CtUserInfo.SOAP_PREFIX = CtUserInfo.SOAP_Address;
		formatPrefixDeletePot();
	}

	private static int checkAddressPort(String str)
	{

		int result = 0;
		char[] strs = str.toCharArray();
		for (int i = 0; i < strs.length; i++)
		{
			if (strs[i] == '/')
			{
				result = i;
				return result;
			}
		}
		return result;
	}

	private static void processNoPortUrl(String all)
	{

		CtUserInfo.SOAP_Port = 80;

		int p = checkThreeDash(all);
		int l = all.length();
		if (p == 0 || p == l)
		{
			CtUserInfo.SOAP_Address = "/";
		}
		else
		{
			CtUserInfo.SOAP_Address = all.substring(p, l);
		}

		filterDoubleLine();
		CtUserInfo.SOAP_PREFIX = CtUserInfo.SOAP_Address;
		formatPrefixDeletePot();
	}

	private static int checkThreeDash(String str)
	{

		int result = 0;
		int p = 0;
		char[] strs = str.toCharArray();
		for (int i = 0; i < strs.length; i++)
		{
			if (strs[i] == '/')
			{
				p++;
				if (p == 3)
				{
					result = i;
				}
			}
		}
		return result;
	}

	@SuppressWarnings("unused")
	private static void prefixParser(String all)
	{

		String prefix = "";
		int p = checkThreeDash(all);
		prefix = all.substring(0, p);
		CtUserInfo.SOAP_PREFIX = prefix;
		formatPrefixDeletePot();
	}
}
