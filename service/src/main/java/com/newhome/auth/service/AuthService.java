package com.newhome.auth.service;

import com.newhome.auth.model.User;
import com.newhome.util.bean.ReturnData;

/**
 * 注册登录Service
 */
public interface AuthService {

    /**
     * 验证用户登录
    * @author  zhangjiayu
    * @description
    * @param
    * @date
    */
    ReturnData authUser(String account, String password);

    /**
     * 插入单个User
    * @author  zhangjiayu
    * @description
    * @param
    * @date
    */
    ReturnData insertUser(User user);

}
