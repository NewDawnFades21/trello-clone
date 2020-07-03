package edu.zsc.todolistproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * 告诉Themeleaf 电子邮件模板在哪
 */
@Configuration
public class ThymeleafTemplateConfig {
    @Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver htmlTemplateResolver(){
        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
        emailTemplateResolver.setPrefix("classpath:/templates/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return emailTemplateResolver;
    }
}
