package com.example.util;

import java.util.Random;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/10/26 20:41
 */
public class SaltUtils {
    /**
     * 生成salt的静态方法
     * @param n
     * @return
     */
    public static String getSalt(int n){
        char[] chars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            //拼接字符
            sb.append(aChar);
        }
        return sb.toString();

    }
}
