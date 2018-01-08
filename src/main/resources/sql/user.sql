#sql("user.list")
  SELECT * FROM `user` u
  WHERE u.status != -1
  #if(userId)
    AND u.`userId` = #(userId)
  #end
  #if(username)
    AND u.`username` = '#(username)'
  #end
  #if(email)
    AND u.`email` = '#(email)'
  #end

  #if(order)
    ORDER BY #(order)
  #else
    ORDER BY u.createTime DESC
  #end
#end