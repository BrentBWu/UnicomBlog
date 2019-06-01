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
 *昵称修改
 * @author 王思诚
 */
@WebServlet(name = "UpdateNickNameServlet")
public class UpdateNickNameServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        User user;
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");

        if(user == null || user.getUid() < 1) {
            //用户未登录
            request.setAttribute("respDesc", "用户未登录");
            //System.out.println("用户未登录");
            //跳转登陆改界面
            request.getRequestDispatcher("#").forward(request, response);
        } else {
            //修改昵称
            Result<Boolean> result = UserService.updateNickName(user.getUid(), nickname);
            if(result.getRespCode().equals("0000")) {
                //昵称修改成功
                request.setAttribute("respDesc", "昵称修改成功");
                // System.out.println("昵称修改成功");
                //跳转用户界面
                request.getRequestDispatcher("#").forward(request, response);
            } else {
                //昵称修改失败
                request.setAttribute("respDesc", "昵称修改失败");
                //System.out.println("昵称修改失败");
                //跳转昵称修改界面
                request.getRequestDispatcher("#").forward(request, response);
            }
        }

    }
}
