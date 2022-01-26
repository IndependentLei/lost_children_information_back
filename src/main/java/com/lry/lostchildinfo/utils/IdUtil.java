package com.lry.lostchildinfo.utils;

import java.util.Random;

/**
 * @author : jdl
 * @description : 生成随机Id
 * @ClassName : IdUtil
 * @create : 2022-01-25 0:53
 */
public class IdUtil {
    public static String genImageName(){
        long millis = System.currentTimeMillis();
        String str = millis+String.format("%03d",new Random().nextInt(999));
        return str;
    }
}
