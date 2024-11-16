package com.attendance.application.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServices {

    @Autowired
    private final JavaMailSender mailSender;

    public EmailServices(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String Subject, String Text) {
    try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(Subject);
            helper.setText(Text);
            mailSender.send(message);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
