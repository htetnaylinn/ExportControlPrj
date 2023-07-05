package com.example.exportControl.controller

import com.example.exportControl.message.Response
import com.example.exportControl.model.PatternRegistration
import com.example.exportControl.repository.CountryMasterRepository
import com.example.exportControl.repository.PatternRegistrationRepository
import com.example.exportControl.repository.UserMasterRepository
import com.example.exportControl.service.CountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.ArrayList

@RestController
class PatternRegistrationController : MenuController()  {

    @Autowired
    private val countryService: CountryService? = null
    @Autowired
    private val countryMasterRepo: CountryMasterRepository? = null

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null
    @Autowired
    private val messageSource: MessageSource? = null

    @Autowired
    private val patternRepo: PatternRegistrationRepository? = null


//    @GetMapping(value = ["/getExportData/patternName"])
//    fun getExportData(@RequestParam(value = "patternName") patternName: String?): Response {
//        val exportData: MutableList<String> = ArrayList()
//        val patternObj: PatternRegistration? = patternRepo?.getPatternObj(patternName)
//        return Response("message", patternObj)
//    }

    @GetMapping(value = ["/patternUpdate"])
    fun updatePattern(model: Model): String {

        val patternLst: List<PatternRegistration>? = patternRepo?.getAllProducts()

        model.addAttribute("allPatternList", patternLst)

        return "patternEdit"
    }

}
