package com.unicom.blog.servlet.blog;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.unicom.blog.beans.Result;
import com.unicom.blog.beans.User;
import com.unicom.blog.service.BlogService;
import com.unicom.blog.utils.ReqUtil;
import com.unicom.blog.utils.RespCode;

/**
 * 文章修改
 * 张永峰 
 * @author Administrator
 *
 */
@WebServlet("/modifyBlog")
public class ModifyBlogServlet extends HttpServlet {

    BlogService blogService = new BlogService();
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) {
        Result<String> result = new Result<>();
        try {

            ReqUtil.setEncoding(request, resp);

            String title = ReqUtil.getString(request, "title");
            String content = ReqUtil.getString(request, "content");
            Integer bid = ReqUtil.getInt(request, "bid");

            HttpSession session = request.getSession();

            if(session.getAttribute("user") == null) {
                result.setRespCode(RespCode.FAIL_CODE);
                result.setRespDesc("获得用户信息失败！");
                resp.getWriter().print(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
                return;
            }

            if(StringUtils.isEmpty(title) || StringUtils.isEmpty(content) || bid == null) {
                result.setRespCode(RespCode.FAIL_CODE);
                result.setRespDesc("字段不能为空！");
                resp.getWriter().print(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
                return;
            }

            User user = (User) session.getAttribute("user");
            resp.getWriter().print(JSON.toJSONString(blogService.modifyBlog(user.getUid(), bid, title, content),
                    SerializerFeature.WriteMapNullValue));
        } catch(Exception e) {
            result.setRespCode(RespCode.FAIL_CODE);
            result.setRespDesc("服务器内部错误" + e.getMessage());
            try {
                resp.getWriter().print(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
            } catch(IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

}
