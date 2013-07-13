package com.sumavision.itv.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sumavision.itv.data.main.Category;
import com.sumavision.itv.data.main.iChannel;
import com.sumavision.itv.data.main.iProgram;


public class DataSet
{
	public static String scanTVResultString = "";
	public static String currentDate;
	public static int currentDuration;
	public static Map<Integer, Category> channelTypeById = new HashMap<Integer, Category>();
	public static Map<String, Category> channelTypeByName = new HashMap<String, Category>();
	//电视数据主caChe
	public static ArrayList<iChannel> currentTvChannels = new ArrayList<iChannel>();
	//48小时节目单数据主caChe
	public static ArrayList<iProgram> currentChannelPrograms = new ArrayList<iProgram>();
}
