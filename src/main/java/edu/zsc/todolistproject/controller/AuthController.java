package edu.zsc.todolistproject.controller;


import com.sun.deploy.net.HttpResponse;
import edu.zsc.todolistproject.auth.MySessionInfo;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.domain.dto.Mail;
import edu.zsc.todolistproject.mapper.UserMapper;
import edu.zsc.todolistproject.service.EmailSenderService;
import edu.zsc.todolistproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class AuthController {
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;
    @Autowired
    private UserService userService;

    @GetMapping("/wordcloud")
    public String CloudNote() {
        return "/login/transition";
    }

    @PostMapping("/wordcloud")
    public String CloudNote1(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login/login");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam("username") final String username, @RequestParam("password") final String password, final HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authReq =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
//        return new ResponseEntity<>(1,HttpStatus.OK);
    }


    @GetMapping("/register")
    public String toRegister() {
        return "login/register";
    }


    @PostMapping("/register")
    public ResponseEntity<?> postRegister(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIntroduction("该用户啥都没写。。");
        int i = userService.register(user);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    /**
     * 检查用户名是否已存在
     *
     * @param username
     * @return
     */
    @PostMapping("/user/check/username")
    public @ResponseBody
    Boolean checkUsernameExists(@RequestParam String username) {
        List<String> usernameIsExist = userService.checkUsernameIsExist(username);
        if (usernameIsExist.size() == 0)
            return true;
        return false;
    }

    /**
     * 跳转到修改密码的界面
     *
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/changePwd")
    public String toChangePwd() {
        return "login/changePwd";
    }


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private MySessionInfo mySessionInfo;

    //only logged in user can operate this
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/user/changePwd")
    public ResponseEntity<?> changeUserPassword(@RequestBody Map<String, Object> payload, HttpServletResponse response) throws IOException {
        User user = userService.getUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
//            encode new password and store it
        String password = (String) payload.get("password");
        user.setPassword(passwordEncoder.encode(password));
        int res = userService.updateUserPassword(user);
        if (res == 0) {
            return new ResponseEntity<>("修改失败", HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>("修改成功", HttpStatus.OK);
    }

    //only logged in user can operate this
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/user/matchOldPassword")
    public @ResponseBody
    Boolean matchOldPassword(@RequestParam String oldPassword) {
        User user = userService.getUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/user/check/emailIsNotExists")
    public @ResponseBody
    Boolean checkEmailNotExists(@RequestParam String email) {
        return userService.getUserByEmail(email).size() != 0;
    }

    @PostMapping("/user/check/emailIsExists")
    public @ResponseBody
    Boolean checkEmailExists(@RequestParam String email) {
        return userService.getUserByEmail(email).size() == 0;
    }

    /**
     * 跳转到忘记密码界面
     *
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/forgetPwd")
    public String toForgetPwd() {
        return "login/forgetPwd";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/user/forgetPwd")
    public ResponseEntity<?> forgetUserPassword(User user) {
        String email = user.getEmail();
//        属性设置
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("username", userService.getUsernameByEmail(email));
        model.put("email",email);
        String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        baseUrl = baseUrl+"/user/check/confirm?email="+email;
        model.put("baseUrl", baseUrl);
        // Create the email
        Mail mail = new Mail();
        mail.setProps(model);
        mail.setFrom("815923676@qq.com");
        mail.setTo(email);
        mail.setSubject("确认修改密码");

        // Send the email
        try {
            emailSenderService.sendEmail(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @GetMapping("/user/check/confirm")
    public ModelAndView toConfirmReset(@RequestParam String email) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login/confirm-password");
        mav.addObject("email",email);
        return mav;
    }

    @PostMapping("/user/changPwdByEmail")
    public ResponseEntity<?> changPwdByEmail(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int res = userService.changPwdByEmail(user);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }



}
