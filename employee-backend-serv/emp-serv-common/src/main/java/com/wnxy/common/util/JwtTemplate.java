package com.wnxy.common.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.Map;

@Component
public class JwtTemplate {

    /**密钥文件路径*/
    private String path = "sdd.jks";


    /**密钥库的密码*/
    private String keyStoreSecurity = "123456";

    /**密钥库的别名*/
    private String alias = "sdd";


    /**
     * 创建密钥对
     */
    public KeyPair keyPair() {
        // 创建密钥对工厂，通过加载sdd.jks配置文件，获取密钥对
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(
                new ClassPathResource(path), keyStoreSecurity.toCharArray());
        KeyPair keyPair = factory.getKeyPair(alias);

        return keyPair;
    }

    /**
     * 创建签名器
     */
    public JWTSigner createJwtSigner() {
        return JWTSignerUtil.createSigner("RSA", keyPair());
    }


    /**
     * 生成token
     */
    public String createToken(Map<String, Object> payload) {
        return JWTUtil.createToken(payload, createJwtSigner());
    }

    /**
     * 验证token
     */
    public boolean verifyToken(String token) {
        return JWTUtil.verify(token, createJwtSigner());
    }

    /**
     * 解析token
     */
    public Object parseToken(String token, String name) {
        JWT jwt = JWTUtil.parseToken(token);

        return jwt.getPayload(name);

    }


}
