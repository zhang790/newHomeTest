package com.newhome.user.service;

/**
 * 用户接口
 */
public interface UserService {

    /**
     *
    * @author  zhangjiayu
    * @description  批量增加用户
    * @param
    * @date
    */
    void  addAndUpdateUserBatch();


    /**
    * @author  zhangjiayu
    * @description 批量删除用户
    * @param
    * @date
    */
    void deleteUserBacth();

    /**
    * @author  zhangjiayu
    * @description 批量更新项目
    * @param
    * @date
    */
    void updateUserBatch();

    /**
    * @author  zhangjiayu
    * @description 批量出巡用户
    * @param
    * @date
    */
    void queryUserBatch();
}
