package com.example.exportControl.repository

import com.example.exportControl.model.Muser
import com.example.exportControl.model.Product
import com.example.exportControl.model.UserControl
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Date
import java.sql.Timestamp

interface UserMasterRepository  : CrudRepository<Muser, String> {

    @Query(value = "select * from m_user where company_code = :companyCode and delete_flag = 0 order by user_code", nativeQuery = true)
    fun getallUsers(@Param("companyCode") companyCode: String?): List<Muser>

    @Query(value = "select * from m_user where user_code =:userCode and delete_flag = '0' and ((Date(start_date) <= Date(Now()) and Date(end_date)>=Date(Now())) or (end_date is null))  ", nativeQuery = true)
    fun selectUserByUserCode(@Param("userCode") userCode: String?): Muser?

    @Query(value = "select c.company_code from m_user as u join m_company as c on u.company_code = c.company_code where u.user_code =:userCode and c.delete_flag = '0'   ", nativeQuery = true)
    fun selectCompanyNameUserCode(@Param("userCode") userCode: String?): String?

    @Query(value = "select * from m_user where (:employeeNumber='' or user_code=:employeeNumber) and (:employeeName='' or user_name=:employeeName) and company_code = :companyCode and delete_flag = 0 order by user_code ", nativeQuery = true)
    fun searchUserCodeAndName(@Param("companyCode") companyCode: String?,@Param("employeeNumber") employeeNumber: String?,@Param("employeeName") employeeName: String?): List<Muser>

    @Modifying
    @Transactional
    @Query(
        value = "update m_user " +
                "set company_code = :companyCode " +
                "where user_code=:userCode",
        nativeQuery = true
    )
    fun updateCompanyCode(@Param("userCode") userCode: String?,
                          @Param("companyCode") companyCode: String?
    )


    @Modifying
    @Transactional
    @Query(
        value = "update m_user " +
                "set delete_flag = 1 " +
                "where user_code=:userCode",
        nativeQuery = true
    )
    fun deleteUserMaster(@Param("userCode") userCode: String?)

    @Query(value = "select password from m_user where user_code =:userCode and delete_flag = '0' ", nativeQuery = true)
    fun selectUserPasswordById(@Param("userCode") userCode: String?): String


    @Modifying
    @Transactional
    @Query(
        value = "update m_user " +
                "set password = :newPassword " +
                "where user_code=:userCode and delete_flag = '0' ",
        nativeQuery = true
    )
    fun changePasswordById(@Param("userCode") userCode: String?,@Param("newPassword") newPassword: String?)

    @Query(value = "select * from m_user where company_code =:companyCode and delete_flag = '0'", nativeQuery = true)
    fun searchUserWithCompanyCode(@Param("companyCode") companyCode: String?): List<Muser>

    @Query(value = "select * from m_user where company_code =:companyCode and delete_flag = '0' and user_code !=:userCode", nativeQuery = true)
    fun searchUserWithCompanyCodeNotIncludedSingedUser(@Param("companyCode") companyCode: String? , @Param("userCode") userCode: String?): List<Muser>


    @Modifying
    @Transactional
    @Query(
        value = "update m_user " +
                "set company_code = :companyCode, user_name = :userName, role = :role,start_date = :startDate," +
                "end_date = (CASE WHEN :endDate <> '' THEN cast(:endDate as Date) ELSE null END),mail_address = :emailAddress,updated_by = :updatedBy,updated_dt = :updatedDt " +
                "where user_code=:userCode",
        nativeQuery = true
    )
    fun updateUserInformation(@Param("userCode") userCode: String?,
                              @Param("userName")userName:String?,
                              @Param("role")role:String?,
                              @Param("startDate")startDate: java.util.Date?,
                              @Param("endDate")endDate:String?,
                              @Param("emailAddress")emailAddress:String?,
                              @Param("companyCode") companyCode: String?,
                              @Param("updatedBy")updatedBy:String?,
                              @Param("updatedDt")updatedDt:Timestamp
    )

    @Query(value = "select user_code from m_user where delete_flag = 0 order by user_code ", nativeQuery = true)
    fun userCodeDeleteFlag0(): List<String>?
}