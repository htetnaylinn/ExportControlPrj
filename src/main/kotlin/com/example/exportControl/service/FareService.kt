package com.example.exportControl.service

import com.example.exportControl.model.Fare
import com.example.exportControl.model.requestModel.FareRequestModel
import com.example.exportControl.repository.CompanyMasterRepository
import com.example.exportControl.repository.FareMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime


@Service
class FareService {

    @Autowired
    private val fareMasterRepo: FareMasterRepository? = null

    var currentDateTime: LocalDateTime = LocalDateTime.now()
    val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

    /**
     * insert company information
     * @param companyReq
     */
    fun insFare(@Param("fareReq") fareReq: FareRequestModel?,updateCheck:String?,userCode:String?) {

        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

        if(updateCheck == "false"){
                val fareObj = Fare(
                    fareReq?.fareCode,
                    fareReq?.orderList,
                    fareReq?.fareTitle,
                    fareReq?.fareContent,
                    fareReq?.fareFile!!.originalFilename,
                    fareReq?.fareFile!!.bytes,
                    0,
                    userCode,
                    currentTime,
                    userCode,
                    currentTime
                )
                fareMasterRepo?.save(fareObj)
        }else{
            fareMasterRepo?.updateFareMaster( fareReq?.fareCode, fareReq?.orderList,fareReq?.fareTitle,fareReq?.fareContent,fareReq?.fareFile!!.originalFilename,
                fareReq.fareFile!!.bytes,userCode,currentTime )
        }

    }

    fun deleteFare(@Param("fareCode") fareCode: String?,userCode:String?) {

        fareMasterRepo?.deleteFareMaster(fareCode,userCode,currentTime)
    }
}