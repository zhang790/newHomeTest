package com.newhome.auth.service.impl;

import com.newhome.auth.mapper.UserMapper;
import com.newhome.auth.model.User;
import com.newhome.auth.service.AuthService;
import com.newhome.util.redis.RedisUtil;
import com.newhome.util.redis.ReturnData;
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

    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ReturnData authUser(String account, String password) {

        ReturnData returnData = new ReturnData();
        returnData.setData("");
        returnData.setCode(ReturnData.FAIL);

        //添加redis操作  后期添加MD5加密
        if (null !=  redisUtil.get(account)){
            System.out.println((String)redisUtil.get(account));
            returnData.setCode(ReturnData.SUCCESS);
            return returnData;
        }


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

        //redis存入  key   Object  expire 过期时间    dataBase 默认数据库
        redisUtil.set(account,"kkk");

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
