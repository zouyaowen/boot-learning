package com.zou.learning.job.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zou
 * @date 2020-02-12 9:36 下午
 */
// @Configuration
@Slf4j
public class XxlJobConfig {

    @Value("${xxl.address}")
    private String adminAddresses;

    @Value("${xxl.appName}")
    private String appName;

    @Value("${xxl.ip}")
    private String ip;

    @Value("${xxl.port}")
    private int port;

    @Value("${xxl.accessToken}")
    private String accessToken;

    @Value("${xxl.logPath}")
    private String logPath;

    @Value("${xxl.logRetentionDays}")
    private int logRetentionDays;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobSpringExecutor;
    }
}
