package com.example.exportControl.model.requestModel

import java.sql.Timestamp

class ProductRequestModel{
    var productCode : String? = null
    var productName : String? = null
    var createdBy : String? = null
    var createdDt : Timestamp? = null
    var updatedBy : String? = null
    var updatedDt : Timestamp? = null
    var deleteFlag : Int? = 0
}

