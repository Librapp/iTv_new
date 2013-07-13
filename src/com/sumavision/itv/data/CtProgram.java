package com.sumavision.itv.data;

import java.util.List;

import android.graphics.drawable.Drawable;

/**
 * 
 * @author 李梦思
 * @createTime 2012-5-16
 * @description 节目单实体类
 */
public class CtProgram
{

	private static CtProgram current;
	// 节目名称
	public String name = "未知节目";
	// 节目id
	public int id = -1;
	// 节目对应电视台
	public String channelName = "CCTV-1";
	// 播出时间
	public String startTime = "25:00";
	// 结束时间
	public String endTime = "00:00";
	// 视频时长
	public String duration = "99:99:99";
	// 原始视频时长
	public String durationHoler = "00:00:00";
	// 节目简介
	public String contentIntro = "精彩节目，马上放送";
	// 节目类型
	public String type = "1";
	// 节目类型，点播：1，直播：0
	public String programType = "1";
	// 节目内容类型，点播：1，直播：0
	public String programContentType = "未知";
	// 导演
	public String director = "未知";
	// 主要演员列表
	public String players = "未知";
	// 剧照，海报URL地址，竖
	public String PicURL = "";
	// 剧照，海报URL地址，横图
	public String PicURL_H = "";
	// 剧照
	public List<String> photoes;
	// 节目评论所需ID
	public long topicId = 0;
	// 节目对应的电视台ID
	public long channelId = 0;
	// 最后签到时间
	public String checkInDate = "";
	// 最后签到时的留言
	public String checkInContent = "";
	// 当前节目共签到几次
	public int checkInProgramCount = 10;
	// 最后收藏时间
	public String feededDate = "";
	// 当前节目共收藏几次
	public int feededProgramCount = 10;
	// 分
	public String mark = "";
	// 节目点播播放地址
	public String playAdress = "";
	// 节目甩屏播放地址
	public String goTVAdress = "";
	// 点播节目的片花URL
	public String goTVPrevuAdress = "";
	// 点播节目的片花URL
	public String goTVPrevueAdressHolder = "";
	// 直播节目的回看URL
	public String goTVVODAdress = "";
	// 直播节目的回看URL
	public String goTVVODAdressHolder = "";
	// 当前节目甩屏播放地址
	public String goTVAdressHolder = "";
	// 是否为HTTP流
	public boolean isHttpStreaming = false;
	// 上映日期
	public String onScreenTime = "未知";
	// 时长
	public String length = "未知";
	// 常用短语
	public List<String> phrases;
	// 评论列表
	public List<CtComment> comment;
	// favId
	public int favId = 0;
	// remark
	public String remark = "";
	// 直播点播
	public int direct = 1;

	public String uploadPicURL;
	public String uploadPicMD5;
	public String uploadPicMIME;
	public String SharePicWithDLNAUploadURL;

	// DLNA分享图片时，AVT::SetTranspotURL时需要
	public String SharePicWithDLNADownURL;

	// 电视相关
	// 频点
	public int tv_freq = 0;
	public short tv_onetID = 0;
	public short tv_TSID = 0;
	public short tv_serviceID = 0;
	public short tv_pid_v = 0;
	public short tv_pid_a = 0;
	// /////////////////////

	// 微博跳转主机地址
	public String jumpWeiBoServerIP = "192.166.62.113";

	public boolean isNowPlaying = false;
	public boolean hasStillPic = true;

	// 剧照列表
	public int postCount;
	public boolean isFavorited = false;
	public boolean isRated = false;
	public boolean isReminded = false;
	public boolean isCheckedIn = false;
	// 评论数量
	public int talkCount = 0;
	// 地区
	public String area = "未知";
	// 节目时间筛选条件
	public String time = "未知";
	public String actor = "未知";
	public String typeId = "";
	// 总集数
	public int totalEpisode = -1;
	// 当前集数
	public int currentEpisode = 0;
	// 大图竖
	public String PicURL_VB = "";
	// 方图
	public String PicURL_SQ = "";
	// 原始方图
	public String PicURL_SQ_Holder = "";
	// 地区Id
	public String areaId = "";

