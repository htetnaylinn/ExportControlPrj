package com.example.exportControl.repository

import com.example.exportControl.model.Company
import com.example.exportControl.model.Country
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface CountryMasterRepository : CrudRepository<Country, String> {

    @Query(value = "select * from m_country where delete_flag = 0 order by country_code ", nativeQuery = true)
    fun selectAllCountry(): List<Country>?

    @Query(value = "select * from m_country where (:countryName='' or country_name=:countryName) and delete_flag = 0 order by country_code ", nativeQuery = true)
    fun searchCountryName(@Param("countryName") countryName: String?): List<Country>

    @Query(value = "select country_code from m_country where delete_flag = 0 order by country_code ", nativeQuery = true)
    fun countryCodeDeleteFlag0(): List<String>?

    @Query(value = "select country_code from m_country where delete_flag = 1 order by country_code ", nativeQuery = true)
    fun countryCodeDeleteFlag1(): List<String>?

    @Modifying
    @Transactional
    @Query(
        value = "update m_country " +
                "set delete_flag = 1, " +
                "updated_dt = :currentTime, " +
                "updated_by = :userName " +
                "where country_code=:countryCode",
        nativeQuery = true
    )
    fun deleteCountryMaster(@Param("countryCode") countryCode: String?,
                            @Param("currentTime") currentTime: Timestamp?,
                            @Param("userName") userName: String?)

    @Modifying
    @Transactional
    @Query(
        value = "update m_country " +
                "set delete_flag = 0, " +
                "country_name =:countryName, " +
                "updated_dt = :currentTime, " +
                "updated_by = :userName " +
                "where country_code=:countryCode",
        nativeQuery = true
    )
    fun updateCountryMaster(@Param("countryCode") countryCode: String?,
                            @Param("countryName") countryName: String?,
                            @Param("currentTime") currentTime: Timestamp?,
                            @Param("userName") userName: String?)

    @Query(value = "select country_name from m_country where delete_flag = 0 order by country_code ", nativeQuery = true)
    fun countryNameDeleteFlag0(): List<String>?

}