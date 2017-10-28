package com.newhome.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.newhome.user.mapper.UserTestMapper;
import com.newhome.user.model.UserTest;
import com.newhome.user.service.UserService;
import com.newhome.util.bean.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ReturnData addAndUpdateUserBatch(String params) {


        if (StringUtils.hasText(params)){
            System.out.println("使用StringUtils的hasText进行判断为空");
        }

        ReturnData returnData = new ReturnData();
        returnData.setCode(ReturnData.FAIL);

        List<UserTest> userTestList = new ArrayList<>();
        UserTest userTestOne = new UserTest();
        UserTest userTestTwo = new UserTest();

        userTestOne.setAccount("测试事务1");
        userTestOne.setPassword("12345");

        userTestTwo.setAccount("测试事务2");
        userTestTwo.setPassword("12334");

        userTestList.add(userTestOne);
        userTestList.add(userTestTwo);

        if (logger.isDebugEnabled()){
            System.out.println("调试模式下，这样做节省内存消耗");
        }

        //先进行增加用户
        userTestMapper.addUserBatch(userTestList);

        int k = 1/0;


        //再进行更新用户
        userTestMapper.updateUserBatch("xxxxx","测试账号1");

        returnData.setCode(ReturnData.SUCCESS);
        return  returnData;

    }

    @Override
    public void deleteUserBacth() {

    }

    @Override
    public ReturnData updateUserBatch(String params) {

        ReturnData returnData = new ReturnData();
        returnData.setCode(ReturnData.FAIL);

        if ( null == params){
            logger.error("params is null");

            returnData.setMsg("params is null");
            return returnData;
        }

        //为什么会使用TreeMap  顺序翻转    学习   什么时候使用正确的工具   大概知道有哪些工具
        Map<String, UserTest>  userTestMap = Maps.newTreeMap(Collections.reverseOrder());

        String account = JSON.parseObject(params).getString("account");


        //警告一般使用在什么地方   我的业务场景里面没有出现过
        if (null == account){
            logger.warn("account is null");
            //默认用户
            account = "defaultUser";
        }

        //使用entrySet而不是KeySet  了解两者的区别和使用的场景
        //TODO

        //使用Iterator和ListIterator进行遍历的场景
        //TODO


        //final 关键字的意义和什么情况下使用final关键字
        UserTest userTest = new UserTest();
        final String a = userTest.getAccount();
        final String p = userTest.getPassword();

        //异步调用外部接口  在JUC中  调用外部接口的方式有很多种   这种方式没有见到过   了解其特性
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //调用一
        Future<Map<String, Map<String, UserTest>>> userCallTest =
                    executorService.submit(new Callable<Map<String, Map<String, UserTest>>>() {
                        @Override
                        public Map<String, Map<String, UserTest>> call() throws Exception {
                            return testCallOne();
                        }
                    });

        //调用二
        Future<Map<String, String>> userCallTestTwo =
                    executorService.submit(new Callable<Map<String, String>>() {
                        @Override
                        public Map<String, String> call() throws Exception {
                            return testCallTwo();
                        }
                    });

        //懒加载
        Map<String, Map<String, UserTest>> resultMapOne = null;
        Map<String, String> resultMapTwo = null;

        //获取异步调用的结果
        try {
            resultMapOne = userCallTest.get();
        }catch (Exception ex){

            logger.error("error.info");
            returnData.setMsg("error.info");
            return  returnData;

        }



        return  returnData;

    }

    /**
    * @author  zhangjiayu
    * @description  异步调用一
    * @param
    * @date
    */
    private Map<String, Map<String, UserTest>> testCallOne(){
       return  null;
    }

    /**
    * @author  zhangjiayu
    * @description 异步调用二
    * @param
    * @date
    */
    private  Map<String ,String> testCallTwo(){
        return null;
    }

    @Override
    public void queryUserBatch() {

    }
}
