package com.sumavision.itv.engine;

import org.cybergarage.http.HTTPNetURG;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.CtUpnpSender;
import org.cybergarage.upnp.control.ActionRequest;

import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.data.DLNAData;
import com.sumavision.itv.data.DeviceData;
import com.sumavision.itv.data.X_CTC_OrderData;
import com.sumavision.itv.dlna.AllUrlParser;
import com.sumavision.itv.listener.UpnpGetListener;
import com.sumavision.itv.listener.UpnpSetListener;

import android.content.Context;

/**
 * 
 * @author yanzhidan
 * @createtime 2013-4-18
 *
 */
public class DlnaManager
{
	public static void dlnaSetTransportUrl(Context context, String currentUri)
	{
		Action setTransportURL = DLNAData.current().getSetTransportURL();

		ArgumentList sal = setTransportURL.getArgumentList();
		sal.getArgument("InstanceID").setValue("0");
		if (DeviceData.getInstance().getSelectedDevice().getFriendlyName().equals("Realtek Embedded UPnP Render()"))
		{

			sal.getArgument("CurrentURIMetaData").setValue(
					//不要骂我，不是我写的~
					"<DIDL-Lite " + "xmlns=\"urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/\" " + "xmlns:dc=\"http://purl.org/dc/elements/1.1/\" " + "xmlns:upnp=\"urn:schemas-upnp-org:metadata-1-0/upnp/\" "
							+ "xmlns:dlna=\"urn:schemas-dlna-org:metadata-1-0/\"" + ">" + "<item><dc:title>" + "video"
							+ "</dc:title><upnp:class>object.item.videoItem</upnp:class><res protocolInfo=\"http-get:*:video/mp4:*\"></res></item></DIDL-Lite>");
		}
		else if (DeviceData.getInstance().getSelectedDevice().getFriendlyName().equals("DaPingMu(Q-1000DF)"))
		{
			sal.getArgument("CurrentURIMetaData").setValue("");
		}
		else
		{
			sal.getArgument("CurrentURIMetaData").setValue(
					//不要骂我，不是我写的~
					"<DIDL-Lite " + "xmlns=\"urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/\" " + "xmlns:dc=\"http://purl.org/dc/elements/1.1/\" " + "xmlns:upnp=\"urn:schemas-upnp-org:metadata-1-0/upnp/\" "
							+ "xmlns:dlna=\"urn:schemas-dlna-org:metadata-1-0/\"" + ">" + "<item>" + "<dc:title>" + "video" + "</dc:title>" + "<upnp:class>object.item.videoItem</upnp:class>"
							+ "<res protocolInfo=\"http-get:*:video/mp4:*\"></res>" + "</item></DIDL-Lite>");
		}
		
		sal.getArgument("CurrentURI").setValue(currentUri);
		setTransportURL.setInArgumentValues(sal);
		ActionRequest ctrlReq = new ActionRequest();
		if (CtUserInfo.remote)
		{
			ctrlReq.setRequest(setTransportURL, sal);
			CtUserInfo.SOAP_Address = AllUrlParser.checkDeviceURL(DLNAData.current().AVT.getControlURL(), false);
			remotePost(context, ctrlReq, ItvEngine.EVENT_DLNA_ACTION_SETTRANSPORTURL);
		}
	}
	
	public static void dlnaPlay(Context context, int speed)
	{
		Action play = DLNAData.current().getPlay();
		ArgumentList pauseal = play.getArgumentList();
		pauseal.getArgument("InstanceID").setValue("0");
		pauseal.getArgument("Speed").setValue(String.valueOf(speed));
		play.setInArgumentValues(pauseal);
		if (CtUserInfo.remote)
		{
			ActionRequest ctrlReq = new ActionRequest();
			ctrlReq.setRequest(play, pauseal);
			CtUserInfo.SOAP_Address = AllUrlParser.checkDeviceURL(DLNAData.current().AVT.getControlURL(), false);
			remotePost(context, ctrlReq, ItvEngine.EVENT_DLNA_ACTION_PLAY);
		}
	}
	
	
	public static void dlnaPause(Context context)
	{
		Action pause = DLNAData.current().getPause();
		ArgumentList pauseal = pause.getArgumentList();
		pauseal.getArgument("InstanceID").setValue("0");
		pause.setInArgumentValues(pauseal);
		if (CtUserInfo.remote)
		{
			ActionRequest ctrlReq = new ActionRequest();
			ctrlReq.setRequest(pause, pauseal);
			CtUserInfo.SOAP_Address = AllUrlParser.checkDeviceURL(DLNAData.current().AVT.getControlURL(), false);
			remotePost(context, ctrlReq, ItvEngine.EVENT_DLNA_ACTION_PAUSE);
		}
		
	}

	public static void dlnaStop(Context context)
	{
		Action stop = DLNAData.current().getStop();
		ArgumentList stopal = stop.getArgumentList();
		stopal.getArgument("InstanceID").setValue("0");
		stop.setInArgumentValues(stopal);
		if (CtUserInfo.remote)
		{
			ActionRequest ctrlReq = new ActionRequest();
			ctrlReq.setRequest(stop, stopal);
			CtUserInfo.SOAP_Address = AllUrlParser.checkDeviceURL(DLNAData.current().AVT.getControlURL(), false);
			remotePost(context, ctrlReq, ItvEngine.EVENT_DLNA_ACTION_STOP);
		}
	}
	
