package com.muzimin.common.exception;

/**
 * @author: 李煌民
 * @date: 2024-08-23 09:59
 * 错误码枚举值
 **/
public enum BizCodeEnum {
    UNKNOWN_EXCEPTION(10000, "系统未知错误"),
    VAILD_EXCEPTION(10001, "参数格式校验失败");

    private int code;
    private String message;

    BizCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
