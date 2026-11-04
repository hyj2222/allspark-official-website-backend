package com.allspark.allsparkofficialwebsite.controller;

import com.allspark.allsparkofficialwebsite.common.BaseResponse;
import com.allspark.allsparkofficialwebsite.common.ErrorCode;
import com.allspark.allsparkofficialwebsite.common.ResultUtils;
import com.allspark.allsparkofficialwebsite.config.AuthInterceptor;
import com.allspark.allsparkofficialwebsite.exception.BusinessException;
import com.allspark.allsparkofficialwebsite.model.AdminUser;
import com.allspark.allsparkofficialwebsite.model.dto.AdminLoginRequest;
import com.allspark.allsparkofficialwebsite.model.vo.AdminUserVO;
import com.allspark.allsparkofficialwebsite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 运营端登录接口
 */
@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public BaseResponse login(@Valid @RequestBody AdminLoginRequest request, HttpSession session) {
        AdminUser admin = adminService.login(request.getUsername(), request.getPassword());
        session.setAttribute(AuthInterceptor.LOGIN_ADMIN, admin);
        return ResultUtils.success(toVO(admin));
    }

    @PostMapping("/logout")
    public BaseResponse logout(HttpSession session) {
        session.invalidate();
        return ResultUtils.success(null);
    }

    @GetMapping("/me")
    public BaseResponse me(HttpSession session) {
        AdminUser admin = (AdminUser) session.getAttribute(AuthInterceptor.LOGIN_ADMIN);
        if (admin == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return ResultUtils.success(toVO(admin));
    }

    private AdminUserVO toVO(AdminUser admin) {
        AdminUserVO vo = new AdminUserVO();
        vo.setAdminId(admin.getAdminId());
        vo.setUsername(admin.getUsername());
        vo.setNickname(admin.getNickname());
        return vo;
    }
}