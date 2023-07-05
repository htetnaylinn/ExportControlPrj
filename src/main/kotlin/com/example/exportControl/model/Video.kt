package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "m_video")
class Video {
    @Id
    @Column(name = "video_code")
    var videoCode : String? = null

    @Column(name = "order_list")
    var orderList : String? = null

    @Column(name = "video_title")
    var videoTitle : String? = null

    @Column(name = "video_content")
    var videoContent : String? = null

    @Column(name = "video_link")
    var videoLink : String? = null

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
        videoCode: String?,
        orderList: String?,
        videoTitle: String?,
        videoContent: String?,
        videoLink: String?,
        deleteFlag: Int?,
        createdBy: String?,
        createdDt: Timestamp?,
        updatedBy: String?,
        updatedDt: Timestamp?
    ) {
        this.videoCode = videoCode
        this.orderList = orderList
        this.videoTitle = videoTitle
        this.videoContent = videoContent
        this.videoLink = videoLink
        this.deleteFlag = deleteFlag
        this.createdBy = createdBy
        this.createdDt = createdDt
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
    }


}