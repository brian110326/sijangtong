package com.example.sijangtong.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Override
    public void sendMail(String name, String title, String content, String email) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);

            // 제목, 내용 설정
            helper.setSubject("제목");
            helper.setText("내용", false);

            helper.setFrom("cloudtechflow@gmail.com");
            // 메일 전송(setTo 파라미터에 문자열 리스트를 넘기면 한번에 여러명에게 전송 가능)
            helper.setTo("cloudtechflow@gmail.com");

            emailSender.send(message);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 발신자 설정(연동된 구글 계정으로 고정)

        // 로컬 첨부 파일 설정

    }

}
