package com.example.exportControl.controller

import com.example.exportControl.config.WebConfiguration
import com.example.exportControl.model.BannerTemp
import com.example.exportControl.model.requestModel.InquiryRequestModel
import com.example.exportControl.model.requestModel.VideoParseModel
import com.example.exportControl.repository.*
import com.example.exportControl.service.InquiryEmailSenderService
import com.sun.mail.smtp.SMTPAddressFailedException
import com.sun.mail.util.MailConnectException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.core.io.ByteArrayResource
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import java.security.Principal
import java.util.*
import javax.mail.MessagingException
import javax.mail.SendFailedException
import javax.net.ssl.HttpsURLConnection
import javax.servlet.http.HttpServletRequest

/**
 * LoginController
 *
 */
@Controller
class SendInquiryController {
    @Autowired
    var inquiryEmailSender: InquiryEmailSenderService? = null

    @Autowired
    private val messageSource: MessageSource? = null

    @Autowired
    val userMasterRepo: UserMasterRepository? = null

    @Autowired
    val companyMasterRepo: CompanyMasterRepository? = null

    @Autowired
    val webConfig: WebConfiguration? = null

    private val PROTOCOL = "pop3"
    private val HOST = "honda-logi.com"

    @RequestMapping("/sendInquiryBeforeLogin")
    fun sendInquiryBeforeLogin(
        model: Model,
        redirectAttributes: RedirectAttributes,
        @ModelAttribute("inquiry") inqReq: InquiryRequestModel?,
    ): Any {
        try {
            inquiryEmailSender?.inquiryEmailSender(this.PROTOCOL, this.HOST, getSendProperties(), inqReq)
        } catch (e: MessagingException) {
            if (e is SendFailedException) {
                val e2 = e.nextException
                if (e2 is SMTPAddressFailedException) {
                    redirectAttributes.addFlashAttribute(
                        "systemErrorMsg",
                        messageSource?.getMessage("wrongAddressErrMsg", null, Locale.ENGLISH).toString()
                    )
                    return RedirectView("/inquiryBeforeLogin", true)
                }
            } else if (e is MailConnectException) {
                redirectAttributes.addFlashAttribute(
                    "systemErrorMsg",
                    messageSource?.getMessage("mailConnectionTimeoutErrMsg", null, Locale.ENGLISH).toString()
                )
                return RedirectView("/inquiryBeforeLogin", true)
            }
        }
        return RedirectView("/inquiryBeforeLogin", true)
    }
    fun getSendProperties(): Properties {
        val props = Properties()
        props["mail.smtp.host"] = HOST
        props["mail.smtp.port"] = "25"
        return props
    }
}