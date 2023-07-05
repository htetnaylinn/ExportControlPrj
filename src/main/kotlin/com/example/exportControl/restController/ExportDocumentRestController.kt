package com.example.exportControl.restController

import com.example.exportControl.message.DocumentResponse
import com.example.exportControl.repository.ExportDocumentSaveRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ExportDocumentRestController {

    @Autowired
    private val exportDocumentRepo: ExportDocumentSaveRepository? = null

    @GetMapping(value = ["getDocumentDownloadUrl"])
    fun getMyAjaxMessage(
        @RequestParam(value = "invoiceCode") invoiceCode: String?,
        @RequestParam(value = "documentId") documentId: Int?
    ): DocumentResponse? {
        var url = ""
        val document = exportDocumentRepo?.selectDocumentFile(invoiceCode, documentId)
        url = String(document?.documentFile!!)
        val fileName = document.documentName
        return DocumentResponse(fileName, url)
    }


}