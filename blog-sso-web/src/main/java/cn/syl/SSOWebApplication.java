package cn.syl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: syl  Date: 2017/11/5 Email:nerosyl@live.com
 * Description:

 */
@ComponentScan(basePackages = "cn.syl")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ImportResource(value = "classpath:dubbo/dubbo-consumer.xml")
@SpringBootApplication
public class SSOWebApplication  extends SpringBootServletInitializer{

    public static void main(String[] args) throws InterruptedException {
        SpringApplication application = new SpringApplication(SSOWebApplication.class);
        //application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SSOWebApplication.class);
    }
}
