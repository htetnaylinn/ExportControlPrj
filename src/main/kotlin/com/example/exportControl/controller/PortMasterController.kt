package com.example.exportControl.controller

import com.example.exportControl.model.Country
import com.example.exportControl.model.Port
import com.example.exportControl.repository.CountryMasterRepository
import com.example.exportControl.repository.PortMasterRepository
import com.example.exportControl.repository.UserMasterRepository
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import org.jfree.data.json.impl.JSONArray
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.security.Principal
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpServletResponse


/**
 * PortMasterController
 *
 */
@Controller
class PortMasterController : MenuController() {
    @Autowired
    private val portMasterRepo: PortMasterRepository? = null

    @Autowired
    private val countryMasterRepo: CountryMasterRepository? = null

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    var systemErrorMsg: String? = null

    @Autowired
    private val messageSource: MessageSource? = null

    /**
     * 港マスタ画面初期表示
     * @param model
     * @param principal
     */
    @RequestMapping("portMaster")
    fun portMaster(model: Model, principal: Principal): String {

        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val allPortList = portMasterRepo?.portNameDeleteFlag0()

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)
        model.addAttribute("AllPortList", portMasterRepo?.selectAllPort())
        model.addAttribute("PortList", portMasterRepo?.selectAllPort())
        model.addAttribute("CountryNameList", countryMasterRepo?.selectAllCountry())
        model.addAttribute("allPortList", allPortList)

        return "portMaster"
    }

    /**
     * 検索処理
     * @param model
     * @param principal
     * @param portCode
     * @param portName
     * @param countryName
     */
    @RequestMapping("searchPortMaster")
    fun searchPortMaster(
        model: Model,
        @Param("portCode") portCode: String?,
        @Param("portName") portName: String?,
        @Param("countryName") countryName: String?,
        principal: Principal,response: HttpServletResponse

    ): String {
        return try {
            this.systemErrorMsg = messageSource?.getMessage("systemErrorMsg", null, Locale.ENGLISH).toString()
            val userName = principal.name
            val muser = usermasterRepo?.selectUserByUserCode(userName);
            val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

            model.addAttribute("loginCompany", loginCompany)
            model.addAttribute("languageOption", webConfig?.messageSourcePath)
            model.addAttribute("portCode", portCode)
            model.addAttribute("portName", portName)
            model.addAttribute("countryName", countryName)
            val AllPortList :List<Port>? = portMasterRepo?.selectAllPort()

            val portCodes: MutableList<String> = mutableListOf()
            val portNames: MutableList<String> = mutableListOf()

            if (AllPortList != null) {
                for (portCode in AllPortList) {

                    portCode.portCode?.let { portCodes.add(it) }

                }
            }
            if (AllPortList != null) {
                for (portName in AllPortList) {

                    portName.portName?.let { portNames.add(it) }

                }
            }
            model.addAttribute("AllPortList", AllPortList)
            model.addAttribute("portCodes", portCodes)
            model.addAttribute("portNames", portNames)

            model.addAttribute("PortList", portMasterRepo?.searchPortCodeAndNameAndCountry(portCode, portName, countryName))

            val countryNameList: List<Country>? = countryMasterRepo?.selectAllCountry()
            val countryNames: MutableList<String> = mutableListOf()
            if (countryNameList != null) {
                for (countryName in countryNameList) {

                    countryName.countryName?.let { countryNames.add(it) }

                }
            }
            model.addAttribute("CountryNameList", countryNameList)
            model.addAttribute("CountryNames", countryNames)

            response.setHeader("Cache-Control", "no-cache");

            return "portMaster"
        } catch (e: Exception) {
            model.addAttribute("systemErrorMsg", this.systemErrorMsg)
            return "portMaster"
        }
    }

    /**
     * 削除処理
     * @param portCode
     */
    @RequestMapping("/deletePortMaster/{portCode}", method = [RequestMethod.GET])
    fun deletePortMaster(@PathVariable("portCode") portCode: String, principal: Principal, model: Model): String {
        return try {
            this.systemErrorMsg = messageSource?.getMessage("systemErrorMsg", null, Locale.ENGLISH).toString()
            var currentDateTime: LocalDateTime = LocalDateTime.now()
            val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
            portMasterRepo?.deletePortMaster(portCode, currentTime)
            return "redirect:/portMaster"
        } catch (e: Exception) {
            model.addAttribute("systemErrorMsg", this.systemErrorMsg)
            return "portMaster"
        }
    }

    /**
     *
     * @param model
     * @param principal
     */
    @RequestMapping("/portMasterDetail")
    fun portMasterDetail(model: Model, principal: Principal): String {

        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val portCodeDeleteFlag0 = portMasterRepo?.portCodeDeleteFlag0()
        val portCodeDeleteFlag1 = portMasterRepo?.portCodeDeleteFlag1()
        val allPortList = portMasterRepo?.portNameDeleteFlag0()

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)
        model.addAttribute("portCodeDeleteFlag0", portCodeDeleteFlag0);
        model.addAttribute("portCodeDeleteFlag1", portCodeDeleteFlag1);
        model.addAttribute("CountryNameList", countryMasterRepo?.selectAllCountry())
        model.addAttribute("allPortList", allPortList)

        return "portMasterDetail"
    }

    /**
     *　更新処理
     * @param model
     * @param portCode
     * @param portName
     * @param countryName
     */
    @RequestMapping("/updatePortMaster")
    fun updatePortMaster(
        @Param("portCode") portCode: String?,
        @Param("portName") portName: String?,
        @Param("countryName") countryName: String?,
        model: Model
    ): String {
        return try {
            this.systemErrorMsg = messageSource?.getMessage("systemErrorMsg", null, Locale.ENGLISH).toString()
            var currentDateTime: LocalDateTime = LocalDateTime.now()
            val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
            portMasterRepo?.updatePortMaster(portCode, portName, countryName, currentTime)
            return "redirect:/portMaster"
        } catch (e: Exception) {
            model.addAttribute("systemErrorMsg", this.systemErrorMsg)
            return "portMaster"
        }
    }

    @RequestMapping("/savePortMaster")
    fun savePortMaster(
        @Param("portCode") portCode: String?,
        @Param("portName") portName: String?,
        @Param("countryName") countryName: String?,
        @Param("updateCheck") updateCheck: String?,
        model: Model
    ): String {
        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

        if (updateCheck == "false") {
            val savePortMaster = Port(
                portCode,
                portName,
                countryName,
                null,
                currentTime,
                null,
                null,
                0
            )
            portMasterRepo?.save(savePortMaster)

        } else {
            portMasterRepo?.updatePortMaster(portCode, portName, countryName, currentTime)
        }
        return "redirect:/portMaster"
    }
}
