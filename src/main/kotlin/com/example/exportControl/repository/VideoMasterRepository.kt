package com.example.exportControl.repository

import com.example.exportControl.model.Company
import com.example.exportControl.model.Fare
import com.example.exportControl.model.Video
import com.example.exportControl.model.requestModel.CompanyRequestModel
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface VideoMasterRepository : CrudRepository<Video, String> {

    @Query(value = "select * from m_video where delete_flag = 0 order by video_code ", nativeQuery = true)
    fun selectAllVideo(): List<Video>?

    @Query(value = "SELECT * FROM m_video WHERE delete_flag = 0 ORDER BY (order_list ~ '^\\d') DESC, (CASE WHEN (order_list ~ '^\\d') THEN CAST(order_list AS numeric) END) NULLS LAST, order_list", nativeQuery = true)
    fun selectAllVideoByOrderList(): List<Video>?

    


    @Query(value = "select * from m_video where delete_flag = 0 order by order_list ", nativeQuery = true)
    fun selectAllFareOrderByOrderList(): List<Video>?

    @Query(value = "select video_code from m_video where delete_flag = 0 order by video_code ", nativeQuery = true)
    fun getFareCodeDeleteFlag0(): List<String>?

    @Query(value = "select video_code from m_video where delete_flag = 1 order by video_code ", nativeQuery = true)
    fun getFareCodeDeleteFlag1(): List<String>?

    @Query(value = "select order_list from m_video where delete_flag = 0 order by video_code ", nativeQuery = true)
    fun getSavedOrderList(): List<String>?

    @Modifying
    @Transactional
    @Query(
        value = "update m_video " +
                "set delete_flag = 0, " +
                "order_list = :orderList, " +
                "video_title = :videoTitle," +
                "video_content = :videoContent," +
                "video_link = :videoLink, " +
                "updated_dt = :currentTime, " +
                "updated_by = :userCode " +
                "where video_code=:videoCode",
        nativeQuery = true
    )
    fun updateVideoMaster(@Param("videoCode") videoCode: String?,
                            @Param("orderList") orderList: String?,
                            @Param("videoTitle") videoTitle: String?,
                            @Param("videoContent") videoContent: String?,
                            @Param("videoLink") videoLink: String?,
                            @Param("userCode") userCode: String?,
                            @Param("currentTime") currentTime: Timestamp?   )

    @Modifying
    @Transactional
    @Query(
        value = "update m_video " +
                "set delete_flag = 1, " +
                "updated_by = :userCode, " +
                "updated_dt = :currentTime " +
                "where video_code=:videoCode",
        nativeQuery = true
    )
    fun deleteVideoMaster(@Param("videoCode") videoCode: String?,
                         @Param("userCode") userCode: String?,
                            @Param("currentTime") currentTime: Timestamp?)


}