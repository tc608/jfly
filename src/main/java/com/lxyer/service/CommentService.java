package com.lxyer.service;

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
}
