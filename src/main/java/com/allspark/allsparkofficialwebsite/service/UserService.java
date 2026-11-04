package com.allspark.allsparkofficialwebsite.service;

import com.allspark.allsparkofficialwebsite.model.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface UserService extends IService<User> {

    IPage<User> pageUsers(long current, long size, String userName, String direction, Integer grade, String qqNumber);

    List<User> listUsersForExport(String direction, Integer grade);
}