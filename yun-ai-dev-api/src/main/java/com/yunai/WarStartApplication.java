package com.yunai;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/*
继承SpringBootServletInitializer，相当于用web.xml的形式去启动部署
 */
public class WarStartApplication extends SpringBootServletInitializer {
    /*
    重写配置
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        //使用web.xml运行应用程序，指向Application，最后执行springboot
        return builder.sources(Application.class);
    }
}
