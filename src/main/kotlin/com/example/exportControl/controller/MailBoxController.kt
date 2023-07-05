package com.example.exportControl.controller

import com.example.exportControl.model.requestModel.MailMessage
import com.example.exportControl.repository.MailReadHistoryRepository
import com.example.exportControl.repository.MailSettingRepository
import com.example.exportControl.repository.UserMasterRepository
import com.example.exportControl.service.ReadEmailBaseImpl
import com.sun.mail.util.MailConnectException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.UnknownHostException
import java.security.Principal
import java.util.*
import javax.mail.BodyPart
import javax.mail.MessagingException
import javax.mail.Multipart
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMultipart
import javax.servlet.http.HttpServletResponse


@Controller
class MailBoxController : MenuController() {

    @Autowired
    override val userMasterRepo: UserMasterRepository? = null

    @Autowired
    private val readEmailBaseImpl: ReadEmailBaseImpl? = null

    @Autowired
    private val mailReadHistoryRepo: MailReadHistoryRepository? = null

    @Autowired
    private val messageSource: MessageSource? = null

    @Autowired
    val mailSettingRepo: MailSettingRepository? = null

    private var PROTOCOL:String? = null
    private var HOST:String? = null
    private var USERNAME:String? = null
    private var PORT:String? = null
    private var PASSWORD:String? = null
    private var UnzipedMail:String? = null

    //
    fun getProperties(): Properties? {
        val props = Properties()
        props["mail.pop3.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        props["mail.pop3.socketFactory.fallback"] = "true"
        props["mail.pop3.socketFactory.port"] = "110"
        props["mail.pop3.port"] = "110"
        props["mail.pop3.ssl.trust"] = HOST
        props["mail.pop3.host"] = HOST
        props["mail.pop3.user"] = USERNAME
        props["mail.pop3.protocol"] = PROTOCOL
        return props
    }

    fun getSendProperties(): Properties? {
        val props = Properties()
        props["mail.smtp.host"] = HOST
        props["mail.smtp.port"] = PORT
        return props
    }

    var selectedSearchMailList : List<String>? = emptyList()

    @RequestMapping("/mailBoxSend")
    fun process(model: Model,redirectAttributes: RedirectAttributes, principal: Principal,response: HttpServletResponse): Any {
        val userCode = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userCode)
        val companyObj = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        val companyCode = companyObj?.companyCode
        val userSelectLst = userMasterRepo?.searchUserWithCompanyCodeNotIncludedSingedUser(user?.companyCode,userCode)
//        model.addAttribute("userSelectLst",userSelectLst)

        var UserMailSetting = mailSettingRepo?.selectRecordsByUserCode(userCode)
        if(UserMailSetting == null){
            redirectAttributes.addFlashAttribute("systemErrorMsg", messageSource?.getMessage("nomailSetting", null, Locale.ENGLISH).toString())
            return RedirectView("/mailCheck", true)
        }else{
            this.PROTOCOL = UserMailSetting.protocol
            this.HOST = UserMailSetting.host
            this.USERNAME = UserMailSetting.userName
            this.PORT = UserMailSetting.port
            this.PASSWORD = UserMailSetting.mailPassword
            this.UnzipedMail = UserMailSetting.unzippedEmailAddress
            try {
                readEmailBaseImpl?.process(this.PROTOCOL, this.HOST,this.USERNAME,this.PASSWORD, getProperties(),companyCode, userCode,response,this.UnzipedMail,this.selectedSearchMailList!!)
                redirectAttributes.addFlashAttribute("msgReplyList",readEmailBaseImpl?.msgReplyList)
                redirectAttributes.addFlashAttribute("msgList",readEmailBaseImpl?.msgInboxList)
                redirectAttributes.addFlashAttribute("userSelectLst",userSelectLst)
//            return "mailBox"
                return RedirectView("/mailCheck", true)
            } catch (e: MessagingException) {
//            logger.error("Error processing emails. " + e.message)
                if(e is MailConnectException){
                    if(e.nextException is UnknownHostException){
                        redirectAttributes.addFlashAttribute("systemErrorMsg", messageSource?.getMessage("wrongMailSetting", null, Locale.ENGLISH).toString())

                    }else{
                        redirectAttributes.addFlashAttribute("systemErrorMsg", messageSource?.getMessage("mailConnectionTimeout", null, Locale.ENGLISH).toString())
                    }
                }
                e.printStackTrace()
                return RedirectView("/mailCheck", true)
            } catch (e: IOException) {
//
                e.printStackTrace()
                return RedirectView("/mailCheck", true)
            }
        }


    }

