package com.unicom.blog.servlet.blog;

import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.unicom.blog.VO.UserBlogVO;
import com.unicom.blog.beans.Result;
import com.unicom.blog.service.BlogService;
import com.unicom.blog.utils.RespCode;

/**
 * Servlet implementation class QryHightlightBlogList4JspServlet
 * 该Servlet用于使用对象Request转发的形式传递列表参数
 * @author 张永峰（张凯南改为转发形式）
 */
@WebServlet("/QryHightlightBlogList4JspServlet")
public class QryHightlightBlogList4JspServlet extends HttpServlet {

    BlogService blogService = new BlogService();
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Result<List<UserBlogVO>> result = new Result<>();
        result = blogService.qryHighlightBlogList();
        request.setAttribute("respCode", result.getRespCode());
        request.setAttribute("respDesc", result.getRespDesc());
        request.setAttribute("respData", result.getRespData());
        try {
            request.getRequestDispatcher("").forward(request, response);
        } catch(Exception e) {
            result.setRespCode(RespCode.FAIL_CODE);
            result.setRespDesc("服务器转发错误" + e.getMessage());
            e.printStackTrace();
            try {
                response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
            } catch(IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
