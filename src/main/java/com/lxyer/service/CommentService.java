package com.lxyer.service;

import com.jfinal.kit.Kv;
import com.lxyer.model.ActLog;
import com.lxyer.model.Comment;
import com.lxyer.model.Content;

/**
 * Created by JUECHENG at 2018/1/9 11:59.
 */
public class CommentService {
    public static final CommentService me = new CommentService();


    public void save(Comment comment, Integer userId) {
        if (comment.getCommentId() == null){
            comment.setUserId(userId);
            comment.setCreateTime(System.currentTimeMillis());
            comment.save();
        }else {
            comment.update();
        }

        //更新评论数
        Content.upReplyNum(comment.getContentId());
    }

    /**
     * 评论点赞
     * @param commentId
     * @param ok
     * @param userId
     * @throws Exception
     */
    public void support(Integer commentId, Integer ok, Integer userId) throws Exception {
        Comment comment = Comment.dao.findById(commentId);
        if (comment == null)
            throw new Exception("操作失败，未查询到相关信息");

        ActLog actLog = ActLog.dao.findFirst(Kv.by("userId", userId).set("tid", commentId).set("cate", 1));
        if (actLog == null){
            actLog = new ActLog();
            actLog.setTid(commentId);
            actLog.setUserId(userId);
            actLog.setCate(1);
            actLog.setStatus(1);
            actLog.setCreateTime(System.currentTimeMillis());
            actLog.save();
        }else if (actLog.getStatus() != ok){
            actLog.setStatus(ok == 1 ? 1 : -1);
            actLog.setCreateTime(System.currentTimeMillis());
            actLog.update();
        }

        Comment.upSupportNum(commentId);
    }
}
