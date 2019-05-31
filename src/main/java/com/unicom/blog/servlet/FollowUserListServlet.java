package com.unicom.blog.servlet;
/**
 *获取关注用户列表
 * @author 王思诚
 */
import com.unicom.blog.beans.Result;
import com.unicom.blog.beans.User;
import com.unicom.blog.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FollowUserListServlet")
public class FollowUserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        HttpSession session=request.getSession();
        user=(User)session.getAttribute("user");

        if(user ==null || user.getUid()<1){
            //用户未登录
            request.setAttribute("respDesc","用户未登录");
            //System.out.println("用户未登录");
            //跳转登陆改界面
            request.getRequestDispatcher("#").forward(request,response);
        }else{
            Result<List<User>> result= UserService.followUserList(user.getUid());
            List<User> followUserList=result.getRespData();
            request.setAttribute("respDesc","获取关注列表成功");
            request.setAttribute("followUserList","followUserList");
            //跳转原来页面
            request.getRequestDispatcher("#").forward(request,response);
        }


    }
}
