package com.unicom.blog.dao;

import com.unicom.blog.beans.User;
import com.unicom.blog.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        JDBCUtils.release(resultSet,preparedStatement,connection);
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
        JDBCUtils.release(resultSet,preparedStatement,connection);
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
        JDBCUtils.release(resultSet,preparedStatement,connection);
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
        JDBCUtils.release(resultSet,preparedStatement,connection);
        return user;
    }

    //用户注册
    public static int userRegister(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
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
        JDBCUtils.release(preparedStatement,connection);
        return result;


    }

    //修改密码
    public static int updatePassword(int uid,String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result=0;
        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            String sql = "update t_user set user_passwd=? where uid=?";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2,uid);
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(preparedStatement,connection);
        return result;

    }

    //修改昵称
    public static int updateNickName(int uid,String nickname){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result=0;
        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            String sql = "update t_user set nick_name=? where uid=?";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1, nickname);
            preparedStatement.setInt(2,uid);
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(preparedStatement,connection);
        return result;
    }

    //关注用户
    public static int followUser(int uid,int followeduid){
        int result=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            String sql = "insert into t_user_follow (uid,followed_uid,sts) values (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setInt(1, uid);
            preparedStatement.setInt(2,followeduid);
            preparedStatement.setString(3, "N");
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(preparedStatement,connection);
        return result;
    }

    //获取关注用户列表
    public static List<User> followUserList(int uid){
        ResultSet resultSet = null;
        List<User> userlist=new ArrayList<User>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            String sql = "select * from t_user where uid in(SELECT followed_uid from t_user_follow WHERE uid=?)";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setInt(1, uid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user=new User();
                user.setUid(resultSet.getInt("uid"));
                user.setNickName(resultSet.getString("nick_name"));
                user.setCreateTime(resultSet.getTimestamp("create_time"));
                user.setHead_icon(resultSet.getString("head_icon"));
                user.setSts(resultSet.getString("sts").charAt(0));
                //user.setUserPassword(resultSet.getString("user_passwd"));//不传密码
                user.setUserName(resultSet.getString("user_name"));
                userlist.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(resultSet,preparedStatement,connection);
        return userlist;
    }
    //UpdateUserInfo
    public static int updateUserInfo(int uid,String password,String nickname){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result=0;
        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            String sql = "update t_user set user_passwd=?,nick_name=? where uid=?";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, nickname);
            preparedStatement.setInt(3,uid);
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(preparedStatement,connection);
        return result;

    }
    //判断是否关注
    public static boolean chkFollowUser(int uid,int followeduid){
        boolean isfollowed=false;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            //创建SQL语句
            String sql = "select * from t_user_follow where uid=? and followed_uid=?";
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setInt(1, uid);
            preparedStatement.setInt(2, followeduid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())isfollowed=true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(resultSet,preparedStatement,connection);
        return isfollowed;


    }

}
