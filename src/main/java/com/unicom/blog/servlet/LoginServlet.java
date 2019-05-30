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
 *登陆
 * @author 王思诚
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Result<User> result= UserService.userLogin(username,password);
        User user;
        HttpSession session=request.getSession();
        if(result.getRespCode().equals("0000")){
            //登陆成功
            user=result.getRespData();
            session.setAttribute("user", user);
            //跳首页
            request.getRequestDispatcher("#").forward(request,response);
        }else{
            //登陆失败
            request.setAttribute("respDesc","用户名或密码错误");
            //跳登陆
            request.getRequestDispatcher("#").forward(request,response);
        }
    }
}
