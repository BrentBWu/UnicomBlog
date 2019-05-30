package com.unicom.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.unicom.blog.utils.JDBCUtils;
import com.unicom.blog.utils.RespCode;
import com.unicom.blog.utils.Result;
import com.unicom.blog.vo.UserCommentVo;

/**
 * 文章评论
 * 张永峰
 * @author Administrator
 *
 */
public class CommentService {

	public Result<String> commentBlog(final Integer uid, final Integer bid, final String content){
	
		Result<String> result = new Result<>();
		  Connection conn = null;
		try {
			    conn = JDBCUtils.getConnection();
			    conn.setAutoCommit(false);
		        String commentSql = "insert into T_BLOG_COMMENTS (bid,uid,content,floor_num) values(?,?,?,0)";
		        String blogSql = "update T_BLOG set comments_count = comments_count + 1 where bid = ?";		      
		    	PreparedStatement pstmt;
		        pstmt = (PreparedStatement) conn.prepareStatement(commentSql);
		        pstmt.setInt(1, bid);
		        pstmt.setInt(2, uid);
		        pstmt.setString(3, content);
		        pstmt.executeUpdate();
		        
		        PreparedStatement pstmt2;
		        pstmt2 = (PreparedStatement) conn.prepareStatement(blogSql);
		        pstmt2.setInt(1, bid);
		        pstmt2.executeUpdate();		     		        
		        conn.commit();	        		        
		        pstmt.close();
		        pstmt2.close();
		        conn.close();
		      

		        result.setRespCode(RespCode.SUCCESS_CODE);
		        result.setRespDesc("文章评论成功");		
		} catch (Exception e) {
			if(conn != null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
	        result.setRespCode(RespCode.FAIL_CODE);
	        result.setRespDesc("文章评论失败");	
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * 获取文章评论
	 * @param bid
	 * @return
	 */
	public Result<List<UserCommentVo>> qryBlogCommentList(final Integer bid){
	
		Result<List<UserCommentVo>> result = new Result<>();
		List<UserCommentVo> UserCommentVoList = new LinkedList<UserCommentVo>();
		try {
			    Connection conn = JDBCUtils.getConnection();
			   
			    String qrySql = "SELECT a.*, b.nick_name, b.head_icon FROM T_BLOG_COMMENTS a"
			    		+ " LEFT JOIN T_USER b ON a.uid = b.uid WHERE a.bid = ? "
			    		+ "ORDER BY a.create_time DESC";
		    	PreparedStatement qryPstmt;
		    	qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
		    	qryPstmt.setInt(1, bid);
		    	ResultSet rs = qryPstmt.executeQuery();
		    	Boolean isHasData  = false;
		    	while(rs.next()){	
		    		isHasData = true;
		    		UserCommentVo userCommentVo =new UserCommentVo();
		    		userCommentVo.setContent(rs.getString("content"));
		    		userCommentVo.setCreateDate(rs.getDate("create_time"));
		    		userCommentVo.setFloorNum(rs.getInt("floor_num"));
		    		userCommentVo.setHead_icon(rs.getString("head_icon"));
		    		userCommentVo.setNickName(rs.getString("nick_name"));
		    		userCommentVo.setUid(rs.getInt("uid"));
		    		UserCommentVoList.add(userCommentVo);
		    	}
		    	//没有数据
		    	if(! isHasData){
		    		result.setRespCode(RespCode.NULL_CODE);
				    result.setRespDesc("获取文章评论失败，没有数据。");	
				    
				    if(rs != null){
			    		rs.close();
			    	}		    	
			    	qryPstmt.close();
			        conn.close();
			        
		    		return result;
		    	}
		    	
		    	if(rs != null){
		    		rs.close();
		    	}		    	
		    	qryPstmt.close();
		        conn.close();
	    		result.setRespCode(RespCode.SUCCESS_CODE);
			    result.setRespDesc("获取文章评论成功");	
	    		result.setRespData(UserCommentVoList);
		} catch (Exception e) {
			result.setRespCode(RespCode.FAIL_CODE);
		    result.setRespDesc(" 获取文章评论失败");	
    		result.setRespData(UserCommentVoList);
			e.printStackTrace();
		}
		return result;
	}
	 
}

