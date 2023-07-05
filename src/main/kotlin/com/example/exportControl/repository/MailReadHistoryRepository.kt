package com.example.exportControl.repository

import com.example.exportControl.model.Company
import com.example.exportControl.model.Country
import com.example.exportControl.model.MailHistory
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface MailReadHistoryRepository : CrudRepository<MailHistory, String> {

    @Query(
        value = "select history_id from t_mail_read_history order by history_id desc limit 1",
        nativeQuery = true
    )
    fun selectLastHistoryId(): Int?

    @Query(
        value = "select * from t_mail_read_history where user_code = :userCode order by history_id ",
        nativeQuery = true
    )
    fun selectReadHistoryByUserCode(@Param("userCode")userCode:String?): List<MailHistory>?

    @Query(
        value = "select mail_id from t_mail_read_history where user_code = :userCode order by history_id ",
        nativeQuery = true
    )
    fun selectMailIdByUserCode(@Param("userCode")userCode:String?): List<Int>


}