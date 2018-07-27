package com.humin.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot04TaskApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 邮件设置
        mailMessage.setSubject("通知-明天放假");
        mailMessage.setText("明天给大家放假");
        mailMessage.setTo("hmlmy0609@163.com");
        mailMessage.setFrom("648869890@qq.com");

        mailSender.send(mailMessage);
    }


    @Test
    public void test02() throws Exception{
        // 1、创建一个复杂的消息邮件
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true);
        // 邮件设置
        helper.setSubject("通知-明天放假");
        helper.setText("<b style='color:red'>明天给大家放假</b>",true);
        helper.setTo("hmlmy0609@163.com");
        helper.setFrom("648869890@qq.com");

        // 上传附件
        helper.addAttachment("cpcc.sql",new File("/Users/humin/Desktop/cpcc.sql"));
        helper.addAttachment("ojdbc6.jar",new File("/Users/humin/Documents/ojdbc6.jar"));
        mailSender.send(mailMessage);
    }

    @Test
    public void test03() throws Exception{
        System.out.println("开始发邮件了！");
        // 1、创建一个复杂的消息邮件
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true);
        // 邮件设置
        helper.setSubject("通知-明天放假");
        helper.setText("<b style='color:red'>明天给大家放假</b>",true);
        helper.setTo(new String[]{"290498254@qq.com"});
        helper.setFrom("648869890@qq.com");
        // 上传附件
        helper.addAttachment("不上班",new File("/Users/humin/Desktop/timg.jpeg"));
        mailSender.send(mailMessage);
    }

}
