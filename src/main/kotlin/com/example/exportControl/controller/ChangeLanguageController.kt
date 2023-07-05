package com.example.exportControl.controller

import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * ChangeLanguageController
 *
 */

@Controller
class ChangeLanguageController : MenuController() {

    /**
     * 言語変更
     * @param url
     */
//    @RequestMapping("changeLanguage")
//    fun changeLanguage(@Param("url")url: String?):String{
//        var toggle = webConfig?.messageSourcePath;
//
//        if(toggle == 0){
//            webConfig?.messageSourcePath = 1;
//        }else if (toggle == 1){
//            webConfig?.messageSourcePath = 0;
//        }
//
//        println(webConfig?.messageSourcePath)
//        webConfig?.langChange()
//        println(url)
//        println("urllanguage")
//        return "redirect:/$url"
//    }
}