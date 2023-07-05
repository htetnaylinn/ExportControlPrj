package com.example.exportControl.repository

import com.example.exportControl.model.ExportChatDailySave
import com.example.exportControl.model.ExportChatDailySaveOrg
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface DailyChatSaveRepositoryOrg : CrudRepository<ExportChatDailySaveOrg,String>{

    @Modifying
    @Transactional
    @Query(
        value = "update  t_export_chat_daily_save  set react_count = react_count+1 where id = :messageId ",
        nativeQuery = true
    )
    fun plusReactionCount(@Param("messageId")messageId:Int?)

    @Modifying
    @Transactional
    @Query(
        value = "update  t_export_chat_daily_save  set react_count = react_count-1 where id = :messageId ",
        nativeQuery = true
    )
    fun minusReactionCount(@Param("messageId")messageId:Int?)

    @Query(
        value = "select react_count from t_export_chat_daily_save where id = :messageId ",
        nativeQuery = true
    )
    fun selectAllReactCountById(@Param("messageId")messageId:Int?):Int?

}