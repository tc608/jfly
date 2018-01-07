package com.lxyer.config;

import com.jfinal.config.*;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.hikaricp.HikariCpPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;
import com.lxyer.config.handler.UrlHandler;
import com.lxyer.config.interceptor.LoginInterceptor;
import com.lxyer.config.route.AdminRoute;
import com.lxyer.config.route.SiteRoute;

/**
 * Created by Lxyer at 2018/01/07
 */
public class FlyConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        PropKit.use("config.properties");
    }

    @Override
    public void configRoute(Routes me) {
        me.add(new AdminRoute());
        me.add(new SiteRoute());
    }

    @Override
    public void configEngine(Engine me) {
        Engine.setMainEngine(me);
        me.setBaseTemplatePath(PathKit.getWebRootPath());

        me.addSharedFunction("/WEB-INF/_t/layout.html");
    }

    @Override
    public void configPlugin(Plugins me) {
        loadPropertyFile("config.properties");

        HikariCpPlugin hikariPlguin = new HikariCpPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"), "com.mysql.cj.jdbc.Driver");
        ActiveRecordPlugin arp = new ActiveRecordPlugin(hikariPlguin);
        me.add(hikariPlguin);
        me.add(arp);

        DbMap.mapping(arp);
        DbMap.addSqlTemplate(arp);

        me.add(new RedisPlugin(getProperty("redis.cache_name"), getProperty("redis.host"), getPropertyToInt("redis.port"), getPropertyToInt("redis.timeout")));
        me.add(new EhCachePlugin());
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new LoginInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {
        me.add(new UrlHandler());
    }
}
