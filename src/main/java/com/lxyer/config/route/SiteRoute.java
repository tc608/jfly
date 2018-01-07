package com.lxyer.config.route;

import com.jfinal.config.Routes;
import com.lxyer.controller.HomeController;

/**
 * Created by JUECHENG at 2018/1/7 11:16.
 */
public class SiteRoute extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/WEB-INF/fly");

        add("/", HomeController.class);
    }
}
