package com.sumavision.itv.data;

/**
 * 
 * @author 李梦思
 * @description 评论实体类
 */
public class CtComment {
	private static CtComment current;

	public int status;
	public int phraseCount;
	public int topicId;
	public int talkId;
	public int userId;
	public boolean isChosen = false;
	public String name;
	public String userPhoto;
	public String content;
	// 发表时间
	public String time;
	// 评论来源
	public String source;

	public String picAllName = "";
	public String picAllNameLogo = "";

	// 反馈Id
	public int replySuggestId;
	// 反馈回复Id
	public int suggestReplyId;
	/**
	 * 是否是自己发的
	 */
	public int myReply;

	public static CtComment current() {
		if (current == null) {
			current = new CtComment();
		}
		return current;
	}

	public void clearMe() {
		if (current != null) {
			current = null;
		}
		System.gc();
	}
}
