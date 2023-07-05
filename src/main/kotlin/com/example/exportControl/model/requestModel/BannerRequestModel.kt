package com.example.exportControl.model.requestModel

import org.springframework.web.multipart.MultipartFile

class BannerRequestModel{

    var bannerCode : String? = null
    var orderList : String? = null
    var bannerTitle : String? = null
    var bannerImage : MultipartFile? = null
    var bannerLink : String? = null
}

