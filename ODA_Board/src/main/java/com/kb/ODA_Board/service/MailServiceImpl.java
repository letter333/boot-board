package com.kb.ODA_Board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    private String code;

    @Override
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("회원가입 이메일 인증");

        StringBuilder html = new StringBuilder("");
        html.append("<div style='margin: 100px'>");
        html.append("<h1> 안녕하세요 </h1>");
        html.append("<h1> 회원가입 이메일 인증을 위한 인증번호 입니다.</h1>");
        html.append("<br>");
        html.append("<h1>아래 인증번호를 회원가입 창에서 입력해주세요.</h1>");
        html.append("<br>");
        html.append("<div align='center' style='border: 1px solid black; font-family: verdana';>");
        html.append("<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>");
        html.append("<div style='font-size:130%'>");
        html.append("인증번호 : <strong>");
        html.append(code).append("</strong><div><br>");
        html.append("</div>");
        message.setText(String.valueOf(html), "utf-8", "html");
        message.setFrom(new InternetAddress("seryong12@naver.com", "letter33"));

        return message;
    }

    @Override
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < 8; i++) {
            int idx = random.nextInt(3);

            switch (idx) {
                case 0:
                    key.append((char)((int)(random.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char)((int)(random.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(10));
                    break;
            }
        }

        return key.toString();
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        code = createKey();

        MimeMessage message = createMessage(to);

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return code;
    }
}