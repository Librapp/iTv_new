package com.sumavision.itv.dlna;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sumavision.itv.data.DLNAData;
import com.sumavision.itv.data.X_CTC_OrderData;
import com.sumavision.itv.data.X_CTC_Product;


/**
 * 
 * @author 李梦思
 * @description 解析GetProductInfoParser参数
 * @changlog 郭鹏 at 20121123
 * 
 * 
 *          1. 成功
 *           <s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/"
 *           s:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
 *           <s:Body> <u:GetProductInfoResponse
 *           xmlns:u="urn:schemas-upnp-org:service:X-CTC_Subscribe:1"> <Products
 *           SOAP-ENC:arrayType="Product[1]"> <Product>
 *           <ProductId>ppv001</ProductId>
 *           <ContentId>00000000020000000009</ContentId>
 *           <ServiceId>ppv</ServiceId> <ColumnId></ColumnId>
 *           <ProductName>ppv01</ProductName> <ProductDesc></ProductDesc>
 *           <Type>3</Type> <Price>0</Price> <Expires></Expires>
 *           <RentalTerm>60</RentalTerm> <Times>0</Times> <IsFree></IsFree>
 *           </Product> </Products> </u:GetProductInfoResponse> </s:Body>
 *           </s:Envelope>
 * 
 *          2. 出错
 *          <s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/" s:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
 *           <s:Body>
 *           <s:Fault>
 *           <faultcode>s:Client</faultcode>
 *           <faultstring>UPnPError</faultstring>
 *           <detail>
 *          <UPnPError xmlns="urn:schemas-upnp-org:control-1-0">
 *          <errorCode>716</errorCode>
 *          <errorDescription>GetProductInfo Fail</errorDescription>
 *          </UPnPError>
 *           </detail>
 *           </s:Fault>
 *           </s:Body>
 *           </s:Envelope>
 *
 * 
 */
public class GetProductInfoParser
{

	public static String letf_tringle = "<";
	public static String right_tringle = ">";
	// 成功
	public static String ENVELOPE = "s:Envelope";
	public static String BODY = "s:Body";
	public static String GET_PRODUCT_INFO_RESPONSE = "u:GetProductInfoResponse";
	public static String PRODUCTS = "Products";
	public static String PRODUCT = "Product";
	public static String PRODUCT_ID = "ProductId";
	public static String SERVICE_ID = "ServiceId";
	public static String CONTENT_ID = "ContentId";
	public static String COLUMN_ID = "ColumnId";
	public static String PRODUCT_NAME = "ProductName";
	public static String PRODUCT_DESC = "ProductDesc";
	public static String TYPE = "Type";
	public static String PRICE = "Price";
	public static String EXPIRES = "ExpireTime";
	public static String RENTAL_TERM = "RentalTerm";
	public static String TIMES = "Times";
	public static String IS_FREE = "IsFree";

	// 出错
	public static String FAULT = "s:Fault";
	public static String DETAIL = "detail";
	public static String UPNP_ERROR = "UPnPError";
	public static String ERROR_DESCRIPTION = "errorDescription";
	public static String ERROR_CODE = "errorCode";
	public static String FAULT_CODE = "faultcode";
	public static String FAULT_STRING = "faultstring";

	public static void parseNew(InputStream is) throws Exception
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

