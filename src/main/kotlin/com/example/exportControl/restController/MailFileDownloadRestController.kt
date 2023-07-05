package com.example.exportControl.restController

import com.example.exportControl.controller.ScheduleController
import com.example.exportControl.message.DocumentResponse
import com.example.exportControl.service.ReadEmailBaseImpl
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URLEncoder
import java.security.Principal
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import javax.mail.BodyPart
import javax.servlet.http.HttpServletResponse


@RestController
class MailFileDownloadRestController {

    @Autowired
    private val readEmailBaseImpl: ReadEmailBaseImpl? = null

    @RequestMapping(value = ["/mailFileDownloadProcess/{fileName}"])
    @PostMapping(value = ["/mailFileDownloadProcess/{fileName}"],consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun mailFileDownloadProcess(response: HttpServletResponse,@PathVariable("fileName") fileName: String?,
    ): String? {
        var allParts: MutableList<BodyPart>? = readEmailBaseImpl?.allParts
        var downloadPart:BodyPart? = null
        allParts?.forEach { part ->
            if(part.fileName == fileName?.trim()){
               downloadPart = part
            }
        }
        fileDownload(downloadPart!!,response)
     return "success"
    }

     fun fileDownload(part:BodyPart,response:HttpServletResponse){
         val `is`: InputStream = part.inputStream
         var type = part.fileName?.split(".")

         response.outputStream.use { outputStream ->
             response.contentType = type?.get(1)
             response.setHeader(
                 "Content-Disposition",
                 String.format(Locale.JAPAN, "attachment;filename=%s", URLEncoder.encode(part.fileName, "utf-8"))
             )
             outputStream.write(`is`.readBytes())
             outputStream.flush()
         }
     }
}

