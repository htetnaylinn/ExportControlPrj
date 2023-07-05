package com.example.exportControl.model.requestModel

import java.io.FileOutputStream
import java.util.*

class MailMessage{
    var mailAdd: String? = null // mail sent Address
    var mailSubject : String? = null
    var mailBody : String? = null
    var mailSender : String? = null // mail received Address
    var mailId : Int? = null
    var mailReceivedDate : Date? = null
    var mailReply : String? = null
    var mailTo : String? = null
    var docList: MutableList<String> = mutableListOf()
    var fileURL: String? = null
//val docList : Array<String> = emptyArray()
//    var docList: ArrayList<String> = ArrayList()
//val docList = listOf<String>()
//var stringList: List<String> = listOf<String>
//var docList = mutableListOf<String>()
}