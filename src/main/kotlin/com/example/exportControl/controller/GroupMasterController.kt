package com.example.exportControl.controller

import com.example.exportControl.model.Group
import com.example.exportControl.model.requestModel.GroupRequestModel
import com.example.exportControl.model.requestModel.UserRequestModel																	 
import com.example.exportControl.repository.GroupMasterRepository
import com.example.exportControl.repository.UserMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute															 
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.security.Principal
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.servlet.http.HttpServletResponse

/**
 * GroupMasterController
 *
 */
@Controller
class GroupMasterController : MenuController() {
    @Autowired
    private val groupMasterRepo: GroupMasterRepository? = null

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    /**
     * 初期表示処理
     * @param model
     * @param principal
     */
    @RequestMapping("groupMaster")
    fun groupMaster(model: Model,principal: Principal): String {
        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)

        model.addAttribute("AllGroupList", groupMasterRepo?.selectAllGroup())
        model.addAttribute("GroupList", groupMasterRepo?.selectAllGroup())
        model.addAttribute("AllCompanyList", companyMasterRepo?.selectAllCompany())

        return "groupMaster"
    }

    /**
     * 検索処理
     * @param groupCode
     * @param groupName
     */
    @RequestMapping("searchGroupMaster")
    fun searchGroupMaster(
        model: Model,
        @Param("groupCode") groupCode: String?,
        @Param("groupName") groupName: String?,
        @Param("companyName") companyName: String?,
		@ModelAttribute("group") groupReq: GroupRequestModel?,
        principal: Principal,response: HttpServletResponse

    ): String {
        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

        model.addAttribute("loginCompany",loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)

        model.addAttribute("groupCode", groupReq?.groupCode)
        model.addAttribute("groupName", groupReq?.groupName)
        model.addAttribute("companyName", groupReq?.companyName)
        model.addAttribute("AllGroupList", groupMasterRepo?.selectAllGroup())
        model.addAttribute("GroupList", groupMasterRepo?.searchGroupCodeAndNameAndCountry(groupReq?.groupCode,groupReq?.groupName,groupReq?.companyName))
        model.addAttribute("AllCompanyList", companyMasterRepo?.selectAllCompany())
        response.setHeader("Cache-Control", "no-cache");

        return "groupMaster"
    }

    @RequestMapping("/deleteGroupMaster/{groupCode}", method = [RequestMethod.GET])
    fun deleteGroupMaster(@PathVariable("groupCode") groupCode: String): String {
        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        groupMasterRepo?.deleteGroupMaster(groupCode,currentTime)
        return "redirect:/groupMaster"
    }

    @RequestMapping("newGroupMaster")
    fun newGroupMaster(model: Model,principal: Principal): String {
        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

        model.addAttribute("loginCompany",loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)

        val groupCodeDeleteFlag0 = groupMasterRepo?.groupCodeDeleteFlag0()
        val groupCodeDeleteFlag1 = groupMasterRepo?.groupCodeDeleteFlag1()

        model.addAttribute("groupCodeDeleteFlag0",groupCodeDeleteFlag0);
        model.addAttribute("groupCodeDeleteFlag1",groupCodeDeleteFlag1);
        model.addAttribute("AllCompanyList", companyMasterRepo?.selectAllCompany())

        return "groupMasterDetail"
    }

    @RequestMapping("/updateGroupMaster")
    fun updateGroupMaster(
        @Param("groupCode") groupCode: String?,
        @Param("groupName") groupName: String?,
        @Param("companyName") companyName: String?,
        model: Model
    ): String {
        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        groupMasterRepo?.updateGroupMaster(groupCode,groupName,companyName,currentTime)
        return "redirect:/groupMaster"
    }

    @RequestMapping("/saveGroupMaster")
    fun saveGroupMaster(
        @Param("groupCode") groupCode: String?,
        @Param("groupName") groupName: String?,
        @Param("companyName") companyName: String?,
        @Param("updateCheck") updateCheck: String?,
        model: Model
    ): String {
        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

        if(updateCheck == "false"){
            val saveGroupMaster = Group(
                groupCode,
                groupName,
                companyName,
                null,
                currentTime,
                null,
                null,
                0
            )
            groupMasterRepo?.save(saveGroupMaster)

        }else{
            groupMasterRepo?.updateGroupMaster(groupCode,groupName,companyName,currentTime)
        }
        return "redirect:/groupMaster"
    }
}
