package com.lxyer.config.handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JUECHENG at 2018/1/7 11:20.
 */
public class UrlHandler extends Handler {
    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {

        next.handle(target, request, response, isHandled);
    }
}
