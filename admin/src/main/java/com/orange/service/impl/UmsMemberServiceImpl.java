package com.orange.service.impl;

import com.orange.service.RedisService;
import com.orange.service.UmsMemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;


    @Override
    public String generatorCode(String telephone) {
        Random random = new Random();
        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            buff.append(random.nextInt(10));
        }
        String code = buff.toString();
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, code);
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return code;
    }

    @Override
    public boolean verifyCode(String telephone, String authCode) {
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return StringUtils.equals(realAuthCode, authCode);
    }
}
