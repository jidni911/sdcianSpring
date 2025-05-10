// package com.jidnivai.sdcian.sdcian.controller;

// import java.io.File;

// import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.mail.SimpleMailMessage;
// import org.springframework.web.bind.annotation.*;

// import com.jidnivai.sdcian.sdcian.service.EmailService;

// import jakarta.mail.MessagingException;

// @RestController
// @RequestMapping("/api/email")
// public class EmailController {

//     @Autowired
//     private EmailService emailService;

//     @GetMapping("/send")
//     public String sendMail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
//         emailService.sendSimpleEmail(to, subject, body);
//         return "Email sent to " + to;
//     }

//     @GetMapping("/send-html")
//     public String sendHtml(@RequestParam String to) throws MessagingException {
//         String html = "<h1>Welcome!</h1><p>This is an <b>HTML email</b>.</p>";
//         emailService.sendHtmlEmail(to, "HTML Email Test", html);
//         return "HTML email sent!";
//     }

//     @GetMapping("/send-attachment")
//     public String sendAttachment(@RequestParam String to) throws MessagingException {
//         File file = new File("C:\\Users\\LeftSword\\Downloads\\Gidni Hossan (1).pdf");
//         String html = "<p>Please find the attachment below.</p>";
//         emailService.sendEmailWithAttachment(to, "Email with Attachment", html, file);
//         return "Email with attachment sent!";
//     }

// }
