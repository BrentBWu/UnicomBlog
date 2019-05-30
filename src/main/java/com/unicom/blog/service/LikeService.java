package com.unicom.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.unicom.blog.utils.JDBCUtils;
import com.unicom.blog.utils.RespCode;
import com.unicom.blog.utils.Result;
/*文章点赞
 * 张永峰
 * @author Administrator
 *
 */
public class LikeService {
	
	/**
	 * 文章点赞
	 * @param uid
	 * @param bid
	 * @return
	 */
	public Result<Integer> likeBlog(final Integer uid, final Integer bid){
		  Result<Integer> result = new Result<>();
		  Connection conn = null;
		try {
			    conn = JDBCUtils.getConnection();
			    conn.setAutoCommit(false);
			    String qrySql = "select * from T_BLOG_LIKE where uid = ? and bid = ?  ";
		        String likeSql = "insert into T_BLOG_LIKE (bid,uid) values(?,?)";
		        String blogSql = "update T_BLOG set likes_count = likes_count + 1 where bid = ?";
		        String qryBlogSql = "select likes_count from T_BLOG  where bid = ?";
		      
		        PreparedStatement pstmt3;
		        pstmt3 = (PreparedStatement) conn.prepareStatement(qrySql);
		        pstmt3.setInt(1, uid);
		        pstmt3.setInt(2, bid);
		        ResultSet rs = pstmt3.executeQuery();
		        
		        PreparedStatement pstmt4;
		        pstmt4 = (PreparedStatement) conn.prepareStatement(qryBlogSql);
		        pstmt4.setInt(1, bid);
		        ResultSet rs2 = pstmt4.executeQuery();
		        if(rs2.next()){
		        	result.setRespData(rs2.getInt("likes_count"));
		        }
		        
		        if(rs.next()){
		        	result.setRespCode(RespCode.FAIL_CODE);
			        result.setRespDesc("文章点赞失败，您已点赞过。");
			        return result;
		        }
		    	PreparedStatement pstmt;
		        pstmt = (PreparedStatement) conn.prepareStatement(likeSql);
		        pstmt.setInt(1, bid);
		        pstmt.setInt(2, uid);
		        pstmt.executeUpdate();
		        
		        PreparedStatement pstmt2;
		        pstmt2 = (PreparedStatement) conn.prepareStatement(blogSql);
		        pstmt2.setInt(1, bid);
		        pstmt2.executeUpdate();
		     
		        //点赞数+1
		        result.setRespData(rs2.getInt("likes_count")+1);
		        
		        conn.commit();	        
		        if(rs != null){
		        	rs.close();
		        }
		        
		        if(rs2 != null){
		        	rs.close();
		        }
		        
		        pstmt.close();
		        pstmt2.close();
		        pstmt3.close();
		        pstmt4.close();
		        conn.close();
		      

		        result.setRespCode(RespCode.SUCCESS_CODE);
		        result.setRespDesc("文章点赞成功");		
		} catch (Exception e) {
			if(conn != null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
	        result.setRespCode(RespCode.FAIL_CODE);
	        result.setRespDesc("文章点赞失败");	
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 *  点赞状态查询
	 * @param uid
	 * @param bid
	 * @return
	 */
	public Result<Boolean> chkLikeBlog(final Integer uid, final Integer bid){
		Result<Boolean> result = new Result<>();
		try {
			    Connection conn = JDBCUtils.getConnection();
			   
			    String qrySql = "select uid from T_BLOG_LIKE where uid = ?  and bid = ?";
		    	PreparedStatement qryPstmt;
		    	qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
		    	qryPstmt.setInt(1, uid);
		    	qryPstmt.setInt(2, bid);
		    	ResultSet rs = qryPstmt.executeQuery();
		    	
		    	if(rs.next()){
		    		result.setRespCode(RespCode.SUCCESS_CODE);
				    result.setRespDesc("点赞状态查询成功");	
		    		result.setRespData(true);
		    	}else{
		    		result.setRespCode(RespCode.SUCCESS_CODE);
				    result.setRespDesc("点赞状态查询成功");	
		    		result.setRespData(false);
		    	}
		    	
		    	if(rs != null){
		    		rs.close();
		    	}		    	
		    	qryPstmt.close();
		        conn.close();
		} catch (Exception e) {
			result.setRespCode(RespCode.FAIL_CODE);
		    result.setRespDesc("点赞状态查询失败");	
    		result.setRespData(false);
			e.printStackTrace();
		}
		return result;
		
	}
}
