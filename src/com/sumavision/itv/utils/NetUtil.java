package com.sumavision.itv.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil
{

	// http://59.56.195.2:801/JsonServlet.do
	public static final String BASE_URL = "http://59.56.195.2:801/";
	// "http://172.16.16.73:9080/";
	public static final String DEFAULT_URL = BASE_URL + "JsonServlet.do";
	// "http://172.16.16.73:9080/JsonServlet.do";
	// 盈创内网服务器
	// "http://192.168.5.187:9080/JsonServlet.do";
	public static final String USER_HEAD = BASE_URL + "resource";

	// 盈创内网服务器
	// "http://192.168.5.187:9080/resource";

	/**
	 * 
	 * @param params
	 * json 字符串
	 * @param url
	 * 服务器地址 如果 url不传 那么用默认的URL
	 * @return
	 */
	public static String execute(Context context, String params, String url)
	{

		if (url == null)
		{
			url = DEFAULT_URL;
		}

		try
		{
			HttpURLConnection conn = getConnection(url, context);
			if (conn == null)
			{
				return null;
			}

			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "application/x-javascript");
			conn.setReadTimeout(20000);
			conn.setConnectTimeout(15000);
			conn.connect();
			if (params != null)
			{
				OutputStream outStream = conn.getOutputStream();
				OutputStreamWriter objSW = new OutputStreamWriter(outStream);
				BufferedWriter out = new BufferedWriter(objSW);
				char[] data = params.toCharArray();
				out.write(data, 0, data.length);
				out.flush();
				out.close();
			}
			int status = conn.getResponseCode();
			if (status == 200)
			{
				InputStream in = conn.getInputStream();
				StringBuilder sb = new StringBuilder();
				BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
				for (String line = r.readLine(); line != null; line = r.readLine())
				{
					sb.append(line);
				}
				in.close();
				return sb.toString();
			}
			else
			{
				return null;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String executeGet(Context context, String params, String url)
	{

		if (url == null)
		{
			url = DEFAULT_URL;
		}
		try
		{
			HttpURLConnection conn = getConnection(url, context);
			if (conn == null)
			{
				return null;
			}

			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "application/x-javascript");
			conn.setReadTimeout(20000);
			conn.setConnectTimeout(15000);
			conn.connect();
			if (params != null)
			{
				OutputStream outStream = conn.getOutputStream();
				OutputStreamWriter objSW = new OutputStreamWriter(outStream);
				BufferedWriter out = new BufferedWriter(objSW);
				char[] data = params.toCharArray();
				out.write(data, 0, data.length);
				out.flush();
				out.close();
			}
			int status = conn.getResponseCode();
			if (status == 200)
			{
				InputStream in = conn.getInputStream();
				StringBuilder sb = new StringBuilder();
				BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
				for (String line = r.readLine(); line != null; line = r.readLine())
				{
					sb.append(line);
				}
				in.close();
				return sb.toString();
			}
			else
			{
				return null;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回httpConnection 如果服务器支持https 可以用httpsConnection 安全性更高
	 * 
	 * @param urlString
	 * @param context
	 * @return
	 * @throws IOException
	 */
	public static HttpURLConnection getConnection(String urlString, Context context) throws IOException
	{

		HttpURLConnection connection = null;

		URL url = new URL(urlString);

		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
		if (null != netInfo && ConnectivityManager.TYPE_WIFI == netInfo.getType())
		{
			connection = (HttpURLConnection) url.openConnection();
		}
		else
		{
			String proxyHost = android.net.Proxy.getDefaultHost();
			if (null == proxyHost)
			{
				connection = (HttpURLConnection) url.openConnection();
			}
			else
			{
				java.net.Proxy p = new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(android.net.Proxy.getDefaultHost(), android.net.Proxy.getDefaultPort()));
				connection = (HttpURLConnection) url.openConnection(p);
			}
		}
		return connection;
	}
}
