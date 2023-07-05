package com.example.exportControl.controller

import org.apache.commons.compress.utils.IOUtils
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.io.InputStream
import javax.servlet.http.HttpServletResponse


@Controller
class FormatFileDownloadController:MenuController() {


    @GetMapping("/downloadFormatFile")
    @Throws(IOException::class)
    fun downloadFile(response: HttpServletResponse) {
        val resource: Resource = ClassPathResource("templates/メールテンプレート.csv")
        response.contentType = "text/csv"
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''%E3%83%A1%E3%83%BC%E3%83%AB%E3%83%86%E3%83%B3%E3%83%97%E3%83%AC%E3%83%BC%E3%83%88.csv");
        val inputStream: InputStream = resource.inputStream
        IOUtils.copy(inputStream, response.outputStream)
        response.flushBuffer()
    }

    @GetMapping("/downloadPriceListFormatFile")
    @Throws(IOException::class)
    fun downloadPriceListFormatFile(response: HttpServletResponse) {
        val resource: Resource = ClassPathResource("templates/料金表テンプレート.csv")
        response.contentType = "text/csv"
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''%E6%96%99%E9%87%91%E8%A1%A8%E3%83%86%E3%83%B3%E3%83%97%E3%83%AC%E3%83%BC%E3%83%88.csv");
        val inputStream: InputStream = resource.inputStream
        IOUtils.copy(inputStream, response.outputStream)
        response.flushBuffer()
    }
}