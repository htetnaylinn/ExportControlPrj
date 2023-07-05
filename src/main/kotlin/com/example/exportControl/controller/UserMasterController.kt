package com.example.exportControl.controller

import com.example.exportControl.model.Muser
import com.example.exportControl.model.UserControl
import com.example.exportControl.model.requestModel.ScheduleRequestModel
import com.example.exportControl.model.requestModel.UserRequestModel
import com.example.exportControl.repository.UserControlRepository
import com.example.exportControl.service.ScheduleControlService
import com.example.exportControl.util.UserTypeEng
import com.example.exportControl.util.UserTypeJpn
import com.example.exportControl.utils.AESUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.thymeleaf.util.ListUtils
import java.security.Principal
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpServletResponse
import kotlin.collections.ArrayList

/**
 * UserMasterController
 *
 */
@Controller
class UserMasterController : MenuController() {

    @Autowired
    private val userControlRepo: UserControlRepository? = null

    @Autowired
    private val scheduleControlService: ScheduleControlService? = null

//    val logger: Logger = LoggerFactory.getLogger(UserMasterController::class.java)

    // 修正中TO DO
    /**
     * 登録処理
     * @param model
     * @param principal
     */
    @RequestMapping("/saveUser")
    fun saveUser(
        @Param("employeeNumber") employeeNumber: String?,
        @Param("employeeName") employeeName: String?,
        @Param("role") role: String?,
        @Param("startDate")startDate :String?,
        @Param("endDate")endDate:String?,
        @Param("emailAddress")emailAddress:String?,
        @Param("companyCode")companyCode: String?,
        @ModelAttribute("schedleControl") schedleControl: ScheduleRequestModel?,
        @Param("exportRegistration")exportRegistration:String?,
        @Param("schedule")schedule:String?,
        @Param("userMaster")userMaster:String?,
        @Param("productMaster")productMaster:String?,
        @Param("companyMaster")companyMaster:String?,
        @Param("groupMaster")groupMaster:String?,
        principal: Principal,

        model: Model
    ): String {
        val userCode = principal.name
        val exportRegistrationFlag : Int? = if (exportRegistration == "editable") 1 else if (exportRegistration == "viewable") 2  else 3
        val scheduleFlag : Int? = if (schedule == "editable") 1 else if (schedule == "viewable") 2  else 3
        val userMasterFlag : Int? = if (userMaster == "editable") 1 else if (userMaster == "viewable") 2  else 3
        val productMasterFlag : Int? = if (productMaster == "editable") 1 else if (productMaster == "viewable") 2  else 3
        val companyMasterFlag : Int? = if (companyMaster == "editable") 1 else if (companyMaster == "viewable") 2  else 3
        val groupMasterFlag : Int? = if (groupMaster == "editable") 1 else if (groupMaster == "viewable") 2  else 3

        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
        val startDate =  formatter.parse(startDate)
        var endDt: Date? = null
        if (endDate != ""){
            endDt = formatter.parse(endDate)
        }else{
            endDt
        }
//        val endDate = formatter.parse(endDate)
        var roleFlag: String = "0"
        roleFlag = if(role == "管理者" || role == "ADMIN"){
            "0"
        }else if(role == "HL") {
            "2"
        }else{
            "1"
        }

        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        var encryptedPasswrod:String? = null

        if(employeeNumber != ""){
            encryptedPasswrod = AESUtils?.encrypt(employeeNumber!!)
        }
            val saveUser = Muser(employeeNumber,employeeName,companyCode,"000001",startDate,endDt,roleFlag,
                encryptedPasswrod,emailAddress,"0",0,
                userCode, currentTime,userCode,currentTime)
            var newUserControlFlag = UserControl(employeeNumber,exportRegistrationFlag,scheduleFlag,userMasterFlag,productMasterFlag,companyMasterFlag,groupMasterFlag, userCode, currentTime,userCode,currentTime)

        userMasterRepo?.save(saveUser)
        scheduleControlService?.saveScheduleControl(schedleControl,employeeNumber)
        userControlRepo?.save(newUserControlFlag)
        return "redirect:/userMaster"
    }

