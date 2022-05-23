package com.example.emailtemplate.repository;

import com.example.emailtemplate.entity.Mail;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailSenderService {
    void sendEmail(Mail mail) throws MessagingException;

    void generateMessage() throws MessagingException;
}
