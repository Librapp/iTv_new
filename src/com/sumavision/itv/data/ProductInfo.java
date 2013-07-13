package com.sumavision.itv.data;

public class ProductInfo {
	public String productId;
	public String contentId;
	public String serviceId;
	public String columnId;
	public String productName;
	public String productDesc;
	// 3是单片，0包月
	public int purchaseType;
	public String price;
	public boolean isFree;
	public int contentType;
	public boolean autoRenewal;

	private static ProductInfo current;

	public static ProductInfo current() {
		if (current == null) {
			current = new ProductInfo();
		}
		return current;
	}
}
