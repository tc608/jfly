package com.lxyer.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.lxyer.model.User;

import java.util.List;

/**
 * Created by JUECHENG at 2018/1/7 14:40.
 */
public class HomeController extends IController {

    public void index(){
        String para = getPara();

        Cache cache = Redis.use();
        String cacheKey = "user-" + para;

        User user = cache.get(cacheKey);
        if (user == null)
            cache.setex(cacheKey, 10, user = User.dao.findById(para+""));

        if (user != null)
            System.out.println(user.toJson());

        renderText("hello fly");
    }

    /**
     * 查询用户
     */
    @Before(CacheInterceptor.class)
    public void query_user(){

        //User.dao.find("select * from user")

        SqlPara sqlPara = Db.getSqlPara("user.list");


        List<User> users = User.dao.find(sqlPara);

        renderJson(users);
    }
}
