package edu.zsc.todolistproject.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    public String hello(){
        return "hello";
    }
}
