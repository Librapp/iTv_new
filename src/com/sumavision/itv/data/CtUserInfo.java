package com.sumavision.itv.data;

import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;


public class CtUserInfo {

	// private static CtUserInfo current;
	// 用户名
	public static String name = "xxx";
	// 新用户名
	public static String nameNew = "";
	// 昵称
	public static String nickName = "xxx";
	// 昵称
	public static String stbAccounts = "";
	// 一句话介绍
	public String intro = "";
	// 新一句话介绍
	public String introNew = "";
	// 密码
	public String passwd;
	// 新密码
	public String passwdNew;
	// 新头像
	public String picNew = "";
	// 电子邮件
	public String eMail = "xxx@xxx.xxx";
	// 新电子邮件
	public String eMailNew = "";
	// sessionId
	public static String sessionId = "";
	// 用户ID,用于网络服务器请求
	public static int id;
	// 登录状态
	public boolean isLogedIn = false;
	// 收藏节目列表
	public List<CtProgram> favoriteProgram = null;
	// 收藏节目数量
	public int favoriteCount = 0;

	public static String twoDimensionalCode = "";
	public static String stbId = "";
	// 推荐节目列表
	public static List<CtProgram> movieProgram;
	public static List<CtProgram> musicProgram;
	public static List<CtProgram> tvProgram;
	public static List<CtProgram> cartoonProgram;
	public static List<CtProgram> varietyProgram;
	public static List<CtProgram> releaseProgram;
	public static List<CtProgram> movieProgramM;
	public static List<CtProgram> musicProgramM;
	public static List<CtProgram> tvProgramM;
	public static List<CtProgram> cartoonProgramM;
	public static List<CtProgram> varietyProgramM;
	public static List<CtProgram> releaseProgramM;

	// 全部节目列表
	public static List<CtProgram> movieProgramAll;
	public static List<CtProgram> musicProgramAll;
	public static List<CtProgram> tvProgramAll;
	public static List<CtProgram> cartoonProgramAll;
	public static List<CtProgram> varietyProgramAll;
	public static List<CtProgram> releaseProgramAll;

	// 用户头像URL
	public static String iconURL = "";
	// 网络超时消息
	public static boolean isTimeOut = true;
	// 用户自己发布的评论列表
	// public List<CtComment> ownComments;
	// 待删除评论
	// public List<CtComment> waitDeleteComments;
	// 评论次数
	public int commentCount = 0;
	// 用户最后更新的热门评论列表
	public List<CtComment> hotComments;
	// 用户最后查询的指定节目的评论列表
	public List<CtComment> allComments;
	// 用户最后查询的指定节目的评论的条数
	public int allCommentsCount;
	// 服务器返回的出错消息
	public static String errMsg = "error";
	// 网络通信状态
	public boolean isResponsed = false;
	// 待取消收藏的节目
	// public List<CtProgram> unSubscribe;
	// 查询请求起始条数
	public static int startNum = 0;
	// 查询请求条数
	public static int pageCount = 30;
	// 视频全部分页
	public static int startNumMore = 0;
	// 视频全部分页
	public static int pageCountMore = 10;
	// 评论分页
	public static int startNumCommentListMore = 0;
	// 评论分页
	public static int pageCountCommentListMore = 30;
	// 搜索到的节目列表
	public static List<CtProgram> searchResultP;
	// 用户AllComment接口，指定节目最新评论查询状态：0.成功，1.无评论，-1.查询出错
	public int allCommentStatus = -1;
	public String nowWeekDate;
	public static String myServerAddress =
	// 正式版本发布用
	"http://59.56.195.2:801/JsonServlet.do";
	// "http://172.16.16.73:9080/JsonServlet.do";
	// "http://61.154.125.22:801/JsonServlet.do";
	// "http://124.127.117.247:9080/JsonServlet.do";
	// "http://192.168.5.187:9080/JsonServlet.do";
	// "http://192.168.5.121:9080/JsonServlet.do";
	// "http://192.168.1.88:9080/JsonServlet.do";
	// "http://192.168.5.188:8081/JsonServlet.do";
	// 用户最后查询的指定节目的所有已有回复
	// public List<CtComment> allReplay;
	public Bitmap tempBitmap;
	public String bitmapPath = "";
	public int whatServer = 1;
	// 0：WIFI，1：BlueTooth
	public int remoteConnectType = -1;
	public String remoteWIFIIP = "";
	// 微博类型
	public String openType = "sina";
	public String IMEI = "";
	public short[] imeis;
	// 当前是否有网络
	public boolean hasNetNow = true;

