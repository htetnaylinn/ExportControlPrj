package com.example.exportControl.repository

import com.example.exportControl.model.ReactUser
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface ReactUserRepository : CrudRepository<ReactUser,String>{

    @Query(
        value = "select id from t_react_user order by id desc limit 1",
        nativeQuery = true
    )
    fun selectLastReactId(): Int?

    @Modifying
    @Transactional
    @Query(
        value = "delete from t_react_user t where t.message_id = :messageId and t.user_code = :userCode ",
        nativeQuery = true
    )
    fun removeReaction(@Param("messageId")messageId:String?,@Param("userCode")userCode:String?)

    @Query(
        value = "select m.user_name from m_user as m  join t_react_user as t on m.user_code = t.user_code where t.message_id = :messageId  ",
        nativeQuery = true
    )
    fun fetchReactedPerson(@Param("messageId")messageId:String?): List<String>?

}