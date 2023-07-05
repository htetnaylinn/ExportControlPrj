package com.example.exportControl.controller

import com.example.exportControl.model.FeeList
import com.example.exportControl.repository.UserMasterRepository
import com.example.exportControl.service.CSVService
import com.example.exportControl.util.CSVHelper
import com.example.exportControl.util.CSVHelper.hasCSVFormat
import com.lowagie.text.*
import com.lowagie.text.pdf.BaseFont
import com.lowagie.text.pdf.PdfWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.awt.Color
import java.io.*
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.file.Paths
import java.security.Principal
import javax.servlet.http.HttpServletResponse

/**
 * Price List controller
 *
 */
@Controller
class PriceListController : MenuController() {

    @Autowired
    var fileService: CSVService? = null


    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    val bf = BaseFont.createFont(
        "KozMinPro-Regular", "UniJIS-UCS2-H",
        BaseFont.EMBEDDED,
    )

    /**
     * 料金表画面初期表示
     * @param model
     */
    @RequestMapping("priceList")
    fun priceList(principal: Principal,model: Model): String {
        var roleFlag :String = "0"
        val userName = principal.name

        if(userName != "00000"){
            roleFlag = "1"
        }
        val exportList: ArrayList<FeeList> = ArrayList<FeeList>()
        val importList: ArrayList<FeeList> = ArrayList<FeeList>()

        model.addAttribute("flg","0")
        try {
            val feeList = fileService?.getFeeList()

            for (feeObj in feeList!!) {
                if (feeObj.opt == "輸出") exportList.add(feeObj)
                else importList.add(feeObj)
            }

            model.addAttribute("priceList",feeList)
            model.addAttribute("exportList",exportList)
            model.addAttribute("importList",importList)
            model.addAttribute("flg","1")
            //"Uploaded the file successfully: " + file.originalFilename
        } catch (e: Exception) {
            //"Could not upload the file: " + file.originalFilename.toString() + "!"
        }
        val muser = usermasterRepo?.selectUserByUserCode(userName);
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("roleFlag",roleFlag)
        return "priceList"
    }

    /**
     * CSV取り込み処理
     * @param file
     * @param principal
     * @param model
     */
    @RequestMapping(value= ["/uploadFile"], method= [RequestMethod.POST])
    fun uploadCSVFile(@RequestParam("file") file: MultipartFile,
                      principal: Principal, model: Model
        ,redirectAttributes: RedirectAttributes
    ): String? {

        if (hasCSVFormat(file)) {
            var saveFlag = fileService?.save(file,webConfig?.messageSourcePath)
            if(saveFlag is String){
                redirectAttributes.addFlashAttribute("saveErrorMsg", saveFlag)
            }
        }else{
            redirectAttributes.addFlashAttribute("saveErrorMsg", if(webConfig?.messageSourcePath != 0){"File format is wrong."}else{"ファイルのフォーマットが間違っています。"})
        }
        return "redirect:/priceList"
    }


