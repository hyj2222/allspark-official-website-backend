package com.allspark.allsparkofficialwebsite.util;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * 密码工具类
 * 规则：sha256(固定盐 + 用户名 + ":" + 明文密码)
 */
public class PasswordUtils {

    private static final String SALT = "allspark";

    private PasswordUtils() {
    }

    public static String encode(String username, String rawPassword) {
        return DigestUtil.sha256Hex(SALT + username + ":" + rawPassword);
    }

    public static boolean matches(String username, String rawPassword, String encoded) {
        return encode(username, rawPassword).equalsIgnoreCase(encoded);
    }
}