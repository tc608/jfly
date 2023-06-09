package com.lxyer.model;

import com.lxyer.model.base.BaseUser;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class User extends BaseUser<User> {
    public static final User dao = new User().dao();

    @Override
    public String sqlSpace() {
        return "user";
    }

    @Override
    public User getDao() {
        return dao;
    }
}
