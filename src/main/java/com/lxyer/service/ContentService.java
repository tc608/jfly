package com.lxyer.service;

import com.jfinal.kit.Kv;
import com.lxyer.model.ActLog;
import com.lxyer.model.Content;

/**
 * Created by JUECHENG at 2018/1/7 16:49.
 */
public class ContentService {

    public static final ContentService me = new ContentService();

    /**
     * 帖子保存
     * @param content
     * @param userId
     */
    public void save(Content content, int userId) {
        if (content.getContentId() == null) {
            content.setCreateTime(System.currentTimeMillis());
            content.setUserId(userId);
            content.save();
        }else {
            content.update();
        }
    }

    /**
     * 删除帖子
     * @param contentId
     * @param userId
     * @throws Exception
     */
    public void del(Integer contentId, Integer userId) throws Exception {
        Content content = Content.dao.findById(contentId);

        if (content == null || content.getStatus() == -1) return;

        //安全校验
        if (userId != 10_0001 && content.getUserId() != userId)
            throw new Exception("操作失败：无权操作");

        content.setStatus(-1);
        content.update();
    }

    /**
     * 帖子收藏
     * @param contentId
     * @param userId
     * @param status
     * @throws Exception
     */
    public void collect(Integer contentId, Integer userId, int status) throws Exception {
        Content content = Content.dao.findById(contentId);
        if (content == null)
            throw new Exception("操作失败，未查询到相关内容");

        Kv kv = Kv.by("tid", contentId).set("userId", userId).set("cate", 2);//cate:2收藏
        ActLog actLog = ActLog.dao.findFirst(kv);

        if (actLog == null){
            actLog = new ActLog();
            actLog.setCate(2);
            actLog.setTid(contentId);
            actLog.setUserId(userId);
            actLog.setCreateTime(System.currentTimeMillis());
            actLog.save();
        }else if (actLog.getStatus() != status){
            actLog.setStatus(status == 1 ? 1 : -1);
            actLog.setCreateTime(System.currentTimeMillis());
            actLog.update();
        }

        //更新收藏数

    }

    /**
     * 帖子置顶/加精
     * @param contentId
     * @param field
     * @param v
     * @param userId
     * @throws Exception
     */
    public void upField(Integer contentId, String field, Integer v, int userId) throws Exception {
        Content content = Content.dao.findById(contentId);

        if (content == null)
            throw new Exception("操作失败，未查询到相关内容");

        if ("top".equals(field) && userId != 10_0001)
            throw new Exception("操作失败：无权限进行此操作");

        content.set(field, v);
        content.update();
    }
}
