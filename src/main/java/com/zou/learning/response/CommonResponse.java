package com.zou.learning.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zou.learning.error.BusinessError;
import lombok.Data;

/**
 * 系统统一返回值
 * @author zou
 * @date 2020-02-02 11:19 下午
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    /**
     * 成功统一返回code
     */
    private static final Integer SUCCESS_CODE = 200;
    /**
     * 成功返回信息
     */
    private static final String SUCCESS_MESSAGE = "success";
    /**
     * 统一内部异常code
     */
    private static final Integer ERROR_CODE = 500;
    /**
     * 错误信息
     */
    private static final String ERROR_MESSAGE = "服务器异常";
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 调用描述信息：成功/错误信息
     */
    private String message;
    /**
     * 泛型返回信息
     */
    private T data;

    public CommonResponse() {
    }

    public CommonResponse(T data) {
        this.code = CommonResponse.SUCCESS_CODE;
        this.message = "成功";
        this.data = data;
    }

    public CommonResponse(Integer code, String message) {
        this.code = CommonResponse.SUCCESS_CODE;
        this.message = message;
    }

    public CommonResponse(BusinessError businessError) {
        this();
        this.code = businessError.getErrCode();
        this.message = businessError.getErrMsg();
    }

    /*public CommonResponse(BusinessException businessException) {
        this();
        this.code = businessException.getErrCode();
        this.message = businessException.getErrMsg();
    }*/

    /**
     * 成功静态方法
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResponse<T> success() {
        return new CommonResponse<T>(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public static <T> CommonResponse<T> success(T object) {
        return new CommonResponse<T>(object);
    }

    public static <T> CommonResponse<T> error(String message) {
        return new CommonResponse<T>(CommonResponse.ERROR_CODE, message);
    }

    public static <T> CommonResponse<T> error(Integer code, String message) {
        return new CommonResponse<T>(code, message);
    }

    public static <T> CommonResponse<T> error(BusinessError businessError) {
        return new CommonResponse<T>(businessError.getErrCode(), businessError.getErrMsg());
    }
}
