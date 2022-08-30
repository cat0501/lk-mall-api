package com.lk.mall.util;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回结果
 * @param <T>
 */
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;

    // 构造方法————————————————————————————————————————————————————————————
    public R() {
    }

    public R(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 构造结果————————————————————————————————————————————————————————————
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;

    // 成功返回————————————————————————————————————————————————————————————
    public R<T> ok(){
        R<T> r = new R<>();
        r.setCode(RESULT_CODE_SUCCESS);
        r.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return r;
    }
    public R<T> ok(String message){
        R<T> r = new R<>();
        r.setCode(RESULT_CODE_SUCCESS);
        r.setMessage(message);
        return r;
    }
    public R<T> ok(T data){
        R<T> r = new R<>();
        r.setCode(RESULT_CODE_SUCCESS);
        r.setMessage(DEFAULT_SUCCESS_MESSAGE);
        r.setData(data);
        return r;
    }

    // 失败返回————————————————————————————————————————————————————————————
    public R<T> fail(String message){
        R<T> r = new R<>();
        r.setCode(RESULT_CODE_SERVER_ERROR);
        r.setMessage( StrUtil.isEmpty(message) ? DEFAULT_FAIL_MESSAGE : message);
        return r;
    }
}
