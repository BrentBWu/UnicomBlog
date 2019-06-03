package com.unicom.blog.VO;

import java.util.Date;

/**
 * 文章评论VO
 * @author Administrator
 *
 */
public class UserCommentVO {

    private int uid;//用户标识
    private String nickName;//用户昵称
    private String head_icon;//用户头像
    private String content;//评论内容
    private Date createDate;//评论时间
    private int floorNum;//评论楼层	

    public UserCommentVO(int uid, String nickName, String head_icon, String content, Date createDate, int floorNum) {
        this.uid = uid;
        this.nickName = nickName;
        this.head_icon = head_icon;
        this.content = content;
        this.createDate = createDate;
        this.floorNum = floorNum;

    }

    public UserCommentVO() {

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
}
