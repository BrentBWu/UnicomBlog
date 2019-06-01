package com.unicom.blog.service;

import com.unicom.blog.beans.Result;
import com.unicom.blog.beans.User;
import com.unicom.blog.dao.UserDao;

import java.util.List;

/**
 *用户服务接口
 * @author 王思诚
 */
public class UserService {

    //用户名重名检查，未重名返回TRUE
    public static Result<Boolean> userNameCkeck(String username){
        Result<Boolean> result=new Result<Boolean>();
        if(UserDao.userCheckByName(username)){
            //未重名，可以使用
            result.setRespCode("0000");
            result.setRespDesc("用户名可用");
            result.setRespData(true);
        }else{
            //重名，不可使用
            result.setRespCode("0001");
            result.setRespDesc("用户名重复");
            result.setRespData(false);
        }
        return result;
    }

    //昵称重名检查，未重名返回TRUE
    public static Result<Boolean> nickNameCkeck(String nickname){

        Result<Boolean> result=new Result<Boolean>();
        if(UserDao.userCheckByNickName(nickname)){
            //未重名，可以使用
            result.setRespCode("0000");
            result.setRespDesc("昵称可用");
            result.setRespData(true);
        }else{
            //重名，不可使用
            result.setRespCode("0001");
            result.setRespDesc("昵称重复");
            result.setRespData(false);
        }
        return result;
    }

    //用户登陆接口
    public static Result<User> userLogin(String username,String password){
        Result<User> result=new Result<User>();
        User user=UserDao.userByName(username);
        if(user.getUid()!= -1){
            //用户存在
            if(user.getUserPassword().equals(password)){
                //密码正确成功登陆
                result.setRespCode("0000");
                result.setRespDesc("登陆成功");
                result.setRespData(user);
                //System.out.println("登陆成功");
//                System.out.println(user.getUid());
//                System.out.println(user.getUserName());
//                System.out.println(user.getNickName());
//                System.out.println(user.getUserPassword());
//                System.out.println(user.getCreateTime());
//                System.out.println(user.getHead_icon());
//                System.out.println(user.getSts());
            }else {
                //密码错误
                result.setRespCode("0001");
                result.setRespDesc("密码错误");
                //System.out.println("密码错误");
            }
        }else {
            //用户不存在
            result.setRespCode("0002");
            result.setRespDesc("用户不存在");
           //System.out.println("用户不存在");
        }
        return result;


    }

    //用户注册
    public static Result<Boolean> userRegister(User user){
        int res=UserDao.userRegister(user);
        Result<Boolean> result=new Result<Boolean>();
        if(res==1){
            //注册成功
            result.setRespCode("0000");
            result.setRespDesc("注册成功");
            result.setRespData(true);
        }else{
            //注册失败
            result.setRespCode("0001");
            result.setRespDesc("注册失败");
            result.setRespData(false);

        }
        return result;
    }


    //修改密码
    public static Result<Boolean> updatePassword(int uid,String password){
        int res=UserDao.updatePassword(uid,password);
        Result<Boolean> result=new Result<Boolean>();
        if(res==1){
            //注册成功
            result.setRespCode("0000");
            result.setRespDesc("密码修改成功");
            result.setRespData(true);
        }else{
            //注册失败
            result.setRespCode("0001");
            result.setRespDesc("密码修改失败");
            result.setRespData(false);
        }
        return result;
    }

    //修改昵称
    public static Result<Boolean> updateNickName(int uid,String nickname){
        int res=UserDao.updateNickName(uid,nickname);
        Result<Boolean> result=new Result<Boolean>();
        if(res==1){
            //修改成功
            result.setRespCode("0000");
            result.setRespDesc("昵称修改成功");
            result.setRespData(true);
        }else{
            //修改失败
            result.setRespCode("0001");
            result.setRespDesc("昵称修改失败");
            result.setRespData(false);
        }
        return result;
    }

    //关注用户
    public static Result<Boolean> followUser(int uid,int followeduid){
        int res=UserDao.followUser(uid,followeduid);
        Result<Boolean> result=new Result<Boolean>();
        if(res==1){
            //关注成功
            result.setRespCode("0000");
            result.setRespDesc("关注成功");
            result.setRespData(true);
        }else{
            //关注失败
            result.setRespCode("0001");
            result.setRespDesc("关注失败");
            result.setRespData(false);
        }
        return result;
    }

    //获取关注用户列表
    public static Result<List<User>> followUserList(int uid){
        List<User> res=UserDao.followUserList(uid);
        Result<List<User>> result=new Result<List<User>>();
        result.setRespCode("0000");
        result.setRespDesc("获取关注列表成功");
        result.setRespData(res);
        for(int i=0;i<res.size();i++){
            User user=res.get(i);
            System.out.println("name="+user.getUserName()+",pass="+user.getUserPassword()+",nickname="+user.getNickName()+",sts="+user.getSts()+",uid="+user.getUid()+",head="+user.getHead_icon()+",time="+user.getCreateTime());
        }
        return result;
    }

    //修改用户信息
    public static Result<Boolean> updateUserInfo(int uid,String password,String nickname){
        int res=UserDao.updateUserInfo(uid,password,nickname);
        Result<Boolean> result=new Result<Boolean>();
        if(res==1){
            //用户信息修改成功
            result.setRespCode("0000");
            result.setRespDesc("用户信息修改成功");
            result.setRespData(true);
        }else{
            //用户信息修改失败
            result.setRespCode("0001");
            result.setRespDesc("用户信息修改失败");
            result.setRespData(false);
        }
        return result;
    }


}
