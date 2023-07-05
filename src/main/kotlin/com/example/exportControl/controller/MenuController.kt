package com.example.exportControl.controller

import com.example.exportControl.config.WebConfiguration
import com.example.exportControl.repository.CompanyMasterRepository
import com.example.exportControl.repository.UserControlRepository
import com.example.exportControl.repository.UserMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import java.security.Principal

@Controller
class MenuController {
    @Autowired
    val userMasterRepo: UserMasterRepository? = null

    @Autowired
    val companyMasterRepo: CompanyMasterRepository? = null

    @Autowired
    private val userControlRepo: UserControlRepository? = null

    @Autowired
    val webConfig: WebConfiguration? = null

    var role :String? = null

    @ModelAttribute("loginUserCode")
    fun menuControl(model:Model,principal: Principal):String?{
        val loginUser = (principal as Authentication).principal as User
        val userCode = loginUser.username
        val user = userMasterRepo?.selectUserByUserCode(userCode)
        this.role = user?.role
            var userControlFlag = userControlRepo?.selectAllByUserId(userCode)

        model.addAttribute("role",this.role)
        model.addAttribute("loginUserCode",userCode)
        model.addAttribute("userControlFlag",userControlFlag)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)

        return userCode
    }

}