package com.example.exportControl.restController

import com.example.exportControl.controller.ScheduleController
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
import javax.servlet.http.HttpServletResponse


@RestController
@CrossOrigin(origins = arrayOf("*"))
class ScheduleRestController {

    @Autowired
    private val scheduleController: ScheduleController? = null


    private final val format: DateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy")
    private final val localDate: LocalDate = LocalDate.parse("1/1/1930", this.format)
    val date: Date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    @RequestMapping(value = ["/scheduleCsvOutput"])
    @PostMapping(value = ["/scheduleCsvOutput"],consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun scheduleCsvOutput(principal: Principal,response: HttpServletResponse,mapper: ObjectMapper): String? {

        val allProducts = scheduleController?.csvAllProducts

        allProducts?.forEach { p ->
            p.declarationDate = if(p.declarationDate == null){date}else{p.declarationDate}
            p.inspectionDate = if(p.inspectionDate == null){date}else{p.inspectionDate}
            p.deliveryDateAndTime = if(p.deliveryDateAndTime == null){date}else{p.deliveryDateAndTime}
            p.possiblePickUpDate = if(p.possiblePickUpDate == null){date}else{p.possiblePickUpDate}
            p.pickUpDate = if(p.pickUpDate == null){date}else{p.pickUpDate}
            p.freeTime = if(p.freeTime == null){date}else{p.freeTime}
            p.billingMonth = if(p.billingMonth == null){date}else{p.billingMonth}
            p.eta = if(p.eta == null){date}else{p.eta}
            p.etd = if(p.etd == null){date}else{p.etd}
            p.cut = if(p.cut == null){date}else{p.cut}
        }
        val dates = scheduleController?.csvDateList
        val columns = scheduleController?.csvColumnarrayList

        val showDeclarationDateFlag = scheduleController?.showDeclarationDateFlag
        val showInspectionDateFlag = scheduleController?.showInspectionDateFlag
        val showDeliveryDateFlag = scheduleController?.showDeclarationDateFlag
        val showpossiblePickUpDateFlag = scheduleController?.showpossiblePickUpDateFlag
        val showPickUpDateFlag = scheduleController?.showPickUpDateFlag
        val showfreeTimeDateFlag = scheduleController?.showfreeTimeDateFlag

        val showBillingMonthFlag = scheduleController?.showBillingMonthFlag
        val showEtaFlag = scheduleController?.showEtaFlag
        val showEtdFlag = scheduleController?.showEtdFlag
        val showCutFlag = scheduleController?.showCutFlag

        var rowCount = 1
        var cellCount = 0

        val  wb = XSSFWorkbook()
        val sheet: Sheet = wb.createSheet("sample")
        val headerRow = sheet.createRow(rowCount)
        val firstColumnHeader = headerRow.createCell(cellCount)
        firstColumnHeader.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
        firstColumnHeader.setCellValue("INV No")

        columns!!.forEach {header ->
            cellCount++
            val columnHeader = headerRow.createCell(cellCount)
            columnHeader.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
            columnHeader.setCellValue(header)
        }
//        cellCount++
//        var chatHeader = headerRow.createCell(cellCount)
//        chatHeader.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
//        chatHeader.setCellValue("チャット")

        dates!!.forEach {header ->
            cellCount++
            val dateHeader = headerRow.createCell(cellCount)
            dateHeader.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
            dateHeader.setCellValue(header)
        }

        allProducts!!.forEach {product ->
            rowCount++
            var bodyCellCount = 0
            val bodyRow = sheet.createRow(rowCount)

            val gson = Gson()
            val jsonString = gson.toJson(product)
            val keys: MutableList<String> = ArrayList()
            val jsonNode: JsonNode = mapper.readTree(jsonString)
            val iterator: Iterator<String> = jsonNode.fieldNames()

            iterator.forEachRemaining { e: String? -> keys.add(e!!) }

            keys.forEach {key ->
                val value = jsonNode.get(key).toString()

                if(key != "declarationDate" && key !="inspectionDate" && key != "deliveryDateAndTime" && key != "possiblePickUpDate" && key != "pickUpDate" && key != "billingMonth" && key != "eta" && key != "etd" && key != "freeTime" && key != "cut" &&
                    key != "declarationDateUpdateStatus" && key != "inspectiondateupdatestatus" && key != "deliverydateupdatestatus" && key != "possiblepickupdateupdatestatus" &&
                        key != "pickupdateupdatestatus" && key != "freedateupdatestatus" && key != "scheduledatesupdatestatus" &&
                        key != "createdBy" && key != "createdDt" && key != "updatedBy" && key != "updatedDt"){
                    val invoiceCell = bodyRow.createCell(bodyCellCount)
                    invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                    invoiceCell.setCellValue(value.substring(1, value.length - 1))
                    bodyCellCount++
                }else{
                    when (key) {
                        "declarationDate" ->{
                            if(showDeclarationDateFlag == 1){
                                val invoiceCell = bodyRow.createCell(bodyCellCount)
                                invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                                invoiceCell.setCellValue(timeStampToFormattedDateString(value))
                                bodyCellCount++
                            }
                        }
                        "inspectionDate" -> {
                            if(showInspectionDateFlag == 1){
                                val invoiceCell = bodyRow.createCell(bodyCellCount)
                                invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                                invoiceCell.setCellValue(timeStampToFormattedDateString(value))
                                bodyCellCount++
                            }
                        }
                        "deliveryDateAndTime" -> {
                            if(showDeliveryDateFlag == 1){
                                val invoiceCell = bodyRow.createCell(bodyCellCount)
                                invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                                invoiceCell.setCellValue(timeStampToFormattedDateString(value))
                                bodyCellCount++
                            }
                        }
                        "possiblePickUpDate" -> {
                            if(showpossiblePickUpDateFlag == 1){
                                val invoiceCell = bodyRow.createCell(bodyCellCount)
                                invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                                invoiceCell.setCellValue(timeStampToFormattedDateString(value))
                                bodyCellCount++
                            }
                        }
                        "pickUpDate" -> {
                            if(showPickUpDateFlag == 1){
                                val invoiceCell = bodyRow.createCell(bodyCellCount)
                                invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                                invoiceCell.setCellValue(timeStampToFormattedDateString(value))
                                bodyCellCount++
                            }
                        }
                        "billingMonth" -> {
                            if(showBillingMonthFlag == 1){
                                val invoiceCell = bodyRow.createCell(bodyCellCount)
                                invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                                invoiceCell.setCellValue(timeStampToFormattedDateString(value))
                                bodyCellCount++
                            }
                        }
                        "eta" -> {
                            if(showEtaFlag == 1){
                                val invoiceCell = bodyRow.createCell(bodyCellCount)
                                invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                                invoiceCell.setCellValue(timeStampToFormattedDateString(value))
                                bodyCellCount++
                            }
                        }
                        "etd" -> {
                            if(showEtdFlag == 1){
                                val invoiceCell = bodyRow.createCell(bodyCellCount)
                                invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                                invoiceCell.setCellValue(timeStampToFormattedDateString(value))
                                bodyCellCount++
                             }
                        }
                        "freeTime" -> {
                            if(showfreeTimeDateFlag == 1){
                                val invoiceCell = bodyRow.createCell(bodyCellCount)
                                invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                                invoiceCell.setCellValue(timeStampToFormattedDateString(value))
                                bodyCellCount++
                            }
                        }
                        "cut" -> {
                            if(showCutFlag == 1){
                                val invoiceCell = bodyRow.createCell(bodyCellCount)
                                invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                                invoiceCell.setCellValue(timeStampToFormattedDateString(value))
                                bodyCellCount++
                            }
                        }
                        else -> { // Note the block
                            print("x is neither 1 nor 2")
                        }
                    }
                }

            }
//            var invoiceCell = bodyRow.createCell(bodyCellCount)
//            invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
//            invoiceCell.setCellValue("既読")

            dates.forEach {date ->

                val pattern = "MM/dd/yyyy"
                val simpleDateFormat = SimpleDateFormat(pattern)
//                val date = simpleDateFormat.format(date)
                val declarationDate = if(product.declarationDate != null){ simpleDateFormat.format(Date(product.declarationDate!!.time))}else{""}
                val inspectionDate = if(product.inspectionDate != null){simpleDateFormat.format(Date(product.inspectionDate!!.time))}else{""}
                val deliveryDate = if(product.deliveryDateAndTime != null){simpleDateFormat.format(Date(product.deliveryDateAndTime!!.time))}else{""}
                val possiblePickUpDate = if(product.possiblePickUpDate != null){ simpleDateFormat.format(Date(product.possiblePickUpDate!!.time))}else{""}
                val pickupDate = if(product.pickUpDate != null){simpleDateFormat.format(Date(product.pickUpDate!!.time))}else{""}
                val freeTimeDate = if(product.freeTime != null){simpleDateFormat.format(Date(product.freeTime!!.time))}else{""}

                if(date == declarationDate) {
                    val invoiceCell = bodyRow.createCell(bodyCellCount)
                    invoiceCell.cellStyle = cellStyle(wb,IndexedColors.PINK1.index)
                    invoiceCell.setCellValue("申告")
                }else if(date == inspectionDate) {
                    val invoiceCell = bodyRow.createCell(bodyCellCount)
                    invoiceCell.cellStyle = cellStyle(wb,IndexedColors.BLUE1.index)
                    invoiceCell.setCellValue("検査")
                }else if(date == deliveryDate) {
                    val invoiceCell = bodyRow.createCell(bodyCellCount)
                    invoiceCell.cellStyle = cellStyle(wb,IndexedColors.RED1.index)
                    invoiceCell.setCellValue("配送")
                }else if(date == possiblePickUpDate) {
                    val invoiceCell = bodyRow.createCell(bodyCellCount)
                    invoiceCell.cellStyle = cellStyle(wb,IndexedColors.GREY_25_PERCENT.index)
                    invoiceCell.setCellValue("引取")
                }else if(date == pickupDate) {
                    val invoiceCell = bodyRow.createCell(bodyCellCount)
                    invoiceCell.cellStyle = cellStyle(wb,IndexedColors.YELLOW.index)
                    invoiceCell.setCellValue("集荷")
                }else if(date == freeTimeDate) {
                    val invoiceCell = bodyRow.createCell(bodyCellCount)
                    invoiceCell.cellStyle = cellStyle(wb,IndexedColors.GREEN.index)
                    invoiceCell.setCellValue("自由")
                }
                else{
                    val invoiceCell = bodyRow.createCell(bodyCellCount)
                    invoiceCell.cellStyle = cellStyle(wb,IndexedColors.WHITE.index)
                    invoiceCell.setCellValue(" ")
                }
                bodyCellCount++
            }
        }
        val out = ByteArrayOutputStream()
        wb.write(out)
        out.close()
        wb.close()
        fileOutPut(out.toByteArray(),response)
        return "success"
    }

    fun fileOutPut(report:ByteArray,response:HttpServletResponse){
//        var today = LocalDateTime.now()
//        var folderPath = System.getProperty("user.home") + "/" +"test"
//        val fName = today.year.toString() + today.monthValue + today.dayOfMonth + "_" + today.hour + today.minute + today.second + "_" + "財務三表"

        val targetStream: InputStream = ByteArrayInputStream(report)
//        val path = Paths.get(folderPath+"/outPut")
//        if (!Files.exists(path)) {
//            Files.createDirectory(path)
//        }
//        val mFile = Paths.get("$path/$fName.xlsx")

        //  Files.copy(targetStream, mFile, StandardCopyOption.REPLACE_EXISTING)
        val fileNameFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-hh:mm:ss")
        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime = currentDateTime.format(fileNameFormatter)
        val companyName = scheduleController?.companyName
        val outPutFileName = "$companyName-$currentTime.xlsx"

        response.outputStream.use { outputStream ->
            response.contentType = "xlsx"
            response.setHeader(
                "Content-Disposition",
                String.format(Locale.JAPAN, "attachment;filename=%s", URLEncoder.encode(outPutFileName, "utf-8"))
            )
            outputStream.write(targetStream.readBytes())
            outputStream.flush()
        }

    }

    fun cellStyle(wb:XSSFWorkbook,color:Short): CellStyle {
        val dataCellStyle = wb.createCellStyle()
        val font = wb.createFont()
        font.bold = true
        dataCellStyle.fillBackgroundColor = color
        dataCellStyle.fillPattern = FillPatternType.SOLID_FOREGROUND
        dataCellStyle.fillForegroundColor = color
        dataCellStyle.borderLeft = BorderStyle.THIN
        dataCellStyle.borderRight = BorderStyle.THIN
        dataCellStyle.borderTop = BorderStyle.THIN
        dataCellStyle.borderBottom = BorderStyle.THIN
        dataCellStyle.setFont(font)
        return dataCellStyle
    }


    fun timeStampToFormattedDateString(value: String): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy, hh:mm:ss a")
            val insertFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")
            val dateString = value.substring(1, value.length - 1)
            val date = LocalDate.parse(dateString, formatter)
           if( date.year.toString() != "1930"){
               return date.format(insertFormat)
           }else{
               return ""
           }

    }
}

