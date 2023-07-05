package com.example.exportControl.repository

import com.example.exportControl.model.Company
import com.example.exportControl.model.Port
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface PortMasterRepository : CrudRepository<Port, String> {

    @Query(value = "select * from m_port where delete_flag = 0 order by port_code ", nativeQuery = true)
    fun selectAllPort(): List<Port>?

    @Query(value = "select port_code from m_port where delete_flag = 0 order by port_code ", nativeQuery = true)
    fun portCodeDeleteFlag0(): List<String>?

    @Query(value = "select port_code from m_port where delete_flag = 1 order by port_code ", nativeQuery = true)
    fun portCodeDeleteFlag1(): List<String>?

    @Query(value = "select * from m_port where (:portCode='' or port_code=:portCode) and (:portName='' or port_name=:portName) and (:countryName='' or country_name=:countryName) and delete_flag = 0 order by port_code ", nativeQuery = true)
    fun searchPortCodeAndNameAndCountry(@Param("portCode") portCode: String?,
                                        @Param("portName") portName: String?,
                                        @Param("countryName") countryName: String?): List<Port>

    @Modifying
    @Transactional
    @Query(
        value = "update m_port " +
                "set delete_flag = 1, " +
                "updated_dt = :currentTime " +
                "where port_code=:portCode",
        nativeQuery = true
    )
    fun deletePortMaster(@Param("portCode") portCode: String?,
                            @Param("currentTime") currentTime: Timestamp?)

    @Modifying
    @Transactional
    @Query(
        value = "update m_port " +
                "set delete_flag = 0, " +
                "country_name = :countryName, " +
                "port_name =:portName, " +
                "updated_dt = :currentTime " +
                "where port_code=:portCode",
        nativeQuery = true
    )
    fun updatePortMaster(@Param("portCode") portCode: String?,
                            @Param("portName") portName: String?,
                            @Param("countryName") countryName: String?,
                            @Param("currentTime") currentTime: Timestamp?)

    @Query(value = "select CONCAT(port_name, '-', country_name) from m_port where delete_flag = 0 order by port_code", nativeQuery = true)
    fun portNameDeleteFlag0(): List<String>?

    @Query(
        value = "select case when :lanId=0 then  port_name else port_name end from m_port where port_code=:portCode",
        nativeQuery = true
    )
    fun selectPortNameByCode(@Param("portCode") portCode: String?,@Param("lanId") lanId: Int?):String?
}