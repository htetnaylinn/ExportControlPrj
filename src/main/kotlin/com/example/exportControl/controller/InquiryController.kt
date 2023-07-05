package com.example.exportControl.controller

import com.example.exportControl.model.requestModel.InquiryRequestModel
import com.example.exportControl.service.InquiryEmailSenderService
import com.sun.mail.smtp.SMTPAddressFailedException
import com.sun.mail.util.MailConnectException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.security.Principal
import java.util.*
import javax.mail.MessagingException
import javax.mail.SendFailedException
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress


@Controller
class InquiryController : MenuController() {

    @Autowired
    var inquiryEmailSender: InquiryEmailSenderService? = null

    @Autowired
    var mailBoxControl: MailBoxController? = null

    @Autowired
    private val messageSource: MessageSource? = null

    private val PROTOCOL = "pop3"
    private val HOST = "honda-logi.com"

    @RequestMapping("inquiry")
    fun inquiry(model: Model, principal: Principal): String {

        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)
        model.addAttribute("menuBarFlag", 1)
        model.addAttribute("formActionName", "sendInquiry")

        model.addAttribute("userCode", userName)

        return "inquiry"
    }

    @RequestMapping("/sendInquiry")
    fun sendInquiry(
        model: Model,
        redirectAttributes: RedirectAttributes,
        principal: Principal,
        @ModelAttribute("inquiry") inqReq: InquiryRequestModel?,
    ): Any {

        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)

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
                    return RedirectView("/inquiry", true)
                }
            } else if (e is MailConnectException) {
                redirectAttributes.addFlashAttribute(
                    "systemErrorMsg",
                    messageSource?.getMessage("mailConnectionTimeoutErrMsg", null, Locale.ENGLISH).toString()
                )
                return RedirectView("/inquiry", true)
            }
        }
        return RedirectView("/inquiry", true)
    }

    fun getSendProperties(): Properties {
        val props = Properties()
        props["mail.smtp.host"] = HOST
        props["mail.smtp.port"] = "25"
        return props
    }

    private fun validateEmail(email: String): Boolean {
        var isValid = false
        try {
            val internetAddress = InternetAddress(email)
            internetAddress.validate()
            isValid = true
        } catch (e: AddressException) {
            e.printStackTrace()
        }
        return isValid
    }
}