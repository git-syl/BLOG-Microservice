package cn.syl;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * @author: syl  Date: 2017/10/28 Email:nerosyl@live.com
 */
@Configuration
//@PropertySource(value = { "classpath:jdbc.properties", "classpath:env.properties",
//        "classpath:httpclient.properties" })
@ComponentScan(basePackages = "cn.syl")
@PropertySource(value = {"classpath:dubbo/dubbo.properties"})
@ImportResource(value = "classpath:dubbo/dubbo-provide.xml")
@SpringBootApplication
//运行tomcat中需要 打开这些注释
public class ManagerApplication /*extends SpringBootServletInitializer*/ {

    //  for tomcat
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        //tomcat
//        return builder.sources(ManagerApplication.class);
//    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication application = new SpringApplication(ManagerApplication.class);
        //application.setBannerMode(Banner.Mode.OFF);
         application.run(args);
    }


}
