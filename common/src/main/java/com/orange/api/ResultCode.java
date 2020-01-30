package com.orange.api;

public enum ResultCode implements IErrorCode {
    SUCCESS(200, "成功"),
    FAILED(500, "服务器错误"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public long getCode() {
        return code;
    }
}
