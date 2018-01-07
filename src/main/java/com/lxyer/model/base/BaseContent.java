package com.lxyer.model.base;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseContent<M extends BaseContent<M>> extends Model<M> implements IBean,IModel<M> {

	public void setContentId(java.lang.Integer contentId) {
		set("contentId", contentId);
	}
	
	public java.lang.Integer getContentId() {
		return getInt("contentId");
	}

	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}
	
	public java.lang.Integer getUserId() {
		return getInt("userId");
	}

	public void setTitle(java.lang.String title) {
		set("title", title);
	}
	
	public java.lang.String getTitle() {
		return getStr("title");
	}

	public void setDigest(java.lang.String digest) {
		set("digest", digest);
	}
	
	public java.lang.String getDigest() {
		return getStr("digest");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	public void setCreateTime(java.lang.Long createTime) {
		set("createTime", createTime);
	}
	
	public java.lang.Long getCreateTime() {
		return getLong("createTime");
	}

	public void setCate(java.lang.Integer cate) {
		set("cate", cate);
	}
	
	public java.lang.Integer getCate() {
		return getInt("cate");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public void setReplyNum(java.lang.Integer replyNum) {
		set("replyNum", replyNum);
	}
	
	public java.lang.Integer getReplyNum() {
		return getInt("replyNum");
	}

	public void setViewNum(java.lang.Integer viewNum) {
		set("viewNum", viewNum);
	}
	
	public java.lang.Integer getViewNum() {
		return getInt("viewNum");
	}

	public void setWonderful(java.lang.Integer wonderful) {
		set("wonderful", wonderful);
	}
	
	public java.lang.Integer getWonderful() {
		return getInt("wonderful");
	}

	public void setTop(java.lang.Integer top) {
		set("top", top);
	}
	
	public java.lang.Integer getTop() {
		return getInt("top");
	}

	public void setSolved(java.lang.Integer solved) {
		set("solved", solved);
	}
	
	public java.lang.Integer getSolved() {
		return getInt("solved");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

}
