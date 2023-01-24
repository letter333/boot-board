package com.kb.ODA_Board.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface MailService {
    MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;
    String createKey();
    String sendSimpleMessage(String to) throws Exception;
}