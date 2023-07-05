package com.example.exportControl.controller

import com.example.exportControl.model.requestModel.FareRequestModel
import com.example.exportControl.model.requestModel.VideoRequestModel
import com.example.exportControl.repository.CountryMasterRepository
import com.example.exportControl.repository.FareMasterRepository
import com.example.exportControl.repository.UserMasterRepository
import com.example.exportControl.repository.VideoMasterRepository
import com.example.exportControl.service.VideoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.security.Principal
import javax.servlet.http.HttpServletResponse

@Controller
class VideoMasterController : MenuController() {

    @Autowired
    private val videoMasterRepo: VideoMasterRepository? = null

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    @Autowired
    private val videoService: VideoService? = null

    var systemErrorMsg: String? = null

    @Autowired
    private val messageSource: MessageSource? = null

    @RequestMapping("videoMaster")
    fun videoMaster(model: Model, principal: Principal): String {

        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val allVideoList = videoMasterRepo?.selectAllVideo()
        val savedorderList = videoMasterRepo?.getSavedOrderList()

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("allVideoList", allVideoList)
        model.addAttribute("savedorderList", savedorderList)
        return "videoMaster"
    }

    @RequestMapping("videoMasterDetail")
    fun videoMasterDetail(model:Model,principal:Principal):String {
        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val videoCodeDeleteFlag0 = videoMasterRepo?.getFareCodeDeleteFlag0()
        val videoCodeDeleteFlag1 = videoMasterRepo?.getFareCodeDeleteFlag1()
        val savedorderList = videoMasterRepo?.getSavedOrderList()
        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("videoCodeDeleteFlag0", videoCodeDeleteFlag0)
        model.addAttribute("videoCodeDeleteFlag1", videoCodeDeleteFlag1)
        model.addAttribute("savedorderList", savedorderList)

        return "videoMasterDetail"
    }

    @RequestMapping("saveVideoMaster", method = [RequestMethod.POST])
    fun saveVideoMaster(
        model: Model,
        @ModelAttribute("videoModel") videoRequestModel: VideoRequestModel,
        principal: Principal, response: HttpServletResponse,
        @Param("updateCheck") updateCheck: String?,

        ): String {
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        videoService?.insVideo(videoRequestModel,updateCheck,userName)
        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)

        response.setHeader("Cache-Control", "no-cache");
        return "redirect:/videoMaster"
    }

    @RequestMapping("updateVideoMaster", method = [RequestMethod.POST])
    fun updateFareMaster(
        model: Model,
        @ModelAttribute("videoModel") videoRequestModel: VideoRequestModel,
        principal: Principal, response: HttpServletResponse,
    ): String {
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        videoService?.insVideo(videoRequestModel,"true",userName)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)

        response.setHeader("Cache-Control", "no-cache");
        return "redirect:/videoMaster"
    }
    @RequestMapping("/deleteVideoMaster/{videoCode}", method = [RequestMethod.GET])
    fun deleteFareMaster(@PathVariable("videoCode") videoCode: String, principal: Principal, response: HttpServletResponse,): String {
        val userName = principal.name
        videoService?.deleteVideo(videoCode,userName)
        return "redirect:/videoMaster"
    }
}
