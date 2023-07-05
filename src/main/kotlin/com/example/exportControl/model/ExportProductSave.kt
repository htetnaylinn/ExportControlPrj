package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.*

@Entity
@IdClass(EvaluatorSerializableExport::class)
@Table(name = "t_export_product_save")
class ExportProductSave {
    @Id
    @Column(name = "invoice_code")
    var invoiceCode : String? = null

    @Id
    @Column(name = "product_code")
    var productCode : String? = null

    @Column(name = "customer_code")
    var customerCode : String? = null

    @Column(name = "quantity")
    var quantity : String? = null

    @Column(name = "loading_port")
    var loadingPort : String? = null

    @Column(name = "unloading_land")
    var unloadingLand : String? = null

    @Column(name = "transport_mode")
    var transportMode : String? = null

    @Column(name = "weight_kg")
    var weightKg : String? = null

    @Column(name = "pick_up")
    var pickUp : String? = null

    @Column(name = "save_date")
    var saveDate : Timestamp? = null

    constructor()
    constructor(
        invoiceCode: String?,
        productCode: String?,
        customerCode: String?,
        quantity: String?,
        loadingPort: String?,
        unloadingLand: String?,
        transportMode: String?,
        weightKg: String?,
        pickUp: String?
    ) {
        this.invoiceCode = invoiceCode
        this.productCode = productCode
        this.customerCode = customerCode
        this.quantity = quantity
        this.loadingPort = loadingPort
        this.unloadingLand = unloadingLand
        this.transportMode = transportMode
        this.weightKg = weightKg
        this.pickUp = pickUp

    }
}