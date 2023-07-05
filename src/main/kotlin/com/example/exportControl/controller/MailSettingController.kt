package com.example.exportControl.controller

import com.example.exportControl.model.requestModel.MailMessage
import com.example.exportControl.repository.MailReadHistoryRepository
import com.example.exportControl.repository.MailSettingRepository
import com.example.exportControl.repository.UserMasterRepository
import com.example.exportControl.service.MailSettingService
import com.example.exportControl.service.ReadEmailBaseImpl
import com.example.exportControl.util.CSVHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.security.Principal
import java.util.*
import javax.mail.BodyPart
import javax.mail.MessagingException
import javax.mail.Multipart
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMultipart


@Controller
class MailSettingController : MenuController() {

    @Autowired
    override val userMasterRepo: UserMasterRepository? = null

    @Autowired
    val mailSettingService: MailSettingService? = null

    @Autowired
    val mailSettingRepo: MailSettingRepository? = null


    @RequestMapping("/mailSetting")
    fun mailSetting(model: Model, principal: Principal, @RequestParam(value = "allMail") allMail: String?): String {
        val mailList = allMail;
        val userName = principal.name
        val muser = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        var allMailSetting = mailSettingRepo?.selectAllRecords(muser?.companyCode)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("allMailSetting",allMailSetting)
        return "mailSetting"
    }

    @RequestMapping(value= ["/uploadMailSettingFile"], method= [RequestMethod.POST])
    fun uploadCSVFile(@RequestParam("file") file: MultipartFile,
                      principal: Principal, model: Model,redirectAttributes:RedirectAttributes): String? {
        var temp = file;
        println("h")
        if (CSVHelper.hasCSVFormat(file)) {
            var userCodeList = mailSettingRepo?.selectAllUserCode()
            var saveFlag = mailSettingService?.saveMailSetting(file,userCodeList!!,webConfig?.messageSourcePath)
            if(saveFlag is String){
                redirectAttributes.addFlashAttribute("saveErrorMsg", saveFlag)
            }
        }else{
            redirectAttributes.addFlashAttribute("saveErrorMsg", if(webConfig?.messageSourcePath != 0){"File format is wrong."}else{"ファイルのフォーマットが間違っています。"})
        }
        return "redirect:/mailSetting"
    }

}