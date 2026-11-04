package com.allspark.allsparkofficialwebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.allspark.allsparkofficialwebsite.common.ErrorCode;
import com.allspark.allsparkofficialwebsite.exception.BusinessException;
import com.allspark.allsparkofficialwebsite.mapper.AdminUserMapper;
import com.allspark.allsparkofficialwebsite.model.AdminUser;
import com.allspark.allsparkofficialwebsite.service.AdminService;
import com.allspark.allsparkofficialwebsite.util.PasswordUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码不能为空");
        }
        AdminUser admin = adminUserMapper.selectOne(new QueryWrapper<AdminUser>().eq("username", username));
        if (admin == null || !PasswordUtils.matches(username, password, admin.getPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码错误");
        }
        return admin;
    }
}