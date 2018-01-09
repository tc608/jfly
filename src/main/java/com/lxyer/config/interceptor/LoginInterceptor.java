package com.lxyer.config.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.lxyer.config.JBean;
import com.lxyer.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JUECHENG at 2018/1/7 11:22.
 */
public class LoginInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {

        Controller controller = inv.getController();
        HttpServletRequest request = controller.getRequest();
        HttpServletResponse response = controller.getResponse();

        User user = controller.getSessionAttr("user");
        if (user == null){
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
                controller.renderJson(new JBean(-1,"请登录后再尝试"));
                return;
            }else {
                controller.redirect("/user/login");
            }
        }else {
            inv.invoke();
        }
    }
}
