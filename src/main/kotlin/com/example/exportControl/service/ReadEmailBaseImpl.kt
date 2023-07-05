package com.example.exportControl.service

import com.example.exportControl.model.requestModel.MailMessage
import com.sun.mail.util.BASE64DecoderStream
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.thymeleaf.spring5.SpringTemplateEngine
import java.io.*
import java.text.CharacterIterator
import java.text.StringCharacterIterator
import java.util.*
import javax.activation.DataHandler
import javax.activation.DataSource
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import javax.mail.search.FromTerm
import javax.mail.search.SearchTerm
import javax.mail.util.ByteArrayDataSource
import javax.servlet.http.HttpServletResponse


@Service
class ReadEmailBaseImpl {

    @Autowired
    private val userService: UserService? = null

    var allParts: MutableList<BodyPart> = ArrayList()

//    protected val USERNAME = "honda-fm7"
//    protected val PASSWORD = "mNa2rdhU7ye$"

    var msgInboxList: MutableList<MailMessage>? = null
    var msgReplyList: MutableList<MailMessage>? = null

    @Autowired
    lateinit var templateEngine: SpringTemplateEngine

    @Throws(MessagingException::class, IOException::class)
    fun process(PROTOCOL: String?, HOST: String?,USERNAME:String?,PASSWORD:String?, props: Properties?,companyCode:String?,userCode:String?,response: HttpServletResponse,unzipMail:String?,selectedSearchMailList:List<String?>){
        msgInboxList =  mutableListOf<MailMessage>()
        msgReplyList =  mutableListOf<MailMessage>()


        val messageList: List<Message>? = null

        val session: Session = Session.getInstance(props)

        // Get the store provider and connect to the store.
        val store: Store = session.getStore(PROTOCOL)
        store.connect(HOST, USERNAME, PASSWORD)

        // Get folder and open the INBOX folder in the store.
        val inbox: Folder = store.getFolder("Inbox")
        inbox.open(Folder.READ_ONLY)

        // declare Array msgList
        var inboxMessages: Array<Message> = emptyArray()
        var outboxMessages: Array<Message> = emptyArray()

        val filteredMails: MutableList<Message> = mutableListOf()
        val autoMail:SearchTerm = FromTerm(InternetAddress(unzipMail))
        val allAutoMails = inbox.search(autoMail)

        // Get userLst with same CompanyCode
        val userLst = userService?.searchUserWithCompanyCode(companyCode)

        // outboxMessage
        for (user in userLst!!) {
            val sender: SearchTerm = FromTerm(InternetAddress(user.emailAddress))
            if(user.userId == userCode) {outboxMessages = inbox.search(sender)}
        }
        // looping
        if(selectedSearchMailList.isEmpty()){

            // inboxMessage
            for (user in userLst!!) {
                val sender: SearchTerm = FromTerm(InternetAddress(user.emailAddress))
                inboxMessages += inbox.search(sender)
            }
            // inboxPasswordMessage
            for (user in userLst) {
                val filtered = allAutoMails.filter { mail ->
                    mail.content.toString().contains(user.emailAddress.toString())
                }
                filteredMails.addAll(filtered)
            }
        }else{
            // inboxMessage
            for (selectedMail in selectedSearchMailList!!) {
                val sender: SearchTerm = FromTerm(InternetAddress(selectedMail))
                inboxMessages += inbox.search(sender)
            }
            // inboxPasswordMessage
            for (selectedMail in selectedSearchMailList) {
                val filtered = allAutoMails.filter { mail ->
                    mail.content.toString().contains(selectedMail.toString())
                }
                filteredMails.addAll(filtered)
            }
        }


        inboxMessages += filteredMails
        if(inboxMessages.isNotEmpty()) {
            inboxMessages = inboxMessages.sortedBy{it.sentDate}.toTypedArray()

        }

        println("INBOX$inboxMessages")

        for (message in inboxMessages) {
            //message.setFlag(Flags.Flag.SEEN, true);  // todo: put back
            if (message != null) {
                val mail =MailMessage()
                val content: MailMessage = getTextFromMessage(message,response)
                this.msgInboxList!!.add(content)
            }
        }

        for (message in outboxMessages) {
            //message.setFlag(Flags.Flag.SEEN, true);  // todo: put back
            if (message != null) {
                val mail =MailMessage()
                val content: MailMessage = getTextFromMessage(message,response)
                msgReplyList!!.add(content)
            }
        }
        if(msgInboxList!!.isNotEmpty()) {
            msgInboxList = msgInboxList!!.reversed() as MutableList<MailMessage>
        }

//        msgReplyList = msgReplyList!!.reversed() as MutableList<MailMessage>
        // Close folder and close store.
        inbox.close(true)
        store.close()
    }

