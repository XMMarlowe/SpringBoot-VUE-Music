package com.marlowe.music.utils;

import java.util.Random;

/**
 * @program: music
 * @description: 随机盐工具类
 * @author: Marlowe
 * @create: 2021-06-12 18:48
 **/
public class SaltUtils {

    public static String getSalt(int n) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}
