package com.example.exportControl.message

import com.example.exportControl.model.PatternRegistration

class Response {
    var status: String? = null
//    var messageList: MutableList<String>? = null
//    var patternObj : PatternRegistration? = null
//    var patternList: List<PatternRegistration>? = null
    var data: Any? = null

//    constructor() {}
//    constructor(status: String?, messageList: MutableList<String>?) {
//        this.status = status
//        this.messageList = messageList
//    }
//
//    constructor(status: String?, patternObj: PatternRegistration?) {
//        this.status = status
//        this.patternObj = patternObj
//    }

    constructor()

    constructor(status: String?,  data: Any?) {
        this.status = status
        this.data = data
    }



}