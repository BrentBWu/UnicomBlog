package com.unicom.blog.servlet.user;

import com.unicom.blog.beans.Result;
import com.unicom.blog.beans.User;
import com.unicom.blog.service.UserService;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *登陆
 * @author 王思诚
 */
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Result<User> result= UserService.userLogin(username,password);
        User user;
        HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        if(result.getRespCode().equals("0000")){
            //登陆成功
            user=result.getRespData();
            session.setAttribute("user", user);
            //跳首页
            //request.getRequestDispatcher("myblog.jsp").forward(request,response);
            out.write("登陆成功");
            out.flush();
            out.close();
        }else{
            //登陆失败
           // request.setAttribute("respDesc","用户名或密码错误");
            //跳登陆
           // request.getRequestDispatcher("index.jsp").forward(request,response);
            out.write("用户名或密码错误");
            out.flush();
            out.close();
        }
    }
}
