package com.unicom.blog.servlet.user;

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
import java.io.PrintWriter;

/**
 *用户信息修改Servlet
 * @author 王思诚
 */
@WebServlet(name = "UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String oldPassword=request.getParameter("oldPassword");
        String password1=request.getParameter("password1");
        String password2=request.getParameter("password2");
        String nickname=request.getParameter("nickname");
        User user;
        HttpSession session=request.getSession();
        user=(User)session.getAttribute("user");
        PrintWriter out = response.getWriter();

        if(user ==null || user.getUid()<1){
            //用户未登录
            //request.setAttribute("respDesc","用户未登录");
            //System.out.println("用户未登录");
            //跳转登陆改界面
            //request.getRequestDispatcher("#").forward(request,response);
            out.write("用户未登录");
            out.flush();
            out.close();
        }else if(nickname==null || nickname.equals("")){
            //昵称为空
            //request.setAttribute("respDesc","昵称为空");
            //跳转用户信息修改界面
            //request.getRequestDispatcher("#").forward(request,response);
            out.write("昵称为空");
            out.flush();
            out.close();
        }else if(!password1.equals(password2)){
            //两次密码不相等
            //request.setAttribute("respDesc","两次密码不相等");
            //跳转用户信息修改界面
           // request.getRequestDispatcher("#").forward(request,response);
            out.write("昵称为空");
            out.flush();
            out.close();
        }else if(password1.length()<6 || password1.length()>20){
            //密码长度不符合
            //request.setAttribute("respDesc","新密码长度不符合");
            // System.out.println("新密码长度不符合");
            //跳转用户信息修改界面
           // request.getRequestDispatcher("#").forward(request,response);
            out.write("新密码长度不符合");
            out.flush();
            out.close();
        }else if(!user.getUserPassword().equals(oldPassword)){
            //旧密码不正确
            //request.setAttribute("respDesc","旧密码不正确");
            //System.out.println("旧密码不正确");
            //跳转用户信息修改界面
            //request.getRequestDispatcher("#").forward(request,response);
            out.write("旧密码不正确");
            out.flush();
            out.close();
        }else{
            //所有条件符合，开始修改用户信息,正常情况能修改完成

            Result<Boolean> result= UserService.updateUserInfo(user.getUid(),password1,nickname);
            if(result.getRespCode().equals("0000")){
                //用户信息修改成功
                //request.setAttribute("respDesc","用户信息修改成功");
                // System.out.println("用户信息修改成功");
                //跳转回用户界面
               // request.getRequestDispatcher("#").forward(request,response);
                out.write("用户信息修改成功");
                out.flush();
                out.close();

            }else{
                //用户信息修改失败，未知错误
               // request.setAttribute("respDesc","用户信息修改失败");
                //System.out.println("用户信息修改失败");
                //未知错误，跳转回修改界面
                //request.getRequestDispatcher("#").forward(request,response);
                out.write("用户信息修改失败");
                out.flush();
                out.close();
            }

        }
    }
}
