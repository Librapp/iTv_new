package com.sumavision.itv.data;

import java.util.List;

/**
 * 
 * @author 郭鹏
 * @description 节目单实体类
 * 
 */
public class Program {

	private static Program current;
	// 节目名称
	private String programName = "未知节目";
	// 节目id
	private String programID = "999";
	// 节目对应电视台
	private String tvStationName = "未知";;
	// 播出时间
	private String startTime = "25:00";
	// 播出年份
	private String palyYear  = "未知";
	// 结束时间
	private String endTime = "00:00";
	// 节目简介
	private String contentIntro = "精彩节目，马上放送";
	// 国家地区
	private String location = "未知";
	// 节目类型
	private String type = "";
	// 导演
	private String director = "未知";
	// 主要演员列表
	private String players = "未知";
	// 剧照，海报URL地址
	private String stillURL = "";
	// 投票、竞猜大图
	private String bigPicURL = "";
	// 节目评论所需ID
	private long topicId = 0;
	// 节目对应的电视台ID
	private long tvStationId;
	// 最后签到时间
	private String checkInDate = "";
	// 最后签到时的留言
	private String checkInContent = "";
	// 当前节目共签到几次
	private int checkInProgramCount = 10;
	// 最后收藏时间
	private String feededDate = "";
	// 当前节目共收藏几次
	private int feededProgramCount = 10;
	// 分
	private String mark = "";
	// 节目点播播放地址
	private String playAdress = "";
	// 是否为HTTP流
	private boolean isHttpStreaming = false;
	// signId
	private long signID;
	// 常用短语
	private List<String> phrases;
	
	// 电视相关
	// 频点
	private int tv_freq = 0;
	private short tv_onetID = 0;
	private short tv_TSID = 0;
	private short tv_serviceID = 0;
	private short tv_pid_v = 0;
	private short tv_pid_a = 0;
	///////////////////////
	
	// 微博跳转主机地址
//	private String jumpWeiBoServerIP = "\"微博跳转主机地址\"";
	private String jumpWeiBoServerIP = "192.166.62.113";
	
	private boolean isNowPlaying = false;
	private boolean hasStillPic = true;

	// 剧照列表
	private List<String> post;
	private int postCount;
	private boolean isFavorited = false;
	private boolean isRated = false;
	private boolean isReminded = false;
	private boolean isCheckedIn = false;
	private String markUser;
	private boolean isFromGuide=false;
	private int talkCount = 0; 

	public int getTalkCount() {
		return talkCount;
	}

	public void setTalkCount(int talkCount) {
		this.talkCount = talkCount;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public boolean isCheckedIn() {
		return isCheckedIn;
	}

	public void setCheckedIn(boolean isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

	public String getMarkUser() {
		return markUser;
	}

	public void setMarkUser(String markUser) {
		this.markUser = markUser;
	}

	public boolean isRated() {
		return isRated;
	}

	public void setRated(boolean isRated) {
		this.isRated = isRated;
	}

	public boolean isReminded() {
		return isReminded;
	}

	public void setReminded(boolean isReminded) {
		this.isReminded = isReminded;
	}

	public boolean isFavorited() {
		return isFavorited;
	}

	public void setFavorited(boolean isFavorited) {
		this.isFavorited = isFavorited;
	}

	public boolean isHasStillPic() {
		return hasStillPic;
	}

	public void setHasStillPic(boolean hasStillPic) {
		this.hasStillPic = hasStillPic;
	}

	public boolean isNowPlaying() {
		return isNowPlaying;
	}

	public void setNowPlaying(boolean isNowPlaying) {
		this.isNowPlaying = isNowPlaying;
	}

	public String getJumpWeiBoServerIP() {
		return jumpWeiBoServerIP;
	}

	public void setJumpWeiBoServerIP(String jumpWeiBoServerIP) {
		this.jumpWeiBoServerIP = jumpWeiBoServerIP;
	}

	public List<String> getPhrases() {
		return phrases;
	}

	public int getTv_freq() {
		return tv_freq;
	}


	public void setTv_freq(int tv_freq) {
		this.tv_freq = tv_freq;
	}


	public short getTv_onetID() {
		return tv_onetID;
	}


	public void setTv_onetID(short tv_onetID) {
		this.tv_onetID = tv_onetID;
	}


	public short getTv_TSID() {
		return tv_TSID;
	}


	public void setTv_TSID(short tv_TSID) {
		this.tv_TSID = tv_TSID;
	}


	public short getTv_serviceID() {
		return tv_serviceID;
	}


	public void setTv_serviceID(short tv_serviceID) {
		this.tv_serviceID = tv_serviceID;
	}


	public short getTv_pid_v() {
		return tv_pid_v;
	}


	public void setTv_pid_v(short tv_pid_v) {
		this.tv_pid_v = tv_pid_v;
	}


	public short getTv_pid_a() {
		return tv_pid_a;
	}


	public void setTv_pid_a(short tv_pid_a) {
		this.tv_pid_a = tv_pid_a;
	}


	public void setPhrases(List<String> phrases) {
		this.phrases = phrases;
	}

	public String getBigPicURL() {
		return bigPicURL;
	}

	public void setBigPicURL(String bigPicURL) {
		this.bigPicURL = bigPicURL;
	}

	public long getSignID() {
		return signID;
	}

	public void setSignID(long signID) {
		this.signID = signID;
	}

	public boolean isHttpStreaming() {
		return isHttpStreaming;
	}

	public void setHttpStreaming(boolean isHttpStreaming) {
		this.isHttpStreaming = isHttpStreaming;
	}

	public String getPlayAdress() {
		return playAdress;
	}

	public void setPlayAdress(String playAdress) {
		this.playAdress = playAdress;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getCheckInProgramCount() {
		return checkInProgramCount;
	}

	public void setCheckInProgramCount(int checkInProgramCount) {
		this.checkInProgramCount = checkInProgramCount;
	}

	public String getCheckInContent() {
		return checkInContent;
	}

	public void setCheckInContent(String checkInCotent) {
		this.checkInContent = checkInCotent;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public long getTvStationId() {
		return tvStationId;
	}

	public void setTvStationId(long tvStationId) {
		this.tvStationId = tvStationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPalyYear() {
		return palyYear;
	}

	public void setPalyYear(String palyYear) {
		this.palyYear = palyYear;
	}

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public String getProgramName() {
		return programName;
	}

	public String getProgramID() {
		return programID;
	}

	public void setProgramID(String programID) {
		this.programID = programID;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getTvStationName() {
		return tvStationName;
	}

	public void setTvStationName(String tvStationName) {
		this.tvStationName = tvStationName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContentIntro() {
		return contentIntro;
	}

	public void setContentIntro(String contentIntro) {
		this.contentIntro = contentIntro;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String strings) {
		this.players = strings;
	}

	public String getStillURL() {
		return stillURL;
	}

	public void setStillURL(String stillURL) {
		this.stillURL = stillURL;
	}

	public static Program current() {
		if (current == null) {
			current = new Program();
		}
		return current;
	}

	public void setFeededDate(String feededDate) {
		this.feededDate = feededDate;
	}

	public String getFeededDate() {
		return feededDate;
	}

	public void setFeededProgramCount(int feededProgramCount) {
		this.feededProgramCount = feededProgramCount;
	}

	public int getFeededProgramCount() {
		return feededProgramCount;
	}

	public void setPost(List<String> post) {
		this.post = post;
	}

	public List<String> getPost() {
		return post;
	}

	public void setFromGuide(boolean isFromGuide) {
		this.isFromGuide = isFromGuide;
	}

	public boolean isFromGuide() {
		return isFromGuide;
	}

}
