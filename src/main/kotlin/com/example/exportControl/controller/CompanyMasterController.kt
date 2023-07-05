package com.example.exportControl.controller

import com.example.exportControl.model.requestModel.CompanyRequestModel
import com.example.exportControl.repository.*
import com.example.exportControl.service.CompanyControlService
import com.example.exportControl.service.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.security.Principal
import javax.servlet.http.HttpServletResponse

/**
 * Company Master controller
 *
 */
@Controller
class CompanyMasterController : MenuController() {

    @Autowired
    private val countryMasterRepo: CountryMasterRepository? = null

    @Autowired
    private val companyService: CompanyService? = null

    @Autowired
    private val companyControlService: CompanyControlService? = null

    @Autowired
    var messageSource: MessageSource? = null

    /**
     * 会社マスタ画面初期表示
     * @param model
     * @param principal
     */
    @RequestMapping("companyMaster")
    fun companyMaster(model: Model, principal: Principal): String {
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        val companyNameJpDeleteFlag0 = companyMasterRepo?.companyNameJpDeleteFlag0()
        val companyNameEnDeleteFlag0 = companyMasterRepo?.companyNameEnDeleteFlag0()

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)
        model.addAttribute("companyList", companyMasterRepo?.selectAllCompany())
        model.addAttribute("countryNameList", countryMasterRepo?.selectAllCountry())
        model.addAttribute("companyNameJpDeleteFlag0", companyNameJpDeleteFlag0)
        model.addAttribute("companyNameEngDeleteFlag0", companyNameEnDeleteFlag0)

        return "companyMaster"
    }

    /**
     * 検索処理
     * @param model
     * @param principal
     * @param companyReq
     */
    @RequestMapping("searchCompanyMaster", method = [RequestMethod.POST])
    fun searchCompanyMaster(
        model: Model,
        @ModelAttribute("company") companyReq: CompanyRequestModel?,
        principal: Principal,response: HttpServletResponse

    ): String {
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        // search company information
        val companyList = companyService?.searchCompany(companyReq)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)
        model.addAttribute("companyCode", companyReq?.companyCode)
        model.addAttribute("searchCompanyName", companyReq?.companyNameJpn)
        model.addAttribute("companyList", companyList)
        model.addAttribute("countryNameList", countryMasterRepo?.selectAllCountry())

        response.setHeader("Cache-Control", "no-cache");
        return "companyMaster"
    }

    /**
     * 新規追加処理
     * @param model
     * @param principal
     */
    @RequestMapping("companyMasterDetail")
    fun newCompanyMaster(model: Model, principal: Principal): String {
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        val companyCodeDeleteFlag0 = companyMasterRepo?.companyCodeDeleteFlag0()
        val companyCodeDeleteFlag1 = companyMasterRepo?.companyCodeDeleteFlag1()
        val companyNameJpDeleteFlag0 = companyMasterRepo?.companyNameJpDeleteFlag0()
        val companyNameEnDeleteFlag0 = companyMasterRepo?.companyNameEnDeleteFlag0()

        model.addAttribute("loginCompany",loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("companyCodeDeleteFlag0", companyCodeDeleteFlag0)
        model.addAttribute("companyCodeDeleteFlag1", companyCodeDeleteFlag1)
        model.addAttribute("companyNameJpDeleteFlag0", companyNameJpDeleteFlag0)
        model.addAttribute("companyNameEngDeleteFlag0", companyNameEnDeleteFlag0)
//        model.addAttribute("companyNameEnDeleteFlag0", companyNameEnDeleteFlag0)
        model.addAttribute("CountryNameList", countryMasterRepo?.selectAllCountry())

        return "companyMasterDetail"
    }

    /**
     * 更新処理
     * @param companyReq
     * @param model
     */
    @RequestMapping("/updateCompanyMaster")
    fun updateCompanyMaster(
            @ModelAttribute("company") companyReq: CompanyRequestModel?,
            model: Model
    ): String {
        companyService?.updateCompany(companyReq)
        return "redirect:/companyMaster"
    }

    /**
     * 削除処理
     * @param companyCode
     */
    @RequestMapping("/deleteCompanyMaster/{companyCode}", method = [RequestMethod.GET])
    fun deleteCompanyMaster(@PathVariable("companyCode") companyCode: String): String {
        companyService?.deleteCompany(companyCode)
        return "redirect:/companyMaster"
    }

    /**
     * 新規登録処理
     * @param companyReq
     */
    @RequestMapping("/saveCompanyMaster")
    fun saveCompanyMaster(
            @ModelAttribute("company") companyReq: CompanyRequestModel?,
            redirectAttributes: RedirectAttributes,model: Model,
            principal: Principal
    ): String {
        val userName = principal.name
//        companyReq.
        //val comObj = companyService?.selectCompanyByCompanyCode(companyReq)
        companyService?.insCompany(companyReq)
//        companyControlService?.insCompanyControl(companyReq)
        return "redirect:/companyMaster"
    }
}
