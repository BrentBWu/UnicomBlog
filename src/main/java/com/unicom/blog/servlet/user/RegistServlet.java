package com.unicom.blog.servlet.user;

import com.unicom.blog.beans.Result;
import com.unicom.blog.beans.User;
import com.unicom.blog.service.UserService;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *注册
 * @author 王思诚
 */

public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //System.out.println("正在注册");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String nickname=request.getParameter("nickname");
        User user;
        //检测用户名重复
        System.out.println("检测用户名重复");
        Result<Boolean> result= UserService.userNameCkeck(username);
        Result<Boolean> result2= null;
        PrintWriter out = response.getWriter();
        if(username.length()<6 || username.length()>12){
            //用户名长度不符
            request.setAttribute("respDesc","用户名长度不符");
            System.out.println("用户名长度不符");
            //跳注册
            //request.getRequestDispatcher("#").forward(request,response);
            out.write("用户名长度不符");
            out.flush();
            out.close();

        }else if(password.length()<6 || password.length()>20){
            //密码长度不符
            request.setAttribute("respDesc","密码长度不符");
            System.out.println("密码长度不符");
            //跳注册
            //request.getRequestDispatcher("#").forward(request,response);
            out.write("密码长度不符");
            out.flush();
            out.close();
        }else if(!result.getRespData()){
            //用户名重复
            request.setAttribute("respDesc","用户名重复");
            System.out.println("用户名重复");
            //跳注册
            //request.getRequestDispatcher("#").forward(request,response);
            out.write("用户名重复");
            out.flush();
            out.close();

        }else{
            //进行注册
            user=new User(-1,username,password,null,'N',nickname,"head");
            result2=UserService.userRegister(user);
            if(result2.getRespCode().equals("0000")){
                //注册成功
                System.out.println("注册成功");
                //跳登陆
                //request.getRequestDispatcher("index.jsp").forward(request,response);
                out.write("注册成功，请登陆");
                out.flush();
                out.close();
            }else{
                //注册失败
                request.setAttribute("respDesc","注册失败");
                System.out.println("注册失败");
                //跳注册
                //request.getRequestDispatcher("#").forward(request,response);
                out.write("网络异常，请重新注册");
                out.flush();
                out.close();
            }
        }
    }
}
