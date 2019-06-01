package com.unicom.blog.beans;

import java.util.Date;

/**
 *博客点赞表
 */
public class BlogLike {

    private int lid;//点赞标识
    private int bid;//被赞文章
    private int uid;//点赞人
    private Date createDate;//创建时间
    private char sts;//启用标识

    public BlogLike() {}

    public BlogLike(int lid, int bid, int uid, Date createDate, char sts) {
        this.lid = lid;
        this.bid = bid;
        this.uid = uid;
        this.createDate = createDate;
        this.sts = sts;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public char getSts() {
        return sts;
    }

    public void setSts(char sts) {
        this.sts = sts;
    }
}
