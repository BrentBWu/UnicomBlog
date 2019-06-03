package com.unicom.blog.beans;

import java.util.Date;

/**
 *博客收藏表
 * @author Xueting Ou
 */
public class BlogCollections {

    private int cid;//收藏标识
    private int uid;//收藏用户
    private int bid;//收藏文章
    private Date createTime;//创建时间
    private char sts;//启用标识

    public BlogCollections() {}

    public BlogCollections(int cid, int uid, int bid, Date createTime, char sts) {
        this.cid = cid;
        this.uid = uid;
        this.bid = bid;
        this.createTime = createTime;
        this.sts = sts;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public char getSts() {
        return sts;
    }

    public void setSts(char sts) {
        this.sts = sts;
    }
}
