package com.sumavision.itv.data;

import java.io.File;


import android.os.Environment;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


/**
 * 
 * @author 郭鹏
 * @description JSON解析与系统其它常量表
 * @changeLog 修改ALLPLAYBILL、SEARCH 2012-5-4 李梦思
 * @changeLog 修改USERCENTER 2012-5-6 李梦思
 * @changeLog 修改TALKTOPIC、PROGRAMPHOTOLIST、PROGRAMDETAIL 2012-5-7 李梦思
 * @changeLog 修改 ADDTALK、CANCELFAVORITE、USERCENTER_FAVORITE、ADDFAVORITE 2012-5-8
 *            李梦思
 * @changeLog 添加DOUBANCOMMENT、PROGRAMPREVIEW、CHANNELLIVEPROGRAM、ALLPROGRAMLIST
 *            、HOTKEY, 修改HOTPROGRAM 2012-5-10 李梦思
 * @changeLog 添加UPLOAD、FILTERCONDITION 2012-5-14 李梦思
 * @changeLog 添加DICTSELECT、PLAYPROGRAM、TALKCOMMON、TOPICCOMMENTLIST 2012-5-16 李梦思
 */
public class JSONMessageType {

	public static final String APP_NAME = "iTV+";
	public static final String APP_VERSION = "1.1.12814";

	public static final String SERVER_OK = "操作成功";
	public static final String SERVER_ERROR = "网络繁忙，请稍后重试";
	public static final String SERVER_NETFAIL = "网络繁忙，请稍后重试";
	public static final String SERVER_INBLACKLIST = "抱歉，您在对方的黑名单中，不能向其发信息！";

	public static final String DLNA_SHARE_REPEAT_PIC = "Pic repeat!";
	// "正在联网，请稍候..."
	public static final String NET_GOING_STR = "";

	public static final int SERVER_CODE_OK = 0;
	public static final int SERVER_CODE_ERROR = 1;

	public static final String LOGO_SDCARD_FOLDER_SHORT = "iTV+/";
	// 头像目录
	public static final String USER_PIC_SDCARD_FOLDER = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator
			+ LOGO_SDCARD_FOLDER_SHORT + "icon/";
	// 相册目录
	public static final String USER_PIC_CAMERA_SDCARD_FOLDER = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator
			+ LOGO_SDCARD_FOLDER_SHORT;

	// 热播节目类型：（1电视剧、2电影、3综艺娱乐、4财经、5科学自然、6教育、7法制、8军事、9新闻综合、10生活人文、11少儿动漫、12体育健康、13戏曲
	// ）

	// 甩图地址
	public static final String shuaiPicPath = Environment
			.getExternalStorageDirectory()
			+ File.separator
			+ "iTV+/videoUploaded" + File.separator + "uploaded.jpg";
	// 快速注册
	public static final String FASTREGISTER = "FastRegedit";
	// 全部
	public static final int ALL = 0;
	public static final String ALL_STR = "综合";
	// 电视剧
	public static final int TELEPLAY = 1;
	public static final String TELEPLAY_STR = "电视剧";
	// 电影
	public static final int MOVIE = 2;
	public static final String MOVIE_STR = "电影";
	// 综艺娱乐
	public static final int ARTS = 3;
	public static final String ARTS_STR = "综艺娱乐";
	// 财经
	public static final int FINANCE = 4;
	public static final String FINANCE_STR = "财经";
	// 科学自然
	public static final int SCIENCE = 5;
	public static final String SCIENCE_STR = "科学自然";
	// 教育
	public static final int EDUCATION = 6;
	public static final String EDUCATION_STR = "教育";
	// 法制
	public static final int LEGAL = 7;
	public static final String LEGAL_STR = "法制";
	// 军事
	public static final int MILITARY = 8;
	public static final String MILITARY_STR = "军事";
	// 新闻综合
	public static final int NEWS = 9;
	public static final String NEWS_STR = "新闻综合";
	// 生活人文
	public static final int LIFE = 10;
	public static final String LIFE_STR = "生活人文";
	// 少儿动漫
	public static final int ANIMATION = 11;
	public static final String ANIMATION_STR = "少儿动漫";
	// 体育健康
	public static final int SPORT = 12;
	public static final String SPORT_STR = "体育健康";
	// 戏曲
	public static final int DRAMA = 13;
	public static final String DRAMA_STR = "戏曲";

