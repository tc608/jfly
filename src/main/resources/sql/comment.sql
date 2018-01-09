### 帖子评论 para:[commentId, userId, pid, cate, contentId, status]
#sql("comment.list")
  SELECT c.*
    ,u.nickname,u.avatar
  FROM `comment` c
    LEFT JOIN user u ON c.userId=u.userId
  WHERE c.status!=-1
  #if(commentId)
    AND c.`commentId`=#(commentId)
  #end
  #if(userId)
    AND c.`userId`=#(userId)
  #end
  #if(pid)
    AND c.`pid`=#(pid)
  #end
  #if(cate)
    AND c.`cate`=#(cate)
  #end
  #if(contentId)
    AND c.`contentId`=#(contentId)
  #end
  #if(status)
    AND c.`status`=#(status)
  #end
#end