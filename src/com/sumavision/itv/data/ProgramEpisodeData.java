package com.sumavision.itv.data;

/**
 * 
 * @author 郭鹏
 * @description 电视剧类节目的子集
 * 
 */
public class ProgramEpisodeData {

	private static ProgramEpisodeData current;

	// 当前集数
	public String episodeNumber = "-1";

	public String episodeName;
	// 当前季
	public String seasonNumber;
	// 点播地址
	public String VODAdrress;
	// 甩屏地址
	public String GoTVAdrress;
	// 片花甩屏地址
	public String GoTVPrevueAdrress;
	// 时长
	public String duration;
	// 是否为子集点播
	public boolean hasEpisode = false;
	// 是否被选中
	public boolean hasFocus = false;

	public static ProgramEpisodeData current() {
		if (current == null) {
			current = new ProgramEpisodeData();
		}
		return current;
	}

}