	// 网络请求方法
	// 客户端获取绑定设备列表
	public static final String CLIENT_REGIST = "ClientRegister";
	// 登录
	public static final String LOGIN = "login";
	// 注册
	public static final String REG = "register";
	// 热播节目
	public static final String HOTPROGRAM = "RecommendProgramList";
	// 节目单
	public static final String PROGRAMLIST = "programlist";
	// 用户中心
	public static final String USERCENTER = "UpdateUserinfo";
	// 收藏节目列表
	public static final String USERCENTER_FAVORITE = "SelfFavouriteList";
	// 取消收藏
	public static final String CANCELFAVORITE = "DelFavourite";
	// 最新评论分享
	public static final String HOTCOMMENT = "talk";
	// 搜索
	public static final String SEARCH = "Search";
	// 发表一条评论
	public static final String ADDTALK = "AddTalk";
	// 指定节目评论取回
	public static final String TALKTOPIC = "TopicCommentList";
	// 查询指定节目详情
	public static final String PROGRAMDETAIL = "ProgramDetail";
	// 查询正在播放的节目
	public static final String PLAYING = "playing";
	// 查询粉丝列表
	public static final String USERCENTER_FANS = "fensi";
	// 查询关注列表
	public static final String USERCENTER_ATTEN = "guanzhu";
	// 添加关注
	public static final String USERCENTER_ADDGZ = "addgz";
	// 取消关注
	public static final String USERCENTER_CANCELGZ = "cancelgz";
	// 查看指定用户评论列表
	public static final String USERCENTER_GETTALKLIST = "talkUser";
	// 删除指定的用户发表的评论
	public static final String USERCENTER_DELETETALK = "deleteTalk";
	// 删除指定的用户发表的回复
	public static final String USERCENTER_DELETEREPLAY = "deleteReply";
	// 转发
	public static final String ADDFORWARD = "addforward";
	// 回复
	public static final String ADDREPLY = "addreply";
	// 签到节目
	public static final String SIGNPROGRAM = "signProgram";
	// 指定节目的签到列表，取回指定节目签到的用户
	public static final String SIGNPROGRAMLIST = "signProgramList";
	// 指定用户签到过的节目
	public static final String SIGNUSERLIST = "signUserList";
	// 取得指定节目的所有已有回复
	public static final String REPLYBYTALK = "replyByTalk";
	// 黑名单取回
	public static final String USERCENTER_BLACKLIST = "blackList";
	// 添加到黑名单
	public static final String ADDTOBLACKLIST = "blackAdd";
	// 取消黑名单
	public static final String DELETEBLACK = "blackList";
	// 对指定节目的添加收藏
	public static final String ADDFAVORITE = "AddFavourite";
	// 对指定的节目打分
	public static final String SCORE = "markfavorite";
	// 热门签到：按签到次数从大到小返回推荐节目列表
	public static final String TRENDINGSIGN = "goodProgramSign";
	// 热门收藏：按收藏次数从大到小返回推荐节目列表
	public static final String TRENDINGFAVORITE = "goodProgramFeed";
	// 用户资料修改
	public static final String USERINFOMPDIFY = "UpdateUserinfo";
	// 系统推荐用户
	public static final String GOODUSER = "goodUser";
	// 取回与指定用户之间私信列表
	public static final String PRIVATEMESSAGE = "mailList";
	// 发私信
	public static final String SENDPRIVATEMESSAGE = "mailSend";
	// 私信收件箱
	public static final String PRIVATEMESSAGELIST = "mailReceive";
	// 投票列表取回
	public static final String VOTELIST = "voteList";
	// 投票提交
	public static final String VOTESEND = "voteSend";
	// 竞猜列表取回
	public static final String GUESSLIST = "guessList";
	// 竞猜
	public static final String GUESSSEND = "guessSend";
	// 全天48个时段全部频道的节目单
	public static final String ALLPLAYBILL = "AllLiveProgramList";
	// 自动更新
	public static final String AUTOUPDATE = "versionLatest";
	// 广东省网用
	public static final String CHANNELNOWPLAYING = "programChannelPlaying";
	// 查询剧照列表
	public static final String PROGRAMPHOTOLIST = "PhotoList";
	// 添加预约定制
	public static final String REMIND_ADD = "remindAdd";
	// 删除预约定制
	public static final String REMIND_DELETE = "remindDelete";
	// 查询磨一时刻预约定制的节目详情
	public static final String REMIND_FIND = "remindList";
	// 明星列表
	public static final String STAR_HOT = "starHot";
	// 明星详情
	public static final String STAR_DETAIL = "starDetail";
	// 当前服务器端频道顺序
	public static final String CHANNEL_ORDER = "channelOrder";
	// 当前节目指南中每个节目的签到数量
	public static final String SIGN_STATUS = "signStatus";
	// 垂直，水平分段查询节目指南
	public static final String PROGRAM_CONTENT = "programContent";
	// 豆瓣评论列表
	public static final String DOUBANCOMMENT = "doubanComment";
	// 节目预播
	public static final String PROGRAMPREVIEW = "PlayProgram";
	// 频道列表
	public static final String CHANNELLIST = "ChannelList";

