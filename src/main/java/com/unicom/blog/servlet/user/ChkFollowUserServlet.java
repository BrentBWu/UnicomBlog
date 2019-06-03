package com.unicom.blog.servlet.user;

import com.alibaba.fastjson.JSON;
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

@WebServlet("/ChkFollowUserServlet")
public class ChkFollowUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        User user;
        HttpSession session=request.getSession();
        user=(User)session.getAttribute("user");
        int  uid=Integer.parseInt(request.getParameter("uid"));//被关注者的UID
        if(user ==null || user.getUid()<1){
            //用户未登录
            Result<String> result = new Result<>();
            result.setRespCode("0001");
            result.setRespDesc("用户未登录");
            response.getWriter().print(JSON.toJSONString(result));
           // request.setAttribute("respDesc","用户未登录");
            //System.out.println("用户未登录");
            //跳转登陆改界面
           // request.getRequestDispatcher("#").forward(request,response);
        }else{
            //判断是否关注
            Result<Boolean> result=UserService.chkFollowUser(user.getUid(),uid);
            boolean isfollowes =result.getRespData();
            // request.setAttribute("isfollowes",isfollowes);
            //isfollowes为true时为已经关注，否则为未关注。
            Result<String> result2 = new Result<>();
            if (isfollowes){
                result2.setRespCode("0002");
                result2.setRespDesc("已关注过");
                response.getWriter().print(JSON.toJSONString(result2));
            }else{
                result2.setRespCode("0000");
                result2.setRespDesc("未关注过");
                response.getWriter().print(JSON.toJSONString(result2));
            }

        }

    }
}
