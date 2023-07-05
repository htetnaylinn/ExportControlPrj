package com.example.exportControl.repository

import com.example.exportControl.model.UserControl
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface UserControlRepository : CrudRepository<UserControl, String> {

    @Query(value = "select * from user_control where user_code= :userCode", nativeQuery = true)
    fun selectAllByUserId(@Param("userCode")userCode: String?): UserControl

    @Modifying
    @Transactional
    @Query(
        value = "update user_control " +
                "set  export_registration_flg = :exportRegistrationFlag,schedule_flg = :scheduleFlag , user_flg = :userMasterFlag," +
                "product_flg = :productMasterFlag , company_flg = :companyMasterFlag , group_flg = :groupMasterFlag " +
                "where user_code=:userCode ",
        nativeQuery = true
    )
    fun updateUserControlByUserCode(@Param("userCode") userCode: String?,
                                    @Param("exportRegistrationFlag") exportRegistrationFlag: Int?,
                                    @Param("scheduleFlag") scheduleFlag: Int?,
                                    @Param("userMasterFlag") userMasterFlag: Int?,
                                    @Param("productMasterFlag") productMasterFlag: Int?,
                                    @Param("companyMasterFlag") companyMasterFlag: Int?,
                                    @Param("groupMasterFlag") groupMasterFlag: Int?,
                                    )
}