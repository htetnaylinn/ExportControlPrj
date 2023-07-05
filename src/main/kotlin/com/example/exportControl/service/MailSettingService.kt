package com.example.exportControl.service

import com.example.exportControl.model.FeeList
import com.example.exportControl.model.MailSetting
import com.example.exportControl.repository.FeeRepository
import com.example.exportControl.repository.MailSettingRepository
import com.example.exportControl.util.CSVHelper.csvToMailTable
import com.example.exportControl.util.CSVHelper.csvToTutorials
import com.lowagie.text.*
import com.lowagie.text.pdf.PdfWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.Color
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.*
import java.net.URLDecoder
import java.nio.file.Paths


@Service
class MailSettingService {

    @Autowired
    val mailSettingRepo: MailSettingRepository? = null

    fun saveMailSetting(file: MultipartFile,userCodeList:List<String>,languageFlag:Int?):Any {
        try {
            val value: Any = csvToMailTable(file.inputStream,userCodeList,languageFlag)
            if(value is String){
                return value
            }else{
                val fees: List<MailSetting> = value as List<MailSetting>
                mailSettingRepo?.saveAll(fees)
                return true
            }
        } catch (e: IOException) {
            throw RuntimeException("fail to store csv data: " + e.message)
        }
    }

}