package edu.zsc.todolistproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("edu.zsc.todolistproject.mapper")
@SpringBootApplication
@EnableCaching
public class TodoListProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListProjectApplication.class, args);
    }

}
