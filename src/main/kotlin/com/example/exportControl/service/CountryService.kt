package com.example.exportControl.service

import com.example.exportControl.model.requestModel.CompanyRequestModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import com.example.exportControl.model.requestModel.CountryRequestModel
import com.example.exportControl.repository.CountryMasterRepository
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class CountryService {

    @Autowired
    private val companyMasterRepo: CountryMasterRepository? = null

    var currentDateTime: LocalDateTime = LocalDateTime.now()
    val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

    /**
     * insert company information
     * @param
     */
    fun insCompany(@Param("companyReq") countryReq: CountryRequestModel?) {

        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
//        val companyObj = Company(
//                companyReq?.companyCode,
//                companyReq?.companyNameJpn,
//                companyReq?.companyNameEng,
//                companyReq?.countryName,
//                null,
//                currentTime,
//                null,
//                null,
//                0
//        )
//        companyMasterRepo?.save(companyObj)
    }

    /**
     * update company information
     * @param companyReq
     */
    fun updateCompany(@Param("companyReq") companyReq: CompanyRequestModel?) {

//        companyMasterRepo?.updateCompanyMaster(companyReq?.companyCode,companyReq?.companyNameJpn,
//                "Test",companyReq?.countryName,currentTime)
    }

    /**
     * delete company information
     * @param companyCode
     */
    fun deleteCompany(@Param("companyCode") companyCode: String?) {

//        companyMasterRepo?.deleteCompanyMaster(companyCode,currentTime)
    }

    /**
     * search company information
     * @param companyReq
     */
    fun searchCompany(@Param("companyReq") companyReq: CompanyRequestModel?) {

//       return companyMasterRepo?.searchCompany(companyReq)
    }
}