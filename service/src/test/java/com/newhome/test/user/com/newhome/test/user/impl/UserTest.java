package com.newhome.test.user.com.newhome.test.user.impl;

import com.newhome.test.user.AbstractServiceTest;
import com.newhome.user.service.UserService;
import com.newhome.util.redis.ReturnData;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试
 *
 * @author zhangjiayu zhangjiayu
 * @create 2017/10/16
 */
public class UserTest extends AbstractServiceTest{

    @Autowired
    private UserService userService;

    @Test
    public void testTransaction(){
        String param = "";
        ReturnData returnData = userService.addAndUpdateUserBatch("xxx");
        Assert.assertEquals(ReturnData.SUCCESS,returnData.getCode());
    }

}
