package com.unicom.blog.VO;

import java.util.Date;
import java.util.List;
/**
 * 博客列表VO
 * @author Administrator
 *
 */
public class UserBlogVo {
	
	private int bid;//文章标识
	private int uid;//文章作者
    private String title;//文章标题
    private Date createTime;//发表时间
    private Date updateTime;//修改时间
    private String content;//文章内容
    private int commentsCount;//评论数
    private int likesCount;//点赞数
    private int collectionCount;//收藏数
    private String nickName;//用户名
    private String head_icon;//用户头像
    private List<UserCommentVo> userCommentVoList; // 文章评论
    
	public List<UserCommentVo> getUserCommentVoList() {
		return userCommentVoList;
	}
	public void setUserCommentVoList(List<UserCommentVo> userCommentVoList) {
		this.userCommentVoList = userCommentVoList;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
	public int getCollectionCount() {
		return collectionCount;
	}
	public void setCollectionCount(int collectionCount) {
		this.collectionCount = collectionCount;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHead_icon() {
		return head_icon;
	}
	public void setHead_icon(String head_icon) {
		this.head_icon = head_icon;
	}
	@Override
	public String toString() {
		return "UserBlogVo [uid=" + uid + ", title=" + title + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", content=" + content + ", commentsCount=" + commentsCount + ", likesCount="
				+ likesCount + ", collectionCount=" + collectionCount + ", nickName=" + nickName + ", head_icon="
				+ head_icon + ", userCommentVoList=" + userCommentVoList + ", bid=" + bid + "]";
	}
	
}
