package com.sumavision.itv.net;

import org.json.JSONObject;

import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.JSONMessageType;
import com.sumavision.itv.data.UpdateData;
import com.sumavision.itv.engine.ItvEngine;
import com.sumavision.itv.utils.NetUtil;

public class CtCheckVersionParser extends JSONParser
{

	@Override
	public int parse(String s)
	{

		JSONObject jAData = null;
		String msg = "";
		int errCode = -1;
		if (s != null && !s.equals(""))
		{
			try
			{
				jAData = new JSONObject(s);
				msg = jAData.getString("msg");
				errCode = jAData.getInt("errorcode");
				if (errCode == JSONMessageType.SERVER_CODE_OK)
				{
					JSONObject content = jAData.getJSONObject("content");
					if (content.has("updateVersionNum"))
					{
						UpdateData.current().versionCodeServer = content.getString("updateVersionNum");
					}
					if (content.has("updateDescription"))
					{
						UpdateData.current().updateDetail = content.getString("updateDescription");
					}
					if (content.has("fileName"))
					{
						UpdateData.current().appDownURL = NetUtil.BASE_URL + content.getString("fileName");
					}
					if (content.has("createTime"))
					{
						UpdateData.current().uploadDate = content.getString("createTime");
					}

					if (!UpdateData.current().versionCodeServer.equals(JSONMessageType.APP_VERSION))
					{
						UpdateData.current().mustUpdate = content.getInt("mustUpdate");
						UpdateData.current().isNeedUpdateApp = true;
					}
					else
					{
						UpdateData.current().isNeedUpdateApp = false;
					}

					CtUserInfo.isTimeOut = false;
					CtUserInfo.errMsg = "";
					jsonErrCode = ItvEngine.EVENT_OK;

				}
				else if (errCode == 1)
				{
					CtUserInfo.isTimeOut = false;
					UpdateData.current().isNeedUpdateApp = false;
					if (msg.equals("无可用更新"))
					{
						jsonErrCode = ItvEngine.EVENT_OK;
						CtUserInfo.errMsg = "";
					}
					else
					{
						jsonErrCode = ItvEngine.EVENT_ERROR;						
						CtUserInfo.errMsg = msg;
					}
				}
				else
				{
					UpdateData.current().isNeedUpdateApp = false;
					CtUserInfo.isTimeOut = true;
					CtUserInfo.errMsg = msg;
					jsonErrCode = ItvEngine.EVENT_ERROR;
				}
			}
			catch (Exception e)
			{
				CtUserInfo.isTimeOut = true;
				jsonErrCode = ItvEngine.EVENT_ERROR;
				CtUserInfo.errMsg = JSONMessageType.SERVER_NETFAIL;
				e.printStackTrace();
			}
			return jsonErrCode;
		}
		return jsonErrCode;
	}

}
