package com.example.exportControl.restController

import com.example.exportControl.message.ReactedPerson
import com.example.exportControl.repository.ExportRegistrationRepository
import com.example.exportControl.repository.ReactUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

@RestController
class FetchInvoiceCode {

    @Autowired
    private val exportRegistrationRepo: ExportRegistrationRepository? = null

    @GetMapping(value = ["fetchInvoiceCode/companyCode"])
    fun fetchInvoiceCode(@RequestParam(value = "companyCode") companyCode: String?): List<String>? {
        var invoiceCode: List<String>? = null
        invoiceCode = exportRegistrationRepo?.getSavedInvoiceListByCompany(companyCode)
        return invoiceCode
    }


}