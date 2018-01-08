package com.lxyer.model.base;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.*;

import java.util.List;

/**
 * Created by JUECHENG at 2018/1/7 16:52.
 */
public interface IModel<M extends Model<M>> {

    String sqlSpace();
    M getDao();

    default List<M> findList(Kv kv){
        SqlPara sqlPara = getDao().getSqlPara(sqlSpace()+".list", kv);

        return getDao().find(sqlPara);
    }

    default M findFirst(Kv kv){
        SqlPara sqlPara = Db.getSqlPara(sqlSpace()+".list", kv);
        return getDao().findFirst(sqlPara);
    }

    default Page<M> findPage(int pn, int ps, Kv kv){
        SqlPara sqlPara = Db.getSqlPara(sqlSpace()+".list", kv);

        return getDao().paginate(pn, ps, sqlPara);
    }

}
