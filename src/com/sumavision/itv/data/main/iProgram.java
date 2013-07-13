package com.sumavision.itv.data.main;

public class iProgram
{

	// 节目id
	public int liveProgramId;
	public String programName;
	public String start;
	public String startTime;
	public String end;
	public String endTime;

	// time status 0未开始 1 进行中 2 未来的
	public int timestatus;
	// 回看url
	public String tvodPath;
	// 是否能回看1为可以
	public String tvodStatus;
	// 甩屏url
	public String tvVideoPath;
	// 标志是哪天的节目 -2前天 -1昨天 0今天 1明天
	public int dayFlag;
	public boolean isLabel = false;
	public boolean isExpanded = false;
}
