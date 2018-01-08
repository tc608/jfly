package com.lxyer.controller;

import com.lxyer.config.JsonBean;
import com.lxyer.model.User;
import com.lxyer.service.UserService;

/**
 * Created by JUECHENG at 2018/1/7 16:40.
 */
public class UserController extends IController {

    private UserService userService = UserService.me;

    /**
     * 注册
     */
    public void create(){
        JsonBean bean = new JsonBean(1);
        String email = getPara("email");
        String pwd = getPara("pwd");
        String nickname = getPara("nickname");

        try {
            userService.create(email, pwd, nickname);
        } catch (Exception e) {
            bean.setCode(-1, e.getMessage());
        }

        renderJson(bean);
    }

    /**
     * 登录
     */
    public void login(){
        String para = getPara();
        if (para == null) {
            render("login.html");
            return;
        }else if ("out".equals(para)){
            removeSessionAttr("user");
            renderJson(JsonBean.success());
            return;
        }
        JsonBean bean = new JsonBean(1);
        String username = getPara("username");
        String pwd = getPara("pwd");

        try {
            User user = userService.login(username, pwd);
            /*未来源app端
            String token = userService.updateToken(user);
            bean.set("user", user);
            bean.set("token", token);*/

            setSessionAttr("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            bean.setCode(-1, e.getMessage());
        }

        renderJson(bean);
    }

    /**
     * todo:用户修改资料
     */
    public void update(){

    }

    /**
     * 修改密码
     */
    public void repwd(){
        JsonBean bean = new JsonBean(1);
        String pwd = getPara("pwd");

        try {
            userService.updatePwd(getUserId(), pwd);
        } catch (Exception e) {
            bean.setCode(-1, e.getMessage());
        }

        renderJson(bean);
    }

}
