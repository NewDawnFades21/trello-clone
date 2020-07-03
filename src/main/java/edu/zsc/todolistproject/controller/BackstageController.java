package edu.zsc.todolistproject.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理 页面操作类
 */
@Slf4j
@Controller
@RequestMapping("/backstage")
public class BackstageController {

    /**
     * 访问后台管理的主页
     * @param model
     * @return
     */
    @GetMapping({"/backstage",""})
    public String tobackstageIndex(Model model){
        return "/backstage/backstageIndex";
    }

    /**
     * 访问后台管理的控件
     * @param url
     * @return
     */
    @GetMapping("/components/{url}")
    public String tobackstageComponents(@PathVariable String url){
        return "/backstage/components/"+url;
    }

}


