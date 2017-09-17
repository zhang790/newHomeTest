package com.newhome.user.service.impl;

import com.newhome.user.mapper.UserTestMapper;
import com.newhome.user.model.UserTest;
import com.newhome.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户操作接口
 *
 * @author zhangjiayu zhangjiayu
 * @create 2017/9/16
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserTestMapper userTestMapper;

    public int login(String userName, String password) {

        UserTest userTest = userTestMapper.selectByPrimaryKey(1);
        System.out.println(userTest.getAccount() + ":" + userTest.getPassword());
        return 0;
    }
}
