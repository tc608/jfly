package com.lxyer.model.base;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

import java.util.List;

/**
 * Created by JUECHENG at 2018/1/7 16:52.
 */
public interface IModel<M extends IModel<M>> {

    String sqlSpace();

    default List<M> findList(Kv kv){
        SqlPara sqlPara = Db.getSqlPara(sqlSpace()+".list", kv);

        return (List) Db.find(sqlPara);
    }

    default Record findFirst(Kv kv){
        SqlPara sqlPara = Db.getSqlPara(sqlSpace()+".list", kv);

        return Db.findFirst(sqlPara);
    }

    default Page<M> findPage(int pn, int ps, Kv kv){
        SqlPara sqlPara = Db.getSqlPara(sqlSpace()+".list", kv);

        return (Page) Db.paginate(pn, ps, sqlPara);
    }

}
