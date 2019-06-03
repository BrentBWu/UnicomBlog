package com.unicom.blog.beans;

import java.util.Date;

/**
 * 用户
 * @author Xueting Ou
 */
public class User {

    private int uid;//用户标识
    private String userName;//用户名
    private String userPassword;//密码
    private Date createTime;//创建时间
    private char sts;//启用标识
    private String nickName;//用户昵称
    private String head_icon;//用户头像

    public User() {

    }

    public User(int uid, String userName, String userPassword, Date createTime, char sts, String nickName,
            String head_icon) {
        this.uid = uid;
        this.userName = userName;
        this.userPassword = userPassword;
        this.createTime = createTime;
        this.sts = sts;
        this.nickName = nickName;
        this.head_icon = head_icon;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setSts(char sts) {
        this.sts = sts;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public char getSts() {
        return sts;
    }

    public String getNickName() {
        return nickName;
    }

    public String getHead_icon() {
        return head_icon;
    }
}
