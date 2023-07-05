package com.example.exportControl.service

import com.example.exportControl.model.requestModel.InquiryRequestModel
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.io.IOException
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

@Service
class InquiryEmailSenderService(private var templateEngine: TemplateEngine?) {


    @Throws(MessagingException::class, IOException::class)
    fun inquiryEmailSender(PROTOCOL: String?, HOST: String?, props: Properties?, inqReq: InquiryRequestModel?){

        val session = Session.getInstance(props)
        val message: MimeMessage = MimeMessage(session)

        message.setFrom(InternetAddress(inqReq?.mail))

        message.addRecipient(Message.RecipientType.TO, InternetAddress("thazin-wai@honda-logi.com"))

        message.subject = "問い合わせ"

        val context = Context()
        context.setVariable("inqReq", inqReq)

        val process = templateEngine?.process("estimateMail", context)
        val messageBodyPart = MimeBodyPart()
        messageBodyPart.setText(process, "utf-8", "html") //here the email template is inputted
        val multipart: Multipart = MimeMultipart()
        multipart.addBodyPart(messageBodyPart)
        message.setContent(multipart)

        Transport.send(message)

    }


}