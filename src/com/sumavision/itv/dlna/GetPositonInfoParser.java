package com.sumavision.itv.dlna;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sumavision.itv.data.CtProgram;
import com.sumavision.itv.data.DLNAData;
import com.sumavision.itv.data.DLNAGetPositionInfoData;

public class GetPositonInfoParser
{

	public static String letf_tringle = "<";
	public static String right_tringle = ">";

	public static String firstLine = "s:Envelope";
	public static String twoLine = "s:Body";
	public static String threeLine = "u:GetPositionInfoResponse";
	public static String fourLine_1 = "Track";
	public static String fourLine_2 = "TrackDuration";
	public static String fourLine_3 = "TrackMetaData";
	public static String fourLine_4 = "TrackURI";
	public static String fourLine_5 = "RelTime";
	public static String fourLine_6 = "AbsTime";
	public static String fourLine_7 = "RelCount";
	public static String fourLine_8 = "AbsCount";

	public static void parse(InputStream is) throws Exception
	{

		DocumentBuilderFactory docBuilderFactory = null;
		DocumentBuilder docBuilder = null;
		Document doc = null;

		docBuilderFactory = DocumentBuilderFactory.newInstance();
		try
		{
			docBuilder = docBuilderFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}

		doc = docBuilder.parse(is);
		Element messageElement = doc.getDocumentElement();
		NodeList nodeList = messageElement.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++)
		{

			if (nodeList.item(i).getNodeName().equalsIgnoreCase(twoLine))
			{
				Element element = (Element) nodeList.item(i);
				NodeList oelementNodeList = element.getChildNodes();
				for (int j = 0; j < oelementNodeList.getLength(); j++)
				{
					if (oelementNodeList.item(j).getNodeName().equalsIgnoreCase(threeLine))
					{
						Element element_node = (Element) oelementNodeList.item(j);
						NodeList elementNodeList = element_node.getChildNodes();
						for (int l = 0; l < elementNodeList.getLength(); l++)
						{

							DLNAGetPositionInfoData data = new DLNAGetPositionInfoData();
							if (elementNodeList.item(l).getNodeName().equalsIgnoreCase(fourLine_1))
							{
								Element mute = (Element) elementNodeList.item(l);
								data.Track = mute.getFirstChild().getNodeValue();
							}
							else if (elementNodeList.item(l).getNodeName().equalsIgnoreCase(fourLine_2))
							{
								Element mute = (Element) elementNodeList.item(l);
								data.TrackDuration = mute.getFirstChild().getNodeValue();
								// 当前播放节目总时长
								if (!data.TrackDuration.equals("00:00:00"))
								{
									DLNAData.current().nowSTBPlayDuration = data.TrackDuration;
									CtProgram.current().duration = DLNAData.current().nowSTBPlayDuration;
								}
								else
								{
									DLNAData.current().nowSTBPlayDuration = CtProgram.current().durationHoler;
									CtProgram.current().duration = DLNAData.current().nowSTBPlayDuration;
								}
							}
							else if (elementNodeList.item(l).getNodeName().equalsIgnoreCase(fourLine_4))
							{
								Element mute = (Element) elementNodeList.item(l);
								data.TrackURI = mute.getFirstChild().getNodeValue();
							}
							else if (elementNodeList.item(l).getNodeName().equalsIgnoreCase(fourLine_5))
							{
								Element mute = (Element) elementNodeList.item(l);
								data.RelTime = mute.getFirstChild().getNodeValue();
								DLNAData.current().nowSTBPlayPosition = data.RelTime;
							}
							else if (elementNodeList.item(l).getNodeName().equalsIgnoreCase(fourLine_6))
							{
								Element mute = (Element) elementNodeList.item(l);
								data.AbsTime = mute.getFirstChild().getNodeValue();

								DLNAData.current().nowSTBLivePlayPosition = data.AbsTime;
							}
							else
							{
							}

							DLNAData.current().data = data;
						}
					}
				}
			}
		}
	}

}
