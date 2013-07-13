package com.sumavision.itv.net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.DataSet;
import com.sumavision.itv.data.main.Category;
import com.sumavision.itv.data.main.iChannel;
import com.sumavision.itv.data.main.iProgram;
import com.sumavision.itv.engine.ItvEngine;

/**
 * 
 * @author yanzhidan
 * @createTime 2013-4-24
 * 
 */
public class AllLiveProgramParser extends JSONParser
{

	ArrayList<JSONArray> arrays = new ArrayList<JSONArray>();

	@Override
	public int parse(String s)
	{

		JSONObject jObject = null;
		if (!isEmpty(s))
		{
			try
			{
				jsonErrCode = ItvEngine.EVENT_OK;
				jObject = new JSONObject(s);
				if (jObject.getInt("errorcode") == jsonErrCode && jObject.has(DataSet.currentDate))
				{
					JSONObject time = jObject.getJSONObject(DataSet.currentDate);
					JSONObject duration = time.getJSONObject(String.valueOf(DataSet.currentDuration));
					JSONArray array2 = jObject.getJSONArray("category");
					DataSet.currentTvChannels.clear();
					for (int i = 0; i < array2.length(); i++)
					{
						JSONObject categoryObject = array2.getJSONObject(i);
						Category category = new Category();
						category.id = categoryObject.getInt("id");
						category.name = categoryObject.getString("name");
						parseData(duration.getJSONArray(category.name), category);
					}

				}
				else
				{
					jsonErrCode = ItvEngine.EVENT_ERROR;
				}
			}
			catch (Exception e)
			{
				jsonErrCode = ItvEngine.EVENT_ERROR;
				e.printStackTrace();
			}
		}
		return jsonErrCode;
	}

	private void parseData(JSONArray jsonArray, Category category) throws JSONException
	{

		String name = category.name;
		int id = category.id;
		iChannel labelChannel = new iChannel();
		labelChannel.categoryId = id;
		labelChannel.categoryName = name;
		labelChannel.isLabel = true;
		DataSet.currentTvChannels.add(labelChannel);
		if (jsonArray != null)
		{
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject object2 = jsonArray.getJSONObject(i);
				iChannel channel = new iChannel();
				channel.canshift = object2.optInt("canshift");
				channel.cantvod = object2.optInt("cantvod");
				channel.channelId = object2.optInt("channelId");
				channel.channelLogoUrl = object2.optString("channelLogoUrl");
				channel.channelName = object2.optString("channelName");
				channel.introShort = object2.optString("introShort");
				channel.mixno = object2.optInt("mixno");
				channel.categoryId = id;
				channel.categoryName = name;

				iProgram program = new iProgram();
				program.liveProgramId = object2.optInt("liveProgramId");
				program.programName = object2.optString("programName");
				program.start = object2.optString("start");
				program.startTime = object2.optString("starttime");
				program.end = object2.optString("end");
				program.endTime = object2.optString("endtime");
				program.tvodPath = object2.optString("tvodPath");
				program.tvodStatus = object2.optString("tvodStatus");
				program.tvVideoPath = object2.optString("tvVideoPath");

				channel.channelProgram = program;

				DataSet.currentTvChannels.add(channel);
			}
		}

	}

}
