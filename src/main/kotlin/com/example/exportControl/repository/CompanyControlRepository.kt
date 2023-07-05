package com.example.exportControl.repository

import com.example.exportControl.model.CompanyControl
import com.example.exportControl.model.UserControl
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param


interface CompanyControlRepository : CrudRepository<CompanyControl, String> {

    @Query(value = "select * from company_control where company_code=:companyCode", nativeQuery = true)
    fun selectAllByCompanyCode(@Param("companyCode") companyCode: String?): CompanyControl

}