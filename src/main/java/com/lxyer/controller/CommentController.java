package com.lxyer.controller;

import com.jfinal.aop.Before;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.lxyer.config.JBean;
import com.lxyer.config.interceptor.LoginInterceptor;
import com.lxyer.model.Comment;
import com.lxyer.service.CommentService;

/**
 * Created by JUECHENG at 2018/1/8 23:07.
 */
public class CommentController extends IController {

    CommentService service = CommentService.me;
    /**
     * 评论列表
     */
    public void list(){
        Kv kv = getParams("contentId");
        Page<Comment> page = Comment.dao.findPage(getPn(), getPs(), kv);

        renderJBean(page);
    }

    /**
     * 评论详情
     */
    public void info(){

    }

    /**
     * 评论保存
     */
    @Before(LoginInterceptor.class)
    public void save(){
        Comment comment = getModel(Comment.class);

        service.save(comment, getUserId());
        renderJBean(JBean.success);
    }

    /**
     * todo:更新状态
     */
    public void update_status(){

    }

    /**
     * todo:评论点赞
     */
    public void support(){

    }
}