	/**
	 * @param context
	 * @param mute 0/1 是否静音
	 */
	public static void dlnaSetMute(Context context, int mute)
	{

		Action setMute = DLNAData.current().getSetMute();
		ArgumentList setal = setMute.getArgumentList();
		// 暂时一直为0
		setal.getArgument("InstanceID").setValue(0);
		setal.getArgument("Channel").setValue("Master");
		setal.getArgument("DesiredMute").setValue(mute);
		setMute.setInArgumentValues(setal);
		if (CtUserInfo.remote)
		{
			ActionRequest ctrlReq = new ActionRequest();
			ctrlReq.setRequest(setMute, setal);
			CtUserInfo.SOAP_Address = AllUrlParser.checkDeviceURL(DLNAData.current().RCS.getControlURL(), false);
			remotePost(context, ctrlReq, ItvEngine.EVENT_DLNA_ACTION_SETMUTE);
		}
	}
	
	public static void dlnaSetVolmue(Context context, int vol)
	{
		Action setVolume = DLNAData.current().getSetVolume();
		ArgumentList setal = setVolume.getArgumentList();
		setal.getArgument("InstanceID").setValue("0");
		setal.getArgument("DesiredVolume").setValue(vol);
		setal.getArgument("Channel").setValue("Master");
		setVolume.setInArgumentValues(setal);
		if (CtUserInfo.remote)
		{
			ActionRequest ctrlReq = new ActionRequest();
			ctrlReq.setRequest(setVolume, setal);
			CtUserInfo.SOAP_Address = AllUrlParser.checkDeviceURL(DLNAData.current().RCS.getControlURL(), false);

			remotePost(context, ctrlReq, ItvEngine.EVENT_DLNA_ACTION_SETVOLMUE);
		}
	}
	
	public static void dlnaSeek(Context context, String seekTime)
	{
		Action seek = DLNAData.current().getSeek();
		ArgumentList seekal = seek.getArgumentList();
		seekal.getArgument("Unit").setValue("ABS_TIME");
		seekal.getArgument("Target").setValue(seekTime);

		seekal.getArgument("InstanceID").setValue("0");
		seek.setInArgumentValues(seekal);
		if (CtUserInfo.remote)
		{
			ActionRequest ctrlReq = new ActionRequest();
			ctrlReq.setRequest(seek, seekal);
			CtUserInfo.SOAP_Address = DLNAData.current().AVT.getControlURL();
			remotePost(context, ctrlReq, ItvEngine.EVENT_DLNA_ACTION_SEEK);
		}
	}
	
	public static void dlnaOrder(Context context, int contentType)
	{
		Action order = DLNAData.current().getOrder();
		ArgumentList getal = order.getArgumentList();
		
		getal.getArgument("Action").setValue("1");
		getal.getArgument("ProductId").setValue(X_CTC_OrderData.current().ProductId);
		getal.getArgument("ContentId").setValue(X_CTC_OrderData.current().ContentId);
		getal.getArgument("ServiceId").setValue(X_CTC_OrderData.current().ServiceId);
		getal.getArgument("ColumnId").setValue(X_CTC_OrderData.current().ColumnId);
		getal.getArgument("PurchaseType").setValue(X_CTC_OrderData.current().PurchaseType);
		getal.getArgument("ContentType").setValue(contentType);
		getal.getArgument("AutoRenewal").setValue(X_CTC_OrderData.current().AutoRenewal + "");
		order.setArgumentList(getal);

		if (CtUserInfo.remote)
		{
			ActionRequest ctrlReq = new ActionRequest();
			ctrlReq.setRequest(order, getal);
			CtUserInfo.SOAP_Address = AllUrlParser.checkDeviceURL(DLNAData.current().XCTC.getControlURL(), false);
			remotePost(context, ctrlReq, ItvEngine.EVENT_DLNA_ACTION_ORDER);
		}
	}
	
	public static void dlnaGetMute()
	{
		String letf_tringle = "<";
		String right_tringle = ">";
		
		//不要骂我，不是我写的~
		DLNAData.current().SOAP_Action_body = letf_tringle + "?xml version=\"1.0\" encoding=\"utf-8\"?" + right_tringle + letf_tringle + "s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" "
				+ "s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"" + right_tringle + letf_tringle + "s:Body" + right_tringle + letf_tringle + "u:GetMute xmlns:u=\"urn:schemas-upnp-org:service:RenderingControl:1\""
				+ right_tringle + letf_tringle + "InstanceID" + right_tringle + "0" + letf_tringle + "/InstanceID" + right_tringle + letf_tringle + "Channel" + right_tringle + "Master" + letf_tringle + "/Channel" + right_tringle
				+ letf_tringle + "/u:GetMute" + right_tringle + letf_tringle + "/s:Body" + right_tringle + letf_tringle + "/s:Envelope" + right_tringle;
		if (CtUserInfo.remote)
		{
			remoteGet(ItvEngine.EVENT_DLNA_ACTION_GETMUTE);
		}
	}
	
