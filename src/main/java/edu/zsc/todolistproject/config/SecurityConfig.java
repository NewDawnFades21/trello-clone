package edu.zsc.todolistproject.config;

import edu.zsc.todolistproject.auth.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.swing.text.html.CSS;
import java.lang.annotation.Target;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*    necessary to prevent security from being applied to the resources
        such as CSS, images and javascripts*/
    private static final String[] resources = new String[]{
            "/bootstrap4/**", "/css/**", "/js/**", "/site/**", "/layer/**"
    };
    @Autowired
    private DataSource dataSource;
    @Autowired
    @Qualifier("userDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions()
                .disable()

                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/user/forgetPwd").permitAll()
                .antMatchers("/user/changPwdByEmail").permitAll()
                .antMatchers("/user/check/**").permitAll()
                .antMatchers(resources).permitAll()
                //后台权限
                .antMatchers("/").permitAll()
                .antMatchers("/backstage/**").permitAll()
                .antMatchers("/backstage/components/**").permitAll()
               // .antMatchers("/backstage/**").hasRole("管理员")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(myAuthenticationSuccessHandler())
                .loginProcessingUrl("/login")
                .failureUrl("/login?error=true")

                .and()
                .logout().deleteCookies("JSESSIONID")

                .and()
                .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);

    }

}
