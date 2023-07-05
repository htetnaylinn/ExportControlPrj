package com.example.exportControl.util

import com.example.exportControl.config.WebConfiguration
import com.example.exportControl.controller.MenuController
import com.example.exportControl.model.FeeList
import com.example.exportControl.model.MailSetting
import com.example.exportControl.repository.MailSettingRepository
import com.example.exportControl.service.MailSettingService
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList


object CSVHelper: MenuController() {

    var TYPE = "text/csv"

    var languageFlag:Int? = null

    //    var HEADERs = arrayOf("Id", "Title", "Description", "Published")
    fun hasCSVFormat(file: MultipartFile): Boolean {
        return TYPE == file.contentType
    }

//    fun csvToTutorials(`is`: InputStream?): List<FeeList> {
//        try {
//            BufferedReader(InputStreamReader(`is`, "UTF-8")).use { fileReader ->
//                CSVParser(
//                    fileReader,
//                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
//                ).use { csvParser ->
//                    var feeHeaderFormatCheck:Boolean = true
//                    val feeList: MutableList<FeeList> = ArrayList<FeeList>()
//                    val csvRecords: Iterable<CSVRecord> = csvParser.records
//                    var errorMsg = ""
//                    for (csvRecord in csvRecords) {
//                        feeHeaderFormatCheck = feeHeaderCheck(csvRecord.parser.headerNames)
//
//                        errorMsg = feeErrorMessage(feeHeaderFormatCheck)
//
//                        if (feeHeaderFormatCheck){}
//                        val feeObj = FeeList(
//                            Date(csvRecord[0]),
//                            csvRecord[1],
//                            csvRecord[2],
//                            csvRecord[3],
//                            csvRecord[4],
//                            csvRecord[5],
//                            csvRecord[6],
//                            csvRecord[7],
//                            csvRecord[8],
//                            csvRecord[9],
//                            csvRecord[10],
//                            csvRecord[11],
//                            csvRecord[12],
//                            csvRecord[13],
//                        )
//                        feeList.add(feeObj)
//                    }
//                    if(feeHeaderFormatCheck){
//                        return feeList
//                    }else{
//                        return errorMsg
//                    }
//
//                }
//            }
//        } catch (e: IOException) {
//            throw RuntimeException("fail to parse CSV file: " + e.message)
//        }
//    }

    fun csvToTutorials(`is`: InputStream?,languageFlag:Int?): Any {
        this.languageFlag = languageFlag
        try {

            BufferedReader(InputStreamReader(`is`, "UTF-8")).use { fileReader ->
                CSVParser(
                    fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
                ).use { csvParser ->
                    var errorMsg = ""
                    var headerFormatCheck: Boolean = true
                    var blankCheck: Boolean = true
                    val feeList: MutableList<FeeList> = ArrayList<FeeList>()
                    val csvRecords: Iterable<CSVRecord> = csvParser.records
                    val length = getRecordLength(csvRecords)
                    for (csvRecord in csvRecords) {
                        headerFormatCheck = feeHeaderCheck(csvRecord.parser.headerNames)
                        blankCheck = priceListBlankCheck(csvRecords,length)
                        errorMsg = feeErrorMessage(headerFormatCheck,blankCheck)
                        if (headerFormatCheck && blankCheck) {
                            val feeObj = FeeList(
                                Date(csvRecord[0]),
                                csvRecord[1],
                                csvRecord[2],
                                csvRecord[3],
                                csvRecord[4],
                                csvRecord[5],
                                csvRecord[6],
                                csvRecord[7],
                                csvRecord[8],
                                csvRecord[9],
                                csvRecord[10],
                                csvRecord[11],
                                csvRecord[12],
                                csvRecord[13],
                            )
                            feeList.add(feeObj)
                        } else {
                            break;
                        }
                    }
                    if (headerFormatCheck && blankCheck) {
                        return feeList
                    } else {
                        return errorMsg
                    }

                }
            }
        } catch (e: Exception) {
            return if(this.languageFlag != 0){"File format is wrong."}else{"ファイルのフォーマットが間違っています。"}
        }
    }