	// 设备名称
	public static String equipName = "Android";
	// Mac地址
	public static String mac = "";
	// 远程发送dlna控制命令true是远程
	public static boolean remote = true;
	// 筛选类别
	public static int type = 0;
	// 筛选类别具体条件
	public static int typeId = 0;
	// 当前时间段
	public static String timeLineNow = "";
	// 要查的起始时间段
	public static String startTimeLine = "";
	// 要查的结束时间段
	public static String endTimeLine = "";
	// 要查的起始日期
	public static String startDate = "";
	// 要查的结束日期
	public static String endDate = "";
	// 图片
	public static String pic = "";
	// 直播频道列表
	public static List<CtChannel> lc;
	// 待取消收藏节目Id
	public static int proramId;
	// 收藏节目列表
	// public static List<CtProgram> favoriteP;
	// 节目单
	public static Map<String, Map<String, Integer>> allProgramList;
	public static Map<String, Integer> namesMap;
	public static List<String> namesList;

	public static List<String> channelNames;
	// 央视 1
	public static List<CtChannel> lc0;
	// 地方 2
	public static List<CtChannel> lc1;
	// 新闻，全部 3
	public static List<CtChannel> lc2;
	// 影视 4
	public static List<CtChannel> lc3;
	// 体育 5
	public static List<CtChannel> lc4;
	// 生活综艺 6
	public static List<CtChannel> lc5;

	// 全部频道列表
	public static List<CtChannel> lcAll;

	public static List<String> hotkey;
	// 频道分类
	public static List<String> channelType;
	public static Map<String, Integer> channelTypeM;
	// 筛选一级菜单
	// public static Map<String, List<CtFilterCondition>> firstTypeM;
	// public static Map<String, List<CtFilterCondition>> firstTypeY;
	// public static List<CtFilterCondition> firstType;
	// // 地区分类
	// public static List<CtFilterCondition> areaN;
	// 地区参数
	public static String areaId;
	public static String filter;
	public static String flag;
	public static String contentType;

	// 标记机顶盒绑定状态
	public static boolean isConnectedSTB = false;

	// 机顶盒ID
	public static String STBId;
	// 机顶盒类型，1.非iTV机顶盒，2.iTV机顶盒
	public static int STBType;
	// 机顶盒类型，用于机顶盒信息发布到友盟平台，完成统计
	public static String STBUserAgent;
	public static boolean scpd = false;
	public static String serviceUrl;
	public static String controlUrl;
	// 当前天数字 1~7
	public static int nowDateNumber;

	// 新头像
	public static Bitmap newIcon;
	// 是否已经绑定微博
	public static boolean hasWeiboAutherized = false;
	// 是否已经绑定机顶盒
	public static boolean hasSTBBinded = false;

	public static int SOAP_Port = 0;
	public static String SOAP_Address = "8.8.8.8";
	public static String SOAP_PREFIX = "/";
	// 手机号
	public static String phoneNumber = "12345678901";
	// 机顶盒名字
	public static String mySTBName = "我的盒子";
	// 推荐节目总数
	public static int totalCount = 12;
	// 图片路径
	public static String picPath = "";
	// ip
	public static String ip;
	/** 当前选中的电视类型 */
	public static int currentTVtype = 3;
	/** 当前选中的电影类型 */
	public static int currentVideotype = 0;

	public void initChannelList(int i) {
		switch (i) {
		case 0:

			break;
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;

		default:
			break;
		}
	}

	// public static CtUserInfo current(){
	// if(current == null){
	// current = new CtUserInfo();
	// }
	// return current;
	// }

	public static void clearMeTV() {
		searchResultP = null;
		// favoriteP = null;

		lc = null;

		if (newIcon != null) {
			newIcon.recycle();
			newIcon = null;
		}

		allProgramList = null;
		namesMap = null;
		namesList = null;
		channelNames = null;
		lc0 = null;
		lc1 = null;
		// 新闻，全部 3
		lc2 = null;
		// 影视 4
		lc3 = null;
		// 体育 5
		lc4 = null;
		// 生活综艺 6
		lc5 = null;

		// 全部频道列表
		lcAll = null;

		hotkey = null;
		// 频道分类
		channelType = null;
		channelTypeM = null;
		// // 筛选一级菜单
		// firstTypeM = null;
		// firstTypeY = null;
		// firstType = null;
		// // 地区分类
		// areaN = null;

		// OtherCacheData.current().firstTypeM = null;
		// OtherCacheData.current().firstTypeY = null;
		// OtherCacheData.current().firstType = null;
		// 地区分类
		// OtherCacheData.current().areaN = null;

		System.gc();
	}

	public static void clearMeVOD() {
		movieProgram = null;
		musicProgram = null;
		tvProgram = null;
		cartoonProgram = null;

		varietyProgram = null;
		releaseProgram = null;
		movieProgramM = null;
		musicProgramM = null;
		tvProgramM = null;
		cartoonProgramM = null;

		varietyProgramM = null;
		releaseProgramM = null;

		movieProgramAll = null;
		musicProgramAll = null;
		cartoonProgramAll = null;
		tvProgramAll = null;
		varietyProgramAll = null;
		releaseProgramAll = null;

		searchResultP = null;
		// favoriteP = null;

		lc = null;

		if (newIcon != null) {
			newIcon.recycle();
			newIcon = null;
		}

		System.gc();
	}
}
