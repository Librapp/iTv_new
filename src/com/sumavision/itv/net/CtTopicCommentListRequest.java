package com.sumavision.itv.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.CtProgram;
import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.DLNAData;
import com.sumavision.itv.data.JSONMessageType;


public class CtTopicCommentListRequest extends JSONRequest
{

	private boolean isFromControllerActivity = false;

	public CtTopicCommentListRequest(boolean isFromControllerActivity)
	{

		this.isFromControllerActivity = isFromControllerActivity;
	}

	@Override
	public String make()
	{

		JSONObject holder = new JSONObject();
		try
		{
			holder.put("method", JSONMessageType.TOPICCOMMENTLIST);
			if (!isFromControllerActivity)
				holder.put("flag", CtProgram.current().programType);
			else
				holder.put("flag", DLNAData.current().prevProgramType);

			if (!isFromControllerActivity)
				holder.put("programId", CtProgram.current().id);
			else
				holder.put("programId", DLNAData.current().prevProgramID);

			holder.put("startNum", CtUserInfo.startNumCommentListMore);

			int c = CtUserInfo.pageCountCommentListMore;
			if (c > 30)
			{
				c = 30;
			}
			holder.put("pageCount", c);

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		return holder.toString();
	}
}
