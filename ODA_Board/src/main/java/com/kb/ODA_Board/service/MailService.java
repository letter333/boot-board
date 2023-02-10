package com.kb.ODA_Board.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface MailService {
    MimeMessage createMessage(String to, String type) throws MessagingException, UnsupportedEncodingException;
    String createKey();
    String createPassword();
    String sendSimpleMessage(String to, String type) throws Exception;
}