	// 频道直播节目列表
	public static final String CHANNELLIVEPROGRAM = "ChannelLiveProgramList";
	// 全部节目列表
	public static final String ALLPROGRAMLIST = "AllProgramList";
	// 热搜词
	public static final String HOTKEY = "HotKey";
	// 上传图片
	public static final String UPLOAD = "Upload";
	// 筛选条件
	public static final String FILTERCONDITION = "FilterCondition";
	// 字典数据
	public static final String DICTSELECT = "DictSelect";
	// 节目预播
	public static final String PLAYPROGRAM = "PlayProgram";
	// 评论常用语
	public static final String TALKCOMMON = "TalkCommon";
	// 话题评论列表
	public static final String TOPICCOMMENTLIST = "TopicCommentList";
	// 频道分类列表
	public static final String CHANNELCATEGORYLIST = "ChannelCategoryList";
	// 甩图-上传图片MD5值
	public static final String UPLOADPICMD5 = "ClientAsyncImageUploadRequest";
	// 意见反馈
	public static final Object ADDSUGGEST = "AddSuggest";

	// 甩图-上传图片
	public static final String UPLOADPIC = "AsyncImageUpload";
	// 图片适配器接口 2012-6-21
	public static final String PICADAPTER = "PicAdapter";
	// 打分接口 2012-6-27
	public static final String RATE = "DoProgramRating";
	// 绑定机顶盒
	public static final String BIND = "ClientBindRequest";
	public static final String UNBIND = "ClientUnbind";
	// 删除收藏，单个或全部
	public static final String DELFAV = "DelFavourite";
	// 检测更新
	public static final String CHECKVERSION = "CheckVersion";

	// 评论类型：0-文字、1-图片、2-视频、3-台词
	public static final int TEXT = 0;
	public static final int PIC = 1;
	public static final int VIDEO = 2;
	public static final int ACTORLINE = 3;
	// 评论类型1：0-原创、1-转发
	public static final int ORIGINAL = 0;
	public static final int FORWARD = 1;
	public static final String COMMENT_SOURCE = "电视粉 Android客户端";

	public static final String LR_SAVED_PIC_SDCARD_FOLDER = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator
			+ "iTV+/channelLogos" + File.separator;

	// 图片绝对路径拼接
	// TODO:这个后面需要修改
	public static final String URL_TITLE = "http://172.16.17.83:8088/socialtv";
	// public static final String URL_TITLE_SERVER = "http://www.taoketv.com/";
	// public static final String URL_TITLE_SERVER =
	// "http://192.168.1.22/socialtv";
	// test
	// public static final String URL_TITLE_SERVER_ =
	// "http://172.16.17.82:8180/socialtv";
	// public static final String URL_TITLE_SERVER_1 =
	// "http://192.168.1.22/socialtv";
	public static String URL_TITLE_SERVER;

	// For test
	public static void checkServerIP() {

		String str = CtUserInfo.myServerAddress;
		URL_TITLE_SERVER = str.substring(0, str.length() - 15);

		// if
		// (UserInfo.current().getMyServerAddress().equals("http://192.168.1.22/socialtv/JsonServlet.do")){
		// URL_TITLE_SERVER = "http://192.168.1.22/socialtv";
		// }
		// else if
		// (UserInfo.current().getMyServerAddress().equals("http://172.16.17.82:8180/socialtv/JsonServlet.do")){
		// URL_TITLE_SERVER = "http://172.16.17.82:8180/socialtv";
		// }
		// else if
		// (UserInfo.current().getMyServerAddress().equals("http://172.20.10.3:8088/socialtv/JsonServlet.do")){
		// URL_TITLE_SERVER = "http://172.20.10.3:8088/socialtv";
		// }else if
		// (UserInfo.current().getMyServerAddress().equals("http://172.16.17.82:8080/socialtv/JsonServlet.do")){
		// URL_TITLE_SERVER = "http://172.16.17.82:8080/socialtv";
		// }else if
		// (UserInfo.current().getMyServerAddress().equals("http://192.168.1.100:8088/socialtv/JsonServlet.do")){
		// URL_TITLE_SERVER = "http://192.168.1.100:8088/socialtv";
		// }else if
		// (UserInfo.current().getMyServerAddress().equals("http://192.166.62.134:8080/socialtv/JsonServlet.do")){
		// URL_TITLE_SERVER = "http://192.166.62.134:8080/socialtv";
		// }
		// else if
		// (UserInfo.current().getMyServerAddress().equals("http://211.103.161.157/socialtv/JsonServlet.do")){
		// URL_TITLE_SERVER = "http://211.103.161.157/socialtv/";
		// }
		// else{
		// URL_TITLE_SERVER = "http://192.168.1.22/socialtv";
		// }
	}

