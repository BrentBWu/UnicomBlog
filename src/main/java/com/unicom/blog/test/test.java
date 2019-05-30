package com.unicom.blog.test;

import com.unicom.blog.beans.User;
import com.unicom.blog.dao.UserDao;
import com.unicom.blog.service.UserService;

public class test {
    public static void main(String[] args) {
       // System.out.println();
        //UserService.userLogin("aa","11");
        User user=new User(-1,"ffff","333",null,'N',"ffff","jpg0");
        User user1=new User(-1,"dddd","333",null,'N',"dddd","jpg1");
        UserDao.userRegister(user);
        UserDao.userRegister(user1);

        User user2=user;
        String newIcon = "png";
        user2.setHead_icon(newIcon);
        UserDao.userIconModify(user2);

    }
}