	// 电视台
	public String live_station = "未知";
	// 时间
	public String live_time = "未知";

	// time status 0未开始 1 进行中 2 未来的
	public int timestatus;

	// 0直播，1回看， 2尚未播放
	public int playingType;

	// 当前节目是否有集数
	public boolean hasEpisode = false;
	private List<ProgramEpisodeData> listEpisodes;
	// 当前为子集点播
	public boolean isEpisodePlay = false;
	// 当前节目已被收藏过
	public boolean isFavorates = false;
	// 是否为点播
	public boolean isVODType = true;

	public int playCount;
	// 赞接口的值
	public long pid;

	public int favCount;

	// 如果为0， contentTpe==14; 如果不为0，contentTpe==1;
	public int isAbstractSeries = -1; // 1 抽象节目 ； 其他代表非抽象 举例 电视剧
	// 用于断点甩屏，2时，断点甩屏需添加breakpoint字段，单位为秒
	public int playURLsource = 0;
	// 当前的视频播放到的位置，用于断点甩屏，单位为秒
	public int nowPayingPosition = 0;
	// 甩到电视的图片
	public Drawable drawableOnTV;
	// 观看时间
	public String playTime;
	// 节目播放状态
	public String playStatus = "正在直播";
	// 直播节目：0，回看；1，直播；2，未播出，2013-1-19 未播出不允许甩屏
	public int liveProgramPlayStatus = 0;
	// 节目支持时移,// 0 不支持回播 1支持回播,时移
	public int canshift = 1;
	// 节目支持回看
	public int cantvod = 1;
	// 节目评论是否可以继续加载更多
	public boolean hasMoreComments = true;
	// 节目是否为直播回看
	public boolean isLiveBackView = false;
	// 查询节目详情后恢复节目详情页
	public boolean isGetDetailReturnProgram = false;
	// 2013-1-24 最终以次字段判断是否可以回看，1为可回看，其他值不可回看
	public int tvodStatus = 1;
	// 当前节目所属栏目
	public String typeName = "电影";

	// 标志是哪天的节目 -2前天 -1昨天 0今天 1明天
	public int dayFlag;
	// 标志这个节目的状态 回看，正在直播，尚未播出等
	public String status;
	// 完整时间
	public String fullTime;

	public List<ProgramEpisodeData> getListEpisodes()
	{

		return listEpisodes;
	}

	public void setListEpisodes(List<ProgramEpisodeData> listEpisodes)
	{

		this.listEpisodes = listEpisodes;
	}

	public CtProgram(String name, String id, String type, String URL, String time)
	{

		int tempId;
		try
		{
			tempId = Integer.parseInt(id);
		}
		catch (Exception e)
		{
			tempId = 0;
		}
		this.name = name;
		this.id = tempId;
		this.type = type;
		this.PicURL_VB = URL;
		this.playTime = time;
	}

	public CtProgram(String name, String id, String type, String URL, int currentEpisode, String time)
	{

		int tempId;
		try
		{
			tempId = Integer.parseInt(id);
		}
		catch (Exception e)
		{
			tempId = 0;
		}
		this.name = name;
		this.id = tempId;
		this.type = type;
		this.PicURL_VB = URL;
		this.playTime = time;
		this.currentEpisode = currentEpisode;
	}

	public CtProgram()
	{

	}

	public static CtProgram current()
	{

		if (current == null)
		{
			current = new CtProgram();
		}
		return current;
	}

	public void setCurrent(CtProgram c)
	{

		current = c;
	}

	public void clearMe()
	{

		if (photoes != null)
			photoes.clear();
		photoes = null;

		if (phrases != null)
			phrases.clear();
		phrases = null;

		if (comment != null)
			comment.clear();
		comment = null;

		drawableOnTV = null;

		if (current != null)
		{
			current = null;
		}

		System.gc();
	}
}
