package com.example.exportControl.controller
import com.example.exportControl.model.ExportDocumentSave
import com.example.exportControl.model.FeeList
import com.example.exportControl.repository.ExportDocumentSaveRepository
import com.example.exportControl.repository.ProductMasterRepository
import com.example.exportControl.repository.UserMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.view.RedirectView
import java.security.Principal
import java.sql.Timestamp
import java.time.LocalDateTime

@Controller
class ExportDocumentController : MenuController() {


    @Autowired
    private val exportDocumentRepo: ExportDocumentSaveRepository? = null

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    @RequestMapping(value = ["/saveExportDocument"], method = [RequestMethod.POST])
    fun saveExportDocument(
        model: Model, principal: Principal,
        @RequestParam("documentFilesSizes") documentFilesSizes: List<String>?,
        @RequestParam("documentFilesNames") documentFilesNames: List<String>?,
        @RequestParam("documentFilesLists") documentFilesLists: List<String>?,
        @RequestParam("invoiceCode") invoiceCode: String
    ): RedirectView {
        val userCode = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userCode)
        val files = getByteFiles(documentFilesLists)
        val filesName = documentFilesNames
        val filesSize = documentFilesSizes
        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)

        filesName!!.forEachIndexed { index, fileName ->
            val getDocumentIdByInvoiceCode = exportDocumentRepo?.selectLastDocumentIdByInvoiceCode(invoiceCode)
            val lastDocumentId =  if(getDocumentIdByInvoiceCode == null){1}else {getDocumentIdByInvoiceCode+1}
            val newDocument = ExportDocumentSave(invoiceCode,lastDocumentId,fileName,filesSize?.get(index)?.toDouble(),files.get(index),userCode,currentTime,userCode,currentTime)
            exportDocumentRepo?.save(newDocument)
        }

        return RedirectView("/scheduletwo/${invoiceCode}", true)
    }

    fun getByteFiles(files:List<String>?):MutableList<ByteArray>{
        var size = files?.size
        var byteFiles :MutableList<ByteArray> = ArrayList<ByteArray>()
        run {
            var i = 0
            while (i <= size!!-1) {
                println(i)
                var temp = files?.get(i)+","+files?.get(i+1)
                byteFiles.add(temp.toByteArray())
                i = i + 2
            }
        }
        var data = String(byteFiles.get(0))
        return byteFiles
    }


    @RequestMapping("/deleteExportDocument/{invoiceCode}-{documentId}", method = [RequestMethod.GET])
    fun deletePortMaster(@PathVariable("invoiceCode") invoiceCode: String?,@PathVariable("documentId") documentId: Int?): RedirectView {
        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        exportDocumentRepo?.deleteDocument(invoiceCode,documentId)

        return RedirectView("/scheduletwo/${invoiceCode}", true)
    }
}