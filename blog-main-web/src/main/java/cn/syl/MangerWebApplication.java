package cn.syl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: syl  Date: 2017/10/29 Email:nerosyl@live.com
 */

//spring boot不爽的地方 非要提供datasource ??
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ImportResource(value = "classpath:dubbo/dubbo-consumer.xml")
@SpringBootApplication
public class MangerWebApplication {
    public static void main(String args[]){
        SpringApplication.run(MangerWebApplication.class,args);
    }
}
