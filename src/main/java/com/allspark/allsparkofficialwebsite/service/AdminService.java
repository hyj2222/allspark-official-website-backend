package com.allspark.allsparkofficialwebsite.service;

import com.allspark.allsparkofficialwebsite.model.AdminUser;

public interface AdminService {

    AdminUser login(String username, String password);
}