package com.lxyer.controller;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lxyer.model.Content;
import com.lxyer.model.User;

import java.util.List;

/**
 * Created by JUECHENG at 2018/1/7 14:40.
 */
public class HomeController extends IController {
    public static final Kv column = Kv.by("qz", 10).set("fx", 20).set("jy", 30).set("gg", 40).set("dt", 50);//栏目

    public void index(){

        //置顶贴
        List<Content> top = Content.dao.findPage(1, 5, Kv.by("top", 1)).getList();

        //非置顶贴
        List<Content> contents = Content.dao.findPage(1, 30,Kv.by("top", 0)).getList();

        //热帖

        //热议
        List<Content> hotReply = Content.dao.findPage(1, 8, Kv.by("order", "replyNum DESC")).getList();

        //最新加入
        List<User> lastReg = User.dao.findPage(1, 8, Kv.by("order", "createTime DESC")).getList();

        setAttr("top", top);
        setAttr("contents", contents);
        setAttr("hotReply", hotReply);
        setAttr("lastReg", lastReg);

        render("index.html");
    }

    /**
     * 帖子栏目列表
     */
    public void column(){
        String para = getPara(0, "");
        int solved = getParaToInt("solved", -1);
        int wonderful = getParaToInt("wonderful", -1);

        Kv kv = Kv.by("type", column.get(para)).set("order", "top DESC,createTime DESC");
        Page<Content> contents = Content.dao.findPage(getPn(), getPs(3), kv);

        //热议
        List<Content> hotReply = Content.dao.findPage(1, 8, Kv.by("order", "replyNum DESC")).getList();

        setAttrs(Kv.by("contents", contents).set("hotReply", hotReply)
                .set("solved", solved).set("wonderful", wonderful).set("column", para));

        render("jie/index.html");
    }

    /**
     * 帖子详情
     */
    public void jie(){
        int contentId = getParaToInt(0);

        //ContentInfo content = contentService.contentInfo(sessionid, contentid);
        //Sheet<CommentInfo> comments = commentService.commentQuery(request.getSessionid(false) ,contentid, new Flipper().limit(30));

        Record content = Content.dao.findFirst(Kv.by("contentId", contentId));

        //热议
        List<Content> hotReply = Content.dao.findPage(1, 8, Kv.by("order", "replyNum DESC")).getList();

        setAttr("bean", content);
        setAttr("hotReply", hotReply);

        render("jie/detail.html");
    }


}
