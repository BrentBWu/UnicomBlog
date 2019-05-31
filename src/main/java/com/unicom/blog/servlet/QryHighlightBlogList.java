package com.unicom.blog.servlet;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.unicom.blog.service.BlogService;
import com.unicom.blog.utils.ReqUtil;
/**
 * 首页热点文章列表查询  按点赞数量排序
 * 张永峰 
 * @author Administrator
 *
 */
@WebServlet("/qryHighlightBlogList")
public class QryHighlightBlogList extends HttpServlet{

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
	        resp.getWriter().print(JSON.toJSONString(blogService.qryHighlightBlogList(),SerializerFeature.WriteMapNullValue));
			}catch (Exception e) {
				e.printStackTrace();
			}
 }
	
}
