package com.example.exportControl.model

class FeeListCSV{

    var feeDate : String? = null
    var startRoute : String? = null
    var endRoute : String? = null
    var thirtyTon : String? = null
    var sixtyTon : String? = null
    var ninetyTon : String? = null
    var hundredTwentyTon : String? = null
    var hundredThirtyTon: String? = null
    var hundredSixtyTon: String? = null
    var hundredNinetyTon: String? = null
    var twoHundredTwentyTon: String? = null
    var twoHundredFiftyTon: String? = null
    var twoHundredEightyTon: String? = null

    constructor(
        feeDate: String?,
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
        twoHundredEightyTon: String?

    ) {
        this.feeDate = feeDate
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

    }
}
