package com.kkk26kkk.bbs.model;

public class UserFollowVo {
	private String followeeId;
	private String followerId;
	
	public UserFollowVo() {}
	
	public String getFolloweeId() {
		return followeeId;
	}
	public void setFolloweeId(String followeeId) {
		this.followeeId = followeeId;
	}
	public String getFollowerId() {
		return followerId;
	}
	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}
}
