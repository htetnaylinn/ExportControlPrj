package com.example.exportControl.repository

import com.example.exportControl.model.Company
import com.example.exportControl.model.Fare
import com.example.exportControl.model.requestModel.CompanyRequestModel
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface FareMasterRepository : CrudRepository<Fare, String> {

    @Query(value = "select * from m_fare where delete_flag = 0 order by fare_code ", nativeQuery = true)
    fun selectAllFare(): List<Fare>?

    @Query(value = "select * from m_fare where delete_flag = 0 ORDER BY (order_list ~ '^\\d') DESC, (CASE WHEN (order_list ~ '^\\d') THEN CAST(order_list AS numeric) END) NULLS LAST, order_list ", nativeQuery = true)
    fun selectAllFareOrderByOrderList(): List<Fare>?

    @Query(value = "select fare_code from m_fare where delete_flag = 0 order by fare_code ", nativeQuery = true)
    fun getFareCodeDeleteFlag0(): List<String>?

    @Query(value = "select fare_code from m_fare where delete_flag = 1 order by fare_code ", nativeQuery = true)
    fun getFareCodeDeleteFlag1(): List<String>?

    @Query(value = "select order_list from m_fare where delete_flag = 0 order by fare_code ", nativeQuery = true)
    fun getSavedOrderList(): List<String>?

    @Modifying
    @Transactional
    @Query(
        value = "update m_fare " +
                "set delete_flag = 0, " +
                "order_list = :orderList, " +
                "fare_title = :fareTitle," +
                "fare_content = :fareContent," +
                "fare_file_name = :fareFileName, " +
                "fare_file = :fareFile,  " +
                "updated_dt = :currentTime, " +
                "updated_by = :userCode " +
                "where fare_code=:fareCode",
        nativeQuery = true
    )
    fun updateFareMaster(@Param("fareCode") fareCode: String?,
                            @Param("orderList") orderList: String?,
                            @Param("fareTitle") fareTitle: String?,
                            @Param("fareContent") fareContent: String?,
                            @Param("fareFileName") fareFileName: String?,
                            @Param("fareFile") fareFile: ByteArray?,
                            @Param("userCode") userCode: String?,
                            @Param("currentTime") currentTime: Timestamp?   )

    @Modifying
    @Transactional
    @Query(
        value = "update m_fare " +
                "set delete_flag = 1, " +
                "updated_by = :userCode, " +
                "updated_dt = :currentTime " +
                "where fare_code=:fareCode",
        nativeQuery = true
    )
    fun deleteFareMaster(@Param("fareCode") companyCode: String?,
                         @Param("userCode") userCode: String?,
                            @Param("currentTime") currentTime: Timestamp?)


}