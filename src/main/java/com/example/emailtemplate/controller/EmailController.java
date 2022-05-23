package com.example.emailtemplate.controller;

import com.example.emailtemplate.repository.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailController {
    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("/test")
    public String email() throws MessagingException {
        emailSenderService.generateMessage();
        return "nguyen tien dong dep trai";
    }
}
