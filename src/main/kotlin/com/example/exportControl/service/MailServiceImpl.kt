package com.example.exportControl.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine

@Component
class MailServiceImpl : MailService {


//    lateinit var sender: String

    @Autowired
    lateinit var environment: Environment

    @Autowired
    override var emailSender: JavaMailSender? = null

    @Autowired
    override lateinit var templateEngine: SpringTemplateEngine



    override fun sendSimpleMessageUsingTemplate(to: String,
                                                subject: String,
                                                template: String,
                                                params:MutableMap<String, Any>) {
        val message = emailSender!!.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "utf-8")
        var context: Context = Context()
        context.setVariables(params)
        val html: String = templateEngine.process(template, context)

        helper.setTo(to)
        helper.setFrom("maythazin.mtz15@gmail.com")
        helper.setText(html, true)
        helper.setSubject(subject)

        emailSender!!.send(message)
    }



}