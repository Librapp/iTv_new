package com.sumavision.itv.data.main;

import java.util.ArrayList;

public class iChannel
{

	// 頻道是否支持时移1/0
	public int canshift;
	// 是否支持回看 1/0
	public int cantvod;

	//频道简介
	public String introShort;
	// 频道混排号
	public int mixno;
	public int channelId;
	public String channelLogoUrl;
	public String channelName;
	
	//类别id
	public int categoryId;
	//类别名称
	public String categoryName;
	public boolean isLabel = false;
	public boolean isExpanded = false;
	
	public iProgram channelProgram;
	
	private ArrayList<iProgram> programs;

	public ArrayList<iProgram> getPrograms()
	{

		return programs;
	}

	public void setPrograms(ArrayList<iProgram> programs)
	{

		this.programs = programs;
	}

}
