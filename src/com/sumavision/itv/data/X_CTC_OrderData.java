package com.sumavision.itv.data;

import android.util.Log;

/**
 * 
 * @author 郭鹏
 * @CreateTime 2012-11-22
 * @description 订购或退订一个产品
 * 
 * Action[IN]: PurchaseAction类型，表示需要执行的操作 （订购，退订，续订） ProductId
 * [IN]: 订购的唯一产品ID. ServiceId [IN]: 节目服务ID (GetProductInfo API返回)
 * ContentId[IN]: 节目内容ID(GetProductInfo API返回) ColumnId[IN]:
 * 节目所在栏目ID (GetProductInfo API返回) PurchaseType[IN]: 购买类型
 * ContentType[IN]: 订购内容类型。 AutoRenewal[IN]:
 * 是否自动续订，可选，仅针对包月产品（PurchaseType = 0）
 * 
 * 返回值 返回值意义 UPnP对应错误描述 801 产品订购失败 订购失败的详细文本描述
 * 
 * 
 */
public class X_CTC_OrderData
{

	private static X_CTC_OrderData current;
	// 表示需要执行的操作 （订购，退订，续订）
	public String PurchaseAction;
	// 订购的唯一产品ID.
	public String ProductId;
	// 节目服务ID (GetProductInfo API返回)
	public String ServiceId;
	// 节目内容ID(GetProductInfo API返回)
	public String ContentId;
	// 节目所在栏目ID (GetProductInfo API返回)
	public String ColumnId;
	// 购买类型
	public String PurchaseType;
	// 订购内容类型，// 如果isAbstractSeries为0， contentTpe==14; 如果不为0，contentTpe==1;
	public String ContentType;
	// 是否自动续订，可选，仅针对包月产品（PurchaseType = 0，是不续定）
	public String AutoRenewal = "0";
	// 产品名字
	public String ProductName = "订购";
	// 产品价格
	public String ProductPrice = "20";

	// 订购结果
	public boolean isOrderOk = true;;
	// 订购出错的错误代码
	public String errCode = "801";
	// 订购出错的错误消息文本
	public String errMsg = "";

	public static X_CTC_OrderData current()
	{

		if (current == null)
		{
			current = new X_CTC_OrderData();
		}
		return current;
	}

	public void initLocalData(int i)
	{

		if (DLNAData.current().getProducts() != null)
		{

			Log.e("X_CTC_OrderData-initLocalData", DLNAData.current().getProducts().size() + "");
			X_CTC_OrderData.current().ProductId = DLNAData.current().getProducts().get(i).ProductId;
			X_CTC_OrderData.current().ContentId = DLNAData.current().getProducts().get(i).ContentId;
			X_CTC_OrderData.current().ServiceId = DLNAData.current().getProducts().get(i).ServiceId;
			X_CTC_OrderData.current().ColumnId = DLNAData.current().getProducts().get(i).ColumnId;
			X_CTC_OrderData.current().PurchaseType = DLNAData.current().getProducts().get(i).PurchaseType;
			X_CTC_OrderData.current().ProductName = DLNAData.current().getProducts().get(i).ColumnId;
			X_CTC_OrderData.current().ProductPrice = DLNAData.current().getProducts().get(i).Price;
			X_CTC_OrderData.current().AutoRenewal = "";

			Log.e("initLocalData", DLNAData.current().getProducts().get(0).ProductId);
		}
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
