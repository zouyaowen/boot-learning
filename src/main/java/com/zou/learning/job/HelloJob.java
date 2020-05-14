package com.zou.learning.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zou
 * @date 2020-02-12 9:35 下午
 */
// @Component
@Slf4j
public class HelloJob {

    @XxlJob("helloJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at:" + i);
            log.info("hello job");
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }
}
