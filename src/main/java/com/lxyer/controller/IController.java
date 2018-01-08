package com.lxyer.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.*;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.lxyer.config.E;
import com.lxyer.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lxyer at 2017/9/10 13:55.
 */
public class IController extends Controller {

    public static final Cache cache = Redis.use();

    public void index(){
        String para = getPara(0, "index");

        render(para + ".html");
    }


    public Integer getUserId() {
        User user = getUser();
        return user == null ? null : user.getUserId();
    }

    public User getUser(){
        return getSessionAttr("user");
    }

    public Kv getParams(String... key) {
        Kv kv = Kv.create();
        for (String k : key) {
            if (k.contains("=")) {   //如果没有值使用默认值
                kv.put(k.split("=")[0], getPara(k.split("=")[0], k.split("=")[1]));
                continue;
            } else if (k.contains("<")) {  //强制使用"<"右侧的值
                kv.put(k.split("<")[0], k.split("<")[1]);
                continue;
            }
            kv.put(k, getPara(k));
        }
        return kv;
    }

    public int getPn(){
        return getParaToInt("pn", 1);
    }
    public int getPs(){
        return getParaToInt("ps", 15);
    }
    public int getPn(int pn){
        return getParaToInt("pn", pn);
    }
    public int getPs(int ps){
        return getParaToInt("ps", ps);
    }

    /**
     * 设置动态属性
     * @param page
     * @param dynAttr
     */
    public void setDynAttr(Page<Model> page, E.DynamicAttr dynAttr, String ... s){
        if (page == null || page.getList().size() == 0 || dynAttr == null) return;
        List ids = new ArrayList<>();
        page.getList().forEach(x->ids.add(x.getInt(dynAttr.id_k())));
        if (!ids.isEmpty()) {
            String ids_ = ids.toString();
            ids_ = ids_.substring(1, ids_.length() - 1);

            //查询动态属性
            Kv kv = Kv.by("table", dynAttr.table()).set("id_k", dynAttr.gk()).set("id_v", ids_).set("cate", dynAttr.getCate());
            if (s.length > 0)
                kv.set("attr", arrToStr(s));

            SqlPara sqlPara = Db.getSqlPara("m.dyn_attr", kv);
            List<Record> attrs = Db.find(sqlPara);

            Map<Integer, Kv> attrMap = new HashMap();
            attrs.forEach(x->{
                Kv nAttr = attrMap.getOrDefault(x.getInt(dynAttr.gk()), Kv.create());
                nAttr.set(x.get("attr"), x.get("value"));
                attrMap.put(x.getInt(dynAttr.gk()), nAttr);
            });
            page.getList().forEach(x->{
                attrMap.getOrDefault(x.getInt(dynAttr.id_k()), Kv.create()).forEach((k, v)->x.put(k+"", v));
            });
        }
    }

    /**
     * 设置动态属性
     * @param model
     * @param dynAttr
     */
    public void setDynAttr(Model model, E.DynamicAttr dynAttr, String ... ss){
        Kv kv = Kv.by("table", dynAttr.table()).set("id_k", dynAttr.gk()).set("id_v", model.getInt(dynAttr.id_k())).set("cate", dynAttr.getCate());
        if (ss.length > 0) kv.set("attr", arrToStr(ss));

        SqlPara sqlPara = Db.getSqlPara("m.dyn_attr", kv);
        List<Record> attrs = Db.find(sqlPara);
        attrs.forEach(x-> model.put(x.get("attr"), x.get("value")));
    }

    private String arrToStr(String ... ss){
        String str = "";
        for (String x : ss){
            str += "'"+x+"',";
        }
        if (str.length() > 0)
            str = str.substring(0, str.length()-1);
        return str;
    }

}
