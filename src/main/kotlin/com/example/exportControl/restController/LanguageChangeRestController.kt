package com.example.exportControl.restController

import com.example.exportControl.controller.MenuController
import com.example.exportControl.message.ReactedPerson
import com.example.exportControl.repository.ReactUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

@RestController
class LanguageChangeRestController:MenuController() {

    @Autowired
    private val reactuserRepo: ReactUserRepository? = null



    @GetMapping("changeLanguage")
    fun changeLanguage():String{
        var toggle = webConfig?.messageSourcePath;

        if(toggle == 0){
            webConfig?.messageSourcePath = 1;
        }else if (toggle == 1){
            webConfig?.messageSourcePath = 0;
        }

        println(webConfig?.messageSourcePath)
        webConfig?.langChange()
        println("urllanguage")
        return "success"
    }
}