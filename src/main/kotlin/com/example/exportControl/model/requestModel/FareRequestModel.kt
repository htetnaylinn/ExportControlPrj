package com.example.exportControl.model.requestModel

import org.springframework.web.multipart.MultipartFile

class FareRequestModel{

    var fareCode : String? = null
    var orderList : String? = null
    var fareTitle : String? = null
    var fareContent : String? = null
    var fareFileName : String? = null
    var fareFile : MultipartFile? = null
}

