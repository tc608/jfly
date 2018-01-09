#sql("content.list")
  SELECT c.*,u.nickname,u.avatar
  FROM content c LEFT JOIN user u ON c.userId=u.userId
  WHERE c.status != -1
  #if(type)
    AND c.`type`=#(type)
  #end
  #if(contentId)
    AND c.`contentId`=#(contentId)
  #end

  #if(order)
    ORDER BY #(order)
  #elseif(top == 1)
    AND c.top > 0
    ORDER BY top DESC,createTime DESC
  #elseif(top == "0")
    AND c.top = 0
    ORDER BY createTime DESC
  #end
#end

### 更新评论数 para:[contentId]
#sql("content.upReplyNum")
  UPDATE content c SET c.replyNum=
    (SELECT COUNT(*) FROM comment WHERE contentId=c.contentId AND status=1)
  #if(contentId)
    WHERE c.contentId=#(contentId)
  #end
#end