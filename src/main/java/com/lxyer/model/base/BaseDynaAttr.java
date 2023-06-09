package com.lxyer.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDynaAttr<M extends BaseDynaAttr<M>> extends Model<M> implements IBean {

	public void setTid(java.lang.Integer tid) {
		set("tid", tid);
	}
	
	public java.lang.Integer getTid() {
		return getInt("tid");
	}

	public void setCate(java.lang.Integer cate) {
		set("cate", cate);
	}
	
	public java.lang.Integer getCate() {
		return getInt("cate");
	}

	public void setAttr(java.lang.String attr) {
		set("attr", attr);
	}
	
	public java.lang.String getAttr() {
		return getStr("attr");
	}

	public void setValue(java.lang.String value) {
		set("value", value);
	}
	
	public java.lang.String getValue() {
		return getStr("value");
	}

}
