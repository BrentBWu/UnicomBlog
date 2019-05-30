package com.unicom.blog.service;

import org.apache.catalina.authenticator.SavedRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Test {
  public static void main(String[] args) {
	  BlogService blogService = new BlogService();
	  CommentService commentService =new CommentService();
	 // commentService.commentBlog(1, 3, "评轮安顺达杀手大手大脚卡");
	 //System.out.println(JSON.toJSONString(commentService.qryBlogCommentList(3),SerializerFeature.WriteMapNullValue));
	  //blogService.chkCollectBlog(1, 3);
	  //System.out.println(blogService.qryHighlightBlogList().getRespData().toString());
	 // System.out.println(blogService.qryUserBlogList(3).getRespData().toString());
	 // System.out.println(blogService.qryUserLikeBlogList(3).toString());
	  //ystem.out.println(blogService.qryUserCollectBlogList(1).getRespData().toString());
	 // System.out.println(blogService.qryBlogListByKeyword("实").getRespData().toString());
	  System.out.println(JSON.toJSONString(blogService.getBlogFullContent(9),SerializerFeature.WriteMapNullValue));
  }
}
