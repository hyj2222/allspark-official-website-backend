package com.allspark.allsparkofficialwebsite.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理员信息（不含密码）
 */
@Data
public class AdminUserVO implements Serializable {

    private Integer adminId;

    private String username;

    private String nickname;
}