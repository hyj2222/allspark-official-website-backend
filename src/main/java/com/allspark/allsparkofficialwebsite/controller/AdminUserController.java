package com.allspark.allsparkofficialwebsite.controller;

import com.alibaba.excel.EasyExcel;
import com.allspark.allsparkofficialwebsite.common.BaseResponse;
import com.allspark.allsparkofficialwebsite.common.ResultUtils;
import com.allspark.allsparkofficialwebsite.model.User;
import com.allspark.allsparkofficialwebsite.model.dto.PageResult;
import com.allspark.allsparkofficialwebsite.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 运营端报名数据管理接口
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public BaseResponse pageUsers(@RequestParam(defaultValue = "1") long current,
                                  @RequestParam(defaultValue = "10") long size,
                                  @RequestParam(required = false) String userName,
                                  @RequestParam(required = false) String direction,
                                  @RequestParam(required = false) Integer grade,
                                  @RequestParam(required = false) String qqNumber) {
        IPage<User> page = userService.pageUsers(current, size, userName, direction, grade, qqNumber);
        PageResult<User> result = new PageResult<>(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent(), page.getPages());
        return ResultUtils.success(result);
    }

    @GetMapping("/users/export")
    public void exportUsers(@RequestParam(required = false) String direction,
                            @RequestParam(required = false) Integer grade,
                            HttpServletResponse response) throws IOException {
        List<User> users = userService.listUsersForExport(direction, grade);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("报名信息", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), User.class).sheet("报名信息").doWrite(users);
    }

    @DeleteMapping("/user/{id}")
    public BaseResponse deleteUser(@PathVariable("id") Integer id) {
        boolean removed = userService.removeById(id);
        if (!removed) {
            return ResultUtils.error(50001, "删除失败，用户不存在");
        }
        return ResultUtils.success(null);
    }
}