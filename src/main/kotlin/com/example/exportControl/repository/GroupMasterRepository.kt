package com.example.exportControl.repository

import com.example.exportControl.model.Group
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface GroupMasterRepository : CrudRepository<Group, String> {

    @Query(value = "select * from m_group where delete_flag = 0 order by group_code ", nativeQuery = true)
    fun selectAllGroup(): List<Group>?

    @Query(value = "select group_code from m_group where delete_flag = 0 order by group_code ", nativeQuery = true)
    fun groupCodeDeleteFlag0(): List<String>?

    @Query(value = "select group_code from m_group where delete_flag = 1 order by group_code ", nativeQuery = true)
    fun groupCodeDeleteFlag1(): List<String>?

    @Query(value = "select * from m_group where (:groupCode='' or group_code=:groupCode) and (:groupName='' or group_name=:groupName) and (:companyName='' or  company_name LIKE %:companyName%) and delete_flag = 0 order by group_code ", nativeQuery = true)
    fun searchGroupCodeAndNameAndCountry(@Param("groupCode") groupCode: String?,
                                        @Param("groupName") groupName: String?,
                                        @Param("companyName") companyName: String?): List<Group>

    @Modifying
    @Transactional
    @Query(
        value = "update m_group " +
                "set delete_flag = 1, " +
                "updated_dt = :currentTime " +
                "where group_code=:groupCode",
        nativeQuery = true
    )
    fun deleteGroupMaster(@Param("groupCode") groupCode: String?,
                            @Param("currentTime") currentTime: Timestamp?)

    @Modifying
    @Transactional
    @Query(
        value = "update m_group " +
                "set delete_flag = 0, " +
                "company_name = :companyName, " +
                "group_name =:groupName, " +
                "updated_dt = :currentTime " +
                "where group_code=:groupCode",
        nativeQuery = true
    )
    fun updateGroupMaster(@Param("groupCode") groupCode: String?,
                            @Param("groupName") groupName: String?,
                            @Param("companyName") companyName: String?,
                            @Param("currentTime") currentTime: Timestamp?)


}