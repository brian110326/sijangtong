package com.example.sijangtong.service;

import com.example.sijangtong.dto.MesageDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

  @Autowired
  private JavaMailSender javaMailSender;

  // 단순 메일

  public void simpleMail(MesageDto mesageDto) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject(mesageDto.getSubject());
    message.setText(mesageDto.getText());

    message.setTo("z1130ion@naver.com");
    message.setFrom(mesageDto.getMeberEmail());

    javaMailSender.send(message);
  }
}
