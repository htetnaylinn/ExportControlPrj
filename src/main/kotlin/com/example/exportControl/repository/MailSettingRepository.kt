package com.example.exportControl.repository

import com.example.exportControl.model.Company
import com.example.exportControl.model.Country
import com.example.exportControl.model.MailHistory
import com.example.exportControl.model.MailSetting
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface MailSettingRepository : CrudRepository<MailSetting, String> {

    @Query(
        value = "select user_code from m_mail ",
        nativeQuery = true
    )
    fun selectAllUserCode(): List<String>?

    @Query(
        value = "select * from m_mail as m join m_user as u on m.user_code = u.user_code where u.company_code = :companyCode  ",
        nativeQuery = true
    )
    fun selectAllRecords(@Param("companyCode")companyCode:String?): List<MailSetting>?

    @Query(
        value = "select * from m_mail where user_code = :userCode ",
        nativeQuery = true
    )
    fun selectRecordsByUserCode(@Param("userCode")userCode:String?): MailSetting?
}