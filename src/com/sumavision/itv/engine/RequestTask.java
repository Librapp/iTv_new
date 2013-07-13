package com.sumavision.itv.engine;

import com.sumavision.itv.net.JSONRequest;
import com.sumavision.itv.utils.NetUtil;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class RequestTask extends AsyncTask<Object, Void, String>
{

	private int eventId;

	public RequestTask(int eventId)
	{

		this.eventId = eventId;
		Log.e("RequestTask", "eventId: " + eventId);
	}

	@Override
	protected void onPreExecute()
	{

	}

	@Override
	protected String doInBackground(Object... params)
	{

		Context context;
		JSONRequest request;
		String data;
		context = (Context) params[0];
		request = (JSONRequest) params[1];
		data = request.make();
		if (data != null)
		{
			Log.e("RequestTask", "request: " + data);
		}
		String s = NetUtil.execute(context, data, null);
		return s;
	}

	@Override
	protected void onCancelled()
	{

	}

	@Override
	protected void onPostExecute(String result)
	{
		Log.e("RequestTask", "result: " + result);
		new ParserTask(eventId).execute(result);
	}
}
