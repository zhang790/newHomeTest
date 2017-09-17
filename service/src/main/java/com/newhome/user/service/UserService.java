package com.newhome.user.service;

import com.newhome.user.model.UserTest;

/**
 * 用户接口
 */
public interface UserService {

    int login(String userName, String password);
}
