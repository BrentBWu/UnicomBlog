package com.unicom.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.unicom.blog.beans.Result;
import com.unicom.blog.utils.JDBCUtils;
import com.unicom.blog.utils.RespCode;

/**
 * 张永峰
 * @author Administrator
 *文章收藏
 */
public class CollectionService {

    /**
     * 文章收藏
     * @param uid
     * @param bid
     * @return
     */
    public Result<Integer> collectBlog(final Integer uid, final Integer bid) {
        Result<Integer> result = new Result<>();
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String qrySql = "select * from T_BLOG_COLLECTIONS where uid = ? and bid = ?  ";
            String likeSql = "insert into T_BLOG_COLLECTIONS (bid,uid) values(?,?)";
            String blogSql = "update T_BLOG set collections_count = collections_count + 1 where bid = ?";
            String qryBlogSql = "select collections_count from T_BLOG  where bid = ?";

            PreparedStatement pstmt3;
            pstmt3 = (PreparedStatement) conn.prepareStatement(qrySql);
            pstmt3.setInt(1, uid);
            pstmt3.setInt(2, bid);
            ResultSet rs = pstmt3.executeQuery();

            PreparedStatement pstmt4;
            pstmt4 = (PreparedStatement) conn.prepareStatement(qryBlogSql);
            pstmt4.setInt(1, bid);
            ResultSet rs2 = pstmt4.executeQuery();
            if(rs2.next()) {
                result.setRespData(rs2.getInt("collections_count"));
            }

            if(rs.next()) {
                result.setRespCode(RespCode.FAIL_CODE);
                result.setRespDesc("文章收藏失败，您已收藏过。");
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
            result.setRespData(rs2.getInt("collections_count") + 1);

            conn.commit();
            if(rs != null) {
                rs.close();
            }

            if(rs2 != null) {
                rs.close();
            }

            pstmt.close();
            pstmt2.close();
            pstmt3.close();
            pstmt4.close();
            conn.close();

            result.setRespCode(RespCode.SUCCESS_CODE);
            result.setRespDesc("文章收藏成功");
        } catch(Exception e) {
            if(conn != null) {
                try {
                    conn.rollback();
                } catch(SQLException e1) {
                    e1.printStackTrace();
                }
            }
            result.setRespCode(RespCode.FAIL_CODE);
            result.setRespDesc("文章收藏失败");
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 文章收藏状态查询
     * @param uid
     * @param bid
     * @return
     */
    public Result<Boolean> chkCollectBlog(final Integer uid, final Integer bid) {
        Result<Boolean> result = new Result<>();
        try {
            Connection conn = JDBCUtils.getConnection();

            String qrySql = "select uid from T_BLOG_COLLECTIONS where uid = ?  and bid = ?";
            PreparedStatement qryPstmt;
            qryPstmt = (PreparedStatement) conn.prepareStatement(qrySql);
            qryPstmt.setInt(1, uid);
            qryPstmt.setInt(2, bid);
            ResultSet rs = qryPstmt.executeQuery();

            if(rs.next()) {
                result.setRespCode(RespCode.SUCCESS_CODE);
                result.setRespDesc("收藏状态查询成功");
                result.setRespData(true);
            } else {
                result.setRespCode(RespCode.SUCCESS_CODE);
                result.setRespDesc("收藏状态查询成功");
                result.setRespData(false);
            }

            if(rs != null) {
                rs.close();
            }
            qryPstmt.close();
            conn.close();
        } catch(Exception e) {
            result.setRespCode(RespCode.FAIL_CODE);
            result.setRespDesc("收藏状态查询失败");
            result.setRespData(false);
            e.printStackTrace();
        }
        return result;
    }
}
