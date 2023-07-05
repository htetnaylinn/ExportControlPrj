package com.example.exportControl.model.requestModel

import org.springframework.web.multipart.MultipartFile

class VideoParseModel{

    var videoId: String? = null
    var videoTitle : String? = null
    var videoContent : String? = null
    var youtubeVideoTitle : String? = null

    constructor(videoId: String?, videoTitle: String?, videoContent: String?, youtubeVideoTitle: String?) {
        this.videoId = videoId
        this.videoTitle = videoTitle
        this.videoContent = videoContent
        this.youtubeVideoTitle = youtubeVideoTitle
    }
}