		// try {
		doc = docBuilder.parse(is);
		Element messageElement = doc.getDocumentElement();
		NodeList nodeList = messageElement.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++)
		{

			if (nodeList.item(i).getNodeName().equalsIgnoreCase(BODY))
			{
				Element element = (Element) nodeList.item(i);
				NodeList oelementNodeList = element.getChildNodes();
				for (int j = 0; j < oelementNodeList.getLength(); j++)
				{

					// 订购成功
					if (oelementNodeList.item(j).getNodeName().equalsIgnoreCase(GET_PRODUCT_INFO_RESPONSE))
					{
						Element element_node = (Element) oelementNodeList.item(j);
						NodeList elementNodeList = element_node.getChildNodes();
						for (int l = 0; l < elementNodeList.getLength(); l++)
						{

							if (elementNodeList.item(l).getNodeName().equalsIgnoreCase(PRODUCTS))
							{
								Element mute = (Element) elementNodeList.item(l);
								NodeList elementNodeList1 = mute.getChildNodes();

								List<X_CTC_Product> lp = new ArrayList<X_CTC_Product>();

								for (int ll = 0; ll < elementNodeList1.getLength(); ll++)
								{
									X_CTC_Product p = new X_CTC_Product();

									if (elementNodeList1.item(ll).getNodeName().equalsIgnoreCase(PRODUCT))
									{
										X_CTC_OrderData.current().isOrderOk = false;
										if (!p.ProductId.equals(""))
										{
											lp.add(p);
										}

										Element mute1 = (Element) elementNodeList1.item(ll);
										NodeList elementNodeList2 = mute1.getChildNodes();

										for (int lll = 0; lll < elementNodeList2.getLength(); lll++)
										{

											// ProductId
											if (elementNodeList2.item(lll).getNodeName().equalsIgnoreCase(PRODUCT_ID))
											{
												Element p_id = (Element) elementNodeList2.item(lll);
												try
												{
													p.ProductId = p_id.getFirstChild().getNodeValue();
												}
												catch (NullPointerException e)
												{
													e.printStackTrace();
													p.ProductId = "";
												}

											}
											// ServiceId
											else if (elementNodeList2.item(lll).getNodeName().equalsIgnoreCase(SERVICE_ID))
											{
												Element p_id = (Element) elementNodeList2.item(lll);
												try
												{
													p.ServiceId = p_id.getFirstChild().getNodeValue();
												}
												catch (NullPointerException e)
												{
													e.printStackTrace();
													p.ServiceId = "";
												}
											}
											// ContentId
											else if (elementNodeList2.item(lll).getNodeName().equalsIgnoreCase(CONTENT_ID))
											{
												Element p_id = (Element) elementNodeList2.item(lll);
												try
												{
													p.ContentId = p_id.getFirstChild().getNodeValue();
												}
												catch (NullPointerException e)
												{
													e.printStackTrace();
													p.ContentId = "";
												}
											}

											// ProductName
											else if (elementNodeList2.item(lll).getNodeName().equalsIgnoreCase(PRODUCT_NAME))
											{
												Element p_id = (Element) elementNodeList2.item(lll);
												try
												{
													p.ProductName = p_id.getFirstChild().getNodeValue();
												}
												catch (NullPointerException e)
												{
													e.printStackTrace();
													p.ProductName = "";
												}
											}


											// TYPE
											else if (elementNodeList2.item(lll).getNodeName().equalsIgnoreCase(TYPE))
											{
												Element p_id = (Element) elementNodeList2.item(lll);
												try
												{
													p.PurchaseType = p_id.getFirstChild().getNodeValue();
												}
												catch (NullPointerException e)
												{
													e.printStackTrace();
													p.PurchaseType = "";
												}
											}

											// PRICE
											else if (elementNodeList2.item(lll).getNodeName().equalsIgnoreCase(PRICE))
											{
												Element p_id = (Element) elementNodeList2.item(lll);
												try
												{
													p.Price = p_id.getFirstChild().getNodeValue();
												}
												catch (NullPointerException e)
												{
													e.printStackTrace();
													p.Price = "";
												}
											}

											else if (elementNodeList2.item(lll).getNodeName().equalsIgnoreCase(EXPIRES))
											{
												Element p_id = (Element) elementNodeList2.item(lll);
												try
												{
													p.Expires = p_id.getFirstChild().getNodeValue();
												}
												catch (NullPointerException e)
												{
													e.printStackTrace();
													p.Expires = "";
												}
											}

											// RentalTerm
											else if (elementNodeList2.item(lll).getNodeName().equalsIgnoreCase(RENTAL_TERM))
											{
												Element p_id = (Element) elementNodeList2.item(lll);
												try
												{
													p.RentalTerm = p_id.getFirstChild().getNodeValue();
												}
												catch (NullPointerException e)
												{
													e.printStackTrace();
													p.RentalTerm = "";
												}
											}

											// Times
											else if (elementNodeList2.item(lll).getNodeName().equalsIgnoreCase(TIMES))
											{
												Element p_id = (Element) elementNodeList2.item(lll);
												try
												{
													p.Times = p_id.getFirstChild().getNodeValue();
												}
												catch (NullPointerException e)
												{
													e.printStackTrace();
													p.Times = "";
												}

												lp.add(p);
												DLNAData.current().setProducts(lp);
											}

										}

									}
								}
								DLNAData.current().setProducts(lp);
								DLNAData.current().GetProductInfoResultCode = 0;
							}
						}
					}
					// 订购失败
					else if (oelementNodeList.item(j).getNodeName().equalsIgnoreCase(FAULT))
					{
						Element element_node = (Element) oelementNodeList.item(j);
						NodeList elementNodeList = element_node.getChildNodes();

						for (int ii = 0; ii < elementNodeList.getLength(); ii++)
						{
							if (elementNodeList.item(ii).getNodeName().equalsIgnoreCase(DETAIL))
							{

								Element element_node1 = (Element) elementNodeList.item(ii);
								NodeList elementNodeList1 = element_node1.getChildNodes();

								for (int iii = 0; iii < elementNodeList1.getLength(); iii++)
								{
									if (elementNodeList1.item(iii).getNodeName().equalsIgnoreCase(UPNP_ERROR))
									{
										Element element_node2 = (Element) elementNodeList1.item(iii);
										NodeList elementNodeList2 = element_node2.getChildNodes();

										for (int iiii = 0; iiii < elementNodeList2.getLength(); iiii++)
										{
											if (elementNodeList2.item(iiii).getNodeName().equalsIgnoreCase(ERROR_CODE))
											{
												Element mute = (Element) elementNodeList2.item(iiii);
												DLNAData.current().GetProductInfoResultCode = Integer.parseInt(mute.getFirstChild().getNodeValue());

											}
											else if (elementNodeList2.item(iiii).getNodeName().equalsIgnoreCase(ERROR_DESCRIPTION))
											{
												Element mute = (Element) elementNodeList2.item(iiii);

												DLNAData.current().GetProductInfoResultMsg = mute.getFirstChild().getNodeValue();
											}
										}

									}

								}

							}
						}

					}
				}
			}
		}
	}


}
