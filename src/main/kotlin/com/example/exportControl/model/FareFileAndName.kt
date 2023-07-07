package com.example.exportControl.model

import org.springframework.beans.factory.annotation.Value

interface FareFileAndName {


    @Value("#{target.fare_file}")
    fun getFareFile(): ByteArray

    @Value("#{target.fare_file_name}")
    fun getFareFileName(): String
}