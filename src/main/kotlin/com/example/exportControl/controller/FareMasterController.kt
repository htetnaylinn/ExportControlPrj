package com.example.exportControl.controller

import ByteArrayMultipartFile
import com.example.exportControl.model.requestModel.CompanyRequestModel
import com.example.exportControl.model.requestModel.FareRequestModel
import com.example.exportControl.repository.CountryMasterRepository
import com.example.exportControl.repository.FareMasterRepository
import com.example.exportControl.repository.UserMasterRepository
import com.example.exportControl.service.CompanyService
import com.example.exportControl.service.FareService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.core.io.ByteArrayResource
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.net.URLEncoder
import java.security.Principal
import javax.servlet.http.HttpServletResponse

@Controller
class FareMasterController : MenuController() {

    @Autowired
    private val fareMasterRepo: FareMasterRepository? = null

    @Autowired
    private val fareService: FareService? = null

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    var systemErrorMsg: String? = null

    @Autowired
    private val messageSource: MessageSource? = null

    @RequestMapping("fareMaster")
    fun fareMaster(model: Model, principal: Principal): String {

        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val allFareList = fareMasterRepo?.selectAllFare()
        val savedorderList = fareMasterRepo?.getSavedOrderList()
        model.addAttribute("allFareList", allFareList)
        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("savedorderList", savedorderList)

        return "fareMaster"
    }

    @RequestMapping("fareMasterDetail")
    fun fareMasterDetail(model:Model,principal:Principal):String {
        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)
        val fareCodeDeleteFlag0 = fareMasterRepo?.getFareCodeDeleteFlag0()
        val fareCodeDeleteFlag1 = fareMasterRepo?.getFareCodeDeleteFlag1()
        val savedorderList = fareMasterRepo?.getSavedOrderList()
        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("fareCodeDeleteFlag0", fareCodeDeleteFlag0)
        model.addAttribute("fareCodeDeleteFlag1", fareCodeDeleteFlag1)
        model.addAttribute("savedorderList", savedorderList)



        return "fareMasterDetail"
    }

    @RequestMapping("saveFareMaster", method = [RequestMethod.POST])
    fun saveFareMaster(
        model: Model,
        @ModelAttribute("fareModel") fareRequestModel: FareRequestModel,
        principal: Principal, response: HttpServletResponse,
        @Param("updateCheck") updateCheck: String?,

        ): String {
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)

        fareService?.insFare(fareRequestModel,updateCheck,userName)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)

        response.setHeader("Cache-Control", "no-cache");
        return "redirect:/fareMaster"
    }

    @RequestMapping("updateFareMaster", method = [RequestMethod.POST])
    fun updateFareMaster(
        model: Model,
        @ModelAttribute("fareModel") fareRequestModel: FareRequestModel,
        principal: Principal, response: HttpServletResponse,
        ): String {
        val userName = principal.name
        val user = userMasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(user?.companyCode)
        val file = fareRequestModel.fareFile
        if (file != null && !file.isEmpty) {
            fareService?.insFare(fareRequestModel,"true",userName)
        }else{
            val fileEntity = fareMasterRepo?.findById(fareRequestModel.fareCode!!)
                ?.orElseThrow { NoSuchElementException("File not found with id: $fareRequestModel.fareCode!!") }
            fareRequestModel.fareFile = fileEntity?.fareFile?.let {
                ByteArrayMultipartFile(it, fileEntity.fareFileName ?: "")
            }
            fareService?.insFare(fareRequestModel,"true",userName)
        }

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption", webConfig?.messageSourcePath)

        response.setHeader("Cache-Control", "no-cache");
        return "redirect:/fareMaster"
    }

    @RequestMapping("/deleteFareMaster/{fareCode}", method = [RequestMethod.GET])
    fun deleteFareMaster(@PathVariable("fareCode") fareCode: String,principal: Principal, response: HttpServletResponse,): String {
        val userName = principal.name
        fareService?.deleteFare(fareCode,userName)
        return "redirect:/fareMaster"
    }

    @GetMapping("/files/{id}/download")
    fun downloadFile(@PathVariable("id") id: String): ResponseEntity<ByteArrayResource> {
        val fileEntity = fareMasterRepo?.findById(id)
            ?.orElseThrow { NoSuchElementException("File not found with id: $id") }

        val resource = ByteArrayResource(fileEntity?.fareFile!!)
        val encodedFileName = URLEncoder.encode(fileEntity.fareFileName, "UTF-8")
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''$encodedFileName")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .contentLength(fileEntity.fareFile!!.size.toLong())
            .body(resource)
    }
}
