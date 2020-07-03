package edu.zsc.todolistproject.service;

import edu.zsc.todolistproject.domain.dto.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.linkbuilder.ILinkBuilder;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service("emailSenderService")
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    @Async
    public void sendEmail(Mail mail) throws MessagingException, IOException {
        Context context = new Context();
        context.setVariables(mail.getProps());
        String html = templateEngine.process("mail/email-inline", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(mail.getFrom());
            mimeMessageHelper.setTo(mail.getTo());
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(html,true);
            javaMailSender.send(mimeMessage);
            System.out.println("邮件发送成功");
        } catch (MessagingException e) {
            System.out.println("邮件发送失败");
            e.printStackTrace();
        }
    }
}
