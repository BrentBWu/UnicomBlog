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
/**
 *密码修改
 * @author 王思诚
 */
@WebServlet(name = "UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword=request.getParameter("oldPassword");
        String password=request.getParameter("password");
        User user;
        HttpSession session=request.getSession();
        user=(User)session.getAttribute("user");

        if(password.length()<6 || password.length()>20){
            //密码长度不符合
            request.setAttribute("respDesc","新密码长度不符合");
           // System.out.println("新密码长度不符合");
            //跳转密码修改界面
            request.getRequestDispatcher("#").forward(request,response);
        }else if(user ==null || user.getUid()<1){
            //用户未登录
            request.setAttribute("respDesc","用户未登录");
            //System.out.println("用户未登录");
            //跳转登陆界面
            request.getRequestDispatcher("#").forward(request,response);
        }else if(!user.getUserPassword().equals(oldPassword)){
            //旧密码不正确
            request.setAttribute("respDesc","旧密码不正确");
            //System.out.println("旧密码不正确");
            //跳转密码修改界面
            request.getRequestDispatcher("#").forward(request,response);
        }else{
            //修改密码
            Result<Boolean> result= UserService.updatePassword(user.getUid(),password);
            if(result.getRespCode().equals("0000")){
                //密码修改成功
                request.setAttribute("respDesc","密码修改成功");
                //System.out.println("密码修改成功");
                //跳转用户界面
                request.getRequestDispatcher("#").forward(request,response);
            }else{
                //密码修改失败
                request.setAttribute("respDesc","密码修改失败");
               // System.out.println("密码修改失败");
                //跳转密码修改界面
                request.getRequestDispatcher("#").forward(request,response);
            }
        }
    }


}
