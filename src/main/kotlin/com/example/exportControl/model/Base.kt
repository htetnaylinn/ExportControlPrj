package com.example.exportControl.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp
import javax.persistence.Column

open class Base{

    @SerializedName("created_by")
    @Column(name = "created_by")
    protected open var createdBy : String? = null

    @Column(name = "created_dt")
    protected var createdDt : Timestamp? = null

    @Column(name = "updated_by")
    protected var updatedBy : String? = null

    @Column(name = "updated_dt")
    protected var updatedDt : Timestamp? = null

}