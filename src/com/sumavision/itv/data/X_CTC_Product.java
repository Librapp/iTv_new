package com.sumavision.itv.data;

/**
 * 
 * @author 郭鹏
 * @CreateTime 2012-11-22
 * @description GetProductInfo返回的某一产品对象
 * 
 *              返回值 返回值意义 UPnP对应错误描述 716 获取对应产品失败 获取产品失败的详细错误描述文本
 * 
 */
public class X_CTC_Product {

	private static X_CTC_Product current;
	// 订购的唯一产品ID.
	public String ProductId = "";
	// 节目服务ID (GetProductInfo API返回)
	public String ServiceId = "";
	// 节目内容ID(GetProductInfo API返回)
	public String ContentId = "";
	// 节目所在栏目ID (GetProductInfo API返回)
	public String ColumnId = "";
	// 产品名字
	public String ProductName = "订购包";
	// 产品描述
	public String ProductDesc = "订购业务";
	// 购买类型
	public String PurchaseType = "";
	// 产品价格，单位￥¥
	public String Price = "";
	// 有效时间
	public String Expires = "72";
	// RentalTerm CM （对按时间ippv必选）
	public String RentalTerm = "";
	// LimitedTimes CM （对按次ipptv必选）
	public String Times = "";
	// IsFree O (默认为0， 不免费)
	public String IsFree = "";

	public static X_CTC_Product current() {
		if (current == null) {
			current = new X_CTC_Product();
		}
		return current;
	}
}
