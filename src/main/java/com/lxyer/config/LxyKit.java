package com.lxyer.config;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

/**
 * Created by Lxy at 2017/11/29 15:17.
 */
public final class LxyKit {

    public static String dateFmt(long time){
        /**
         * 刚刚       60秒内        60 * 1000
         * x分钟前     1小时候内    60 * 60*1000
         * x小时前     1天内        24 * 60*60*1000
         * x天前       1周内        7 * 24*60*60*1000
         * 年-月-日    1周前
         */
        long now = System.currentTimeMillis();

        long diff = now - time;
        if (diff < 60 * 1000)
            return "刚刚";
        else if (diff < 60 * 60 *1000)
            return Math.floorDiv(diff, 60 *1000) + "分钟前";
        else if (diff < 24 * 60*60*1000)
            return Math.floorDiv(diff, 60 *60*1000) + "小时前";
        else if (diff > 24 * 60*60*1000 && diff < 7 * 24*60*60*1000)
            return Math.floorDiv(diff, 24 * 60*60*1000) + "天前";
        else
            return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }

    public static String md5IfNeed(String password){
        if (password == null || password.isEmpty()) return "";
        if (password.length() == 32) return password;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] bytes = password.trim().getBytes();
        bytes = md5.digest(bytes);

        return HexBin.encode(bytes);
    }
}
