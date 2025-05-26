package com.notificationservice.service;


import com.notificationservice.dto.NotificationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    SimpleMailMessage mailMessage = new SimpleMailMessage();

    @Override
    public ResponseEntity<?> sendMail(NotificationDetails details) {
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getRecipient());
        mailMessage.setSubject(details.getSubject());
        mailMessage.setText(details.getMsgBody());

        javaMailSender.send(mailMessage);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> registerUser(String email, String password) {
        String subject = "Verify your email";
        String body = "Dear user,\n\nUse this password below:\n" + password + "\n\n for login \n\nThank you!";

        //SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);
        return new ResponseEntity<>("Verification email sent successfully", HttpStatus.OK);
    }
}
