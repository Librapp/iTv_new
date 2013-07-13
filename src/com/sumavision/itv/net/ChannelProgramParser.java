package com.sumavision.itv.net;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.sumavision.itv.data.DataSet;
import com.sumavision.itv.data.main.iProgram;
import com.sumavision.itv.engine.ItvEngine;

public class ChannelProgramParser extends JSONParser
{

	private String qiantianDateString;
	private String zuotianDateString;
	private String nowDateString;
	private String tomorrowDateString;

	public ChannelProgramParser()
	{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(new Date().getTime() - 3600 * 1000 * 24 * 2);
		qiantianDateString = sdf.format(date);
		Date date1 = new Date(new Date().getTime() - 3600 * 1000 * 24 * 1);
		zuotianDateString = sdf.format(date1);
		nowDateString = sdf.format(new Date());
		Date date3 = new Date(new Date().getTime() + 3600 * 1000 * 24);
		tomorrowDateString = sdf.format(date3);
	}

	public int parse(String s)
	{

		try
		{
			JSONObject jsonObject = new JSONObject(s);
			int code = jsonObject.getInt("errorcode");
			jsonErrCode = ItvEngine.EVENT_OK;
			if (jsonErrCode == code)
			{
				JSONArray content = jsonObject.getJSONArray("content");
				DataSet.currentChannelPrograms.clear();
				for (int i = 0; i < content.length(); ++i)
				{
					iProgram p = new iProgram();
					JSONObject program = content.getJSONObject(i).getJSONObject("LiveProgram");
					p.liveProgramId = Integer.parseInt(program.getString("liveProgramId"));
					p.programName = program.getString("programName");
					p.start = program.getString("start");
					p.end = program.getString("end");
					p.endTime = program.getString("endtime");
					p.timestatus = program.getInt("timestatus");
					String startTime = program.getString("starttime");
					p.startTime = startTime;
					p.dayFlag = getDayFlag(startTime);
					p.tvodPath = program.optString("tvodPath");
					p.tvodStatus = program.optString("tvodStatus");
					p.tvVideoPath = program.optString("tvVideoPath");
					Log.e("View", "timestatus: " + p.timestatus);
					DataSet.currentChannelPrograms.add(p);
				}
			}
			else
			{
				jsonErrCode = ItvEngine.EVENT_ERROR;
			}

		}
		catch (JSONException e)
		{
			e.printStackTrace();
			jsonErrCode = ItvEngine.EVENT_ERROR;
			return jsonErrCode;
		}
		return jsonErrCode;
	}

	private int getDayFlag(String startTime)
	{

		if (startTime.contains(qiantianDateString))
		{
			return -2;
		}
		if (startTime.contains(zuotianDateString))
		{
			return -1;
		}
		if (startTime.contains(nowDateString))
		{
			return 0;
		}
		if (startTime.contains(tomorrowDateString))
		{
			return 1;
		}
		return 0;
	}
}
