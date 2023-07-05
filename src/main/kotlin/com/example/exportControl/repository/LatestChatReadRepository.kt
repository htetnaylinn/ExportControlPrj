package com.example.exportControl.repository

import com.example.exportControl.model.Company
import com.example.exportControl.model.Country
import com.example.exportControl.model.LatestChatRead
import com.example.exportControl.model.MailHistory
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface LatestChatReadRepository : CrudRepository<LatestChatRead, String> {

    @Query(
        value = "select id from t_latest_chat_read order by id desc limit 1",
        nativeQuery = true
    )
    fun selectLatestId(): Int?

    @Query(
        value = "select * from t_latest_chat_read where user_code = :userCode and invoice_code = :invoiceCode and send_company_code = :companyCode ",
        nativeQuery = true
    )
    fun getAllRecordByUserCodeAndInvoice(@Param("userCode")userCode:String?,@Param("invoiceCode")invoiceCode:String?,@Param("companyCode")companyCode:String?): List<LatestChatRead>?


    @Modifying
    @Transactional
    @Query(
        value = "update  t_latest_chat_read  set message_id = :messageId where user_code = :userCode and invoice_code = :invoiceCode and send_company_code = :companyCode ",
        nativeQuery = true
    )
    fun updateMessageId(@Param("messageId")messageId:Int?,@Param("userCode")userCode:String?,@Param("invoiceCode")invoiceCode:String?,@Param("companyCode")companyCode:String?)

    @Query(
        value = "select message_id from t_latest_chat_read where user_code = :userCode and invoice_code = :invoiceCode and send_company_code = :companyCode",
        nativeQuery = true
    )
    fun selectMessageIdByUserCodeAndInvoice(@Param("userCode")userCode:String?,@Param("invoiceCode")invoiceCode:String?,@Param("companyCode")companyCode:String?): Int?
}