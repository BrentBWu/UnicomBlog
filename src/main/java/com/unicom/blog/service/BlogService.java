package com.unicom.blog.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


import com.unicom.blog.VO.UserBlogVO;
import com.unicom.blog.beans.Result;
import com.unicom.blog.utils.JDBCUtils;
import com.unicom.blog.utils.RespCode;

/**
 * 张永峰
 * @author Administrator
 * 博客管理
 */
public class BlogService {

	/**
	 * 文章发布
	 * @param uid
	 * @param title
	 * @param content
	 * @return
	 */
	public Result<String> publishBlog(final Integer uid, final String title, final String content){
		Result<String> result = new Result<>();
		try {
			    Connection conn = JDBCUtils.getConnection();
		        String sql = "insert into T_BLOG (uid,title,content) values(?,?,?)";
		    	PreparedStatement pstmt;
		        pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        pstmt.setInt(1, uid);
		        pstmt.setString(2, title);
		        pstmt.setString(3, content);
		        pstmt.executeUpdate();
		      
		        pstmt.close();
		        conn.close();
		        
		        result.setRespCode(RespCode.SUCCESS_CODE);
		        result.setRespDesc("文章发布成功");		
		} catch (Exception e) {
	        result.setRespCode(RespCode.FAIL_CODE);
	        result.setRespDesc("文章发布失败");	
			e.printStackTrace();
		}
		return result;
				
	}
	/**
	 * 文章修改
	 * @param uid
	 * @param bid
	 * @param title
	 * @param content
	 * @return
	 */
	public Result<String> modifyBlog(final Integer uid, final Integer bid, final String title, final String content){
		Result<String> result = new Result<>();
		try {
			    Connection conn = JDBCUtils.getConnection();
			   
			    String qrySql = "select uid from T_BLOG where bid = ? ";
		        String insertSql = "UPDATE T_BLOG SET uid = ?,title = ?,content = ?,update_time = ? WHERE bid = ?";
		    	PreparedStatement qryPstmt;
		    	qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
		    	qryPstmt.setInt(1, bid);	
		    	ResultSet rs = qryPstmt.executeQuery();
		    	while(rs.next()){
		    		Integer  qryUid = rs.getInt("uid");
			    	//非法修改
			    	if(qryUid != null && !uid.equals(qryUid) ){
				        result.setRespCode(RespCode.FAIL_CODE);
				        result.setRespDesc("文章修改失败，非法修改！");	
				        return result;
			    	}
		    	}
		    	PreparedStatement insertPstmt;
		        insertPstmt = (PreparedStatement) conn.prepareStatement(insertSql);
		        insertPstmt.setInt(1, uid);
		        insertPstmt.setString(2, title);
		        insertPstmt.setString(3, content);
		        insertPstmt.setDate(4, new Date(new java.util.Date().getTime()));
		        insertPstmt.setInt(5, bid);
		        insertPstmt.executeUpdate();
		        
		        if(rs != null){
		    		rs.close();
		    	}
		    	qryPstmt.close();
		        insertPstmt.close();
		        conn.close();
		        
		        result.setRespCode(RespCode.SUCCESS_CODE);
		        result.setRespDesc("文章修改成功");		
		} catch (Exception e) {
	        result.setRespCode(RespCode.FAIL_CODE);
	        result.setRespDesc("文章修改失败");	
			e.printStackTrace();
		}
		return result;
				
	}

