package cn.huihongcloud.component;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/5/6
 */
@Component
public class JWTComponent {

    @Value("${cn.huihongcloud.jwt.key}")
    private String key;

    @Value("${cn.huihongcloud.jwt.expire-time}")
    private int expireTime;

    public String createToken(String userName) {
        Date iaDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, expireTime);
        Date expiresDate = nowTime.getTime();
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = null;
        try {
            token = JWT.create()
                    .withHeader(map)
                    .withClaim("name", userName)
                    .withExpiresAt(expiresDate)
                    .withIssuedAt(iaDate)
                    .sign(Algorithm.HMAC256(key));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    public String verify(String token) {
        JWTVerifier jwtVerifier = null;
        try {
            jwtVerifier = JWT.require(Algorithm.HMAC256(key))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        DecodedJWT jwt = null;
        try {
            jwt = jwtVerifier.verify(token);

        } catch (Exception e) {
            return null;
        }
        return jwt.getClaim("name").asString();
    }
}
