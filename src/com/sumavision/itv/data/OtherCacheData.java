package com.sumavision.itv.data;

import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;

public class OtherCacheData
{

	private static OtherCacheData current;
	public String scanTVResultString = "";
	// 是否为调试模式
	public boolean isDebugMod = false;

	public static OtherCacheData current()
	{

		if (current == null)
		{
			current = new OtherCacheData();
		}
		return current;
	}

	public boolean isGridShow = true;
	// 当前城市
	public String currentCity = "泉州";
	// public String currentCity = "北京";
	public boolean isLowAbilityDevice = false;
	public Bitmap uploadPic;

	// 网络异常
	public boolean dontHasNet = false;

	// 筛选一级菜单
	public Map<String, List<CtFilterCondition>> firstTypeM;
	public Map<String, List<CtFilterCondition>> firstTypeY;
	public List<CtFilterCondition> firstType;
	// 地区分类
	public List<CtFilterCondition> areaN;
	public List<CtProgram> favoriteP;

	// 搜索结果列表count
	public int searchResultCount = 0;
	// 是否在友盟接口中添加stbAccounts字段
	public boolean hasStbAccountsForUMeng = true;
	// 是否需要关闭侧拉
	public boolean needCloseSide = false;
	// 是否为回甩
	public boolean isBackToPhonePlay = false;

	public static void clearMe()
	{

		if (current != null)
		{
			current = null;
		}
		System.gc();
	}
}