	/**
	 *  删除文章
	 * @param uid
	 * @param bid
	 * @return
	 */
	public Result<String> deleteBlog(final Integer uid, final Integer bid){
		Result<String> result = new Result<>();
		try {
			    Connection conn = JDBCUtils.getConnection();
			   
			    String qrySql = "select uid from T_BLOG where bid = ? ";
		        String deleteSql = "UPDATE T_BLOG set sts = 'X' where bid = ?";
		    	PreparedStatement qryPstmt;
		    	qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
		    	qryPstmt.setInt(1, bid);	
		    	ResultSet rs = qryPstmt.executeQuery();
		    	while(rs.next()){
		    		Integer  qryUid = rs.getInt("uid");
			    	//非法删除
			    	if(qryUid != null && !uid.equals(qryUid) ){
				        result.setRespCode(RespCode.FAIL_CODE);
				        result.setRespDesc("文章删除失败，非法删除！");	
				        return result;
			    	}
		    	}
		    	
		    	PreparedStatement deletePstmt;
		        deletePstmt = (PreparedStatement) conn.prepareStatement(deleteSql);
		        deletePstmt.setInt(1, bid);
		        deletePstmt.executeUpdate();
		        if(rs != null){
		    		rs.close();
		    	}
		        qryPstmt.close();
		        deletePstmt.close();
		        conn.close();
		        result.setRespCode(RespCode.SUCCESS_CODE);
		        result.setRespDesc("文章删除成功");		
		} catch (Exception e) {
	        result.setRespCode(RespCode.FAIL_CODE);
	        result.setRespDesc("文章删除失败");	
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 首页热点文章列表查询  按点赞数量排序
	 * @return
	 */
	public Result<List<UserBlogVO>> qryHighlightBlogList(){
		Result<List<UserBlogVO>> result = new Result<>();
		List<UserBlogVO> userBlogVoList = new LinkedList<UserBlogVO>();
		try {
			    Connection conn = JDBCUtils.getConnection();
			    
			    String qrySql = "SELECT a.*,b.nick_name,b.head_icon from T_BLOG  a"
			    		+ " LEFT JOIN T_USER b on  a.uid = b.uid where a.sts = 'N' ORDER BY likes_count desc";
		    	PreparedStatement qryPstmt;
		    	qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
		    	ResultSet rs = qryPstmt.executeQuery();
		    	Boolean isHasData  = false;
		    	while(rs.next()){	
		    		isHasData = true;
		    		UserBlogVO userBlogVo =new UserBlogVO();
		    		userBlogVo.setBid(rs.getInt("bid"));
		    		userBlogVo.setCollectionCount(rs.getInt("collections_count"));
		    		userBlogVo.setCommentsCount(rs.getInt("comments_count"));
		    		userBlogVo.setContent(rs.getString("content"));
		    		userBlogVo.setCreateTime(rs.getDate("create_time"));
		    		userBlogVo.setHead_icon(rs.getString("head_icon"));
		    		userBlogVo.setLikesCount(rs.getInt("likes_count"));
		    		userBlogVo.setNickName(rs.getString("nick_name"));
		    		userBlogVo.setTitle(rs.getString("title"));
		    		userBlogVo.setUid(rs.getInt("uid"));
		    		userBlogVo.setUpdateTime(rs.getDate("update_time"));
		    		userBlogVoList.add(userBlogVo);
		    	}
		    	//没有数据
		    	if(! isHasData){
		    		result.setRespCode(RespCode.NULL_CODE);
				    result.setRespDesc("获取首页热点文章列表失败，没有数据。");	
				   
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
			    result.setRespDesc("获取首页热点文章列表成功");	
	    		result.setRespData(userBlogVoList);
		} catch (Exception e) {
			result.setRespCode(RespCode.FAIL_CODE);
		    result.setRespDesc(" 获取首页热点文章列表失败");	
    		result.setRespData(userBlogVoList);
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 用户文章列表查询
	 * @param uid
	 * @return
	 */
	public Result<List<UserBlogVO>> qryUserBlogList(final Integer uid){
		Result<List<UserBlogVO>> result = new Result<>();
		List<UserBlogVO> userBlogVoList = new LinkedList<UserBlogVO>();
		try {
			    Connection conn = JDBCUtils.getConnection();
			    
			    String qrySql = "SELECT a.*,b.nick_name,b.head_icon from T_BLOG  a"
			    		+ " LEFT JOIN T_USER b on  a.uid = b.uid  where a.uid = ? and a.sts = 'N' ORDER BY create_time desc";
		    	PreparedStatement qryPstmt;
		    	qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
		    	qryPstmt.setInt(1, uid);
		    	ResultSet rs = qryPstmt.executeQuery();
		    	Boolean isHasData  = false;
		    	while(rs.next()){	
		    		isHasData = true;
		    		UserBlogVO userBlogVo =new UserBlogVO();
		    		userBlogVo.setBid(rs.getInt("bid"));
		    		userBlogVo.setCollectionCount(rs.getInt("collections_count"));
		    		userBlogVo.setCommentsCount(rs.getInt("comments_count"));
		    		userBlogVo.setContent(rs.getString("content"));
		    		userBlogVo.setCreateTime(rs.getDate("create_time"));
		    		userBlogVo.setHead_icon(rs.getString("head_icon"));
		    		userBlogVo.setLikesCount(rs.getInt("likes_count"));
		    		userBlogVo.setNickName(rs.getString("nick_name"));
		    		userBlogVo.setTitle(rs.getString("title"));
		    		userBlogVo.setUid(rs.getInt("uid"));
		    		userBlogVo.setUpdateTime(rs.getDate("update_time"));
		    		userBlogVoList.add(userBlogVo);
		    	}
		    	//没有数据
		    	if(! isHasData){
		    		result.setRespCode(RespCode.NULL_CODE);
				    result.setRespDesc(" 用户文章列表查询失败，没有数据。");	
				    
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
			    result.setRespDesc("用户文章列表查询成功");	
	    		result.setRespData(userBlogVoList);
		} catch (Exception e) {
			result.setRespCode(RespCode.FAIL_CODE);
		    result.setRespDesc(" 用户文章列表查询失败");	
    		result.setRespData(userBlogVoList);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 用户点赞文章列表查询
	 * @param uid
	 * @return
	 */
	public Result<List<UserBlogVO>> qryUserLikeBlogList(final Integer uid){
		Result<List<UserBlogVO>> result = new Result<>();
		List<UserBlogVO> userBlogVoList = new LinkedList<UserBlogVO>();
		try {
			    Connection conn = JDBCUtils.getConnection();
			    
			    String qrySql = "SELECT b.*,c.nick_name,c.head_icon from T_BLOG_LIKE"
			    		+ " a LEFT JOIN T_BLOG  b on a.bid = b.bid  LEFT JOIN T_USER c"
			    		+ " ON  b.uid =c.uid where b.uid = ? and a.sts = 'N' ORDER BY a.create_time";
		    	PreparedStatement qryPstmt;
		    	qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
		    	qryPstmt.setInt(1, uid);
		    	ResultSet rs = qryPstmt.executeQuery();
		    	Boolean isHasData  = false;
		    	while(rs.next()){	
		    		isHasData = true;
		    		UserBlogVO userBlogVo =new UserBlogVO();
		    		userBlogVo.setBid(rs.getInt("bid"));
		    		userBlogVo.setCollectionCount(rs.getInt("collections_count"));
		    		userBlogVo.setCommentsCount(rs.getInt("comments_count"));
		    		userBlogVo.setContent(rs.getString("content"));
		    		userBlogVo.setCreateTime(rs.getDate("create_time"));
		    		userBlogVo.setHead_icon(rs.getString("head_icon"));
		    		userBlogVo.setLikesCount(rs.getInt("likes_count"));
		    		userBlogVo.setNickName(rs.getString("nick_name"));
		    		userBlogVo.setTitle(rs.getString("title"));
		    		userBlogVo.setUid(rs.getInt("uid"));
		    		userBlogVo.setUpdateTime(rs.getDate("update_time"));
		    		userBlogVoList.add(userBlogVo);
		    	}
		    	//没有数据
		    	if(! isHasData){
		    		result.setRespCode(RespCode.NULL_CODE);
				    result.setRespDesc("用户点赞文章列表查询失败，没有数据。");
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
			    result.setRespDesc("用户点赞文章列表查询成功");	
	    		result.setRespData(userBlogVoList);
		} catch (Exception e) {
			result.setRespCode(RespCode.FAIL_CODE);
		    result.setRespDesc("用户点赞文章列表查询失败");	
    		result.setRespData(userBlogVoList);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 用户收藏文章列表查询
	 * @param uid
	 * @return
	 */
	public Result<List<UserBlogVO>> qryUserCollectBlogList(final Integer uid){
		Result<List<UserBlogVO>> result = new Result<>();
		List<UserBlogVO> userBlogVoList = new LinkedList<UserBlogVO>();
		try {
			    Connection conn = JDBCUtils.getConnection();
			    
			    String qrySql = "SELECT b.*,c.nick_name,c.head_icon from T_BLOG_COLLECTIONS"
			    		+ " a LEFT JOIN T_BLOG  b on a.bid = b.bid  LEFT JOIN T_USER c"
			    		+ " ON  b.uid =c.uid where b.uid = ? and a.sts = 'N' ORDER BY a.create_time";
		    	PreparedStatement qryPstmt;
		    	qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
		    	qryPstmt.setInt(1, uid);
		    	ResultSet rs = qryPstmt.executeQuery();
		    	Boolean isHasData  = false;
		    	while(rs.next()){	
		    		isHasData = true;
		    		UserBlogVO userBlogVo =new UserBlogVO();
		    		userBlogVo.setBid(rs.getInt("bid"));
		    		userBlogVo.setCollectionCount(rs.getInt("collections_count"));
		    		userBlogVo.setCommentsCount(rs.getInt("comments_count"));
		    		userBlogVo.setContent(rs.getString("content"));
		    		userBlogVo.setCreateTime(rs.getDate("create_time"));
		    		userBlogVo.setHead_icon(rs.getString("head_icon"));
		    		userBlogVo.setLikesCount(rs.getInt("likes_count"));
		    		userBlogVo.setNickName(rs.getString("nick_name"));
		    		userBlogVo.setTitle(rs.getString("title"));
		    		userBlogVo.setUid(rs.getInt("uid"));
		    		userBlogVo.setUpdateTime(rs.getDate("update_time"));
		    		userBlogVoList.add(userBlogVo);
		    	}
		    	//没有数据
		    	if(! isHasData){
		    		result.setRespCode(RespCode.NULL_CODE);
				    result.setRespDesc("用户收藏文章列表查询失败，没有数据。");	
				    
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
			    result.setRespDesc("用户收藏文章列表查询成功");	
	    		result.setRespData(userBlogVoList);
		} catch (Exception e) {
			result.setRespCode(RespCode.FAIL_CODE);
		    result.setRespDesc("用户收藏文章列表查询失败");	
    		result.setRespData(userBlogVoList);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 文章搜索接口
	 * @param keyword
	 * @return
	 */
	public Result<List<UserBlogVO>> qryBlogListByKeyword(final String keyword){
		Result<List<UserBlogVO>> result = new Result<>();
		List<UserBlogVO> userBlogVoList = new LinkedList<UserBlogVO>();
		try {
			    Connection conn = JDBCUtils.getConnection();
			    
			    String qrySql = "SELECT a.*,b.nick_name,b.head_icon from T_BLOG  a"
			    		+ " LEFT JOIN T_USER b on  a.uid = b.uid where a.title like ? and a.sts = 'N' ORDER BY likes_count desc";
		    	PreparedStatement qryPstmt;
		    	qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
		    	qryPstmt.setString(1, "%"+keyword.trim()+"%");
		    	ResultSet rs = qryPstmt.executeQuery();
		    	Boolean isHasData  = false;
		    	while(rs.next()){	
		    		isHasData = true;
		    		UserBlogVO userBlogVo =new UserBlogVO();
		    		userBlogVo.setBid(rs.getInt("bid"));
		    		userBlogVo.setCollectionCount(rs.getInt("collections_count"));
		    		userBlogVo.setCommentsCount(rs.getInt("comments_count"));
		    		userBlogVo.setContent(rs.getString("content"));
		    		userBlogVo.setCreateTime(rs.getDate("create_time"));
		    		userBlogVo.setHead_icon(rs.getString("head_icon"));
		    		userBlogVo.setLikesCount(rs.getInt("likes_count"));
		    		userBlogVo.setNickName(rs.getString("nick_name"));
		    		userBlogVo.setTitle(rs.getString("title"));
		    		userBlogVo.setUid(rs.getInt("uid"));
		    		userBlogVo.setUpdateTime(rs.getDate("update_time"));
		    		userBlogVoList.add(userBlogVo);
		    	}
		    	//没有数据
		    	if(! isHasData){
		    		result.setRespCode(RespCode.NULL_CODE);
				    result.setRespDesc("用户收藏文章列表查询失败，没有数据。");
				    
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
			    result.setRespDesc("获取首页热点文章列表成功");	
	    		result.setRespData(userBlogVoList);
		} catch (Exception e) {
			result.setRespCode(RespCode.FAIL_CODE);
		    result.setRespDesc(" 获取首页热点文章列表失败");	
    		result.setRespData(userBlogVoList);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取文章内容（含评论）
	 * @param bid
	 * @return
	 */
	public Result<UserBlogVO> getBlogFullContent(final Integer bid){
		Result<UserBlogVO> result = new Result<>();
		UserBlogVO userBlogVo =new UserBlogVO();
		try {
			    Connection conn = JDBCUtils.getConnection();
			    
			    String qrySql = "SELECT a.*,b.nick_name,b.head_icon from T_BLOG  a"
			    		+ " LEFT JOIN T_USER b on  a.uid = b.uid where a.bid = ? ORDER BY likes_count desc";
		    	PreparedStatement qryPstmt;
		    	qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
		    	qryPstmt.setInt(1, bid);
		    	ResultSet rs = qryPstmt.executeQuery();
		    	Boolean isHasData  = false;
		    	while(rs.next()){	
		    		isHasData = true;
		    		userBlogVo.setBid(rs.getInt("bid"));
		    		userBlogVo.setCollectionCount(rs.getInt("collections_count"));
		    		userBlogVo.setCommentsCount(rs.getInt("comments_count"));
		    		userBlogVo.setContent(rs.getString("content"));
		    		userBlogVo.setCreateTime(rs.getDate("create_time"));
		    		userBlogVo.setHead_icon(rs.getString("head_icon"));
		    		userBlogVo.setLikesCount(rs.getInt("likes_count"));
		    		userBlogVo.setNickName(rs.getString("nick_name"));
		    		userBlogVo.setTitle(rs.getString("title"));
		    		userBlogVo.setUid(rs.getInt("uid"));
		    		userBlogVo.setUpdateTime(rs.getDate("update_time"));		    		
		    		CommentService commentService = new CommentService();
		    		//组装评论信息
		    		userBlogVo.setUserCommentVoList(commentService.qryBlogCommentList(bid).getRespData());
		    	}
		    	//没有数据
		    	if(! isHasData){
		    		result.setRespCode(RespCode.NULL_CODE);
				    result.setRespDesc("获取文章内容（含评论）失败，没有数据。");
				    
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
			    result.setRespDesc("获取文章内容（含评论）成功");	
	    		result.setRespData(userBlogVo);
		} catch (Exception e) {
			result.setRespCode(RespCode.FAIL_CODE);
		    result.setRespDesc("获取文章内容（含评论）失败");	
    		result.setRespData(userBlogVo);
			e.printStackTrace();
		}
		return result;
	}
}

