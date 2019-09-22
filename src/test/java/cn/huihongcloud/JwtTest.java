package cn.huihongcloud;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/5/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    @Value("${cn.huihongcloud.jwt.key}")
    private String key;

    public String createTokenTest() {
        Date iaDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 1);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = null;
        try {
            token = JWT.create()
                    .withHeader(map)
                    .withClaim("name", "haha")
                    .withExpiresAt(expiresDate)
                    .withIssuedAt(iaDate)
                    .sign(Algorithm.HMAC256("zhh"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(token);
        return token;
    }

    public void verifyTokenTest(String token) {
        JWTVerifier jwtVerifier = null;
        try {
            jwtVerifier = JWT.require(Algorithm.HMAC256("zhh"))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DecodedJWT jwt = null;
        try {
            jwt = jwtVerifier.verify(token);

        } catch (Exception e) {
            System.out.println("验证失败");
        }
        System.out.println(jwt.getClaims().get("name").asString());
    }
    @Test
    public void createAndVerify() {
        System.out.println(key);
        verifyTokenTest(createTokenTest());
    }
}