    fun csvToMailTable(`is`: InputStream?, userCodeList: List<String>,languageFlag:Int?): Any {
        this.languageFlag = languageFlag
        try {

            BufferedReader(InputStreamReader(`is`, "UTF-8")).use { fileReader ->
                CSVParser(
                    fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
                ).use { csvParser ->
                    var errorMsg = ""
                    var headerFormatCheck: Boolean = true
                    var duplicateCheck: Boolean = true
                    var blankCheck: Boolean = true
                    var inputDuplicateCheck: Boolean = true
                    val feeList: MutableList<MailSetting> = ArrayList<MailSetting>()
                    val csvRecords: Iterable<CSVRecord> = csvParser.records
                    val length = getRecordLength(csvRecords)
                    duplicateCheck = mailSettingDuplicateCheck(userCodeList, csvRecords)
                    blankCheck = mailSettingBlankCheck(csvRecords,length)
                    inputDuplicateCheck = mailSettingInputDuplicateCheck(csvRecords,length)
                    for (csvRecord in csvRecords) {
                        headerFormatCheck = mailSettingHeaderCheck(csvRecord.parser.headerNames)

                        errorMsg = errorMessage(headerFormatCheck, duplicateCheck, blankCheck, inputDuplicateCheck)

                        if (headerFormatCheck && duplicateCheck && blankCheck && inputDuplicateCheck) {
                            var userCode = csvRecord[0]
                            val feeObj = MailSetting(
                                csvRecord[0],
                                csvRecord[1],
                                csvRecord[2],
                                csvRecord[3],
                                csvRecord[4],
                                csvRecord[5],
                                csvRecord[6]
                            )
                            feeList.add(feeObj)
                        } else {
                            break;
                        }
                    }
                    if (headerFormatCheck && duplicateCheck && blankCheck && inputDuplicateCheck) {
                        return feeList
                    } else {
                        return errorMsg
                    }

                }
            }
        } catch (e: Exception) {
            return if(this.languageFlag != 0){"File format is wrong."}else{"ファイルのフォーマットが間違っています。"}
        }
    }

    private fun mailSettingHeaderCheck(headerList: List<Any>?): Boolean {
        var headerSize = headerList?.size
        if (headerSize == 7) {
            var firstheader = headerList?.get(1)
            if (headerList?.get(0) != "\uFEFF社員番号") {
                return false
            } else if (headerList?.get(1) != "ホスト") {
                return false
            } else if (headerList?.get(2) != "ポート") {
                return false
            } else if (headerList?.get(3) != "プロトコル") {
                return false
            } else if (headerList?.get(4) != "メールユーザー名") {
                return false
            } else if (headerList?.get(5) != "メールパスワード") {
                return false
            } else return headerList?.get(6) == "解凍メールアドレス"

        } else {
            return false
        }
    }

    private fun mailSettingDuplicateCheck(savedList: List<String>?, inputList: Iterable<CSVRecord>): Boolean {
        for (input in inputList) {
            if (savedList!!.contains(input[0])) {
                return false
            }
        }
        return true
    }

    private fun mailSettingBlankCheck(csvRecords: Iterable<CSVRecord>,length:Int?): Boolean {
        var returnType: Boolean = true;
        var count = 0
        for (csvRecord in csvRecords) {
            count++
            if (csvRecord[0] != "" && csvRecord[1] != "" && csvRecord[2] != "" && csvRecord[3] != "" && csvRecord[4] != "" && csvRecord[5] != "") {
                returnType = true
            } else {
                returnType = false
                break
            }

            if(count == length){
                break
            }
        }
        return returnType
    }

