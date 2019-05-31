package com.unicom.blog.servlet.comment;


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
import com.unicom.blog.service.CommentService;
import com.unicom.blog.utils.ReqUtil;
import com.unicom.blog.utils.RespCode;
/**
 * 文章评论
 * 张永峰 
 * @author Administrator
 *
 */
@WebServlet("/commentBlog")
public class CommentBlogServlet extends HttpServlet{

	/**
	 * 
	 */
	CommentService commentService =  new CommentService();
	private static final long serialVersionUID = 1L;
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp){
		Result<String> result = new Result<>();
		try{
	
			ReqUtil.setEncoding(request, resp);
			
			Integer bid = ReqUtil.getInt(request, "bid");
			String content = ReqUtil.getString(request, "content");
			
			HttpSession session = request.getSession();
			if(session.getAttribute("user") == null){
				result.setRespCode(RespCode.FAIL_CODE);
				result.setRespDesc("获得用户信息失败！");
				resp.getWriter().print(JSON.toJSONString(result,SerializerFeature.WriteMapNullValue));
				return;
			}
			if(bid == null || StringUtils.isEmpty(content)){
				result.setRespCode(RespCode.FAIL_CODE);
				result.setRespDesc("字段不能为空！");
				resp.getWriter().print(JSON.toJSONString(result,SerializerFeature.WriteMapNullValue));
				return;
			}
			User user = (User)session.getAttribute("user");
	        resp.getWriter().print(JSON.toJSONString(commentService.commentBlog(user.getUid(),bid,content),SerializerFeature.WriteMapNullValue));
			}catch (Exception e) {
				result.setRespCode(RespCode.FAIL_CODE);
				result.setRespDesc("服务器内部错误"+e.getMessage());
				try {
					resp.getWriter().print(JSON.toJSONString(result,SerializerFeature.WriteMapNullValue));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
 }
	
}
