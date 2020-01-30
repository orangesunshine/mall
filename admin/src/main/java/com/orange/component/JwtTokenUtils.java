package com.orange.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {
    Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtils.class.getSimpleName());

    final String CLAIM_KEY_CREATED = null;//负载：token创建时间 健
    final String CLAIM_KEY_USERNAME = "sub";//载：用户名 健

    @Value("{jwt.expiration}")
    long expiration = 120;//过期时长
    @Value("{jwt.secret}")
    String secret;//密钥

    /**
     * 验证token是否有效
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String usernameFromToken = getUsernameFromToken(token);
        return null != userDetails
                && StringUtils.equals(usernameFromToken, userDetails.getUsername())
                && !isTokenExpired(token);
    }

    /**
     * 负载 -> token
     *
     * @param claims
     * @return
     */
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成过期时间：当前时间+expiration 秒
     *
     * @return
     */
    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * token -> 负载 -> 用户名
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            if (null != claims) {
                username = claims.getSubject();
            }
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * token -> 负载
     *
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.error("jwt token 解析失败", e);
        }
        return claims;
    }

    /**
     * token -> 负载 -> 过期时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (null != claims) {
            return claims.getExpiration();
        }
        return null;
    }

    /**
     * userDetails -> token
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        if (null != userDetails) {
            Map<String, Object> claims = new HashMap<>();
            claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
            claims.put(CLAIM_KEY_CREATED, new Date());
        }
        return null;
    }

    /**
     * 判断token是否失效
     *
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpirationDateFromToken(token);
        if (null != expiredDate) {
            return expiredDate.before(new Date());
        }
        return false;
    }

    /**
     * 判断token是否可以被刷新
     *
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return isTokenExpired(token);
    }

    /**
     * 刷新token
     *
     * @param token
     */
    public String refresh(String token) {
        Claims claims = getClaimsFromToken(token);
        if (null != claims) {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
        return null;
    }
}
