package com.gh.management;



import com.gh.management.system.netty.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.gh.management.system.mapper")
public class ManagementApplication implements CommandLineRunner {


    @Autowired
    NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
        System.out.println("netty启动完成了----》");
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
    }
}