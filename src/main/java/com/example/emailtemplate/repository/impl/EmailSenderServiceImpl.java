package com.example.emailtemplate.repository.impl;

import com.example.emailtemplate.entity.EmailConstant;
import com.example.emailtemplate.entity.Mail;
import com.example.emailtemplate.entity.UserMail;
import com.example.emailtemplate.repository.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Mail mail) throws MessagingException {

    }

    @Override
    public void generateMessage() throws MessagingException {
//        UserMail userMail = (UserMail) object;
        Mail mail = setContentMailSend();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(mail.getProps());
        String html = templateEngine.process(mail.getTemplate(), context);
        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        javaMailSender.send(message);
    }

    private Mail setContentMailSend() {
        Mail mail = new Mail();
        mail.setFrom("dongptit1999");
        mail.setMailTo("dong19069999@gmail.com");
        mail.setSubject("nguyen tien dong test send email");
        Map<String, Object> model = new HashMap<>();
        model.put("test", "nguyen tien dong dep trai khong doi thu");
        mail.setTemplate(EmailConstant.EMAIL_TEMPLATE);
        mail.setProps(model);
        return mail;
    }
}
