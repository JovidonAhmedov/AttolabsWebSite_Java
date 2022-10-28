package com.example.postgresql.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public String sendEmail(String toEmail,
                          String subject,
                          String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("jovidon_a.90@mail.ru");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        return "Message sent successfully!";
    }
    public void uploadFile(MultipartFile file) throws IOException  {
            file.transferTo(new File("C:\\Users\\JovidonAhmedov\\Desktop\\Image\\New folder\\" + "1.webp"));
    }
}
