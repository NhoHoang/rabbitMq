package com.example.notification2service.service;


import com.example.notification2service.dto.Mail;
import com.example.notification2service.dto.UserInfo;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    public JavaMailSender emailSender;


    public EmailServiceImpl(JavaMailSender emailSender ) {
        this.emailSender = emailSender;

    }

    @Override
    public void sendSimpleMessage(UserInfo userInfo) {
        try {
            Mail newMail = new Mail();
            newMail.setToEmail(userInfo.getEmail());
            newMail.setSubject("New Account Registration");
            newMail.setText( " Dear Sir/Madam,"+
                    "\n\n" +
                            "Congratulations!" +
                            "\n" +
                            "\n" +
                            "Your account has been successfully registered !" +
                            "\n" +
                            "Your classification follow salary: "+userInfo.getSalary()+ " is:  \n" +
                            userInfo.getMemberType()
                            );

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(newMail.getToEmail());
            message.setSubject(newMail.getSubject());
            message.setText(newMail.getText());
            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }

    }
}
