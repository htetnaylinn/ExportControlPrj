package com.example.exportControl.service

import com.example.exportControl.model.Fare
import com.example.exportControl.model.Video
import com.example.exportControl.model.requestModel.FareRequestModel
import com.example.exportControl.model.requestModel.VideoRequestModel
import com.example.exportControl.repository.CompanyMasterRepository
import com.example.exportControl.repository.FareMasterRepository
import com.example.exportControl.repository.VideoMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime


@Service
class VideoService {

    @Autowired
    private val videoMasterRepo: VideoMasterRepository? = null

    @Autowired
    private val fareService: FareService? = null

    var currentDateTime: LocalDateTime = LocalDateTime.now()
    val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

    /**
     * insert company information
     * @param companyReq
     */
    fun insVideo(@Param("videoReq") videoReq: VideoRequestModel?,updateCheck:String?,userCode:String?) {

        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

        if(updateCheck == "false"){
                val videoObj = Video(
                    videoReq?.videoCode,
                    videoReq?.orderList,
                    videoReq?.videoTitle,
                    videoReq?.videoContent,
                    videoReq?.videoLink,
                    0,
                    userCode,
                    currentTime,
                    userCode,
                    currentTime
                )
                videoMasterRepo?.save(videoObj)
        }else{
            videoMasterRepo?.updateVideoMaster( videoReq?.videoCode, videoReq?.orderList,videoReq?.videoTitle,videoReq?.videoContent,videoReq?.videoLink,userCode,currentTime)
        }
    }
    fun deleteVideo(@Param("videoCode") videoCode: String?,userCode:String?) {

        videoMasterRepo?.deleteVideoMaster(videoCode,userCode,currentTime)
    }
}