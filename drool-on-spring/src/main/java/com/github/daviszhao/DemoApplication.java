package com.github.daviszhao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Slf4j
@ImportResource("classpath:/kmodule-spring.xml")
public class DemoApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
//        log.info("defined beans:");
//        Arrays.stream(context.getBeanDefinitionNames()).forEach(log::info);

        TestBean testBean = context.getBean(TestBean.class);
        testBean.testRule(context);
        testBean.testProcess(context);
        testBean.testDt(context);
    }

}
