package com.example.exportControl.service

import com.example.exportControl.model.Banner
import com.example.exportControl.model.Fare
import com.example.exportControl.model.requestModel.BannerRequestModel
import com.example.exportControl.model.requestModel.FareRequestModel
import com.example.exportControl.repository.BannerMasterRepository
import com.example.exportControl.repository.CompanyMasterRepository
import com.example.exportControl.repository.FareMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime


@Service
class BannerService {

    @Autowired
    private val bannerMasterRepo: BannerMasterRepository? = null

    var currentDateTime: LocalDateTime = LocalDateTime.now()
    val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

    /**
     * insert company information
     * @param companyReq
     */
    fun insBanner(@Param("bannerReq") bannerReq: BannerRequestModel?,updateCheck:String?,userCode:String?) {

        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

        if(updateCheck == "false"){
                val bannerObj = Banner(
                    bannerReq?.bannerCode,
                    bannerReq?.orderList,
                    bannerReq?.bannerTitle,
                    bannerReq?.bannerImage!!.originalFilename,
                    bannerReq.bannerImage!!.bytes,
                    bannerReq.bannerLink,
                    0,
                    userCode,
                    currentTime,
                    userCode,
                    currentTime
                )
                bannerMasterRepo?.save(bannerObj)
        }else{
            bannerMasterRepo?.updateBannerMaster( bannerReq?.bannerCode, bannerReq?.orderList,bannerReq?.bannerTitle,bannerReq?.bannerImage!!.originalFilename,
                bannerReq.bannerImage!!.bytes,bannerReq.bannerLink,userCode,currentTime )
        }

    }

    fun deleteBanner(@Param("bannerCode") bannerCode: String?,userCode:String?) {

        bannerMasterRepo?.deleteBannerMaster(bannerCode,userCode,currentTime)
    }
}