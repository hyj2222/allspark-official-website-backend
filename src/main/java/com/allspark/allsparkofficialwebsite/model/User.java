package com.allspark.allsparkofficialwebsite.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@TableName(value = "user")
@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    @ExcelProperty("ID")
    private Integer userId;

    @ExcelProperty("姓名")
    private String userName;

    @ExcelProperty("QQ号")
    private String qqNumber;

    @ExcelProperty("年级")
    private Integer grade;

    @ExcelProperty("方向")
    private String direction;

    @ExcelProperty("学习进展")
    private String progress;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("报名时间")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}