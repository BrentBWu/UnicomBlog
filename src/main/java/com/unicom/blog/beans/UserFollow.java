package com.unicom.blog.beans;

import java.util.Date;

/**
 *用户关注表
 * @author Xueting Ou
 */
public class UserFollow {

    private int fid;//关注标识
    private int uid;//用户标识
    private int followedUid;//被关注人标识
    private Date createTime;//创建时间
    private char sts;//启用标识

    public UserFollow() {}

    public UserFollow(int fid, int uid, int followedUid, Date createTime, char sts) {
        this.fid = fid;
        this.uid = uid;
        this.followedUid = followedUid;
        this.createTime = createTime;
        this.sts = sts;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFollowedUid() {
        return followedUid;
    }

    public void setFollowedUid(int followedUid) {
        this.followedUid = followedUid;
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
