package org.fdu.awt.minifduuser.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifduuser.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author Violette
 * @date 2024/4/16 19:36
 */
@Slf4j
public class JWTUtils {
    // 随机生成的密钥（16位字符串）
    private static final String SING = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);

    /**
     * 生成token
     */
    public static String getToken(User user) {
        log.info("SING:{}", SING);
        // 默认7天过期
        Calendar instance = Calendar.getInstance();  // GMT+8
        instance.add(Calendar.DATE, 7);

        // 生成token
        String token = JWT.create()
                .withClaim("id", user.getId().toString())
                .withClaim("name", user.getName())
                .withExpiresAt(instance.getTime()) // token过期时间
                .sign(Algorithm.HMAC256(SING));    // 密钥

        return token;
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /**
     * 获取token过期时间
     */
    public static Date getExpirationTime(String token) {
        DecodedJWT decodedJWT = verify(token);
        return decodedJWT.getExpiresAt();
    }
}