	// public static final String URL_TITLE_SERVER =
	// "http://192.168.1.22/socialtv";
	// public static final String URL_TITLE_SERVER =
	// "http://192.168.1.22/socialtv";
	// 杜强笔记本测试用
	// public static final String URL_TITLE_SERVER =
	// "http://172.20.10.3/socialtv";
	public static final String FILE_TYPE = ".jpg";
	public static final String PIC_BIG = "b";
	public static final String PIC_MIDDLE = "m";
	public static final String PIC_SMALL = "s";

	// 代码中自定义的空间ID
	// 最新评论分享界面列表的更新按钮
	public static final int HOTCOMMENT_BTN_REFRESH = 0x11;
	// 最新评论分享界面列表的加载更多按钮
	public static final int HOTCOMMENT_BTN_MORE = 0x12;
	// 用户节目收藏界面列表的更新按钮
	public static final int FAVORITE_BTN_REFRESH = 0x13;
	// 用户节目收藏界面列表的加载更多按钮
	public static final int FAVORITE_BTN_MORE = 0x14;
	// 发表评论，评论列表界面列表的加载更多按钮
	public static final int COMMENTLIST_BTN_MORE = 0x15;
	// 用户个人评论界面列表的更新按钮
	public static final int OWNCOMMENT_BTN_REFRESH = 0x16;
	// 用户个人评论界面列表的加载更多按钮
	public static final int OWNCOMMENT_BTN_MORE = 0x17;
	// 用户黑名单界面的加载更多按钮
	public static final int BLACKLIST_BTN_MORE = 0x18;

	// 签到过的节目详情更新按钮
	public static final int CHECKEDINPD_BTN_REFRESH = 0x19;
	// 签到过的节目详情加载更多按钮
	public static final int CHECKEDINPD_BTN_MORE = 0x20;

	// 签到过的节目更新按钮
	public static final int CHECKEDINP_BTN_REFRESH = 0x21;
	// 签到过的节目加载更多按钮
	public static final int CHECKEDINP_BTN_MORE = 0x22;
	// 私信加载更多
	public static final int PRIVATEMESSAGE_BTN_MORE = 0x23;
	// 明星秀
	public static final int STARLIST_BTN_MORE = 0x24;
	// 发表评论，评论列表界面列表的杀心按钮
	public static final int COMMENTLIST_BTN_REFRESH = 0x25;
	// 节目详情谁在看
	public static final int PDN_WHO_BTN_MORE = 0x26;
	// 节目详情好友
	public static final int PDN_FRIEND_ATTEN_BTN_MORE = 0x27;
	public static final int PDN_FRIEND_FANS_BTN_MORE = 0x28;