	public static void dlnaGetPositionInfo()
	{
		String letf_tringle = "<";
		String right_tringle = ">";
		
		//不要骂我，不是我写的~
		DLNAData.current().SOAP_Action_body = letf_tringle + "?xml version=\"1.0\" encoding=\"utf-8\"?" + right_tringle + letf_tringle + "s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" "
				+ "s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"" + right_tringle + letf_tringle + "s:Body" + right_tringle + letf_tringle + "u:GetPositionInfo xmlns:u=\"urn:schemas-upnp-org:service:AVTransport:1\""
				+ right_tringle + letf_tringle + "InstanceID" + right_tringle + "0" + letf_tringle + "/InstanceID" + right_tringle + letf_tringle + "/u:GetPositionInfo" + right_tringle + letf_tringle + "/s:Body" + right_tringle
				+ letf_tringle + "/s:Envelope" + right_tringle;
		if (CtUserInfo.remote)
		{
			remoteGet(ItvEngine.EVENT_DLNA_ACTION_GETPOSITIONINFO);
		}
	}
	
	public static void dlnaGetTranSportInfo()
	{
		String letf_tringle = "<";
		String right_tringle = ">";
		DLNAData.current().SOAP_Action_body = letf_tringle + "?xml version=\"1.0\" encoding=\"utf-8\"?" + right_tringle + letf_tringle + "s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" "
				+ "s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"" + right_tringle + letf_tringle + "s:Body" + right_tringle + letf_tringle + "u:GetTransportInfo xmlns:u=\"urn:schemas-upnp-org:service:AVTransport:1\""
				+ right_tringle + letf_tringle + "InstanceID" + right_tringle + "0" + letf_tringle + "/InstanceID" + right_tringle + letf_tringle + "/u:GetTransportInfo" + right_tringle + letf_tringle + "/s:Body" + right_tringle
				+ letf_tringle + "/s:Envelope" + right_tringle;
		if (CtUserInfo.remote)
		{
			remoteGet(ItvEngine.EVENT_DLNA_ACTION_GETTRANSPORINFO);
		}
	}
	
	public static void dlnaGetVolume()
	{

		String letf_tringle = "<";
		String right_tringle = ">";

		DLNAData.current().SOAP_Action_body = letf_tringle + "?xml version=\"1.0\" encoding=\"utf-8\"?" + right_tringle + letf_tringle + "s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" "
				+ "s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"" + right_tringle + letf_tringle + "s:Body" + right_tringle + letf_tringle + "u:GetVolume xmlns:u=\"urn:schemas-upnp-org:service:RenderingControl:1\""
				+ right_tringle + letf_tringle + "InstanceID" + right_tringle + "0" + letf_tringle + "/InstanceID" + right_tringle + letf_tringle + "Channel" + right_tringle + "Master" + letf_tringle + "/Channel" + right_tringle
				+ letf_tringle + "/u:GetVolume" + right_tringle + letf_tringle + "/s:Body" + right_tringle + letf_tringle + "/s:Envelope" + right_tringle;
		if (CtUserInfo.remote)
		{
			remoteGet(ItvEngine.EVENT_DLNA_ACTION_GETVOLUME);
		}
	}
	
	public static void dlnaGetProductInfo(Context context, String currentUri)
	{

		Action getProductInfo = DLNAData.current().getGetProductInfo();
		ArgumentList getal = getProductInfo.getArgumentList();
		try
		{
			//存疑
			if (getal.size() > 1)
				getal.remove(1);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
		
		getal.getArgument("CurrentURI").setValue(currentUri);
		getProductInfo.setArgumentList(getal);
		if (CtUserInfo.remote)
		{
			ActionRequest ctrlReq = new ActionRequest();
			ctrlReq.setRequest(getProductInfo, getal);
			CtUserInfo.SOAP_Address = AllUrlParser.checkDeviceURL(DLNAData.current().XCTC.getControlURL(), false);
			remotePost(context, ctrlReq, ItvEngine.EVENT_DLNA_ACTION_GETPRODUCTINFO);
		}
	}
	
	private static void remotePost(Context context, ActionRequest ctrlReq, int actionId)
	{
		CtUpnpSender upnp;
		try
		{
			upnp = new CtUpnpSender();
			upnp.setUpnpListener(new UpnpSetListener(actionId) , context);
			upnp.request(ctrlReq, false);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			ItvEngine.sendMessage(actionId, ItvEngine.EVENT_ERROR, actionId + " event failed");
		}
	}
	
	private static void remoteGet(int actionId)
	{
		HTTPNetURG netUrg;
		try
		{
			netUrg = new HTTPNetURG();
			netUrg.setListener(new UpnpGetListener(actionId));
			netUrg.request(DLNAData.current().SOAP_Action_body);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ItvEngine.sendMessage(actionId, ItvEngine.EVENT_ERROR, actionId + " event failed");
		}
	}
	
}
