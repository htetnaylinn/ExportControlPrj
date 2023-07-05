package com.example.exportControl.message

import com.example.exportControl.model.PatternRegistration

class DocumentResponse {

var fileName: String? = null
var url: String? = null

    constructor(fileName: String?, url: String?) {
        this.fileName = fileName
        this.url = url
    }
}