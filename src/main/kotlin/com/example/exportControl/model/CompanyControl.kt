package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "company_control")
class CompanyControl{

    @Id
    @Column(name = "company_code")
    var companyCode : String? = null

    @Column(name = "product_name_flg")
    var productNameFlag : Int? = null

    @Column(name = "loading_port_flg")
    var loadingPortFlag : Int? = null

    @Column(name = "unloading_land_flg")
    var unloadingLandFlag : Int? = null

    @Column(name = "transport_mode_flg")
    var transportModeFlag : Int? = null

    @Column(name = "weight_kg_flg")
    var weightKgFlag : Int? = null

    @Column(name = "pick_up_flg")
    var pickUpFlag : Int? = null

    constructor()
    constructor(
        companyCode: String?,
        productNameFlag: Int?,
        loadingPortFlag: Int?,
        unloadingLandFlag: Int?,
        transportModeFlag: Int?,
        weightKgFlag: Int?,
        pickUpFlag: Int?
    ) {
        this.companyCode = companyCode
        this.productNameFlag = productNameFlag
        this.loadingPortFlag = loadingPortFlag
        this.unloadingLandFlag = unloadingLandFlag
        this.transportModeFlag = transportModeFlag
        this.weightKgFlag = weightKgFlag
        this.pickUpFlag = pickUpFlag
    }
}
