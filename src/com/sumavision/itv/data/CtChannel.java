package com.sumavision.itv.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 李梦思
 * @description 频道数据类
 * @createTime 2012-5-23
 */

public class CtChannel implements Serializable
{

	private static final long serialVersionUID = 3805736902709520441L;
	public static final int NOT_CHANGED = 0;
	public static final int HAS_CHANGED = 1;
	private static CtChannel current;
	// 频道短名称
	private String channelName = "CCTV-1";
	// 频道长名称
	private String channelLongName;
	// 频道ID
	private int channelId = 0;
	// 节目列表(备用)
	private List<CtProgram> lp;
	// 单一节目
	private CtProgram p;
	// 当前频道信息所属段数(0,47)
	private int segment = 48;
	// 节目类型：1~13
	private int type = 0;
	// 仅用于节目预约
	private String userId;
	private String liveStreamingPath = "";
	private String photoName = "";
	// 用于动态刷新节目指南的指定某一条目
	private int itemStatus = NOT_CHANGED;
	// 频道图片
	public String logoURL;
	// 当前正在播放节目所处的列表ID
	public int nowPlayingItemPosition = 0;
	// 频道编号
	public String mixno = "";
	// 甩屏连接
	public String tvVideoPath;
	// 頻道是否支持甩屏，1支持
	public int canshift;

	public void initLocalProgramData()
	{

		List<CtProgram> lp = new ArrayList<CtProgram>();
		for (int i = 0; i < 3; i++)
		{
			CtProgram cp = new CtProgram();
			cp.time = "20:00";
			cp.name = "测试节目";
			lp.add(cp);
		}
		this.lp = lp;
	}

	public int getItemStatus()
	{

		return itemStatus;
	}

	public void setItemStatus(int itemStatus)
	{

		this.itemStatus = itemStatus;
	}

	public String getPhotoName()
	{

		return photoName;
	}

	public void setPhotoName(String photoName)
	{

		this.photoName = photoName;
	}

	public String getLiveStreamingPath()
	{

		return liveStreamingPath;
	}

	public void setLiveStreamingPath(String liveStreamingPath)
	{

		this.liveStreamingPath = liveStreamingPath;
	}

	public String getUserId()
	{

		return userId;
	}

	public void setUserId(String userId)
	{

		this.userId = userId;
	}

	public int getType()
	{

		return type;
	}

	public void setType(int type)
	{

		this.type = type;
	}

	public CtChannel()
	{

	}

	public CtChannel(int seg, String cn, String cID, String pIntro, String pTopicID, String pn, String pURL)
	{

		this.segment = seg;
		this.channelName = cn;
		this.channelId = Integer.parseInt(cID);

		CtProgram p = new CtProgram();
		p.name = pn;
		p.PicURL = pURL;
		p.topicId = Long.parseLong(pTopicID);

		setP(p);
	}

	public CtChannel(int seg, String cn, String cID, String pIntro, String pTopicID, String pn, String pURL, String programType)
	{

		this.segment = seg;
		this.channelName = cn;
		this.channelId = Integer.parseInt(cID);

		CtProgram p = new CtProgram();
		p.name = pn;
		p.PicURL = pURL;
		p.topicId = Long.parseLong(pTopicID);

		setP(p);
	}

	public CtChannel(String cn, String cID, String pIntro, String pTopicID, String pn, String pURL, String pTime, String userID)
	{

		this.channelName = cn;
		this.channelId = Integer.parseInt(cID);
		this.userId = userID;

		CtProgram p = new CtProgram();
		p.name = pn;
		p.PicURL = pURL;
		p.topicId = Long.parseLong(pTopicID);
		p.startTime = pTime;

		setP(p);
	}

	public CtChannel(int seg, String cn, String cID, String pIntro, String pTopicID, String pn, String pURL, String programType, String pTime, String endTime, String playAddress, String mark, String programID)
	{

		this.channelName = cn;
		this.channelId = Integer.parseInt(cID);
		this.segment = seg;

		CtProgram p = new CtProgram();
		p.name = pn;
		p.PicURL = pURL;
		p.topicId = Long.parseLong(pTopicID);
		p.startTime = pTime;
		p.type = programType;
		p.endTime = endTime;
		p.playAdress = playAddress;
		p.mark = mark;
		p.id = Integer.parseInt(programID);

		setP(p);
	}

	public CtChannel(String name, int id, String logo)
	{

		this.channelName = name;
		this.channelId = id;
		this.logoURL = logo;
	}

	public CtChannel(String name, int id, String logo, String mixno, String tvVideoPath)
	{

		this.channelName = name;
		this.channelId = id;
		this.logoURL = logo;
		this.mixno = mixno;
		this.tvVideoPath = tvVideoPath;
	}

	public int getSegment()
	{

		return segment;
	}

	public void setSegment(int segment)
	{

		this.segment = segment;
	}

	public CtProgram getP()
	{

		if (p == null)
		{
			p = new CtProgram();
		}
		return p;
	}

	public void setP(CtProgram p)
	{

		this.p = p;
	}

	public String getChannelLongName()
	{

		return channelLongName;
	}

	public void setChannelLongName(String channelLongName)
	{

		this.channelLongName = channelLongName;
	}

	public String getChannelName()
	{

		return channelName;
	}

	public void setChannelName(String channelName)
	{

		this.channelName = channelName;
	}

	public int getChannelId()
	{

		return channelId;
	}

	public void setChannelId(int channelId)
	{

		this.channelId = channelId;
	}

	public List<CtProgram> getLp()
	{

		if (lp == null)
		{
			lp = new ArrayList<CtProgram>();
		}

		return lp;
	}

	public void setLp(List<CtProgram> lp)
	{

		this.lp = lp;
	}

	public static CtChannel current()
	{

		if (current == null)
		{
			current = new CtChannel();
		}
		return current;
	}

	public static void clearMe()
	{

		if (current != null)
		{
			current = null;
		}
		System.gc();
	}
}
