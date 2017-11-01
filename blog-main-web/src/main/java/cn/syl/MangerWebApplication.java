package cn.syl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: syl  Date: 2017/10/29 Email:nerosyl@live.com
 */

//spring boot不爽的地方 非要提供datasource ??
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ImportResource(value = "classpath:dubbo/dubbo-consumer.xml")
@SpringBootApplication
//如果在tomcat运行需要打开下面的注释
public class MangerWebApplication  /* extends SpringBootServletInitializer*/ {
    public static void main(String args[]){
        SpringApplication application = new SpringApplication(MangerWebApplication.class);
        //application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(MangerWebApplication.class);
//    }
}
