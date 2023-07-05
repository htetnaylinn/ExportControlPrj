package com.example.exportControl.controller

import com.example.exportControl.config.WebConfiguration
import com.example.exportControl.repository.CompanyMasterRepository
import com.example.exportControl.repository.UserMasterRepository
import com.example.exportControl.utils.AESUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal
import java.util.*

@Controller
class PasswordChangeController : MenuController() {

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    @Autowired
    private val AESUtils:AESUtils? = null

    @Autowired
    private val messageSource: MessageSource? = null

    @RequestMapping("/passwordChange")
    fun passwordChange(
        model: Model,
        principal: Principal,
        @ModelAttribute("newPassword") newPassword: String?,
        ): String {

        val userId = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userId)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

        var encryptedPasswrod:String? = null

        if(newPassword != ""){
            encryptedPasswrod = AESUtils?.encrypt(newPassword!!)
            usermasterRepo?.changePasswordById(userId,encryptedPasswrod)
            model.addAttribute("changePasswordSuccessMsg", messageSource?.getMessage("changePasswordSuccessMsg", null, Locale.ENGLISH).toString())
        }
        val password = usermasterRepo?.selectUserPasswordById(userId)
        var decryptedPassword = AESUtils?.decrypt(password)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("currentPassword", decryptedPassword)

        return "passwordChange"
    }




}
