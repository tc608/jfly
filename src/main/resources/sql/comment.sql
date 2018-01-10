### 帖子评论 para:[commentId, userId, pid, cate, contentId, status]
#sql("comment.list")
  SELECT c.*
    ,u.nickname,u.avatar
    #if(login_user_id)
      ,(SELECT IFNULL((SELECT al.logid FROM act_log al WHERE al.userId=#(login_user_id) AND al.cate=1 AND al.status=1 AND al.tid=c.commentId),-1)) AS 'hadSupport'
    #end
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


### 更新点赞数 para:[contentId]
#sql("comment.upSupportNum")
  UPDATE comment c SET c.supportNum=
    (SELECT COUNT(*) FROM act_log WHERE tid=c.commentId AND status=1 AND cate=1)
  #if(commentId)
    WHERE c.commentId=#(commentId)
  #end
#end