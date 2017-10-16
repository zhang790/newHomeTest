package com.newhome.util.bean;

import java.io.Serializable;

/**
 * 返回统一结构
 *
 * @author zhangjiayu zhangjiayu
 * @create 2017/10/14
 */
public class ReturnData <T> implements Serializable{

    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public ReturnData() {
        super();
    }

    public ReturnData(T data) {
        super();
        this.data = data;
    }

    public ReturnData(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static int getNoLogin() {
        return NO_LOGIN;
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static int getFAIL() {
        return FAIL;
    }

    public static int getNoPermission() {
        return NO_PERMISSION;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
