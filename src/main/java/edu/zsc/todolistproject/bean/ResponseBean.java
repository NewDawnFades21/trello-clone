package edu.zsc.todolistproject.bean;

import edu.zsc.todolistproject.enums.ResultCode;
import lombok.Data;

/**
 * 封装向客户端发回的响应数据
 */
@Data
public class ResponseBean {
    Integer code;         //响应码
    private String msg;    //响应消息
    private Object data;       //具体前端数据

    //返回成功的消息
    public static ResponseBean success(Object data) {
        return new ResponseBean(ResultCode.SUCCESS, data);
    }
    public static ResponseBean success() {
        return new ResponseBean(ResultCode.SUCCESS,null);
    }

    //返回失败的消息
    public static ResponseBean fail() {
        return new ResponseBean(ResultCode.ERROR, null);
    }

    //返回失败的消息
    public static ResponseBean notFound() {
        return new ResponseBean(ResultCode.NOT_FOUND, null);
    }

    //返回参数异常、内部错误等其他情况,根据resultCode构造响应结果
    public static ResponseBean setStatus(ResultCode resultCode){
        return new ResponseBean(resultCode, null);
    }
    //构造函数
    private ResponseBean(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }
}
