package com.newhome.auth.service.impl;

import com.newhome.auth.mapper.UserMapper;
import com.newhome.auth.model.User;
import com.newhome.auth.service.AuthService;
import com.newhome.util.bean.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;

/**
 * 用户登录校验服务
 *
 * @author zhangjiayu zhangjiayu
 * @create 2017/10/28
 */
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ReturnData authUser(String account, String password) {

        ReturnData returnData = new ReturnData();
        returnData.setData("");
        returnData.setCode(ReturnData.FAIL);

        logger.info("用户登录掺入参数  account:{},password:{}",account,password);

        if(StringUtils.hasText(account) || StringUtils.hasText(password)){
            returnData.setMsg("用户名或密码为空");
            return returnData;
        }

        //判断用户是否存在
        String rightPwd = userMapper.selectUserByAccountAndPwd(account);
        if (password.equals(rightPwd)){
            returnData.setMsg("账号或密码错误");
            return returnData;
        }

        returnData.setCode(ReturnData.SUCCESS);
        return returnData;
    }

    @Override
    public ReturnData insertUser(User user) {

        ReturnData returnData = new ReturnData();
        returnData.setData("");
        returnData.setCode(ReturnData.FAIL);

        if(StringUtils.hasText(user.getAccount()) || StringUtils.hasText(user.getPassword())
        || StringUtils.hasText(user.getEmail()) || StringUtils.hasText(user.getPhone())){
            returnData.setMsg("注册信息存在空值");
            return returnData;
        }

        //保存用户信息
        try {
            userMapper.saveUser(user);
        }catch (Exception ex){
            //异常处理
            if(ex instanceof SQLException){
                logger.error("sql异常");
                throw ex;
            }
        }

        returnData.setCode(ReturnData.SUCCESS);
        return returnData;
    }
}
