package com.newhome.auth.service.impl;

import com.newhome.auth.model.User;
import com.newhome.auth.service.AuthService;
import com.newhome.util.bean.ReturnData;

/**
 * 用户登录校验服务
 *
 * @author zhangjiayu zhangjiayu
 * @create 2017/10/28
 */
public class AuthServiceImpl implements AuthService{

    @Override
    public ReturnData authUser(String account, String password) {

        ReturnData returnData = new ReturnData();
        returnData.setData("");
        returnData.setCode(ReturnData.FAIL);

//        if(){
//
//        }

        return null;
    }

    @Override
    public ReturnData insertUser(User user) {
        return null;
    }
}
