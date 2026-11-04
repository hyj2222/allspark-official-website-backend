package com.allspark.allsparkofficialwebsite.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@TableName(value = "admin_user")
@Data
public class AdminUser implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer adminId;

    private String username;

    private String password;

    private String nickname;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}