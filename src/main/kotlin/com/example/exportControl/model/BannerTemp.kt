package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "m_banner")
class BannerTemp {
    @Id
    @Column(name = "banner_code")
    var bannerCode : String? = null

    @Column(name = "order_list")
    var orderList : String? = null

    @Column(name = "banner_title")
    var bannerTitle : String? = null

    @Column(name = "banner_image_name")
    var bannerImageName : String? = null

    @Column(name = "banner_image")
    var bannerImage : String? = null

    @Column(name = "banner_link")
    var bannerLink : String? = null

    @Column(name = "delete_flag")
    var deleteFlag : Int? = 0

    @Column(name = "created_by")
    var createdBy : String? = null

    @Column(name = "created_dt")
    var createdDt : Timestamp? = null

    @Column(name = "updated_by")
    var updatedBy : String? = null

    @Column(name = "updated_dt")
    var updatedDt : Timestamp? = null


    constructor()
    constructor(
        bannerCode: String?,
        orderList: String?,
        bannerTitle: String?,
        bannerImageName: String?,
        bannerImage: String?,
        bannerLink: String?,
        deleteFlag: Int?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?
    ) {
        this.bannerCode = bannerCode
        this.orderList = orderList
        this.bannerTitle = bannerTitle
        this.bannerImageName = bannerImageName
        this.bannerImage = bannerImage
        this.bannerLink = bannerLink
        this.deleteFlag = deleteFlag
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
    }


}