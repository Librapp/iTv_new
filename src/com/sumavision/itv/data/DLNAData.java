package com.sumavision.itv.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Service;
import org.cybergarage.xml.ParserException;

import com.sumavision.itv.dlna.AllUrlParser;

import android.util.Log;

public class DLNAData
{

	private static DLNAData current;

	public Service CM;
	public Service AVT;
	public Service RCS;
	public Service XCTC;
	// RCS
	private Action getAllowedTransforms;
	private Action getVolume;
	private Action setVolume;
	private Action getMute;
	private Action setMute;
	// CM
	private Action getProtocolInfo;
	private Action prepareForConnection;
	private Action connectionComplete;
	// AVT
	private Action setTransportURL;
	private Action play;
	private Action pause;
	private Action stop;
	private Action seek;
	private Action getPositionInfo;
	// X-CTC
	private Action getProductInfo;
	private Action order;

	private List<ProductInfo> lp;
	// 连接ID
	public int AVTransportID = 0;
	// 设置过TranspotURI
	public boolean hasSetTransportURI = false;

	// 当前的SOAP Action头
	public String SOAP_Action_name;
	// 当前的Action消息体
	public String SOAP_Action_body;
	// 当前的Action全部头
	public String SOAP_Action_header;
	// 当前的Action全部头名字列表
	public List<String> SOAP_Action_header_names = new ArrayList<String>();
	// 当前的Action全部头内容列表
	public List<String> SOAP_Action_header_values = new ArrayList<String>();
	// 当前链接的设备端口
	public int SOAP_Port;
	// 当前链接的设备地址
	public String SOAP_Address;

	// 当前静音参数
	public String CurrentMute;
	// 当前静音参数
	public String CurrentVolume = "";
	// 当前TranportInfo参数,PLAYING or not
	public String CurrentTransportState;
	// 当前TranportInfo参数,OK or not
	public String CurrentTransportStatus;
	// 当前TranportInfo参数,当前速度，默认均为1
	public String CurrentSpeed;

	// GetPositionInfo
	public DLNAGetPositionInfoData data;
	// 当前DLNA get指令类型
	public int DLNA_get_type;
	public int DLNA_post_type;
	int type = -1;
	public static final int GET_RCS = 1;
	public static final int GET_AVT = 2;
	public static final int GET_XCTC = 3;
	private HttpGet get;
	private String url = "";

	// RCS
	public static final int GET_MUTE_TYPE = 1;
	public static final int GET_VOLUME_TYPE = 2;
	// AVT
	public static final int GET_POSITION_INFO_TYPE = 3;
	public static final int GET_TRANSPORT_INFO_TYPE = 4;
	public static final int GET_MEDIA_INFO_TYPE = 5;

	// 实例化动作
	public boolean initActionDone = false;
	// 上次甩到电视上的节目
	public int prevProgramID = 0;
	// 上次甩到电视上的节目得到子ID，如电视剧类型的子集，子集的集数
	public String prevProgramSubID;
	// 上次甩到电视上的节目子集列表
	public List<ProgramEpisodeData> prevListEpisodes;
	// 上次甩到电视上的节目的类型，0直播，1点播
	public String prevProgramType;
	// 上次甩到电视上的节目的名字
	public String prevProgramName = "未知节目";
	// 上次甩到电视上的节目的方图地址
	public String prevProgramUrlSQ;
	// 上次甩到电视上的节目的播放地址
	public String prevProgramPlayUrl;
	// 上次甩到电视上的节目的是否有子集
	public boolean prevProgramHasEpisode = false;
	// 当前集数
	public int prevCurrentEpisode = 0;
	// 上次甩到电视上的节目的总时长
	public String prevProgramDuration;
	// 上次甩到电视后，返回的四叶草时保存的节目播放位置，毫秒值
	public int prevProgramPlayingPosition;
	// 上次甩到电视后，返回的四叶草时保存的系统毫秒数值
	public long prevProgramSystemMillions;
	// 是否已经甩过界面到电视
	public boolean hasPlayingOnTV = false;
	// 上次退出时机顶盒状态
	public boolean isPlayingOnTV = false;
	// 是否为子集播放
	public boolean isEpisodePlaying = false;
	// 甩之前的播放时间
	public String playedTimeText;
	// 上次甩到电视上的节目的播放状态：常速1，快进8，快退-8
	public int prevProgramPlaySpeed = 1;
	// <!-----------------仅用于setPositionInfo接口------------------------->
	// 当前播放位置：点播
	public String nowSTBPlayPosition = "00:00:00";
	// 当前播放位置：直播
	public String nowSTBLivePlayPosition = "00:00:00";
	// 当前播放时长
	public String nowSTBPlayDuration = "00:00:00";
	// 当前播放中的视频URL
	public String nowSTBPlayURL = "";

