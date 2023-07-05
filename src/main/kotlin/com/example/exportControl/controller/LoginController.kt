package com.example.exportControl.controller

import com.example.exportControl.model.BannerTemp
import com.example.exportControl.model.requestModel.VideoParseModel
import com.example.exportControl.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import java.security.Principal
import java.util.*
import javax.net.ssl.HttpsURLConnection
import javax.servlet.http.HttpServletRequest

/**
 * LoginController
 *
 */
@Controller
class LoginController {

    @Autowired
    private val messageSource: MessageSource? = null

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    @Autowired
    private val bannerMasterRepo : BannerMasterRepository? = null

    @Autowired
    private val videoMasterRepository : VideoMasterRepository? = null

    @Autowired
    private val fareMasterRepo: FareMasterRepository? = null

    private val apiKey = "AIzaSyB-8909HuJJTnwrRkoLK4-u-jT5ywZb0z0"
    /**
     * ログイン処理
     * @param model
     * @param request
     */
    @RequestMapping(value = ["/", "/login"], method = [RequestMethod.GET]) // login logout home page
    fun loginPage(model: Model, request: HttpServletRequest): String {
        val allBannerList: MutableList<BannerTemp> = mutableListOf()
        val allVideoList: MutableList<MutableList<VideoParseModel>> = processVideo()


        model.addAttribute("allVideoList",allVideoList)
        model.addAttribute("title", "Welcome")
        model.addAttribute("message", "This is welcome page!")

//        FareList
        val allFareList = fareMasterRepo?.selectAllFareOrderByOrderList()
        model.addAttribute("allFareList", allFareList)

//        BannerList
        val allBannerListTemp = bannerMasterRepo?.selectAllBannerByOrderList()
        allBannerListTemp?.forEachIndexed { index, e ->
            allBannerList.add(
                BannerTemp(
                    e.bannerCode, e.orderList, e.bannerTitle, e.bannerImageName,
                    Base64.getEncoder().encodeToString(e.bannerImage!!), e.bannerLink,
                    e.deleteFlag, e.createdBy, e.createdDt, e.updatedBy, e.updatedDt
                )
            )
        }
        model.addAttribute("allBannerList", allBannerList)

        val auth = SecurityContextHolder.getContext().authentication
        if (auth != null && request.userPrincipal != null) {
            return "redirect:/entryPage"
        } else {
            return "login"
        }
    }
    private fun getVideoId(url: String): String? {
        val regex = Regex("[?&]v=([^&#]*)")
        val matchResult = regex.find(url)
        return matchResult?.groupValues?.getOrNull(1)
    }

    private fun fetchVideoInfo(videoId: String): String? {
        val urlString = "https://www.googleapis.com/youtube/v3/videos?part=snippet&id=$videoId&key=$apiKey"
        val url = URL(urlString)
        val connection = url.openConnection() as? HttpsURLConnection
        connection?.requestMethod = "GET"

        val responseCode = connection?.responseCode
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = reader.readText()
            reader.close()
            return response
        }

        return null
    }

    fun processVideo(): MutableList<MutableList<VideoParseModel>> {

        val listA: MutableList<MutableList<VideoParseModel>> = mutableListOf()

        val videoParseList: MutableList<VideoParseModel> = mutableListOf()

        val allVideoListTemp = videoMasterRepository?.selectAllVideoByOrderList()
        allVideoListTemp?.forEachIndexed { index, e ->
           if(videoParseList.size < 10){
               videoParseList.add(
                   VideoParseModel(
                       e.videoLink?.let { getVideoId(it) },e.videoTitle,e.videoContent, e.videoLink?.let {
                           getVideoId(it)?.let {
                               getYoutubeVideoTItle(
                                   it
                               )
                           }
                       }
                   )
               )
           }else{
               listA.add(videoParseList.toMutableList())
               videoParseList.clear()
               videoParseList.add(
                   VideoParseModel(
                       e.videoLink?.let { getVideoId(it) },e.videoTitle,e.videoContent, e.videoLink?.let {
                           getVideoId(it)?.let {
                               getYoutubeVideoTItle(
                                   it
                               )
                           }
                       }
                   )
               )
           }
        }
        listA.add(videoParseList.toMutableList())
        return listA
    }

    fun getYoutubeVideoTItle(videoId:String): String? {
        val videoInfoJson = fetchVideoInfo(videoId)
        var videoTitle:String? = null
        if (videoInfoJson != null) {
            // Extract video title from the JSON response
            val videoTitleRegex = Regex("\"title\": \"(.*?)\"")
            val matchResult = videoTitleRegex.find(videoInfoJson)
            videoTitle = matchResult?.groupValues?.getOrNull(1)


        }
        return videoTitle
    }
    /**
     * ログイン失敗処理
     * @param model
     * @param request
     * @param redirectAttributes
     */
    @RequestMapping("/loginFail")
    fun loginFail(model: Model,request: HttpServletRequest,redirectAttributes: RedirectAttributes): String {
        redirectAttributes.addFlashAttribute("systemErrorMsg", messageSource?.getMessage("wrongPasswordErrorMsg", null, Locale.ENGLISH).toString())
        return "redirect:/"
    }

    @GetMapping("/files/{id}/downloadBeforeLogin")
    fun downloadBeforeLogin(@PathVariable("id") id: String): ResponseEntity<ByteArrayResource> {
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

    @RequestMapping("inquiryBeforeLogin")
    fun inquiry(model: Model): String {
        model.addAttribute("menuBarFlag", 0)
        model.addAttribute("formActionName", "sendInquiryBeforeLogin")
        return "inquiry"
    }
}