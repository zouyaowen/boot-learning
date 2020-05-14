package com.zou.learning.conf;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zou.learning.intercepter.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author zouyaowen
 * @date 2020-05
 */
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1、统一返回值返回String特殊处理：JSON数据处理优先级高于String
        // 2、统一返回值返回内部数据空值处理，返回值为null不进行序列化
        converters.add(0, new MappingJackson2HttpMessageConverter() {
            //3、以下测试不起作用
            @Override
            public ObjectMapper getObjectMapper() {
                super.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
                return super.getObjectMapper();
            }
        });
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置HTTP请求拦截器
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
