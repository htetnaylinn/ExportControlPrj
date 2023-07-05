package com.example.exportControl.messagingstompwebsocket

class Notification {

    var invoiceCode:String? = null
        private set

    constructor() {}
    constructor(invoiceCode: String?) {
        this.invoiceCode = invoiceCode
    }


}