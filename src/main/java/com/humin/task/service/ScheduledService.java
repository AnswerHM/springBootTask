package com.humin.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created with IntelliJ IDEA
 *
 * @Author:humin
 * @Date:27/07/20183:04 PM
 */
@Service
public class ScheduledService {

    @Autowired
    JavaMailSenderImpl mailSender;
    /**
     * second, minute, hour, day of month, month , day of week
     * 0 * * * * MON-FRI
     * 【0 0/5 14,18 * * ?】每天14点整，和18点整，每隔5分钟执行一次
     * 【0 15 10 ? * 1-6】每个月的周一至周六10：15分执行一次
     * 【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
     * 【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
     * 【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每隔整点都执行一次
     *
     */
//    @Scheduled(cron = "0 * * * * MON-FRI")
//    @Scheduled(cron = "0,1,2,3,4,29,40,55 * * * * MON-SAT")
//    @Scheduled(cron = "0-4 * * * * MON-SAT")
//    @Scheduled(cron = "0/4 * * * * MON-SAT")  // 每4秒执行一次
    public void hello(){
        System.out.println("我是一个定时任务！");
    }


//    @Scheduled(cron = "0,1,2,3,4,29,40,55 * * * * MON-SAT")
    public void sendMail(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 邮件设置
        mailMessage.setSubject("通知-明天放假");
        mailMessage.setText("明天给大家放假，不用来上班！");
        mailMessage.setTo("1534456352@qq.com");
        mailMessage.setFrom("648869890@qq.com");
        mailSender.send(mailMessage);
    }

    @Scheduled(cron = "0 * * * * MON-FRI")
    public void sendMimeMail() throws Exception{
        System.out.println("开始发邮件了！");
        // 1、创建一个复杂的消息邮件
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true);
        // 邮件设置
        helper.setSubject("通知-明天放假");
        helper.setText("<b style='color:red'>明天给大家放假</b>",true);
        helper.setTo(new String[]{"290498254@qq.com","413024568@qq.com","kangmeng2012@163.com","tcf_888888@163.com"});
        helper.setFrom("648869890@qq.com");
        // 上传附件
        helper.addAttachment("不上班",new File("/Users/humin/Desktop/timg.jpeg"));
        mailSender.send(mailMessage);
    }
}
