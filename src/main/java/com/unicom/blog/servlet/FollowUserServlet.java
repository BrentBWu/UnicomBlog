package com.unicom.blog.servlet;

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

/**
 *关注用户
 * @author 王思诚
 */
@WebServlet(name = "FollowUserServlet")
public class FollowUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        User user;
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");

        if(uid < 1) {
            //UID错误,正常不会到这
            request.setAttribute("respDesc", "非法操作");
            //System.out.println("非法操作");
            //跳转首页
            request.getRequestDispatcher("#").forward(request, response);
        } else if(user == null || user.getUid() < 1) {
            //用户未登录
            request.setAttribute("respDesc", "用户未登录");
            //System.out.println("用户未登录");
            //跳转登陆改界面
            request.getRequestDispatcher("#").forward(request, response);
        } else {
            Result<Boolean> result = UserService.followUser(user.getUid(), uid);
            if(result.getRespData()) {
                //关注成功
                request.setAttribute("respDesc", "关注成功");
                // System.out.println("关注成功");
                //跳转原来页面
                request.getRequestDispatcher("#").forward(request, response);
            } else {
                //关注失败
                request.setAttribute("respDesc", "关注失败");
                // System.out.println("关注失败");
                //跳转原来页面
                request.getRequestDispatcher("#").forward(request, response);
            }
        }

    }
}
