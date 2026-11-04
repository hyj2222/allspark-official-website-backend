package com.allspark.allsparkofficialwebsite.service.impl;

import com.allspark.allsparkofficialwebsite.model.User;
import com.allspark.allsparkofficialwebsite.mapper.UserMapper;
import com.allspark.allsparkofficialwebsite.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage<User> pageUsers(long current, long size, String userName, String direction, Integer grade, String qqNumber) {
        QueryWrapper<User> wrapper = buildQuery(userName, direction, grade, qqNumber);
        wrapper.orderByDesc("create_time");
        return page(new Page<>(current, size), wrapper);
    }

    @Override
    public List<User> listUsersForExport(String direction, Integer grade) {
        QueryWrapper<User> wrapper = buildQuery(null, direction, grade, null);
        wrapper.orderByDesc("create_time");
        return list(wrapper);
    }

    private QueryWrapper<User> buildQuery(String userName, String direction, Integer grade, String qqNumber) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            wrapper.like("user_name", userName);
        }
        if (StringUtils.isNotBlank(direction)) {
            wrapper.eq("direction", direction);
        }
        if (grade != null) {
            wrapper.eq("grade", grade);
        }
        if (StringUtils.isNotBlank(qqNumber)) {
            wrapper.like("qq_number", qqNumber);
        }
        return wrapper;
    }
}