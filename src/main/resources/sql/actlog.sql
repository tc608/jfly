### 帖子操作日志 para:[logid, cate, tid, userId, status]
#sql("actLog.list")
  SELECT al.*
  FROM `comment` al
  WHERE al.status!=-1
  #if(logid)
    AND al.`logid`=#(logid)
  #end
  #if(cate)
    AND al.`cate`=#(cate)
  #end
  #if(tid)
    AND al.`tid`=#(tid)
  #end
  #if(userId)
    AND al.`userId`=#(userId)
  #end
  #if(status)
    AND al.`status`=#(status)
  #end
#end