    private fun priceListBlankCheck(csvRecords: Iterable<CSVRecord>,length:Int?): Boolean {
        var returnType: Boolean = true;
        var count = 0
        for (csvRecord in csvRecords) {
            count++
            if (csvRecord[0] != "" && csvRecord[1] != "" && csvRecord[2] != "" && csvRecord[3] != "" && csvRecord[4] != "" && csvRecord[5] != "" && csvRecord[6] != "" && csvRecord[7] != "" && csvRecord[8] != "" && csvRecord[9] != "" && csvRecord[10] != "" && csvRecord[11] != "" && csvRecord[12] != "" && csvRecord[13] != "") {
                returnType = true
            } else {
                returnType = false
                break
            }

            if(count == length){
                break
            }
        }
        return returnType
    }

    private fun mailSettingInputDuplicateCheck(csvRecords: Iterable<CSVRecord>,length:Int?): Boolean {
        var returnType: Boolean = true
        var count = 0
        for (x in csvRecords) {
            count++
            var duplicateCount: Int = 0;
            for (y in csvRecords) {
                if (x[0] == y[0]) {
                    duplicateCount++
                }
            }
            if (duplicateCount != 1) {
                returnType = false
                break;
            }

            if(count == length){
                break;
            }
        }

        return returnType
    }

    private fun getRecordLength(csvRecords: Iterable<CSVRecord>): Int? {
        var lengthNum = 0
        var count = 0
        for (csvRecord in csvRecords) {
            count++
            if (csvRecord[0] != "" || csvRecord[1] != "" || csvRecord[2] != "" || csvRecord[3] != "" || csvRecord[4] != "" || csvRecord[5] != "") {
                lengthNum = count
            }
        }
        return lengthNum
    }

    private fun errorMessage(format: Boolean, duplicate: Boolean, blank: Boolean, inputDuplicate: Boolean): String {
        if (!format && !duplicate && !blank && !inputDuplicate) {
            return if(this.languageFlag != 0){"File format is wrong."}else{"ファイルのフォーマットが間違っています。"}
        } else if (!format) {
            return if(this.languageFlag != 0){"File format is wrong."}else{"ファイルのフォーマットが間違っています。"}
        } else if (!inputDuplicate) {
            return if(this.languageFlag != 0){"User Id is duplicate in file."}else{"取込ファイルにユーザー ID が重複しています。"}
        } else if (!duplicate) {
            return if(this.languageFlag != 0){"Some data of the imported file is already registered."}else{"取込ファイルには以前存在したデータはあります。"}
        } else {
            return if(this.languageFlag != 0){"Value must not be blank."}else{"値を空白にすることはできません。"}
        }
    }

    private fun feeHeaderCheck(headerList: List<Any>?): Boolean {
        var headerSize = headerList?.size
        if (headerSize == 14) {
            if (headerList?.get(0) != "\uFEFF日付") {
                return false
            } else if (headerList?.get(1) != "輸出/輸入") {
                return false
            } else if (headerList?.get(2) != "入ルート") {
                return false
            } else if (headerList?.get(3) != "出ルート") {
                return false
            } else if (headerList?.get(4) != "30RT（㎥／トン）") {
                return false
            } else if (headerList?.get(5) != "60RT（㎥／トン）") {
                return false
            } else if (headerList?.get(6) != "90RT（㎥／トン）") {
                return false
            } else if (headerList?.get(7) != "120RT（㎥／トン）") {
                return false
            } else if (headerList?.get(8) != "130RT（㎥／トン）") {
                return false
            } else if (headerList?.get(9) != "160RT（㎥／トン）") {
                return false
            } else if (headerList?.get(10) != "190RT（㎥／トン）") {
                return false
            } else if (headerList?.get(11) != "220RT（㎥／トン）") {
                return false
            } else if (headerList?.get(12) != "250RT（㎥／トン）") {
                return false
            } else return headerList?.get(13) == "280RT（㎥／トン）"

        } else {
            return false
        }
    }

    private fun feeErrorMessage(headerFormat: Boolean,blankCheck:Boolean): String {
        if (!headerFormat) {
            return if(this.languageFlag != 0){"File format is wrong."}else{"ファイルのフォーマットが間違っています。"}
        }else if(!blankCheck){
            return if(this.languageFlag != 0){"Value must not be blank."}else{"値を空白にすることはできません。"}
        }else{
            return ""
        }
    }
}