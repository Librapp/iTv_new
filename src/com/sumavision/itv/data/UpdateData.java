package com.sumavision.itv.data;

/**
 * 
 * @author 郭鹏
 * @createTime
 * @description 自动更新实体类
 * @changeLog
 * 
 */
public class UpdateData {

	private static UpdateData instance;
	// 本地程序版本
	public String versionCodeNow;
	// 服务器端程序版本
	public String versionCodeServer;
	public String versionMini;
	// 程序更新细节
	public String updateDetail;
	public String fileSize;
	// 程序下载地址
	public String appDownURL;
	// 程序更新时间
	public String uploadDate;
	// 是否需要更新程序
	public boolean isNeedUpdateApp = false;
	public int mustUpdate = 0;

	public static UpdateData current() {
		if (instance == null) {
			instance = new UpdateData();
		}
		return instance;
	}
}
