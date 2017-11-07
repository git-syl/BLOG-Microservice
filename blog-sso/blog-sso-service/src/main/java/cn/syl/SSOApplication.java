package cn.syl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: syl  Date: 2017/11/5 Email:nerosyl@live.com
 * Description:

提示注入失败,要引入jpa相关配置,EnableAutoConfigurationb不能排除jpa等配置 //@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
Field userRepository in cn.syl.sso.service.impl.LoginServiceImpl required a bean of type
'cn.syl.blogmain.repository.UserRepository' that could not be found.
 */
@ComponentScan(basePackages = "cn.syl")
@ImportResource(value = "classpath:dubbo/dubbo-provide.xml")
@SpringBootApplication
public class SSOApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication application = new SpringApplication(SSOApplication.class);
        //application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
