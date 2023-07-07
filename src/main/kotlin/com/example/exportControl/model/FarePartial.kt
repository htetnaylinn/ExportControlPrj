package com.example.exportControl.model

import org.springframework.beans.factory.annotation.Value

interface FarePartial {
    @Value("#{target.fare_code}")
    fun getFareCode(): String

    @Value("#{target.order_list}")
    fun getOrderList(): String

    @Value("#{target.fare_title}")
    fun getFareTitle(): String

    @Value("#{target.fare_content}")
    fun getFareContent(): String

    @Value("#{target.fare_file_name}")
    fun getFareFileName(): String
}