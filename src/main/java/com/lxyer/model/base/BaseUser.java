package com.lxyer.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean,IModel<M> {

	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}
	
	public java.lang.Integer getUserId() {
		return getInt("userId");
	}

	public void setUsername(java.lang.String username) {
		set("username", username);
	}
	
	public java.lang.String getUsername() {
		return getStr("username");
	}

	public void setSex(java.lang.Integer sex) {
		set("sex", sex);
	}
	
	public java.lang.Integer getSex() {
		return getInt("sex");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}
	
	public java.lang.String getPhone() {
		return getStr("phone");
	}

	public void setNickname(java.lang.String nickname) {
		set("nickname", nickname);
	}
	
	public java.lang.String getNickname() {
		return getStr("nickname");
	}

	public void setAvatar(java.lang.String avatar) {
		set("avatar", avatar);
	}
	
	public java.lang.String getAvatar() {
		return getStr("avatar");
	}

	public void setRealname(java.lang.String realname) {
		set("realname", realname);
	}
	
	public java.lang.String getRealname() {
		return getStr("realname");
	}

	public void setEmail(java.lang.String email) {
		set("email", email);
	}
	
	public java.lang.String getEmail() {
		return getStr("email");
	}

	public void setCreateTime(java.lang.Long createTime) {
		set("createTime", createTime);
	}
	
	public java.lang.Long getCreateTime() {
		return getLong("createTime");
	}

	public void setSign(java.lang.String sign) {
		set("sign", sign);
	}
	
	public java.lang.String getSign() {
		return getStr("sign");
	}

	public void setCity(java.lang.String city) {
		set("city", city);
	}
	
	public java.lang.String getCity() {
		return getStr("city");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

}
