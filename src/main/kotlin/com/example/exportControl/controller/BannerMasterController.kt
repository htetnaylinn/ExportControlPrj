package com.example.exportControl.controller
import ByteArrayMultipartFile
import com.example.exportControl.model.requestModel.BannerRequestModel
import com.example.exportControl.model.requestModel.FareRequestModel
import com.example.exportControl.repository.BannerMasterRepository
import com.example.exportControl.repository.CountryMasterRepository
import com.example.exportControl.repository.UserMasterRepository
import com.example.exportControl.service.BannerService
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
class BannerMasterController : MenuController() {

    @Autowired
    private val bannerMasterRepo: BannerMasterRepository? = null

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    @Autowired
    private val bannerService: BannerService? = null

    var systemErrorMsg: String? = null

    @Autowired
    private val messageSource: MessageSource? = null

    @RequestMapping("bannerMaster")
    fun bannerMaster(model: Model, principal: Principal): String {

        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val allBannerList = bannerMasterRepo?.selectAllBanner()
        val savedorderList = bannerMasterRepo?.getSavedOrderList()

        model.addAttribute("allBannerList", allBannerList)
        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("savedorderList", savedorderList)

        return "bannerMaster"
    }

    @RequestMapping("bannerMasterDetail")
    fun bannerMasterDetail(model:Model,principal:Principal):String {
        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val bannerCodeDeleteFlag0 = bannerMasterRepo?.getBannerCodeDeleteFlag0()
        val bannerCodeDeleteFlag1 = bannerMasterRepo?.getBannerCodeDeleteFlag1()
        val savedorderList = bannerMasterRepo?.getSavedOrderList()
        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("bannerCodeDeleteFlag0", bannerCodeDeleteFlag0)
        model.addAttribute("bannerCodeDeleteFlag1", bannerCodeDeleteFlag1)
        model.addAttribute("savedorderList", savedorderList)

        return "bannerMasterDetail"
    }

    @RequestMapping("saveBannerMaster", method = [RequestMethod.POST])
    fun saveBannerMaster(
        model: Model,
        @ModelAttribute("bannerModel") bannerRequestModel: BannerRequestModel,
        principal: Principal, response: HttpServletResponse,
        @Param("updateCheck") updateCheck: String?,

        ): String {
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)

        bannerService?.insBanner(bannerRequestModel,updateCheck,userName)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)

        response.setHeader("Cache-Control", "no-cache");
        return "redirect:/bannerMaster"
    }

    @RequestMapping("updateBannerMaster", method = [RequestMethod.POST])
    fun updateBannerMaster(
        model: Model,
        @ModelAttribute("bannerModel") bannerRequestModel: BannerRequestModel,
        principal: Principal, response: HttpServletResponse,
    ): String {
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        val file = bannerRequestModel.bannerImage
        if (file != null && !file.isEmpty) {
            bannerService?.insBanner(bannerRequestModel,"true",userName)
        }else{
            val fileEntity = bannerMasterRepo?.findById(bannerRequestModel.bannerCode!!)
                ?.orElseThrow { NoSuchElementException("File not found with id: $bannerRequestModel.fareCode!!") }

            bannerRequestModel.bannerImage = fileEntity?.bannerImage?.let {
                ByteArrayMultipartFile(it, fileEntity.bannerImageName ?: "")
            }

            bannerService?.insBanner(bannerRequestModel,"true",userName)
        }

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)

        response.setHeader("Cache-Control", "no-cache");
        return "redirect:/bannerMaster"
    }

    @RequestMapping("/deleteBannerMaster/{bannerCode}", method = [RequestMethod.GET])
    fun deleteBannerMaster(@PathVariable("bannerCode") bannerCode: String, principal: Principal, response: HttpServletResponse,): String {
        val userName = principal.name
        bannerService?.deleteBanner(bannerCode,userName)
        return "redirect:/bannerMaster"
    }


}
