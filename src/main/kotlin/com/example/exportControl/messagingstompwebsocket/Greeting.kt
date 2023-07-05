package com.example.exportControl.messagingstompwebsocket

class Greeting {
    var content: String? = null
    var imageUrl: String? = null
    var reactMessageId: String? = null
    var userCode:String? = null
    var reactCount:Int? = null
    var sendImg:Boolean? = false
    var sendText:Boolean? = false
    var loginedUserCode:String? = null
    var invoiceCode:String? = null
        private set

    constructor() {}
    constructor(
        content: String?,
        imageUrl: String?,
        reactMessageId: String?,
        userCode: String?,
        reactCount: Int?,
        sendImg: Boolean?,
        sendText: Boolean?,
        loginedUserCode: String?,
        invoiceCode: String?
    ) {
        this.content = content
        this.imageUrl = imageUrl
        this.reactMessageId = reactMessageId
        this.userCode = userCode
        this.reactCount = reactCount
        this.sendImg = sendImg
        this.sendText = sendText
        this.loginedUserCode = loginedUserCode
        this.invoiceCode = invoiceCode
    }


}