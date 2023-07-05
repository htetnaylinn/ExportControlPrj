package com.example.exportControl.service


import org.springframework.mail.javamail.JavaMailSender
import org.thymeleaf.spring5.SpringTemplateEngine

internal interface  MailService {

    val emailSender: JavaMailSender?
    val templateEngine: SpringTemplateEngine?

    fun sendSimpleMessageUsingTemplate(to: String,
                                       subject: String,
                                       template: String,
                                       params:MutableMap<String, Any>)
}