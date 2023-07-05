package com.example.exportControl.controller

import com.example.exportControl.config.WebConfiguration
import com.example.exportControl.model.Company
import com.example.exportControl.repository.CompanyMasterRepository
import com.example.exportControl.repository.UserControlRepository
import com.example.exportControl.repository.UserMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.security.Principal
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * EntryPageController
 *
 */
@Controller
class EntryPageController  {

    @Autowired
    val userMasterRepo: UserMasterRepository? = null

    @Autowired
    val companyMasterRepo: CompanyMasterRepository? = null

    @Autowired
    val webConfig: WebConfiguration? = null

    @Autowired
    private val messageSource: MessageSource? = null
    /**
     * 権限で画面移動処理
     * @param model
     * @param principal
     * @param request
     */
    @RequestMapping(value = ["/entryPage"], method = [RequestMethod.GET])
    fun entryPage(model: Model, principal: Principal, request: HttpServletRequest,redirectAttributes: RedirectAttributes): String{
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        if(user != null){
            val companyValid = userMasterRepo?.selectCompanyNameUserCode(userName)
            if(companyValid != null){
                val companyCode = user?.companyCode
                val loginCompany: Company? = companyMasterRepo?.selectCompanyByCompanyCode(companyCode)
                model.addAttribute("loginCompany",loginCompany)
                model.addAttribute("languageOption",webConfig?.messageSourcePath)
                if(user?.role == "2"){
                    val companyList = companyMasterRepo?.selectAllCompany()
                    model.addAttribute("companyList",companyList)
                    model.addAttribute("muser",user)
                    return "companySelection"
                }else{
                    return "redirect:/scheduleOne"
                }
            }else{
                SecurityContextHolder.getContext().authentication = null
                redirectAttributes.addFlashAttribute("systemErrorMsg", messageSource?.getMessage("companyExpiredErrorMsg", null, Locale.ENGLISH).toString())
                return "redirect:/"
            }

        }else{
            SecurityContextHolder.getContext().authentication = null
            redirectAttributes.addFlashAttribute("systemErrorMsg", messageSource?.getMessage("userExpiredErrorMsg", null, Locale.ENGLISH).toString())
            return "redirect:/"
        }

    }
}