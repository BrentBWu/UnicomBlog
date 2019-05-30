package com.unicom.blog.servlet.blog;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.unicom.blog.beans.User;
import com.unicom.blog.service.BlogService;
import com.unicom.blog.utils.ReqUtil;
import com.unicom.blog.utils.RespCode;
import com.unicom.blog.utils.Result;
/**
 * 删除文章
 * 张永峰 
 * @author Administrator
 *
 */
@WebServlet("/deleteBlogServlet")
public class DeleteBlogServlet extends HttpServlet{

	BlogService blogService =  new BlogService();
	private static final long serialVersionUID = 1L;
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp){
		try{
	
			ReqUtil.setEncoding(request, resp);
			
			Integer bid = ReqUtil.getInt(request, "bid");
			
			Result<String> result = new Result<>();
			HttpSession session = request.getSession();
			
			if(session.getAttribute("user") == null){
				result.setRespCode(RespCode.FAIL_CODE);
				result.setRespDesc("获得用户信息失败！");
				resp.getWriter().print(JSON.toJSONString(result,SerializerFeature.WriteMapNullValue));
				return;
			}
			
			if(bid == null){
				result.setRespCode(RespCode.FAIL_CODE);
				result.setRespDesc("字段不能为空！");
				resp.getWriter().print(JSON.toJSONString(result,SerializerFeature.WriteMapNullValue));
				return;
			}
			
			User user = (User)session.getAttribute("user");
	        resp.getWriter().print(JSON.toJSONString(blogService.deleteBlog(user.getUid(),bid),SerializerFeature.WriteMapNullValue));
			}catch (Exception e) {
				e.printStackTrace();
			}
 }
	
}
