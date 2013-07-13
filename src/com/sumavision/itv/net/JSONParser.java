package com.sumavision.itv.net;

public abstract class JSONParser {
	
	public int jsonErrCode = -1;
	public abstract int parse(String s);
	
	public boolean isEmpty(String str)
	{

		if (str == null || (str != null) && str.trim().length() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
