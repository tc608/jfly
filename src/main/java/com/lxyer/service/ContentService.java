package com.lxyer.service;

import com.lxyer.model.Content; /**
 * Created by JUECHENG at 2018/1/7 16:49.
 */
public class ContentService {

    public static final ContentService me = new ContentService();

    public void save(Content content, int userId) {
        if (content.getContentId() == null) {
            content.setCreateTime(System.currentTimeMillis());
            content.setUserId(userId);
            content.save();
        }else {
            content.update();
        }
    }
}
