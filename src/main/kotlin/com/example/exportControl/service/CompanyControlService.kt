package com.example.exportControl.service

import com.example.exportControl.model.CompanyControl
import com.example.exportControl.model.requestModel.CompanyRequestModel
import com.example.exportControl.repository.CompanyControlRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class CompanyControlService  {

    @Autowired
    private val companyControlRepo: CompanyControlRepository? = null

    /**
     * 新規会社コントロール登録実施
     * @param companyReq
     */

    fun insCompanyControl(@Param("companyReq") companyReq: CompanyRequestModel?){

        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        val billMonthFlg: Int = if (companyReq?.billingMonth != null) 1 else 0
        val contractFlg: Int = if (companyReq?.contract != null) 1 else 0
        val modeFlg: Int = if (companyReq?.mode != null) 1 else 0
        val fortyFlg: Int = if (companyReq?.fortyF != null) 1 else 0
        val twentyFlg: Int = if (companyReq?.twentyF != null) 1 else 0
        val shipNameFlg: Int = if (companyReq?.shipName != null) 1 else 0
        val awbFlg: Int = if (companyReq?.awb != null) 1 else 0
        val landFlg: Int = if (companyReq?.land != null) 1 else 0
        val departFlg: Int = if (companyReq?.depart != null) 1 else 0
        val loanFlg: Int = if (companyReq?.loan != null) 1 else 0

        val compControlObj = CompanyControl(
                companyReq?.companyCode,
                billMonthFlg, contractFlg, modeFlg,
                fortyFlg, twentyFlg, shipNameFlg,
                )

        companyControlRepo?.save(compControlObj)

    }
}