    @Throws(MessagingException::class, IOException::class)
    private fun getTextFromMessage(message: Message,response: HttpServletResponse): MailMessage {
        var result = ""
        var mailMessage = MailMessage()
        mailMessage.mailSubject = message.subject
        mailMessage.mailReceivedDate = message.sentDate
        mailMessage.mailId = message.messageNumber
//        mailMessage.mailSender = "may-thazin@honda-logi.com" // todo: put back
        val fromAddresses = arrayOf(InternetAddress(message.from[0].toString()))
        mailMessage.mailSender = fromAddresses[0].address// todo: put back

        val toAddresses: MutableList<String> = ArrayList()
        val recipients: Array<out String>? = message.getHeader("Delivered-To")
        if (message.isMimeType("text/plain")) {
            result = message.content.toString()
        } else if (message.isMimeType("multipart/*")) {
            val mimeMultipart = message.content as MimeMultipart
            result = getTextFromMimeMultipart(mimeMultipart)
            mailMessage = writePart(message,response)!!
        }

        mailMessage.mailBody = result
        mailMessage.mailTo = toAddresses.toString()
        return mailMessage
    }

    @Throws(MessagingException::class, IOException::class)
    private fun getTextFromMimeMultipart(
        mimeMultipart: MimeMultipart
    ): String {
        var result = ""
        val count = mimeMultipart.count
        for (i in 0 until count) {
            val bodyPart = mimeMultipart.getBodyPart(i)
            if (bodyPart.isMimeType("text/plain")) {
                result = """
                $result
                ${bodyPart.content}
                """.trimIndent()
                break // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                val html = bodyPart.content as String
                result = """
                $result
                ${Jsoup.parse(html).text()}
                """.trimIndent()
            } else if (bodyPart.content is MimeMultipart) {
                result += getTextFromMimeMultipart(bodyPart.content as MimeMultipart)
            }
//                getAttachments(message)
        }
        return result
    }

//    }

    @Throws(MessagingException::class, IOException::class)
    fun sendProcess(PROTOCOL: String?, HOST: String?, props: Properties?,email: MailMessage,files: List<MultipartFile>){

        val session = Session.getInstance(props)
        val message: MimeMessage = MimeMessage(session)

        message.setFrom(InternetAddress(email.mailSender))
        message.addRecipient(Message.RecipientType.BCC,InternetAddress(email.mailSender))

        message.addRecipient(Message.RecipientType.TO, InternetAddress(email.mailAdd))

        message.subject = email.mailSubject

        val sb = StringBuilder()
        sb.append(email.mailReply).appendLine().appendLine()
            .appendLine()
            .append("-----Original Message-----").appendLine().
            append("From: ").append(email.mailAdd).appendLine().
            append("To: ").append(email.mailSender).appendLine().
            append("Subject: ").append(email.mailSubject).appendLine().
            append(email.mailBody)
        val originalMessage = sb.toString()

        message.setText(originalMessage)
        message.disposition = Part.INLINE

        if(checkFilesSize(files)){
            val multipart: Multipart = MimeMultipart()

            files.forEachIndexed { _, multipartFile ->
                var messageBodyPart: BodyPart = MimeBodyPart()
                messageBodyPart.setText(originalMessage)
                multipart.addBodyPart(messageBodyPart)
                messageBodyPart = MimeBodyPart()
                val ds: DataSource = ByteArrayDataSource(multipartFile.bytes,multipartFile.contentType)
                messageBodyPart.setDataHandler(DataHandler(ds))
                messageBodyPart.setFileName(multipartFile.originalFilename)
                multipart.addBodyPart(messageBodyPart)
            }
            message.setContent(multipart)
            message.disposition = Part.ATTACHMENT
        }
        println("sending...")

        Transport.send(message)

    }

