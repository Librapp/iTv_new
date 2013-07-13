package com.sumavision.itv.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.DataSet;
import com.sumavision.itv.utils.Lunar;

/**
 * 
 * @author yanzhidan
 * @createTime 2013-4-23
 *
 */
public class AllLiveProgramRequest extends JSONRequest
{

	@Override
	public String make()
	{

		JSONObject holder = new JSONObject();
		try
		{
			DataSet.currentDuration  = Lunar.getCurrentDuration();
			DataSet.currentDate = Lunar.getCurrentDate();
			holder.put("method", "AllLiveProgramList");
			holder.put("startTimePoint",  DataSet.currentDuration);
			holder.put("endTimePoint",  DataSet.currentDuration);
			holder.put("startDayPoint",  DataSet.currentDate);
			holder.put("endDayPoint",  DataSet.currentDate);
			holder.put("isCategory",  1);

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		return holder.toString();
	}

}
