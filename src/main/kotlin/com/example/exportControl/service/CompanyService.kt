package com.example.exportControl.service

import com.example.exportControl.model.Company
import com.example.exportControl.model.requestModel.CompanyRequestModel
import com.example.exportControl.repository.CompanyMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime


@Service
class CompanyService {

    @Autowired
    private val companyMasterRepo: CompanyMasterRepository? = null

    var currentDateTime: LocalDateTime = LocalDateTime.now()
    val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

    /**
     * insert company information
     * @param companyReq
     */
    fun insCompany(@Param("companyReq") companyReq: CompanyRequestModel?) {

        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        val companyObj = Company(
                companyReq?.companyCode,
                companyReq?.companyNameJpn,
                companyReq?.companyNameEng,
                companyReq?.countryName,
                null,
                currentTime,
                null,
                null,
                0
        )
        companyMasterRepo?.save(companyObj)
    }

    /**
     * update company information
     * @param companyReq
     */
    fun updateCompany(@Param("companyReq") companyReq: CompanyRequestModel?) {

        companyMasterRepo?.updateCompanyMaster(companyReq?.companyCode,companyReq?.companyNameJpn,
            companyReq?.companyNameEng,companyReq?.countryName,currentTime)
    }

    /**
     * delete company information
     * @param companyCode
     */
    fun deleteCompany(@Param("companyCode") companyCode: String?) {

        companyMasterRepo?.deleteCompanyMaster(companyCode,currentTime)
    }

    /**
     * search company information
     * @param companyReq
     */
    fun searchCompany(@Param("companyReq") companyReq: CompanyRequestModel?): List<Company>? {

       return companyMasterRepo?.searchCompany(companyReq)
    }

    /**
     * search company information by code
     * @param companyReq
     */
    fun selectCompanyByCompanyCode(@Param("companyReq") companyReq: CompanyRequestModel?): Company? {

        val companyCode = companyReq?.companyCode
        return companyMasterRepo?.selectCompanyByCompanyCode(companyCode)
    }
}