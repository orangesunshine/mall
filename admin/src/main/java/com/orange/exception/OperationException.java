package com.orange.exception;

/**
 * 操作异常（插入失败）
 */
public class OperationException extends RuntimeException {

    public OperationException(String message) {
        super(message);
    }
}
