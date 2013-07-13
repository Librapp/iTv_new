package com.sumavision.itv.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.JSONMessageType;



public class CtBindRequest extends JSONRequest {

	@Override
	public String make() {

		JSONObject jsonHolder = new JSONObject();
		try {
			jsonHolder.put("method", JSONMessageType.BIND);
			jsonHolder.put("equipMac", CtUserInfo.mac);
			jsonHolder.put("equipName", CtUserInfo.equipName);
			jsonHolder.put("validCode", CtUserInfo.twoDimensionalCode);
		} catch (JSONException e) {
			return null;
		}

		return jsonHolder.toString();
	}

}