	// <!-----------------仅用于甩图到电视------------------------->
	// 图片上传成功
	public boolean hasPicAlready = false;
	// 图片已存在
	public boolean isExistPic = false;
	// 从预播界面甩到播控界面
	public boolean isFromPreView2Control = false;
	// 搜索设备时服务器返回408code
	public boolean isGoted408Code = false;
	// 进入时记录，与上次甩到电视的是否为同一个节目
	public boolean isStopAlready = false;

	// Play后，甩屏时，未订购节目的errorcode，需要订购
	public static final int NEEED_BUY_PROGRAM = 724;
	// Play后，鉴权出错
	public static final int FORBIDDEN_PROGRAM = 721;
	// GetProductInfo后，查询订购信息出错
	public final int GET_PRODUCT_ERROR_CODE = 716;
	// Order后，订购出错
	public final int ORDER_ERROR_CODE = 801;
	// Order后，订购出错
	public final int ORDER_ERROR_CODE_1 = 401;
	// Order后，订购成功
	public final int ORDER_OK_CODE = 0;
	// 节目对应的订购产品列表
	private List<X_CTC_Product> Products;
	// 节目播放返回结果
	public int PlayResultCode = 0;
	public String PlayResultMsg = "";
	// 节目需要订购
	public boolean isNeedBuyProgram = false;

	// 取得节目订购信息播放返回结果
	public int GetProductInfoResultCode = 0;
	public String GetProductInfoResultMsg = "";

	// 关闭Debug
	public boolean isCloseDebug = true;
	// 断点甩屏,需要seek
	public boolean needSeek = false;

	// //////////////////////////////////////仅用于直播甩屏
	// 上次退出直播甩屏界面时的播放位置，此位置为相对于2小时的相对位置，即positionSeekBarAbsPosition
	public int prevLivePlayPosition = 0;
	// 当前直播状态，0播放，8快进，-8快退, 1暂停
	public int prevLiveFastPlaySatus = 0;
	// 当前直播状态，0播放，1暂停
	public int prevLivePlaySatus = 0;
	// 退出瞬间的时间戳
	public long prevLivePlayTimeStamp = 0;
	// 上次甩节目是否为直播回看
	public boolean prevIsLiveBackView = false;

	// 当前节目与之前甩的节目非同一节目
	public boolean isNotSameProgram = true;
	// 甩图页直接拍照
	public boolean shotPicFromShuaiGallery = false;

	// 订购错误码
	public int orderErrorCode = 0;
	// 从电视页面甩屏成功，正在播放节目均无图片
	public boolean formTVStation2M2TVDontHasPic = false;

	public List<X_CTC_Product> getProducts()
	{

		return Products;
	}

	public void setProducts(List<X_CTC_Product> products)
	{

		Products = products;
	}

	public void initDlnaService()
	{

		type = GET_RCS;
		getService();
	}

