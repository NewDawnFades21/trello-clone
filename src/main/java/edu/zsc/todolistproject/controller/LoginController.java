package edu.zsc.todolistproject.controller;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/notebook")
public class LoginController {
    @Autowired
    LoginMapper loginMapper;


    @GetMapping("/login")
    public String Login(){
        return "/login/login";
    }

    @PostMapping("/login")
    public  String Login1(HttpServletRequest request, @RequestParam String username, @RequestParam String password){
        HttpSession session=request.getSession();
        User user=loginMapper.findByUsernamePassWord(username,password);
        if(null!=user){
           session.setAttribute("loginUser",user);
           return "redirect:/notebook/user/home";
        }
        else {
            return "redirect:/notebook/login";
        }
    }
    @GetMapping("/register")
    public String Register(){
        return "/login/register";
    }

    @PostMapping("/register")
    public String Register1(@RequestParam("username") String username,
                            @RequestParam("password") String password){
        User user=loginMapper.checkIsExist(username);
        if(null==user){
            Date registerdate=new Date();
            loginMapper.insertUser(username,password,registerdate);
            return "redirect:/notebook/login";
            //改：注册成功提示框
        }
        else {
            //改：跳出用户名已经存在提示框
            System.out.println("用户名已存在");
            return "redirect:/notebook/register";
        }

    }
    }
