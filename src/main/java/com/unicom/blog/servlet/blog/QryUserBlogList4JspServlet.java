package com.unicom.blog.servlet.blog;

import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.unicom.blog.VO.UserBlogVO;
import com.unicom.blog.beans.Result;
import com.unicom.blog.service.BlogService;
import com.unicom.blog.utils.RespCode;

/**
 * Servlet implementation class QryUserBlogList4JspServlet
 * 该Servlet用于使用对象Request转发的形式传递列表参数
 * @author 张永峰（张凯南改为转发形式）
 */
@WebServlet("/QryUserBlogList4JspServlet")
public class QryUserBlogList4JspServlet extends HttpServlet {

    BlogService blogService = new BlogService();
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Result<List<UserBlogVO>> result = new Result<>();
        String stype = null;
        try {
            Integer uid = null;
            String sUid = request.getParameter("uid");
            stype=request.getParameter("type");

            if(StringUtils.isEmpty(sUid)) {
                result.setRespCode(RespCode.FAIL_CODE);
                result.setRespDesc("字段不能为空！");
            } else if(!StringUtils.isNumeric(sUid)) {
                result.setRespCode(RespCode.FAIL_CODE);
                result.setRespDesc("字段必须为数字！");
            } else {
                uid = Integer.parseInt(sUid);
                result = blogService.qryUserBlogList(uid);
            }
        } catch(Exception e) {
            result.setRespCode(RespCode.FAIL_CODE);
            result.setRespDesc("服务器内部错误" + e.getMessage());
            e.printStackTrace();
        }
        request.setAttribute("respCode", result.getRespCode());
        request.setAttribute("respDesc", result.getRespDesc());
        request.setAttribute("UserBlogList", result.getRespData());

        if (result.getRespData()!=null){
            request.setAttribute("length", result.getRespData().size());
            request.setAttribute("nickName",result.getRespData().get(0).getNickName());
            request.setAttribute("authorId",result.getRespData().get(0).getUid());
        }
       // System.out.println(request.getAttribute("collectionBolgList"));
        try {
            if("all".equals(stype)){
                request.getRequestDispatcher("personalpage.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("myblog.jsp").forward(request, response);
            }

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
