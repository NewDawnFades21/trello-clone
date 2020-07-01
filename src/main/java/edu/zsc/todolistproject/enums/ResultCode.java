package edu.zsc.todolistproject.enums;

import lombok.Getter;

/**
 * 响应码枚举
 */
@Getter
public enum ResultCode {

    SUCCESS(200, "success"),

    FAILED(400, "failed"),

    UNAUTHORIZED(401, "没有相应权限"),

    NOT_FOUND(404, "没有相应权限"),

    VALIDATE_FAILED(405, "参数校验失败"),

    ERROR(500, "服务器内部异常");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

