package com.example.exportControl.repository

import com.example.exportControl.model.Company
import com.example.exportControl.model.requestModel.CompanyRequestModel
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface CompanyMasterRepository : CrudRepository<Company, String> {

    @Query(value = "select * from m_company where delete_flag = 0 order by company_code ", nativeQuery = true)
    fun selectAllCompany(): List<Company>?

    @Query(value = "select company_code from m_company where delete_flag = 0 order by company_code ", nativeQuery = true)
    fun companyCodeDeleteFlag0(): List<String>?

    @Query(value = "select company_code from m_company where delete_flag = 1 order by company_code ", nativeQuery = true)
    fun companyCodeDeleteFlag1(): List<String>?

    @Query(value = "select company_name_jpn ||'-'|| country_name||'-'|| company_code from m_company where delete_flag = 0 order by company_code ", nativeQuery = true)
    fun companyNameJpDeleteFlag0(): List<String>?

    @Query(value = "select company_name_eng ||'-'|| country_name||'-'|| company_code from m_company where delete_flag = 0 order by company_code ", nativeQuery = true)
    fun companyNameEnDeleteFlag0(): List<String>?

    @Query(value = "select * from m_company where company_code =:companyCode and delete_flag = 0",
            nativeQuery = true)
    fun selectCompanyByCompanyCode(@Param("companyCode") companyCode: String?): Company?

    @Modifying
    @Transactional
    @Query(
            value = "update m_company " +
                    "set delete_flag = 1, " +
                    "updated_dt = :currentTime " +
                    "where company_code=:companyCode",
            nativeQuery = true
    )
    fun deleteCompanyMaster(@Param("companyCode") companyCode: String?,
                            @Param("currentTime") currentTime: Timestamp?)

    @Modifying
    @Transactional
    @Query(
            value = "update m_company " +
                    "set delete_flag = 0, " +
                    "country_name = :countryName, " +
                    "company_name_jpn =:companyNameJp, " +
                    "company_name_eng =:companyNameEng, " +
                    "updated_dt = :currentTime " +
                    "where company_code=:companyCode",
            nativeQuery = true
    )
    fun updateCompanyMaster(@Param("companyCode") companyCode: String?,
                            @Param("companyNameJp") companyNameJp: String?,
                            @Param("companyNameEng") companyNameEng: String?,
                            @Param("countryName") countryName: String?,
                            @Param("currentTime") currentTime: Timestamp?)


    @Query(
            value ="""select * from m_company where 
                  (:#{#companyReq.companyCode}='' or company_code=:#{#companyReq.companyCode}) and 
                  (:#{#companyReq.companyNameJpn}='' or company_name_jpn=:#{#companyReq.companyNameJpn} or company_name_eng=:#{#companyReq.companyNameJpn}) 
                  and delete_flag = 0 order by company_code""",
            nativeQuery = true)
    fun searchCompany(@Param("companyReq") companyReq: CompanyRequestModel?): List<Company>

//    @Query(value = "select company_name_jpn ,company_code from m_company where delete_flag = 0 order by company_code ", nativeQuery = true)
//    fun selectAllCompanyJpn(): List<Any>?
//
//    @Query(value = "select company_name_eng ,company_code from m_company where delete_flag = 0 order by company_code ", nativeQuery = true)
//    fun selectAllCompanyEng(): List<Company>?

    @Query(
        value = "select case when :lanId=0 then  company_name_jpn else company_name_eng end from m_company where company_code=:companyCode",
        nativeQuery = true
    )
    fun selectCompanyNameByCode(@Param("companyCode") companyCode: String?,@Param("lanId") lanId: Int?):String?

    @Query(value = "select * from m_company where delete_flag = 0 and company_code!=:companyCode order by company_code ", nativeQuery = true)
    fun selectCompanyWithCode(@Param("companyCode") companyCode: String?): List<Company>?
}