package com.example.exportControl.controller

import com.example.exportControl.model.Country
import com.example.exportControl.repository.CountryMasterRepository
import com.example.exportControl.repository.UserMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.security.Principal
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

/**
 * Country Master controller
 *
 */

@Controller
class CountryMasterController : MenuController() {

    @Autowired
    private val countryMasterRepo: CountryMasterRepository? = null

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    @Autowired
    private val messageSource: MessageSource? = null

    var systemErrorMsg: String? = null

    /**
     * 国名マスタ画面初期表示
     * @param model
     * @param principal
     */
    @RequestMapping("/countryMaster")
    fun countryMaster(model: Model, principal: Principal): String {
        this.systemErrorMsg = messageSource?.getMessage("systemErrorMsg", null, Locale.ENGLISH).toString()
        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val countryNameDeleteFlag0 = countryMasterRepo?.countryNameDeleteFlag0()
        model.addAttribute("countryNameDeleteFlag0", countryNameDeleteFlag0)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)
        model.addAttribute("CountryName", "")
        model.addAttribute("CountryNameList", countryMasterRepo?.selectAllCountry())
        model.addAttribute("CountryList", countryMasterRepo?.selectAllCountry())
        return "countryMaster"
    }

    /**
     * 検索処理
     * @param countryName
     * @param principal
     */
    @RequestMapping("searchCountryMaster")
    fun searchCountryMaster(
        model: Model,
        @Param("countryName") countryName: String?,
        principal: Principal

    ): String {
        try {
            this.systemErrorMsg = messageSource?.getMessage("systemErrorMsg", null, Locale.ENGLISH).toString()
            val userName = principal.name
            val muser = usermasterRepo?.selectUserByUserCode(userName)
            val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

            model.addAttribute("loginCompany", loginCompany)
            model.addAttribute("languageOption", webConfig?.messageSourcePath)

            val countryNameList: List<Country>? = countryMasterRepo?.selectAllCountry()
            val countryNames: MutableList<String> = mutableListOf()
            if (countryNameList != null) {
                for (countryName in countryNameList) {

                    countryName.countryName?.let { countryNames.add(it) }

                }
            }
            model.addAttribute("CountryName", countryName)
            model.addAttribute("CountryNames", countryNames)
            model.addAttribute("CountryNameList", countryMasterRepo?.selectAllCountry())
            model.addAttribute("CountryList", countryMasterRepo?.searchCountryName(countryName))
            return "countryMaster"
        } catch (e: Exception) {
            model.addAttribute("systemErrorMsg", this.systemErrorMsg)
            return "countryMaster"
        }
    }

    /**
     * 新規追加処理
     * @param model
     * @param principal
     * @param redirectAttributes
     */
    @RequestMapping("newCountryMaster")
    fun newCountryMaster(model: Model, principal: Principal, redirectAttributes: RedirectAttributes): String {
        try {
            this.systemErrorMsg = messageSource?.getMessage("systemErrorMsg", null, Locale.ENGLISH).toString()
            val userName = principal.name
            val muser = usermasterRepo?.selectUserByUserCode(userName)
            val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
            val countryCodeDeleteFlag0 = countryMasterRepo?.countryCodeDeleteFlag0()
            val countryCodeDeleteFlag1 = countryMasterRepo?.countryCodeDeleteFlag1()
            val countryNameDeleteFlag0 = countryMasterRepo?.countryNameDeleteFlag0()
            model.addAttribute("countryNameDeleteFlag0", countryNameDeleteFlag0)

            model.addAttribute("loginCompany", loginCompany)
            model.addAttribute("languageOption", webConfig?.messageSourcePath)
            model.addAttribute("countryCodeDeleteFlag0", countryCodeDeleteFlag0)
            model.addAttribute("countryCodeDeleteFlag1", countryCodeDeleteFlag1)
            //model.addAttribute("countryNameDeleteFlag0", countryNameDeleteFlag0)

            return "countryMasterDetail"
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("systemErrorMsg", this.systemErrorMsg)
            return "redirect:/countryMaster"
        }
    }

    /**
     * 削除処理
     * @param countryCode
     * @param redirectAttributes
     */
    @RequestMapping("/deleteCountryMaster/{countryCode}", method = [RequestMethod.GET])
    fun deleteCountryMaster(
        @PathVariable("countryCode") countryCode: String,
        redirectAttributes: RedirectAttributes,
        principal: Principal,
    ): String {
        return try {
            val userName = principal.name
            val currentDateTime: LocalDateTime = LocalDateTime.now()
            val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
            countryMasterRepo?.deleteCountryMaster(countryCode, currentTime, userName)
            "redirect:/countryMaster"
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("systemErrorMsg", this.systemErrorMsg)
            "redirect:/countryMaster"
        }
    }

    /**
     * 更新処理
     * @param countryCode
     * @param countryName
     * @param redirectAttributes
     */
    @RequestMapping("/updateCountryMaster")
    fun updateCountryMaster(
        @Param("countryCode") countryCode: String?,
        @Param("countryName") countryName: String?,
        model: Model, redirectAttributes: RedirectAttributes,
        principal: Principal
    ): String {
        return try {
            val currentDateTime: LocalDateTime = LocalDateTime.now()
            val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
            val userName = principal.name
            countryMasterRepo?.updateCountryMaster(countryCode, countryName, currentTime, userName)
            "redirect:/countryMaster"
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("systemErrorMsg", this.systemErrorMsg)
            "redirect:/countryMaster"
        }
    }

    /**
     * 保存処理
     * @param countryCode
     * @param countryName
     * @param redirectAttributes
     */
    @RequestMapping("/saveCountryMaster")
    fun saveCountryMaster(
        @Param("countryCode") countryCode: String?,
        @Param("countryName") countryName: String?,
        @Param("updateCheck") updateCheck: String?,
        model: Model, redirectAttributes: RedirectAttributes,
        principal: Principal
    ): String {
        try {
            val currentDateTime: LocalDateTime = LocalDateTime.now()
            val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
            val userName = principal.name
            if (updateCheck == "false") {
                val saveCountryMaster = Country(
                    countryCode,
                    countryName,
                    userName,
                    currentTime,
                    null,
                    null,
                    0
                )
                countryMasterRepo?.save(saveCountryMaster)
            } else {
                countryMasterRepo?.updateCountryMaster(countryCode, countryName, currentTime, userName)
            }
            return "redirect:/countryMaster"
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("systemErrorMsg", this.systemErrorMsg)
            return "redirect:/newCountryMaster"
        }
    }

    /**
     * 会社選択画面移動
     * @param model
     */
    @RequestMapping("companyselection")
    fun companySelection(model: Model): String {
        return "companySelection"

    }
}
