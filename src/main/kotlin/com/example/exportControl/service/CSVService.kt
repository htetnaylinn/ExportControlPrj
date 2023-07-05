package com.example.exportControl.service

import com.example.exportControl.model.FeeList
import com.example.exportControl.model.MailSetting
import com.example.exportControl.repository.FeeRepository
import com.example.exportControl.util.CSVHelper
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
class CSVService {

    @Autowired
    var repository: FeeRepository? = null


    @Autowired
    private val env: ConfigurableEnvironment? = null
    private val YAML_NAME = "sample1.pdf"


    /**
     * CSV取り込み処理
     * @param file
     */
    fun save(file: MultipartFile,languageFlag:Int?): Any {
//        try {
//            val fees: List<FeeList> = csvToTutorials(file.inputStream)
//            repository?.saveAll(fees)
//        } catch (e: IOException) {
//            throw RuntimeException("fail to store csv data: " + e.message)
//        }

        try {
            val value: Any = csvToTutorials(file.inputStream,languageFlag)
            if(value is String){
                return value
            }else{
                val fees: List<FeeList> = value as List<FeeList>
                repository?.saveAll(fees)
                return true
            }
        } catch (e: IOException) {
            throw RuntimeException("fail to store csv data: " + e.message)
        }
    }

    /**
     * 輸出入金額取得処理
     */
    fun getFeeList(): List<FeeList>? {
        try {
            return repository?.getFeeList()

        } catch (e: IOException) {
            throw RuntimeException("fail to fetch feeList: " + e.message)
        }
    }


    fun getA(): InputStreamResource? {
        val pdfOutputFile = FileOutputStream("./sample1.pdf")

        val myPDFDoc = Document()

        // 1) Define a string
        val title = "Learning OpenPDF with Kotlin"

        // 2) Define a multi line string using | character for margin
        // so it can be removed using trimMargin()
        val lorenIpsum1 = """Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
    |ligula eget dolor. Aenean massa.
    |Cum sociis natoque penatibus et magnis dis
    |parturient montes, nascetur ridiculus mus.

    |Donec quam felis, ultricies nec, pellentesque eu,
    |pretium quis, sem. Nulla consequat massa quis enim.
    |Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.
    |In enim justo, rhoncus ut, imperdiet a, venenatis vitae,
    |justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.
    |Cras dapibus. Vivamus elementum semper nisi.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus."""
            .trimMargin() // removed the margin on occurrences for the char "|"

        val pdfWriter = PdfWriter.getInstance(myPDFDoc, pdfOutputFile)

        myPDFDoc.apply {

            addTitle("This is a simple PDF example")
            addSubject("This is a tutorial explaining how to use openPDF")
            addKeywords("Kotlin, OpenPDF, Basic sample")
            addCreator("Miguel and Kesizo.com")
            addAuthor("Miguel Doctor")

            open()
            // 3) Create a Font object with the faimily, size, style and color
            // to apply on the title
            val titleFont = Font(Font.COURIER, 20f, Font.BOLDITALIC, Color.BLUE)
            add(
                Paragraph(title, titleFont).apply {
                    // 4) Element class provides properties to align
                    // Content elements within the document
                    setAlignment(Element.ALIGN_CENTER)
                })

            // 5) Adding an empty line
            add(Paragraph(Chunk.NEWLINE))

            // 6) Include the text as content of the pdf
            add(Paragraph(lorenIpsum1))
            close()
        }

        pdfWriter.close() // close the File writer


        val res = getYamlPath()
        print(res)

        val file = File(res)
        val headers = HttpHeaders()
        headers.add("content-disposition", "inline;filename=sample1.pdf")


        return InputStreamResource(FileInputStream(file))
    }
    fun getB(): InputStreamResource? {
        val pdfOutputFile = FileOutputStream("./sample1.pdf")

        val myPDFDoc = Document()

        // 1) Define a string
        val title = "Learning OpenPDF with Kotlin"

        // 2) Define a multi line string using | character for margin
        // so it can be removed using trimMargin()
        val lorenIpsum1 = """Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
    |ligula eget dolor. Aenean massa.
    |Cum sociis natoque penatibus et magnis dis
    |parturient montes, nascetur ridiculus mus.

    |Donec quam felis, ultricies nec, pellentesque eu,
    |pretium quis, sem. Nulla consequat massa quis enim.
    |Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.
    |In enim justo, rhoncus ut, imperdiet a, venenatis vitae,
    |justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.
    |Cras dapibus. Vivamus elementum semper nisi.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus.
    |Aenean vulputate eleifend tellus."""
            .trimMargin() // removed the margin on occurrences for the char "|"

        val pdfWriter = PdfWriter.getInstance(myPDFDoc, pdfOutputFile)

        myPDFDoc.apply {

            addTitle("This is a simple PDF example")
            addSubject("This is a tutorial explaining how to use openPDF")
            addKeywords("Kotlin, OpenPDF, Basic sample")
            addCreator("Miguel and Kesizo.com")
            addAuthor("Miguel Doctor")

            open()
            // 3) Create a Font object with the faimily, size, style and color
            // to apply on the title
            val titleFont = Font(Font.COURIER, 20f, Font.BOLDITALIC, Color.BLUE)
            add(
                Paragraph(title, titleFont).apply {
                    // 4) Element class provides properties to align
                    // Content elements within the document
                    setAlignment(Element.ALIGN_CENTER)
                })

            // 5) Adding an empty line
            add(Paragraph(Chunk.NEWLINE))

            // 6) Include the text as content of the pdf
            add(Paragraph(lorenIpsum1))
            close()
        }

        pdfWriter.close() // close the File writer


        val res = getYamlPath()
        print(res)

        val file = File(res)
        val headers = HttpHeaders()
        headers.add("content-disposition", "inline;filename=sample1.pdf")


        return InputStreamResource(FileInputStream(file))
    }


    @Throws(UnsupportedEncodingException::class)
    private fun getYamlPath(): String? {
        val projectPath = System.getProperty("user.dir")
        val decodedPath = URLDecoder.decode(projectPath, "UTF-8")

        //Get all properies
        val propertySources = env!!.propertySources
        var result: String? = null
        for (source in propertySources) {
            val sourceName = source.name

            //If configuration loaded we can find properties in environment with name like
            //"Config resource '...[absolute or relative path]' via ... 'path'"
            //If path not in classpath -> take path in brackets [] and build absolute path
            if (sourceName.contains("Config resource 'file") && !sourceName.contains("classpath")) {
                val filePath = sourceName.substring(sourceName.indexOf("[") + 1, sourceName.indexOf("]"))
                result = if (Paths.get(filePath).isAbsolute()) {
                    filePath
                } else {
                    decodedPath + File.separator.toString() + filePath
                }
                break
            }
        }

        //If configuration not loaded - return default path
        return result ?: decodedPath + File.separator.toString() + YAML_NAME
    }
}