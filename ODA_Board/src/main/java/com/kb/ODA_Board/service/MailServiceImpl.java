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
    public MimeMessage createMessage(String to, String type) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        StringBuilder html = new StringBuilder("");

        if(type.equals("createMember")) {
            message.setSubject("회원가입 이메일 인증");

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
        } else if(type.equals("findPassword")) {
            message.setSubject("임시 비밀번호 발급");

            html.append("<div style='margin: 100px'>");
            html.append("<h1> 안녕하세요 </h1>");
            html.append("<h1> 임시로 발급된 비밀번호입니다..</h1>");
            html.append("<br>");
            html.append("<h1>임시 비밀번호로 로그인 후 반드시 비밀번호를 변경해주세요.</h1>");
            html.append("<br>");
            html.append("<div align='center' style='border: 1px solid black; font-family: verdana';>");
            html.append("<h3 style='color:blue;'>임시 비밀번호.</h3>");
            html.append("<div style='font-size:130%'>");
            html.append("인증번호 : <strong>");
            html.append(code).append("</strong><div><br>");
            html.append("</div>");
            message.setText(String.valueOf(html), "utf-8", "html");
            message.setFrom(new InternetAddress("seryong12@naver.com", "letter33"));
        }
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
    public String createPassword() {
        StringBuffer pw = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < 11; i++) {
            int idx = random.nextInt(2);

            switch (idx) {
                case 0:
                    pw.append((char)((int)(random.nextInt(26)) + 97));
                    break;
                case 1:
                    pw.append((char)((int)(random.nextInt(26)) + 65));
                    break;
            }
        }

        return pw.toString();
    }

    @Override
    public String sendSimpleMessage(String to, String type) throws Exception {
        if(type.equals("createMember")) {
            code = createKey();
        } else if(type.equals("findPassword")) {
            code = createPassword();
        }

        MimeMessage message = createMessage(to, type);

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return code;
    }
}