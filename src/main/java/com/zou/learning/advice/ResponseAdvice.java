package com.zou.learning.advice;

import com.alibaba.fastjson.JSON;
import com.zou.learning.log.RequestHolder;
import com.zou.learning.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一返回值处理
 *
 * @author zou
 * @date 2020-02-03 1:57 上午
 */
@RestControllerAdvice(annotations={RestController.class, Controller.class})
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonResponse<Object> res = new CommonResponse<>(200, "success");
        log.info("返回值处理");
        if (null == body) {
            log.info("res=[{}]", JSON.toJSONString(res));
            RequestHolder.add("response",JSON.toJSONString(res));
            return res;
        } else if (body instanceof CommonResponse) {
            log.info("body=[{}]",body);
            RequestHolder.add("response",JSON.toJSONString(body));
            return body;
        } else {
            res.setData(body);
        }
        log.info("res result=[{}]",res);
        RequestHolder.add("response",JSON.toJSONString(res));
        return res;
    }
}