    @Throws(Exception::class)
    fun writePart(p: Part,response: HttpServletResponse) : MailMessage? {
        val result: String?= "----------------------------"
        var mailMessage: MailMessage? = null
        if (p is Message) //Call methos writeEnvelope
            mailMessage = writeEnvelope(p)
        println("----------------------------")
        System.out.println("CONTENT-TYPE: " + p.contentType)

        //check if the content is plain text
        if (p.isMimeType("text/plain")) {
            println("This is plain text")
            println("---------------------------")
            println(p.content as String)
        } else if (p.isMimeType("multipart/*")) {
            println("This is a Multipart")
            println("---------------------------")
            val mp = p.content as Multipart
            val count = mp.count
            for (i in 0 until count) {
                writePart(mp.getBodyPart(i),response)
                val part= mp.getBodyPart(i)
                if (Part.INLINE.equals(part.disposition, ignoreCase = true) || Part.ATTACHMENT.equals(part.disposition, ignoreCase = true)) {
                    // this part is attachment

                    part.fileName = isMimeEncoding(part.fileName)
                    val fileName = part.fileName
                    mailMessage?.docList?.add(fileName)
//                    =?utf-8?B?MjAyMzAxMTNfw6vCtcO74pWpw4nilozDrnYueGxzeA==?=
                    allParts.add(part)

                    println(fileName)
                } else {
                    // this part may be the message content
//                    messageContent = part.content.toString()
                }
            }
        } else if (p.isMimeType("message/rfc822")) {
            println("This is a Nested Message")
            println("---------------------------")
            writePart(p.content as Message,response)
        }
        return mailMessage
    }
    @Throws(java.lang.Exception::class)
    fun writeEnvelope(m: Message) :MailMessage{
        println("This is the message envelope")
        println("---------------------------")
        var a: Array<Address>

        //var fromList: String = "---------------------------"
        var toList: MutableList<String> = mutableListOf()
        var fromList: MutableList<String> = mutableListOf()

        var mailMessage = MailMessage()

        // FROM
        if (m.from.also { a = it } != null) {
            for (j in a.indices) {
                //val valueSt = a[j].toString()
                val valueSt = cutEmailString(a[j].toString())
                //val tokens: List<String> = a[j].toString().split("[\\<>]")
                fromList.add(valueSt)
                System.out.println("FROM: " + a[j].toString())

            }
        }
        mailMessage.mailSender = fromList.joinToString(separator = ",")
        // TO
        if (m.getRecipients(Message.RecipientType.TO).also { a = it } != null) {
            for (j in 0 until a.size) {
                var toAddressSt = cutEmailString(a[j].toString())
                toList.add(toAddressSt)
                System.out.println("TO: " + a[j].toString())
            }
        }

        mailMessage.mailAdd = toList.joinToString(separator = ",")


        // SUBJECT
        if (m.subject != null) System.out.println("SUBJECT: " + m.subject)

        mailMessage.mailSubject = m.subject
        mailMessage.mailReceivedDate = m.sentDate
        mailMessage.mailId = m.messageNumber
        return mailMessage
    }

    fun cutEmailString(email: String): String {
        if (!email.contains("<")){
            return email
        }else{
            return email.substring(email.indexOf("<")+1 , email.indexOf(">"))
        }
    }

    fun checkFilesSize(files:List<MultipartFile>?):Boolean{
        var validFlag:Boolean = true
        var size:Double? = 0.0
        if (files != null) {
            files.forEach{
                var sizeInMB = humanReadableByteCountBin(it.size)
                size = size!! + sizeInMB!!
            }
        }
        if(size == 0.0){
            validFlag = false
        }
        return validFlag
    }

    fun humanReadableByteCountBin(bytes: Long): Double? {
        val absB = if (bytes == Long.MIN_VALUE) Long.MAX_VALUE else Math.abs(bytes)
        if (absB < 1024) {
            return bytes.toDouble()
        }
        var value = absB
        val ci: CharacterIterator = StringCharacterIterator("KMGTPE")
        var i = 40
        while (i >= 0 && absB > 0xfffccccccccccccL shr i) {
            value = value shr 10
            ci.next()
            i -= 10
        }
        value *= java.lang.Long.signum(bytes).toLong()
        return java.lang.String.format("%.1f", value / 1024.0).toDoubleOrNull()
    }

    fun isMimeEncoding(name:String):String?{
       var fileName:String? = ""
       var charSetName = ""
        if(name.contains("?B?")){
            var sp = name.split("?=")
            sp.forEach{s ->
                if(s != ""){
                    var temp = s.split("?B?")
                    var cs = temp[0]
                    charSetName = cs.split("=?")[1]
                    val sourceBytes: ByteArray = Base64.getDecoder().decode(temp[1])
                    fileName += String(sourceBytes, charset(charSetName))
                }
            }
        }else{
            fileName = name
        }
        return fileName
    }
}