    /**
     * 航空便料金表（PDF）処理

     */
    @RequestMapping(value= ["/pdfPreview"], method= [RequestMethod.POST])
    fun pdfPreview(response:HttpServletResponse) {
//    fun pdfPreview(): ResponseEntity<InputStreamResource>? {
        var exportList: ArrayList<FeeList> = ArrayList<FeeList>()
        var importList: ArrayList<FeeList> = ArrayList<FeeList>()
        val title1 = "輸出金額"
        val title2 = "輸入金額"
        val tFont = Font(bf)
        tFont.size = 20F
        val font = Font(bf)
        font.size = 9F

        val feeList = fileService?.getFeeList()
        for (feeObj in feeList!!) {
            if (feeObj.opt == "輸出") exportList?.add(feeObj)
            else importList?.add(feeObj)
        }

        val myPDFDoc = Document() // down

        val byteArrayOutputStream = ByteArrayOutputStream()
        val pdfWriter = PdfWriter.getInstance(myPDFDoc, byteArrayOutputStream)

        val exportTable = getTableFrame()
        val importTable = getTableFrame()

        for (exportObj in exportList!!) {
            exportTable.apply {
                addCell(Cell(Phrase(exportObj.opt,font)))
                addCell(Cell(Phrase(exportObj.startRoute,font)))
                addCell(Cell(Phrase(exportObj.endRoute,font)))
                addCell(Cell(Phrase(exportObj.thirtyTon,font)))
                addCell(Cell(Phrase(exportObj.sixtyTon,font)))
                addCell(Cell(Phrase(exportObj.ninetyTon,font)))
                addCell(Cell(Phrase(exportObj.hundredTwentyTon,font)))
                addCell(Cell(Phrase(exportObj.hundredThirtyTon,font)))
                addCell(Cell(Phrase(exportObj.hundredSixtyTon,font)))
                addCell(Cell(Phrase(exportObj.hundredNinetyTon,font)))
                addCell(Cell(Phrase(exportObj.twoHundredTwentyTon,font)))
                addCell(Cell(Phrase(exportObj.twoHundredFiftyTon,font)))
                addCell(Cell(Phrase(exportObj.twoHundredEightyTon,font)))
            }
        }

        for (importObj in importList!!) {
            importTable.apply {
                addCell(Cell(Phrase(importObj.opt,font)))
                addCell(Cell(Phrase(importObj.startRoute,font)))
                addCell(Cell(Phrase(importObj.endRoute,font)))
                addCell(Cell(Phrase(importObj.thirtyTon,font)))
                addCell(Cell(Phrase(importObj.sixtyTon,font)))
                addCell(Cell(Phrase(importObj.ninetyTon,font)))
                addCell(Cell(Phrase(importObj.hundredTwentyTon,font)))
                addCell(Cell(Phrase(importObj.hundredThirtyTon,font)))
                addCell(Cell(Phrase(importObj.hundredSixtyTon,font)))
                addCell(Cell(Phrase(importObj.hundredNinetyTon,font)))
                addCell(Cell(Phrase(importObj.twoHundredTwentyTon,font)))
                addCell(Cell(Phrase(importObj.twoHundredFiftyTon,font)))
                addCell(Cell(Phrase(importObj.twoHundredEightyTon,font)))
            }
        }

        myPDFDoc.apply {
            open()
            add(
                Paragraph(title1,tFont).apply {
                setAlignment(Element.ALIGN_CENTER)
                }
            )

            add(Paragraph(Chunk.NEWLINE))
            add(exportTable)
            myPDFDoc.newPage()
            myPDFDoc.add(
                Paragraph(title2,tFont).apply {
                setAlignment(Element.ALIGN_CENTER)
            })
            add(importTable)
            close()
        }

        pdfWriter.close() // close the File writer

        response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncoder.encode("料金表", "UTF-8") + "\"");
        response.setHeader("Cache-Control", "no-cache")
        response.setContentType("application/pdf")
        response.outputStream.write(byteArrayOutputStream.toByteArray())
        response.outputStream.flush()

    }

    fun getTableFrame(): Table {

        val font = Font(bf)
        font.size = 9F

        val myTable = Table(13).apply { // 3 columns
            setPadding(2f)
            setSpacing(1f)
            setWidth(100f)
        }

        val p1 = Phrase()
        p1.add(Chunk("輸出/ ",font))
        p1.add(Chunk("\n輸入 ",font))
        myTable.addCell(Cell(p1).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })

        val p2 = Phrase()
        p2.add(Chunk("開始 ",font))
        p2.add(Chunk("\nルート ",font))
        myTable.addCell(Cell(p2).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })

        val p3 = Phrase()
        p3.add(Chunk("終了 ",font))
        p3.add(Chunk("\nルート ",font))
        myTable.addCell(Cell(p3).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) } )

        // 30RT（㎥／トン）(㎥/トン)
        val p4 = Phrase()
        p4.add(Chunk("30RT",font))
        p4.add(Chunk("\n(㎥/トン) ",font))
        myTable.addCell(Cell(p4).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })

        val p5 = Phrase()
        p5.add(Chunk("60RT ",font))
        p5.add(Chunk("\n(㎥/トン) ",font))
        myTable.addCell(Cell(p5).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })

        val p6 = Phrase()
        p6.add(Chunk("90RT",font))
        p6.add(Chunk("\n(㎥/トン) ",font))
        myTable.addCell(Cell(p6).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })

        val p7 = Phrase()
        p7.add(Chunk("120RT ",font))
        p7.add(Chunk("\n(㎥/トン) ",font))
        myTable.addCell(Cell(p7).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })

        val p8 = Phrase()
        p8.add(Chunk("130RT ",font))
        p8.add(Chunk("\n(㎥/トン) ",font))
        myTable.addCell(Cell(p8).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })

        val p9 = Phrase()
        p9.add(Chunk("160RT ",font))
        p9.add(Chunk("\n(㎥/トン) ",font))
        myTable.addCell(Cell(p9).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })

        val p10 = Phrase()
        p10.add(Chunk("190RT ",font))
        p10.add(Chunk("\n(㎥/トン) ",font))
        myTable.addCell(Cell(p10).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })


        val p11 = Phrase()
        p11.add(Chunk("220RT ",font))
        p11.add(Chunk("\n(㎥/トン) ",font))
        myTable.addCell(Cell(p11).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })


        val p12 = Phrase()
        p12.add(Chunk("250RT ",font))
        p12.add(Chunk("\n(㎥/トン) ",font))
        myTable.addCell(Cell(p12).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })

        val p13 = Phrase()
        p13.add(Chunk("280RT ",font))
        p13.add(Chunk("\n(㎥/トン) ",font))
        myTable.addCell(Cell(p13).apply { setHeader(true)
            setBackgroundColor(Color.LIGHT_GRAY) })

        return myTable
    }

}