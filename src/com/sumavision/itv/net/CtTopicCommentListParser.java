package com.sumavision.itv.net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.CtComment;
import com.sumavision.itv.data.CtProgram;
import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.JSONMessageType;
import com.sumavision.itv.engine.ItvEngine;

public class CtTopicCommentListParser extends JSONParser
{

	@Override
	public int parse(String s)
	{

		JSONObject jData = null;
		JSONArray content = null;
		JSONObject item = null;
		JSONObject talk = null;
		List<CtComment> lc;
		if (CtProgram.current().comment == null)
		{
			lc = new ArrayList<CtComment>();
		}
		else
		{
			lc = CtProgram.current().comment;
		}

		String msg = "";
		int err = -1;
		if (s != null && !s.equals(""))
		{
			try
			{
				jData = new JSONObject(s);
				msg = jData.getString("msg");
				err = jData.getInt("errorcode");
				if (err == JSONMessageType.SERVER_CODE_OK)
				{
					content = jData.getJSONArray("content");
					CtProgram.current().talkCount = jData.getInt("totalcount");
					for (int i = 0; i < content.length(); i++)
					{
						CtComment c = new CtComment();
						item = content.getJSONObject(i);
						talk = item.getJSONObject("talk");
						JSONObject user = talk.getJSONObject("user");
						if (user.has("userId"))
						{
							c.userId = user.getInt("userId");
						}
						c.userPhoto = JSONMessageType.URL_TITLE_SERVER + user.getString("headPhoto") + JSONMessageType.FILE_TYPE;
						c.name = user.getString("nickname");

						if (user.has("username"))
						{
							c.name = user.getString("username");
						}

						if (user.has("status"))
						{
							c.status = talk.getInt("status");
						}

						if (user.has("phraseCount"))
						{
							c.phraseCount = talk.getInt("phraseCount");
						}

						c.talkId = talk.getInt("talkId");
						c.topicId = talk.getInt("topicId");
						c.time = talk.getString("time");
						c.content = talk.getString("content");
						c.source = talk.getString("source");
						lc.add(c);
					}
					if (content.length() != 0)
					{
						CtProgram.current().comment = lc;
					}
					else
					{
						CtProgram.current().hasMoreComments = false;
					}
					CtUserInfo.isTimeOut = false;
					CtUserInfo.errMsg = "";
					jsonErrCode = ItvEngine.EVENT_OK;
				}
				else
				{
					CtUserInfo.isTimeOut = true;
					CtUserInfo.errMsg = msg;
					jsonErrCode = ItvEngine.EVENT_ERROR;
				}
				return jsonErrCode;
			}
			catch (JSONException e)
			{
				CtUserInfo.isTimeOut = true;
				CtUserInfo.errMsg = JSONMessageType.SERVER_NETFAIL;
				jsonErrCode = ItvEngine.EVENT_ERROR;
				e.printStackTrace();
			}
		}
		return jsonErrCode;
	}
}
