/*package com.shanzhu.parking;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 启动类
 * 项目启动入口（点击右键 选择 "run BackendApplication" 启动项目）
 * <p>
 * 什么是SpringBoot？（https://www.php.cn/faq/498384.html）
 *
 * @author: Zi Cheng
 * @date: 2024-09-22
 */
/*@Slf4j
@MapperScan("com.shanzhu.parking.mapper")
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {

        //SpringBoot 执行启动
        SpringApplication.run(BackendApplication.class, args);

        log.info("=====================The project backend was successfully started============================");
    }

}
/* */
package com.shanzhu.parking;

import com.shanzhu.parking.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("com.shanzhu.parking.mapper")
@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private UserServiceImpl userService;

    public static void main(String[] args) {
        //SpringBoot Execute Startup
        SpringApplication.run(BackendApplication.class, args);
        log.info("=====================The project backend was successfully started============================");
    }

    /*@Override
    public void run(String... args) throws Exception {
        log.info("Starting to perform batch encryption of user passwords...");
        userService.encryptExistingPasswords(); // Calling the bulk encryption method
        log.info("User password batch encryption operation completed!");
    }
}


