package com.example.exportControl.repository

import com.example.exportControl.model.Banner
import com.example.exportControl.model.Company
import com.example.exportControl.model.Fare
import com.example.exportControl.model.requestModel.CompanyRequestModel
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface BannerMasterRepository : CrudRepository<Banner, String> {

    @Query(value = "select * from m_banner where delete_flag = 0 order by banner_code ", nativeQuery = true)
    fun selectAllBanner(): List<Banner>?

    @Query(value = "select * from m_banner where delete_flag = 0 order by order_list ", nativeQuery = true)
    fun selectAllBannerByOrderList(): List<Banner>?

    @Query(value = "select * from m_banner where delete_flag = 0 order by order_list ", nativeQuery = true)
    fun selectAllFareOrderByOrderList(): List<Banner>?

    @Query(value = "select banner_code from m_banner where delete_flag = 0 order by banner_code ", nativeQuery = true)
    fun getBannerCodeDeleteFlag0(): List<String>?

    @Query(value = "select banner_code from m_banner where delete_flag = 1 order by banner_code ", nativeQuery = true)
    fun getBannerCodeDeleteFlag1(): List<String>?

    @Query(value = "select order_list from m_banner where delete_flag = 0 order by banner_code ", nativeQuery = true)
    fun getSavedOrderList(): List<String>?

    @Modifying
    @Transactional
    @Query(
        value = "update m_banner " +
                "set delete_flag = 0, " +
                "order_list = :orderList, " +
                "banner_title = :bannerTitle," +
                "banner_image_name = :bannerImageName, " +
                "banner_image = :bannerImage,  " +
                "banner_link = :bannerLink,  " +
                "updated_dt = :currentTime, " +
                "updated_by = :userCode " +
                "where banner_code=:bannerCode",
        nativeQuery = true
    )
    fun updateBannerMaster(@Param("bannerCode") bannerCode: String?,
                            @Param("orderList") orderList: String?,
                            @Param("bannerTitle") bannerTitle: String?,
                            @Param("bannerImageName") bannerImageName: String?,
                            @Param("bannerImage") bannerImage: ByteArray?,
                           @Param("bannerLink") bannerLink: String?,
                           @Param("userCode") userCode: String?,
                            @Param("currentTime") currentTime: Timestamp?   )

    @Modifying
    @Transactional
    @Query(
        value = "update m_banner " +
                "set delete_flag = 1, " +
                "updated_by = :userCode, " +
                "updated_dt = :currentTime " +
                "where banner_code=:bannerCode",
        nativeQuery = true
    )
    fun deleteBannerMaster(@Param("bannerCode") bannerCode: String?,
                         @Param("userCode") userCode: String?,
                            @Param("currentTime") currentTime: Timestamp?)


}