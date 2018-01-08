package com.lxyer.controller;

import com.jfinal.aop.Before;
import com.jfinal.kit.Kv;
import com.lxyer.config.JsonBean;
import com.lxyer.config.interceptor.LoginInterceptor;
import com.lxyer.model.Content;
import com.lxyer.service.ContentService;

import java.util.List;

/**
 * Created by JUECHENG at 2018/1/7 16:48.
 */
public class JieController extends IController{

    private ContentService contentService = ContentService.me;
    private int userId;

    /**
     * 帖子详情
     */
    public void index(){
        int contentId = getParaToInt(0);

        //ContentInfo content = contentService.contentInfo(sessionid, contentid);
        //Sheet<CommentInfo> comments = commentService.commentQuery(request.getSessionid(false) ,contentid, new Flipper().limit(30));

        Content content = Content.dao.findFirst(Kv.by("contentId", contentId));

        //热议
        List<Content> hotReply = Content.dao.findPage(1, 8, Kv.by("order", "replyNum DESC")).getList();

        setAttr("bean", content);
        setAttr("hotReply", hotReply);

        render("detail.html");
    }

    /**
     * 添加/修改帖子
     */
    @Before(LoginInterceptor.class)
    public void add(){
        setAttr("bean", Content.dao.findById(getParaToInt()));

        render("add.html");
    }

    /**
     * 帖子保存
     */
    @Before(LoginInterceptor.class)
    public void save() {
        Content content = getModel(Content.class);

        contentService.save(content, getUserId());

        renderJson(JsonBean.success());
    }

}
