package com.lxyer.service;

import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.lxyer.config.LxyKit;
import com.lxyer.model.User;
import com.lxyer.model.UserPwd;

import java.util.Random;

/**
 * Created by JUECHENG at 2018/1/7 22:59.
 */
public class UserService {
    public static final UserService me = new UserService();

    /**
     * 创建用户
     * @param email
     * @param pwd
     * @param nickname
     * @throws Exception
     */
    public void create(String email, String pwd, String nickname) throws Exception {
        if (StrKit.isBlank(email))
            throw new Exception("注册失败，邮箱格式不正确");
        if (StrKit.isBlank(pwd))
            throw new Exception("注册失败，密码格式不正确");
        if (User.dao.findFirst(Kv.by("email", email)) != null)
            throw new Exception("注册失败，邮箱被占用");

        User user = new User();
        user.setUsername(email);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setAvatar("/res/images/avatar/"+ new Random().nextInt(21) +".jpg");//默认头像
        user.setCreateTime(System.currentTimeMillis());
        user.setStatus(1);
        user.save();

        UserPwd userPwd = new UserPwd();
        userPwd.setUserId(user.getUserId());
        userPwd.setPwd(pwd);
        userPwd.save();
    }

    public User login(String username, String pwd) throws Exception {
        User user = User.dao.findFirst(Kv.by("username", username));
        if (user == null){
            throw new Exception("密码错误！");
        }

        UserPwd userPwd = UserPwd.dao.findById(user.getUserId());
        if (userPwd == null || !LxyKit.md5IfNeed(pwd).equalsIgnoreCase(userPwd.getPwd())){
            throw new Exception("密码错误！");
        }

        if (user.getInt("status") == -1){
            throw new Exception("限制登录");
        }

        return user;
    }

    public String updateToken(User user) {
        return null;
    }

    /**
     * 修改密码
     * @param userId
     * @param pwd
     */
    public void updatePwd(Integer userId, String pwd) throws Exception {
        if (StrKit.isBlank(pwd))
            throw new Exception("操作失败，密码格式不正确");

        UserPwd userPwd = new UserPwd();
        userPwd.setUserId(userId);
        userPwd.setPwd(pwd);

        userPwd.update();
    }
}
