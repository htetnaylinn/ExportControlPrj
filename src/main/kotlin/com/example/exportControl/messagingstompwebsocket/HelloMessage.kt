package com.example.exportControl.messagingstompwebsocket


class HelloMessage {
    var name: String? = null
    var image: String? = null
    var reactMessageId:String? = null
    var userCode:String? = null
    var reactionStatus:String? = null
    constructor() {}
    constructor(name: String?, image: String?, reactMessageId: String?, userCode: String?, reactionStatus: String?) {
        this.name = name
        this.image = image
        this.reactMessageId = reactMessageId
        this.userCode = userCode
        this.reactionStatus = reactionStatus
    }


}