	// 布局参数
	public static LayoutParams FWlayoutParams = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.FILL_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);

	public static LayoutParams FFlayoutParams = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.FILL_PARENT,
			LinearLayout.LayoutParams.FILL_PARENT);

	// 搜索类型，1节目，2明星, 3明星和节目
	public static final int ISPROGRAM = 1;
	public static final int ISSTAR = 2;
	public static final int ISPROGRAM_AND_STAR = 3;

	// 用于查询指定节目的全部评论接口
	public static final int NOCOMMENT = 1;
	public static final int ERR = -1;

	public static final String NOCOMMENTNOW = "没有相关评论";
	public static final String NOCOMMENTNOW1 = "还没有人发表评论，快来抢沙发吧！";
	public static final String NOPROGRAMERROR = "查询Topic错误";
	public static final String NOPROGRAMERROR_OUR = "暂无此节目相关信息";
	public static final String NOCUSTOM_ERROR = "25:00";
	public static final String NOCUSTOM_OUR = "此节目无法预约";

	// 星期
	public static final String WEEK_MON = "周一";
	public static final String WEEK_TUES = "周二";
	public static final String WEEK_WED = "周三";
	public static final String WEEK_THUR = "周四";
	public static final String WEEK_FRI = "周五";
	public static final String WEEK_SAT = "周六";
	public static final String WEEK_SUN = "周日";
	public static final String WEEK_MON_HK = "星期一";
	public static final String WEEK_TUES_HK = "星期二";
	public static final String WEEK_WED_HK = "星期三";
	public static final String WEEK_THUR_HK = "星期四";
	public static final String WEEK_FRI_HK = "星期五";
	public static final String WEEK_SAT_HK = "星期六";
	public static final String WEEK_SUN_HK = "星期日";
	public static final String OVER_MONTH_FIRST_DATE = "01";
	public static final int WEEK_MON_ = 1;
	public static final int WEEK_TUES_ = 2;
	public static final int WEEK_WED_ = 3;
	public static final int WEEK_THUR_ = 4;
	public static final int WEEK_FRI_ = 5;
	public static final int WEEK_SAT_ = 6;
	public static final int WEEK_SUN_ = 7;

	// 取节目单的长度参数
	public static final String LENGTH_DAY = "day";
	public static final String LENGTH_WEEK = "week";

	public static final int ISREPLY = 2;
	public static final int ISFORWARD = 1;

	public static final int LIVE = 0;
	public static final int VOD = 1;

	public static final String ADDATTENERROR = "该用户已经被关注";
	public static final String ADDBLACKERROR = "该用户已经在黑名单内";
	public static final String RATE_ERROR = "你已经评过分数";
	public static final String CHECKIN_ERROR = "今天已经看过这个节目";
	public static final String CUSTOM_ERROR = "该节目单已经定制提醒";
	public static final String FAVORITE_ERROR = "已经收藏过该节目";
	public static final String GETPOLL_ERROR = "没有投票数据";
	public static final String GETGUESS_ERROR = "没有竞猜数据";

	public static final String NOSIXIN = "没有私信";
	public static final long PIC_SIZE_LIMITE = 300000;
	public static final int PIC_SIZE_LIMITE_W = 150;
	public static final int PIC_SIZE_LIMITE_H = 150;

	public static final int REMOTE_FROM_WIFI = 1;
	public static final int REMOTE_FROM_BLIETOOTH = 2;

	public static final String SHARE_MESSAGE = "我正在使用\"米花TV\"，可以将大片甩到电视上看了，非常给力，你也试试吧！"
			+ // "下载地址：http://dl.mihua.tv/app/download.html"
			"下载地址：http://dl.mihua.tv/app/mihuaiTV.apk";

	public static final int VIBRATE_PERIOD = 80;
	public static final int NET_TIME_OUT_TIME = 15000;
	public final static float TARGET_HEAP_UTILIZATION = 0.75f;

	public static final String GUIDE_MON_MAP = "guideMonMap";
	public static final String GUIDE_TUE_MAP = "guideTueMap";
	public static final String GUIDE_WED_MAP = "guideWedMap";
	public static final String GUIDE_THUR_MAP = "guideThurMap";
	public static final String GUIDE_FRI_MAP = "guideFriMap";
	public static final String GUIDE_SAT_MAP = "guideSatMap";
	public static final String GUIDE_SUN_MAP = "guideSunMap";

	// 微博类型
	public static final int SINA = 1;
	public static final int TECENT = 2;
	public static final int RENREN = 3;
	public static final int KAIXIN = 4;
	public static final int DOUBAN = 5;

	public static final String LINKBOX_OFF = "机顶盒连接超时，请确定网络连接或机顶盒状态";
	// 机顶盒IP被其他人抢注
	public static final String LINKBOX_ERROR = "无法甩屏，请先解绑后请重新绑定机顶盒";
	public static final String LINKBOX_ERROR_REPEAT = "请稍后重试";
	public static final String DESCRIPTION_FAIL = "获取描述文件失败";
	public static final int NET_BEGIN = 0x1110;
	public static final int NET_END = 0x1111;

	public static final String weiboStr = "我正在使用中国电信iTV+";
	public static final String NEED_RELOGIN = "sessionId为空";
	public static final String ALREADY_ORDERED = "您已订购该内容相关产品，可以直接使用！";
	// 标记机顶盒类型
	// 非iTV机顶盒
	public static final int STB_TYPE_NOT_ITV = 1;
	// iTV机顶盒
	public static final int STB_TYPE_ITV = 2;
}
