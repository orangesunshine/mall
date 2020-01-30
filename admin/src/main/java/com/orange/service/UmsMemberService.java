package com.orange.service;

public interface UmsMemberService {
    /**
     * 生成验证码
     *
     * @return
     */
    String generatorCode(String telephone);

    /**
     * 校验验证码
     *
     * @param telephone
     * @param authCode
     * @return
     */
    boolean verifyCode(String telephone, String authCode);
}
