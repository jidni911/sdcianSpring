package com.jidnivai.sdcian.sdcian.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your_email@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        System.out.println("Mail Sent Successfully...");
    }

    public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("your_email@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // true = enable HTML

        mailSender.send(message);
    }

    public void sendEmailWithAttachment(String to, String subject, String htmlBody, File file) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
    
        helper.setFrom("your_email@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.addAttachment(file.getName(), file); // add attachment
    
        mailSender.send(message);
    }
}
