package com.example.backend.service.Implement;/**
 * @Classname SendMessageService
 * @Description TODO
 * @Date 2021/12/8 19:41
 * @Created by 86150
 */

import com.example.backend.entitiy.UserIdentify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @ program: 后端test
 * @ description:
 * @ author: YXJ
 * @ date: 2021-12-08 19:41:47
 */
@Service
public class SendMessageService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserIdentifyServiceImpl userIdentifyService;

    // 我的QQ邮箱
    @Value("${spring.mail.username}")
    private String myQQMail;





    public void sendIdentifyCodeMailTo(String targetEmail , String title) {
//        String targetMail = userIdentify.getEmail();
        String code = this.generateCode();

        // 邮件的主题和内容
        final String subject = title;

        String simpleText = "欢迎您使用本系统。您的验证码是: " + code
                + " \n\n";

        // 设置邮件发送内容
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 发件人: setFrom处必须填写自己的邮箱地址，否则会报553错误
        mailMessage.setFrom(myQQMail);
        // 收件人
        mailMessage.setTo(targetEmail);
        // 抄送收件人:网易邮箱要指定抄送收件人，不然会报 554（发送内容错误）
//        mailMessage.setCc(my163Mail);
        // 主题
        mailMessage.setSubject(subject);
        // 内容
        mailMessage.setText(simpleText);
        try {
            javaMailSender.send(mailMessage);
            /*
             * TODO: 将email-验证码关系存入数据库
             */
            UserIdentify userIdentify = new UserIdentify(targetEmail , code);
            userIdentifyService.addUserIdentify(userIdentify);
            System.out.println("发送简单文本邮件成功,主题是：" + subject);
        } catch (Exception e) {
            System.out.println("-----发送简单文本邮件失败!-------" + e.toString());
            e.printStackTrace();
        }
    }

    private String generateCode()
    {
        String code = "";
        Random random = new Random();
        for(int i =0 ; i<6 ;i++)
        {
            code += random.nextInt(10);
        }
        return code;
    }

}
