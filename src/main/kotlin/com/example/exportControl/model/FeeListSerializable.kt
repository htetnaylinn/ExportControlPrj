package com.example.exportControl.model

import java.util.*
import java.io.Serializable

class FeeListSerializable : Serializable{

    var feeDate : Date? = null

    var opt : String? = null

    var startRoute : String? = null

    var endRoute : String? = null
}
