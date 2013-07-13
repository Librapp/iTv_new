package com.sumavision.itv.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.CtProgram;
import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.DLNAData;
import com.sumavision.itv.data.JSONMessageType;

public class CtAddFavoriteRequest extends JSONRequest
{

	private boolean isFromControllerActivity = false;

	public CtAddFavoriteRequest(boolean isFromControllerActivity)
	{

		this.isFromControllerActivity = isFromControllerActivity;
	}

	@Override
	public String make()
	{

		JSONObject holder = new JSONObject();

		try
		{
			holder.put("userId", CtUserInfo.id);
			if (!isFromControllerActivity)
				holder.put("pid", CtProgram.current().id);
			else
				holder.put("pid", DLNAData.current().prevProgramID);

			holder.put("method", JSONMessageType.ADDFAVORITE);
			holder.put("sessionId", CtUserInfo.sessionId);

			if (!isFromControllerActivity)
				holder.put("flag", CtProgram.current().programType);
			else
				holder.put("flag", DLNAData.current().prevProgramType);

			holder.put("remark", "test");
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		return holder.toString();
	}
}
