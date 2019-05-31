package com.unicom.blog.test;

import com.unicom.blog.beans.User;
import com.unicom.blog.dao.UserDao;
import com.unicom.blog.service.UserService;

public class test {
    public static void main(String[] args) {
       // System.out.println();
        //UserService.userLogin("aa","11");
//        User user=new User(-1,"cc","333",null,'N',"昵称","toux");
//        UserDao.userRegister(user);
        UserService.followUserList(2);

    }
}
