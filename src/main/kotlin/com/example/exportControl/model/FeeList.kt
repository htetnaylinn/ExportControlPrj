package com.example.exportControl.model

import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity
@IdClass(FeeListSerializable::class)
@Table(name = "export_import_fee")
class FeeList{

    @Id
    @Column(name = "fee_date")
    var feeDate : Date? = null

    @Id
    @Column(name = "opt")
    var opt : String? = null

    @Id
    @Column(name = "start_route")
    var startRoute : String? = null

    @Id
    @Column(name = "end_route")
    var endRoute : String? = null

    @Column(name = "thirty_ton")
    var thirtyTon : String? = null

    @Column(name = "sixty_ton")
    var sixtyTon : String? = null

    @Column(name = "ninety_ton")
    var ninetyTon : String? = null

    @Column(name = "hundred_twenty_ton")
    var hundredTwentyTon : String? = null

    @Column(name = "hundred_thirty_ton")
    var hundredThirtyTon: String? = null

    @Column(name = "hundred_sixty_ton")
    var hundredSixtyTon: String? = null

    @Column(name = "hundred_ninety_ton")
    var  hundredNinetyTon: String? = null

    @Column(name = "two_hundred_twenty_ton")
    var  twoHundredTwentyTon: String? = null

    @Column(name = "two_hundred_fifty_ton")
    var  twoHundredFiftyTon: String? = null

    @Column(name = "two_hundred_eighty_ton")
    var  twoHundredEightyTon: String? = null

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
        feeDate: Date?,
        opt: String?,
        startRoute: String?,
        endRoute: String?,
        thirtyTon: String?,
        sixtyTon: String?,
        ninetyTon: String?,
        hundredTwentyTon: String?,
        hundredThirtyTon: String?,
        hundredSixtyTon: String?,
        hundredNinetyTon: String?,
        twoHundredTwentyTon: String?,
        twoHundredFiftyTon: String?,
        twoHundredEightyTon: String?,
//        createdBy: String?,
//        createdDt: Timestamp?,
//        updatedBy: String?,
//        updatedDt: Timestamp?
    ) {
        this.feeDate = feeDate
        this.opt = opt
        this.startRoute = startRoute
        this.endRoute = endRoute
        this.thirtyTon = thirtyTon
        this.sixtyTon = sixtyTon
        this.ninetyTon = ninetyTon
        this.hundredTwentyTon = hundredTwentyTon
        this.hundredThirtyTon = hundredThirtyTon
        this.hundredSixtyTon = hundredSixtyTon
        this.hundredNinetyTon = hundredNinetyTon
        this.twoHundredTwentyTon = twoHundredTwentyTon
        this.twoHundredFiftyTon = twoHundredFiftyTon
        this.twoHundredEightyTon = twoHundredEightyTon
//        this.createdBy = createdBy
//        this.createdDt = createdDt
//        this.updatedBy = updatedBy
//        this.updatedDt = updatedDt
    }
}
