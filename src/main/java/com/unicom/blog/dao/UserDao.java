package com.unicom.blog.dao;

import com.unicom.blog.beans.User;
import com.unicom.blog.utils.JDBCUtils;

import java.sql.*;

/**
 *用户DAO
 * @author 王思诚
 */
public class UserDao {

    //根据用户名检查重名
    public static boolean userCheckByName(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            String sql = "select * from t_user where user_name=? ";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                flag = false;//有重名，不允许使用
            }else{
                flag = true;//未重名，允许使用
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    //根据昵称检查重名
    public static boolean userCheckByNickName(String nickname){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            String sql = "select * from t_user where nick_name=? ";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1, nickname);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                flag = false;//有重名，不允许使用
            }else{
                flag = true;//未重名，允许使用
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    //根据用户名返回user对象
    public static User userByName(String username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        User user=new User();
        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            String sql = "select * from t_user where user_name=? ";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //找到用户
                user.setUid(resultSet.getInt("uid"));
                user.setNickName(resultSet.getString("nick_name"));
                user.setCreateTime(resultSet.getTimestamp("create_time"));
                user.setHead_icon(resultSet.getString("head_icon"));
                user.setSts(resultSet.getString("sts").charAt(0));
                user.setUserPassword(resultSet.getString("user_passwd"));
                user.setUserName(resultSet.getString("user_name"));
            }else{
                //未找到用户
                user.setUid(-1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //根据UID返回user对象
    public static User userByUid(int uid){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user=new User();
        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            String sql = "select * from t_user where uid=? ";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setInt(1, uid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //找到用户
                user.setUid(resultSet.getInt("uid"));
                user.setNickName(resultSet.getString("nick_name"));
                user.setCreateTime(resultSet.getTimestamp("create_time"));
                user.setHead_icon(resultSet.getString("head_icon"));
                user.setSts(resultSet.getString("sts").charAt(0));
                user.setUserPassword(resultSet.getString("user_passwd"));
                user.setUserName(resultSet.getString("user_name"));
            }else{
                //未找到用户
                user.setUid(-1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //用户注册
    public static int userRegister(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result=0;
        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            //String sql = "insert into t_user (user_name,user_passwd,create_time,sts,nick_name,head_icon) values(?,?,?,?,?,?)  ";
            String sql = "insert into t_user (user_name,user_passwd,sts,nick_name,head_icon) values(?,?,?,?,?)  ";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPassword());
           // preparedStatement.setTimestamp(3, new Timestamp(user.getCreateTime().getTime()));
            //preparedStatement.setTimestamp(3, user.getCreateTime());
            preparedStatement.setString(3, String.valueOf(user.getSts()));
            preparedStatement.setString(4,user.getNickName());
            preparedStatement.setString(5, user.getHead_icon());
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
/*
*  test
* */
    //用户头像修改
    public static int userIconModify(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result=0;
        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
   //String sql = "update t_user set head_icon = '.../src\main\webapp\Img\Icon/png1' where user_name = 'xx';";
            String sql = "update t_user set head_icon = ? where user_name = ?;";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1, user.getHead_icon());
            preparedStatement.setString(2, user.getUserName());
// executeUpdate 的返回值是一个整数（int），指示受影响的行数（即更新计数）。
            result = preparedStatement.executeUpdate();
 //  对于 CREATE 或 DROP 等不操作行的语句，executeUpdate 的返回值总为零
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(null,connection);
        }
        return result;


    }
}