	// 过滤SOAP_Address中起始时的两个斜杠
	public String filterDoubleLine(String input)
	{

		String out = input;
		String tmp = input;
		char[] tmps = tmp.toCharArray();
		if (tmps[0] == '/' && tmps[1] == '/')
		{
			out = input.substring(1);
		}

		return out;
	}

	private void getService()
	{

		JSONMessageType.checkServerIP();
		url = JSONMessageType.URL_TITLE_SERVER + "/StbProxy.do";
		get = new HttpGet(url);
		get.addHeader("Ais-EquipMac", CtUserInfo.mac);
		get.addHeader("Ais-EquipName", CtUserInfo.equipName);
		get.addHeader("Ais-MsgType", "Ais-UPnP-Discription");
		get.addHeader("Ais-STBID", CtUserInfo.stbId);
		get.addHeader("Ais-OrigPort", CtUserInfo.SOAP_Port + "");
		switch (type)
		{
			case GET_AVT:
				get.addHeader("Ais-OrigPath", AllUrlParser.checkDeviceURL(AVT.getSCPDURL(), true));
				break;
			case GET_RCS:
				get.addHeader("Ais-OrigPath", AllUrlParser.checkDeviceURL(RCS.getSCPDURL(), true));
				break;
			case GET_XCTC:
				try
				{
					get.addHeader("Ais-OrigPath", AllUrlParser.checkDeviceURL(XCTC.getSCPDURL(), true));
				}
				catch (NullPointerException e)
				{
					e.printStackTrace();
					XCTC = DeviceData.getInstance().getSelectedDevice().getService("urn:schemas-upnp-org:service:X-CTC_Subscribe:1");
					get.addHeader("Ais-OrigPath", AllUrlParser.checkDeviceURL(XCTC.getSCPDURL(), true));
				}
				break;
			default:
				break;
		}

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{

				get.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
				// 网络超时
				HttpParams params = get.getParams();
				int timeoutConnection = JSONMessageType.NET_TIME_OUT_TIME;
				HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
				int timeoutSocket = JSONMessageType.NET_TIME_OUT_TIME;
				HttpConnectionParams.setSoTimeout(params, timeoutSocket);
				try
				{
					HttpClient client = new DefaultHttpClient();
					get.setParams(params);
					if (type == GET_XCTC)
					{
					}
					client.execute(get, responseHandler);
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}).start();
	}

	// 重试取得XCTC服务次数为
	private int getXCTCCount = 0;
	protected ResponseHandler<String> responseHandler = new ResponseHandler<String>()
	{

		@Override
		public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException
		{

			if (response.getStatusLine().getStatusCode() == 404)
			{
			}
			else if (response.getStatusLine().getStatusCode() == 200)
			{
				try
				{
					switch (type)
					{
						case GET_RCS:
							current().RCS.loadSCPD(response.getEntity().getContent());
							type = GET_AVT;
							getService();
							break;
						case GET_AVT:
							current().AVT.loadSCPD(response.getEntity().getContent());

							try
							{
								XCTC = DeviceData.getInstance().getSelectedDevice().getService("urn:schemas-upnp-org:service:X-CTC_Subscribe:1");
							}
							catch (NullPointerException e)
							{
								e.printStackTrace();
							}
							if (XCTC != null)
							{
								type = GET_XCTC;
								getService();
							}
							else
							{

								if (getXCTCCount < 5)
								{
									getXCTCCount++;
									XCTC = DeviceData.getInstance().getSelectedDevice().getService("urn:schemas-upnp-org:service:X-CTC_Subscribe:1");
								}
								else
								{
									Log.e("service", "无XCTC");
									initDlnaAction();
								}

							}
							break;
						case GET_XCTC:
							processXCTCXML();
							initDlnaAction();
							break;
						default:
							break;
					}
				}
				catch (IllegalStateException e)
				{
					e.printStackTrace();
				}
				catch (ParserException e)
				{
					e.printStackTrace();
				}
			}
			return null;
		}
	};

