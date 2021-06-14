package com.marlowe.music.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @program: music
 * @description: JWT工具类
 * @author: Marlowe
 * @create: 2021-06-11 17:04
 **/
public class JWTUtils {

    private static final String SING = "FAHGAGAJG:G@R";

    /**
     * 生成token
     *
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map) {

        Calendar instance = Calendar.getInstance();
        // 默认7天过期
        instance.add(Calendar.DATE, 7);

        // 创建JWT builder
        JWTCreator.Builder builder = JWT.create();

        // payload
        map.forEach(builder::withClaim);

        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
        return token;
    }


    /**
     * 验证token 合法性
     *
     * @param token
     * @return
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }


//    /**
//     * 获取token信息
//     *
//     * @param token
//     * @return
//     */
//    public static DecodedJWT getTokenInfo(String token) {
//        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
//        return verify;
//    }
}
