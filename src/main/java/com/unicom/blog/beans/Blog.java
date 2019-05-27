package com.unicom.blog.beans;

import java.util.Date;

/**
 * 博客
 */
public class Blog {
    private int bid;//文章标识
    private int uid;//文章作者
    private String title;//文章标题
    private Date createTime;//发表时间
    private Date updateTime;//修改时间
    private String content;//文章内容
    private char sts;//启用标识
    private int commentsCount;//评论数
    private int likesCount;//点赞数
    private int collectionCount;//收藏数

    public Blog(){

    }
    public Blog(int bid, int uid, String title, Date createTime, Date updateTime, String content, char sts, int commentsCount, int likesCount, int collectionCount) {
        this.bid = bid;
        this.uid = uid;
        this.title = title;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.content = content;
        this.sts = sts;
        this.commentsCount = commentsCount;
        this.likesCount = likesCount;
        this.collectionCount = collectionCount;
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

    public char getSts() {
        return sts;
    }

    public void setSts(char sts) {
        this.sts = sts;
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
}
