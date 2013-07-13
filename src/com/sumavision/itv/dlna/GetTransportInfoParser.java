package com.sumavision.itv.dlna;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sumavision.itv.data.DLNAData;

public class GetTransportInfoParser
{

	public static String letf_tringle = "<";
	public static String right_tringle = ">";

	public static String firstLine = "s:Envelope";
	public static String twoLine = "s:Body";
	public static String threeLine = "u:GetTransportInfoResponse";
	public static String fourLine_1 = "CurrentTransportState";
	public static String fourLine_2 = "CurrentTransportStatus";
	public static String fourLine_3 = "CurrentSpeed";

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

							if (elementNodeList.item(l).getNodeName().equalsIgnoreCase(fourLine_1))
							{
								Element mute = (Element) elementNodeList.item(l);
								DLNAData.current().CurrentTransportState = mute.getFirstChild().getNodeValue();
							}
							else if (elementNodeList.item(l).getNodeName().equalsIgnoreCase(fourLine_2))
							{
								Element mute = (Element) elementNodeList.item(l);
								DLNAData.current().CurrentTransportStatus = mute.getFirstChild().getNodeValue();
							}
							else if (elementNodeList.item(l).getNodeName().equalsIgnoreCase(fourLine_3))
							{
								Element mute = (Element) elementNodeList.item(l);
								DLNAData.current().CurrentSpeed = mute.getFirstChild().getNodeValue();
							}
						}
					}
				}
			}
		}
	}

}