    @RequestMapping("/mailCheck")
    fun mailCheck(model: Model,principal: Principal): String {

        val userName = principal.name
        val muser = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val mailReadHistory = mailReadHistoryRepo?.selectReadHistoryByUserCode(userName)
        model.addAttribute("selectedSearchMailList",this.selectedSearchMailList)
        this.selectedSearchMailList = emptyList()
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        var mailIdList:MutableList<Int> = ArrayList()

        mailReadHistory?.forEachIndexed { index, e ->
            var id = e.mailId
            if (id != null) {
                mailIdList.add(id)
            }
        }
        var list = mailIdList
        model.addAttribute("mailIdList",mailIdList)
        model.addAttribute("loginCompany", loginCompany)

        return "mailBox"
    }

    @RequestMapping("/sendMail")
    fun sendMail(model: Model, principal: Principal,
                 @RequestParam(value = "allMail") allMail: String?,
                 @RequestParam(value = "mailHeader") mailHeader: String?,
                 @RequestParam(value = "mailBody") mailBody: String?,
                 @RequestParam(value = "mailReply") mailReply: String?,
                 @RequestParam(value = "mailSenderAddress") mailSenderAddress: String?,
                 @RequestParam(value = "file")file: List<MultipartFile>?,
                 redirectAttributes: RedirectAttributes
    ): Any {

        val mailList = allMail
        val userName = principal.name
        var file = file;

//        val inputStream: InputStream = BufferedInputStream(file?.get(0)?.getInputStream())
//        inputStream.read()
        val multipart: Multipart = MimeMultipart()
        var messageBodyPart: BodyPart = MimeBodyPart()
//        messageBodyPart = MimeBodyPart()
//        val filename = "/home/manisha/file.txt"
//        val source: DataSource = FileDataSource(file?.get(0)?.originalFilename)
//        messageBodyPart.setDataHandler(DataHandler(source))
//        messageBodyPart.setFileName(file?.get(0)?.originalFilename)
//            multipart.addBodyPart(file?.get(0).get)
//        val muser = usermasterRepo?.selectUserByUserCode(userName)
//        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
//        model.addAttribute("languageOption",webConfig?.messageSourcePath)
//        model.addAttribute("loginCompany", loginCompany)
        val userObj = userMasterRepo?.selectUserByUserCode(userName)
        val sendMailObj: MailMessage = MailMessage()

        sendMailObj?.mailSender= userObj?.emailAddress
        sendMailObj?.mailBody =  mailBody
        sendMailObj?.mailReply =  mailReply
        sendMailObj?.mailAdd = mailSenderAddress
        sendMailObj?.mailSubject = "RE:"+ mailHeader

        try {
                readEmailBaseImpl?.sendProcess(this.PROTOCOL, this.HOST, getSendProperties(),sendMailObj,file!!)

//            readEmailBaseImpl?.sendSimpleMessageUsingTemplate(getSendProperties(),sendMailObj)

            redirectAttributes.addFlashAttribute("msgReplyList",readEmailBaseImpl?.msgReplyList)
//            return "mailBox"
            return RedirectView("/mailCheck", true)
        } catch (e: MessagingException) {
//            logger.error("Error processing emails. " + e.message)
            e.printStackTrace()
            return RedirectView("/mailCheck", true)
        } catch (e: IOException) {
//            logger.error("Error processing emails. " + e.message)
            e.printStackTrace()
            return RedirectView("/mailCheck", true)
        }
        return "mailBox"
    }



    @RequestMapping("/mailFilterSearch")
    fun mailFilterSearch(model: Model,principal: Principal,
        @RequestParam(value = "mailFilterAddress") mailFilterAddress: List<String>?
        ): String {
        val mailList = mailFilterAddress
        val userName = principal.name
        val muser = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("loginCompany", loginCompany)
        this.selectedSearchMailList = mailList
        return "mailBox"
    }


}