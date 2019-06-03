package com.unicom.blog.beans;

import java.util.Date;

/**
 * 博客评论
 * @author Xueting Ou
 */
public class BlogComments {

    private int cid;//评论标识
    private int bid;//文章标识
    private int uid;//评论人
    private String content;//评论内容
    private Date createDate;//评论时间
    private int floorNum;//评论楼层
    private char sts;//启用标识

    public BlogComments() {

    }

    public BlogComments(int cid, int bid, int uid, String content, Date createDate, int floorNum, char sts) {
        this.cid = cid;
        this.bid = bid;
        this.uid = uid;
        this.content = content;
        this.createDate = createDate;
        this.floorNum = floorNum;
        this.sts = sts;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    public char getSts() {
        return sts;
    }

    public void setSts(char sts) {
        this.sts = sts;
    }
}
