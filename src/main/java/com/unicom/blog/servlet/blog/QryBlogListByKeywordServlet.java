package com.unicom.blog.servlet.blog;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.unicom.blog.beans.Result;
import com.unicom.blog.service.BlogService;
import com.unicom.blog.utils.ReqUtil;
import com.unicom.blog.utils.RespCode;
/**
 * 文章搜索接口
 * 张永峰 
 * @author Administrator
 *
 */
@WebServlet("/qryBlogListByKeyword")
public class QryBlogListByKeywordServlet extends HttpServlet{

	/**
	 * 
	 */
	BlogService blogService =  new BlogService();
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
			
			String keyword = ReqUtil.getString(request, "keyword");
			
			if(StringUtils.isEmpty(keyword)){
				result.setRespCode(RespCode.FAIL_CODE);
				result.setRespDesc("字段不能为空！");
				resp.getWriter().print(JSON.toJSONString(result,SerializerFeature.WriteMapNullValue));
				return;
			}
			
	        resp.getWriter().print(JSON.toJSONString(blogService.qryBlogListByKeyword(keyword),SerializerFeature.WriteMapNullValue));
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