	public void initDlnaAction()
	{

		setVolume = current().RCS.getAction("SetVolume");
		getVolume = current().RCS.getAction("GetVolume");
		setMute = current().RCS.getAction("SetMute");
		getMute = current().RCS.getAction("GetMute");

		setTransportURL = current().AVT.getAction("SetAVTransportURI");
		seek = current().AVT.getAction("Seek");
		stop = current().AVT.getAction("Stop");
		play = current().AVT.getAction("Play");
		pause = current().AVT.getAction("Pause");
		getPositionInfo = current().AVT.getAction("GetPositionInfo");
		try
		{
			getProductInfo = current().XCTC.getAction("GetProductInfo");
			order = current().XCTC.getAction("Order");
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		current().initActionDone = true;
	}

	public Action getGetAllowedTransforms()
	{

		if (getAllowedTransforms == null)
		{}
		return getAllowedTransforms;
	}

	public void setGetAllowedTransforms(Action getAllowedTransforms)
	{

		this.getAllowedTransforms = getAllowedTransforms;
	}

	public Action getGetVolume()
	{

		if (getVolume == null)
		{
			getVolume = current().RCS.getAction("GetVolume");
		}
		return getVolume;
	}

	public void setGetVolume(Action getVolume)
	{

		this.getVolume = getVolume;
	}

	public Action getSetVolume()
	{

		if (setVolume == null)
		{
			setVolume = current().RCS.getAction("SetVolume");
		}
		return setVolume;
	}

	public void setSetVolume(Action setVolume)
	{

		this.setVolume = setVolume;
	}

	public Action getGetMute()
	{

		return getMute;
	}

	public void setGetMute(Action getMute)
	{

		this.getMute = getMute;
	}

	public Action getSetMute()
	{

		return setMute;
	}

	public void setSetMute(Action setMute)
	{

		this.setMute = setMute;
	}

	public Action getGetProtocolInfo()
	{

		return getProtocolInfo;
	}

	public void setGetProtocolInfo(Action getProtocolInfo)
	{

		this.getProtocolInfo = getProtocolInfo;
	}

	public Action getPrepareForConnection()
	{

		return prepareForConnection;
	}

	public void setPrepareForConnection(Action prepareForConnection)
	{

		this.prepareForConnection = prepareForConnection;
	}

	public Action getConnectionComplete()
	{

		return connectionComplete;
	}

	public void setConnectionComplete(Action connectionComplete)
	{

		this.connectionComplete = connectionComplete;
	}

	public Action getSetTransportURL()
	{

		if (setTransportURL == null)
		{
			setTransportURL = current().AVT.getAction("SetAVTransportURL");
		}
		return setTransportURL;
	}

	public void setSetTransportURL(Action setTransportURL)
	{

		this.setTransportURL = setTransportURL;
	}

	public Action getPlay()
	{

		if (play == null)
		{
			play = current().AVT.getAction("Play");
		}
		return play;
	}

	public void setPlay(Action play)
	{

		this.play = play;
	}

	public Action getPause()
	{

		if (pause == null)
		{
			pause = current().AVT.getAction("Pause");
		}
		return pause;
	}

	public void setPause(Action pause)
	{

		this.pause = pause;
	}

	public Action getStop()
	{

		if (stop == null)
		{
			try
			{
				stop = current().AVT.getAction("Stop");
			}
			catch (NullPointerException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return stop;
	}

	public void setStop(Action stop)
	{

		this.stop = stop;
	}

	public Action getSeek()
	{

		if (seek == null)
		{
			seek = current().AVT.getAction("Seek");
		}
		return seek;
	}

	public void setSeek(Action seek)
	{

		this.seek = seek;
	}

	public Action getGetPositionInfo()
	{

		return getPositionInfo;
	}

	public void setGetPositionInfo(Action getPositionInfo)
	{

		this.getPositionInfo = getPositionInfo;
	}

	public void setGetProductInfo(Action getProductInfo)
	{

		this.getProductInfo = getProductInfo;
	}

	public Action getGetProductInfo()
	{

		if (getProductInfo == null)
		{
			getProductInfo = current().XCTC.getAction("getProductInfo");
		}
		return getProductInfo;
	}

	public void setOrder(Action order)
	{

		this.order = order;
	}

	public Action getOrder()
	{

		if (order == null)
		{
			order = current().XCTC.getAction("Order");
		}
		return order;
	}

	public static DLNAData current()
	{

		if (current == null)
		{
			current = new DLNAData();
		}
		return current;
	}

	public void setLp(List<ProductInfo> lp)
	{

		this.lp = lp;
	}

	public List<ProductInfo> getLp()
	{

		return lp;
	}

	/**
	 * 将String转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream StringTOInputStream(String in) throws Exception
	{

		ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("ISO-8859-1"));
		return is;
	}

	private String X_CTC_SCPD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<scpd xmlns=\"urn:schemas-upnp-org:service-1-0\">" + "<specVersion>" + " <major>1</major>" + "<minor>0</minor>" + "</specVersion>" + "<actionList>"
			+ "<action>" + "<name>GetProductInfo</name>" + "<argumentList>" + "<argument>" + "<name>CurrentURI</name>" + " <direction>in</direction>" + "<relatedStateVariable>AVTransportURI</relatedStateVariable>" + "</argument>"
			+ "<argument>" + " <name>Products</name>" + "<direction>out</direction>" + "<relatedStateVariable>A_ARG_TYPE_Products</relatedStateVariable>" + "</argument>" + "</argumentList>" + "</action>" + "<action>" + "<name>Order</name>"
			+ "<argumentList>" + "<argument>" + "<name>Action</name>" + "<direction>in</direction>" + "<relatedStateVariable>PurchaseAction</relatedStateVariable>" + "</argument>" + "<argument>" + "<name>ProductId</name>"
			+ "<direction>in</direction>" + "<relatedStateVariable>ProductId</relatedStateVariable>" + "</argument>" + "<argument>" + "<name>ServiceId</name>" + "<direction>in</direction>"
			+ "<relatedStateVariable>ServiceId</relatedStateVariable>" + "</argument>" + "<argument>" + "<name>ContentId</name>" + "<direction>in</direction>" + "<relatedStateVariable>ContentId</relatedStateVariable>" + "</argument>"
			+ "<argument>" + "<name>ColumnId</name>" + "<direction>in</direction>" + "<relatedStateVariable>ColumnId</relatedStateVariable>" + "</argument>" + "<argument>" + "<name>PurchaseType</name>" + "<direction>in</direction>"
			+ "<relatedStateVariable>PurchaseType</relatedStateVariable>" + "</argument>" + "<argument>" + "<name>ContentType</name>" + "<direction>in</direction>" + "<relatedStateVariable>ContentType</relatedStateVariable>"
			+ "</argument>" + "<argument>" + "<name>AutoRenewal</name>" + "<direction>in</direction>" + "<relatedStateVariable>AutoRenewal</relatedStateVariable>" + "</argument>" + "</argumentList>" + "</action>" + "</actionList>"
			+ "<serviceStateTable>" + "<stateVariable" + " sendEvents=\"no\">" + "<name>A_ARG_TYPE_Products</name>" + "<dataType type=\"urn:CTC:X-CTC_Subscribe:Products\">string</dataType>" + "</stateVariable>"
			+ "<stateVariable sendEvents=\"no\">" + "<name>PurchaseAction</name>" + "<dataType>int</dataType>" + "<defaultValue>1</defaultValue>" + "<allowedValueList>" + "<allowedValue>1</allowedValue>" + "<allowedValue>2</allowedValue>"
			+ "<allowedValue>3</allowedValue>" + "</allowedValueList>" + "</stateVariable>" + "<stateVariable" + " " + "sendEvents=\"no\">" + "<name>AVTransportURI</name>" + "<dataType>string</dataType>" + "</stateVariable>"
			+ "<stateVariable sendEvents=\"no\">" + "<name>ProductId</name>" + "<dataType>string</dataType>" + "</stateVariable>" + "<stateVariable sendEvents=\"no\">" + "<name>ServiceId</name>" + "<dataType>string</dataType>"
			+ "</stateVariable>" + "<stateVariable sendEvents=\"no\">" + "<name>ContentId</name>" + "<dataType>string</dataType>" + "</stateVariable>" + "<stateVariable sendEvents=\"no\">" + "<name>ColumnId</name>"
			+ "<dataType>string</dataType>" + "</stateVariable>" + "<stateVariable sendEvents=\"no\">" + "<name>PurchaseType</name>" + "<dataType>int</dataType>" + "<defaultValue>0</defaultValue>" + "<allowedValueList>"
			+ "<allowedValue>0</allowedValue>" + "<allowedValue>1</allowedValue>" + "<allowedValue>2</allowedValue>" + "<allowedValue>3</allowedValue>" + "</allowedValueList>" + "</stateVariable>" + "<stateVariable sendEvents=\"no\">"
			+ "<name>ContentType</name>" + "<dataType>int</dataType>" + "<defaultValue>1</defaultValue>" + "<allowedValueList>" + "<allowedValue>1</allowedValue>" + "<allowedValue>2</allowedValue>" + "<allowedValue>10</allowedValue>"
			+ "<allowedValue>14</allowedValue>" + "</allowedValueList>" + "</stateVariable>" + "<stateVariable sendEvents=\"no\">" + "<name>AutoRenewal</name>" + "<dataType>boolean</dataType>" + "<defaultValue>true</defaultValue>"
			+ "</stateVariable>" + "<stateVariable sendEvents=\"no\">" + "<name>IsFree</name>" + "<dataType>boolean</dataType>" + "<defaultValue>false</defaultValue>" + "</stateVariable>" + "<stateVariable sendEvents=\"no\">"
			+ "<name>ProductPrice</name>" + "<dataType>int</dataType>" + "</stateVariable>" + "<stateVariable sendEvents=\"no\">" + "<name>ExpireTime</name>" + "<dataType>dateTime</dataType>" + "</stateVariable>"
			+ "<stateVariable sendEvents=\"no\">" + "<name>RentalTerm</name>" + "<dataType>int</dataType>" + "</stateVariable>" + "<stateVariable sendEvents=\"no\">" + "<name>LimitedTimes</name>" + "<dataType>int</dataType>"
			+ "</stateVariable>" + "</serviceStateTable>" + "</scpd>";

	private void processXCTCXML()
	{

		try
		{
			current().XCTC.loadSCPD(StringTOInputStream(X_CTC_SCPD));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void clearMe()
	{

		if (CM != null)
			CM = null;

		AVT = null;
		RCS = null;
		XCTC = null;
		// RCS
		getAllowedTransforms = null;
		getVolume = null;
		setVolume = null;
		getMute = null;
		setMute = null;
		// CM
		getProtocolInfo = null;
		prepareForConnection = null;
		connectionComplete = null;
		// AVT
		setTransportURL = null;
		play = null;
		pause = null;
		stop = null;
		seek = null;
		getPositionInfo = null;
		// X-CTC
		getProductInfo = null;
		order = null;

		data = null;

		if (lp != null)
			lp.clear();
		lp = null;

		if (get != null)
			get.abort();
		get = null;

		if (Products != null)
			Products.clear();
		Products = null;

		if (SOAP_Action_header_names != null)
			SOAP_Action_header_names.clear();
		SOAP_Action_header_names = null;

		if (SOAP_Action_header_values != null)
			SOAP_Action_header_values.clear();
		SOAP_Action_header_values = null;

		if (current != null)
		{
			current = null;
		}
		System.gc();
	}
}
