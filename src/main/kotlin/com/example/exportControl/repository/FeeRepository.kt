package com.example.exportControl.repository

import com.example.exportControl.model.FeeList
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface FeeRepository  : CrudRepository<FeeList, String> {

    @Query(value = "SELECT * FROM export_import_fee WHERE fee_date = (SELECT max(fee_date) FROM export_import_fee) ", nativeQuery = true)
    fun getFeeList(): List<FeeList>?
}