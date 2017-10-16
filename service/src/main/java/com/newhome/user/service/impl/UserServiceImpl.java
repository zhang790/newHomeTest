package com.newhome.user.service.impl;

import com.newhome.user.mapper.UserTestMapper;
import com.newhome.user.model.UserTest;
import com.newhome.user.service.UserService;
import com.newhome.util.bean.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户操作接口
 *
 * @author zhangjiayu zhangjiayu
 * @create 2017/9/16
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserTestMapper userTestMapper;

    @Override
    public ReturnData addAndUpdateUserBatch() {

        ReturnData returnData = new ReturnData();
        returnData.setCode(ReturnData.FAIL);

        List<UserTest> userTestList = new ArrayList<>();
        UserTest userTestOne = new UserTest();
        UserTest userTestTwo = new UserTest();

        userTestOne.setAccount("测试账号1");
        userTestOne.setPassword("12345");

        userTestTwo.setAccount("测试账号2");
        userTestTwo.setPassword("12334");

        userTestList.add(userTestOne);
        userTestList.add(userTestTwo);

        //先进行增加用户
        userTestMapper.addUserBatch(userTestList);


        //再进行更新用户
        userTestMapper.updateUserBatch("xxxxx","测试账号1");

        returnData.setCode(ReturnData.SUCCESS);
        return  returnData;

    }

    @Override
    public void deleteUserBacth() {

    }

    @Override
    public void updateUserBatch() {

    }

    @Override
    public void queryUserBatch() {

    }
}
