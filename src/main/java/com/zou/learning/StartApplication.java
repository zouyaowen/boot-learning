package com.zou.learning;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

/**
 * @author zou
 */
@SpringBootApplication
@Slf4j
public class StartApplication {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("Spring Boot Application");
        stopWatch.start("Boot Learning Application");
        SpringApplication.run(StartApplication.class, args);
        stopWatch.stop();
        log.info("启动耗时={}秒",stopWatch.getTotalTimeSeconds());
        log.info(stopWatch.prettyPrint());
    }
}