    /**
     * 会社コード更新処理
     * @param companyCode
     * @param principal
     */
    @RequestMapping("/updateCompanyCode")
    fun updateCompanyCode(@Param("companyCode") companyCode: String?, principal: Principal,
                    ):String{
        val userName = principal.name
        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

        userMasterRepo?.updateCompanyCode(userName,companyCode)
        return "redirect:/scheduleOne"
    }

    /**
     * 削除処理
     * @param userCode
     */
    @RequestMapping("/deleteUserMaster/{userCode}", method = [RequestMethod.GET])
    fun deleteProductMaster(@PathVariable("userCode") userCode: String): String {
        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        userMasterRepo?.deleteUserMaster(userCode)
        return "redirect:/userMaster"
    }

    /**
     * 検索処理
     * @param model
     * @param userReq
     * @param principal
     */
    @RequestMapping("searchUserMaster")
    fun searchUserMaster(
        model: Model,
        @ModelAttribute("user") userReq: UserRequestModel?,
        principal: Principal,
        response:HttpServletResponse
    ): String {
        val userName = principal.name
        val muser = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val userList = userMasterRepo?.searchUserCodeAndName(muser?.companyCode,userReq?.userCode, userReq?.userName)
        var isAdmin: Boolean = false
        var adminUserrole = "2"
        if(muser?.role == "2"){
            isAdmin = true
            adminUserrole = ""
        }

        var isSystemAdmin:Boolean = false
        if(muser?.role == "0" || muser?.role == "2" ){
            isSystemAdmin = true
        }
        model.addAttribute("loginCompany",loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("employeeNumber", userReq?.userCode)
        model.addAttribute("employeeName", userReq?.userName)
//        model.addAttribute("SearchList", productMasterRepo?.selectAllProduct())
        model.addAttribute("getallUsers",userList)
//        model.addAttribute("ProductList2", productList2)
        model.addAttribute("isAdmin", isAdmin)
        model.addAttribute("isSystemAdmin", isSystemAdmin)
        model.addAttribute("userCode", userName)
        model.addAttribute("adminUserrole", adminUserrole)

        response.setHeader("Cache-Control", "no-cache");
        return "userMaster"
    }

    /**
     * ユーザーマスタ詳細移動処理
     * @param model
     * @param principal
     */
    @RequestMapping("usercontrol")
    fun userControl(model: Model,principal: Principal):String{
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val role = user?.role
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        val userDeleteFlag0 = userMasterRepo?.userCodeDeleteFlag0()
        if(webConfig?.messageSourcePath == 0){
            val arr = enumValues<UserTypeJpn>()
            val userTypes: MutableList<UserTypeJpn> = ArrayList()
            val roles = checkRoleForRoleOptionJp(userTypes,arr.toList())
            model.addAttribute("UserTypes",roles)
        }else{
            val arr = enumValues<UserTypeEng>()
            val userTypes: MutableList<UserTypeEng> = ArrayList()
            val roles = checkRoleForRoleOptionEn(userTypes,arr.toList())
            model.addAttribute("UserTypes",roles)
        }
        model.addAttribute("companyList",companyMasterRepo?.selectAllCompany())
        model.addAttribute("loginCompany",loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("userDeleteFlag0", userDeleteFlag0)

        return "userControl"
    }

    /**
     * 初期表示処理
     * @param model
     * @param principal
     */
    @RequestMapping("userMaster")
    fun userMaster(model: Model,principal: Principal):String {
//        logger.info("Before|GET|/userMaster")
        val gson = Gson()
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()

        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)

        model.addAttribute("loginCompany",loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)

        val getallUsers = userMasterRepo?.getallUsers(user?.companyCode)
        if(webConfig?.messageSourcePath == 0){
            val arr = enumValues<UserTypeJpn>()
            val userTypes: MutableList<UserTypeJpn> = ArrayList()
            val roles = checkRoleForRoleOptionJp(userTypes,arr.toList())
            model.addAttribute("UserTypes",roles)
        }else{
            val arr = enumValues<UserTypeEng>()
            val userTypes: MutableList<UserTypeEng> = ArrayList()
            val roles = checkRoleForRoleOptionEn(userTypes,arr.toList())
            model.addAttribute("UserTypes",roles)
        }

        var isAdmin: Boolean = false
        var adminUserrole = "2"
        if(user?.role == "2"){
            isAdmin = true
            adminUserrole = ""
        }

        var isSystemAdmin:Boolean = false
        if(user?.role == "0" || user?.role == "2" ){
            isSystemAdmin = true
        }
        model.addAttribute("getallUsers",getallUsers)
        model.addAttribute("companyList", companyMasterRepo?.selectAllCompany())
        model.addAttribute("userCode", userName)
//        logger.debug("userMaster from Logback {}", gsonPretty.toJson(getallUsers))
        model.addAttribute("isAdmin", isAdmin)
        model.addAttribute("isSystemAdmin", isSystemAdmin)
        model.addAttribute("adminUserrole", adminUserrole)

//        logger.info("After|GET|/userMaster")
        return "userMaster"
    }

    // 修正中TO DO
    /**
     * 更新処理
     * @param model
     * @param principal
     */
    @RequestMapping("/updateUserInformation")
    fun updateUserInformation(
        model: Model,
        principal: Principal,
        @Param("employeeNumber") employeeNumber: String?,
        @Param("employeeName") employeeName: String?,
        @Param("role") role: String?,
        @Param("startDate")startDate :String?,
        @Param("endDate")endDate:String?,
        @Param("emailAddress")emailAddress:String?,
        @Param("companyCode")companyCode: String?,
    ):String {
        val userCode = principal.name
        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
        val startDate =   if(startDate != ""){formatter.parse(startDate)}else{null}

        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

        userMasterRepo?.updateUserInformation(employeeNumber,
            employeeName,
            role,
            startDate,
            endDate,
            emailAddress,
            companyCode,
            userCode,
            currentTime
        )
        return "redirect:/userMaster"
    }

    /**
     * 輸出登録の覧の更新処理
     * @param userCode
     * @param schedleControl
     */
    @RequestMapping("/updateUserScheduleControl")
    fun updateUserScheduleControl(
        model: Model,
        principal: Principal,
        @Param("userCode")userCode:String?,
        @ModelAttribute("schedleControl") schedleControl: ScheduleRequestModel?
    ):String {
        scheduleControlService?.saveScheduleControl(schedleControl,userCode)
        return "redirect:/userMaster"
    }

    // 修正中TO DO
    /**
     * ユーザーの制限
     * @param userCode
     * @param principal
     */
    @RequestMapping("/updateUserControl")
    fun updateUserControl(
        model:Model,
        principal:Principal,
        @Param("userCode")userCode:String?,
        @Param("exportRegistrationFlg")exportRegistrationFlg:String?,
        @Param("scheduleFlag")scheduleFlag:String?,
        @Param("userFlag")userFlag:String?,
        @Param("productFlag")productFlag:String?,
        @Param("companyFlag")companyFlag:String?,
        @Param("groupFlag")groupFlag:String?
    ): String {

        val exportRegistrationFlag : Int? = if (exportRegistrationFlg == "editable") 1 else if (exportRegistrationFlg == "viewable") 2  else 3
        val scheduleFlag : Int? = if (scheduleFlag == "editable") 1 else if (scheduleFlag == "viewable") 2  else 3
        val userMasterFlag : Int? = if (userFlag == "editable") 1 else if (userFlag == "viewable") 2  else 3
        val productMasterFlag : Int? = if (productFlag == "editable") 1 else if (productFlag == "viewable") 2  else 3
        val companyMasterFlag : Int? = if (companyFlag == "editable") 1 else if (companyFlag == "viewable") 2  else 3
        val groupMasterFlag : Int? = if (groupFlag == "editable") 1 else if (groupFlag == "viewable") 2  else 3

        userControlRepo?.updateUserControlByUserCode(userCode,exportRegistrationFlag, scheduleFlag, userMasterFlag, productMasterFlag, companyMasterFlag, groupMasterFlag)

        return "redirect:/userMaster"
    }

    fun checkRoleForRoleOptionJp(roles: MutableList<UserTypeJpn>,enums:List<UserTypeJpn>):MutableList<UserTypeJpn>?{
        roles.add(enums[0])
        roles.add(enums[1])
        if(role == "2"){
            roles.add(enums[2])
        }
        return roles
    }

    fun checkRoleForRoleOptionEn(roles: MutableList<UserTypeEng>,enums:List<UserTypeEng>):MutableList<UserTypeEng>?{
        roles.add(enums[0])
        roles.add(enums[1])
        if(role == "2"){
            roles.add(enums[2])
        }
        return roles
    }

}