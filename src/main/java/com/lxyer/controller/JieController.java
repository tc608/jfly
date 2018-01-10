package com.lxyer.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.lxyer.config.JBean;
import com.lxyer.config.interceptor.LoginInterceptor;
import com.lxyer.model.Comment;
import com.lxyer.model.Content;
import com.lxyer.service.ContentService;

import java.util.List;

/**
 * Created by JUECHENG at 2018/1/7 16:48.
 */
@Before(LoginInterceptor.class)
public class JieController extends IController{

    private ContentService service = ContentService.me;
    private int userId;

    /**
     * 帖子详情
     */
    @Clear(LoginInterceptor.class)
    public void index(){
        int contentId = getParaToInt(0);

        Content content = Content.dao.findFirst(Kv.by("contentId", contentId));

        //评论
        Page<Comment> comments = Comment.dao.findPage(getPn(), getPs(), Kv.by("contentId", contentId).set("login_user_id", getUserId()));

        //热议
        List<Content> hotReply = Content.dao.findPage(1, 8, Kv.by("order", "replyNum DESC")).getList();

        setAttr("bean", content);
        setAttr("comments", comments);
        setAttr("hotReply", hotReply);

        //todo: 访问量+1

        render("detail.html");
    }

    /**
     * 添加/修改帖子
     */
    public void add(){
        setAttr("bean", Content.dao.findById(getParaToInt()));

        render("add.html");
    }

    /**
     * 帖子保存
     */
    public void save() {
        Content content = getModel(Content.class);

        service.save(content, getUserId());

        renderJson(JBean.success);
    }

    /**
     * 帖子删除
     */
    public void del(){
        JBean bean = new JBean(1);
        try {
            service.del(getParaToInt("contentId"), getUserId());
        } catch (Exception e) {
            bean.setCode(-1, e.getMessage());
        }

        renderJson(bean);
    }

    /**
     * 帖子收藏
     */
    public void collect(){
        JBean bean = new JBean(1);

        Integer contentId = getParaToInt("contentId");
        Integer status = getParaToInt("ok", 1);
        try {
            service.collect(contentId, getUserId(), status);

        } catch (Exception e) {
            bean.setCode(-1, e.getMessage());
        }

        renderJson(bean);
    }

    /**
     * 帖子加精/置顶
     */
    public void set(){
        JBean bean = new JBean(1);

        Integer contentId = getParaToInt("id");
        String field = getPara("field");
        Integer v = getParaToInt("v");

        try {
            service.upField(contentId, field, v, getUserId());
        } catch (Exception e) {
            bean.setCode(-1, e.getMessage());
        }

        renderJson(bean);
    }
}
