package com.lxyer.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lxyer lxy208@126.com at 2016/8/4 0:13.
 */
public class JBean {
    public int code;//全局状态码： -1失败，1成功，2未登录
    public String msg;
    public Object obj;

    public static final JBean success = new JBean(1, "操作成功");
    public static final JBean error = new JBean(-1, "操作失败");

    public JBean(int code){
        this.code = code;
        if (code == 1)
            this.msg = "操作成功";
        if (code == -1)
            this.msg = "操作失败";
        if (code == 2)
            this.msg = "未登录，请前往登录";
    }

    public JBean(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public JBean(int code, String msg, Object obj){
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public JBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public JBean setCode(int code) {
        this.code = code;
        if (code == 1)
            this.msg = "操作成功";
        if (code == -1)
            this.msg = "操作失败";
        return this;
    }

    public JBean setCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }
    public JBean setCode(int code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public JBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }
    public <K, V> JBean set(Object k, Object v){
        if (!(obj instanceof Map)){
            obj = new HashMap();
        }
        ((Map) obj).put(k, v);
        return this;
    }


    /*
        下面的是一些快速创建JsonBean的方式，
        使用中可以使用new ，同时也可以直接使用
     */
    public static JBean success(Object obj){
        return new JBean(1, "操作成功",obj);
    }
    public static JBean success(int code, String msg){
        return new JBean(1, msg);
    }
    public static JBean success(int code, String msg, Object obj){
        return new JBean(1, msg, obj);
    }

    public static JBean error(int code, String msg){
        return new JBean(code, msg);
    }
    public static JBean error(int code, String msg, Object obj){
        return new JBean(code, msg, obj);
    }
    public static JBean error(String msg){
        return new JBean(-1, msg);
    }

    @Override
    public String toString() {
        return "JBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }
}
