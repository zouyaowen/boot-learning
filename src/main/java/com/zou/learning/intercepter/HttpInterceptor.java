package com.zou.learning.intercepter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zou.learning.log.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * HTTP 请求拦截器
 *
 * @author zouyaowen
 * @date 2020-05
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    public HttpInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, Object> threadMap = new HashMap<>();
        RequestHolder.set(threadMap);
        //获取请求数据
        log.info("preHandle 方法执行");
        String requestURI = request.getRequestURI();
        log.info("requestURI=[{}]", requestURI);
        Enumeration<String> parameterNames = request.getParameterNames();
        JSONObject jsonParams = new JSONObject();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            log.info("paramName=[{}],paramValue=[{}]", paramName, request.getParameter(paramName));
            jsonParams.put(paramName, request.getParameter(paramName));
        }
        Enumeration<String> headerNames = request.getHeaderNames();
        JSONObject headerParams = new JSONObject();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.info("headerName=[{}],headerValue=[{}]", headerName, request.getHeader(headerName));
            headerParams.put(headerName, request.getHeader(headerName));
        }
        RequestHolder.add("jsonParams", jsonParams.toJSONString());
        RequestHolder.add("headerParams", headerParams.toJSONString());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle 方法执行");
        Object jsonParams = RequestHolder.get("jsonParams");
        log.info("postHandle-------jsonParams=[{}]", jsonParams);
        Object headerParams = RequestHolder.get("headerParams");
        log.info("postHandle-------headerParams=[{}]", headerParams);
        Object res = RequestHolder.get("response");
        log.info("postHandle-------res=[{}]", res);
        //记录日志
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion 方法执行");
        log.info("afterCompletion response=[{}]", response);
        RequestHolder.remove();
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //异步返回调用此方法，返回值是StreamingResponseBody
        log.info("afterConcurrentHandlingStarted 方法执行");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
    // @GetMapping(value = "/hello2")
    // public StreamingResponseBody hello2() throws InterruptedException {
    //     Thread.sleep(500);
    //     return (OutputStream outputStream) -> {
    //         outputStream.write("success".getBytes());
    //         outputStream.flush();
    //         outputStream.close();
    //     };
    // }
}
