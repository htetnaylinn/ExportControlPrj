package com.example.exportControl.repository

import com.example.exportControl.model.Product
import com.example.exportControl.model.requestModel.ProductRequestModel
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

interface ProductMasterRepository : CrudRepository<Product, String> {

    @Query(value = "select * from m_product where delete_flag = 0 order by product_code ", nativeQuery = true)
    fun selectAllProduct(): List<Product>?

    @Query(value = "select product_code from m_product where delete_flag = 0 order by product_code ", nativeQuery = true)
    fun productCode_df_0(): List<String>?

    @Query(value = "select product_code from m_product where delete_flag = 1 order by product_code ", nativeQuery = true)
    fun productCode_df_1(): List<String>?

    @Query(value = "select * from m_product where (:productCode='' or product_code=:productCode) and (:productName='' or product_name=:productName) and delete_flag = 0 order by product_code ", nativeQuery = true)
    fun searchProductCodeAndName(@Param("productCode") productCode: String?,@Param("productName") productName: String?): List<Product>

    @Modifying
    @Transactional
    @Query(
        value = "update m_product " +
                "set delete_flag = 1,updated_by = :updatedBy, " +
                "updated_dt = :currentTime " +
                "where product_code=:productCode",
        nativeQuery = true
    )
    fun deleteProductMaster(@Param("productCode") productCode: String?,
                            @Param("currentTime") currentTime: Timestamp?,
                            @Param("updatedBy") updatedBy: String?)

    @Query(value = "select * from m_product where product_code =:productCode and delete_flag = 0 ", nativeQuery = true)
    fun selectProductByProductCode(@Param("productCode") productCode: String?): Product

//

    @Modifying
    @Transactional
    @Query(
            value = """update m_product set delete_flag = 0, product_name = :#{#productReq.productName}, updated_dt = :#{#productReq.updatedDt},updated_by = :#{#productReq.updatedBy} where product_code= :#{#productReq.productCode}""",
            nativeQuery = true
    )
    fun updateProductMaster(@Param("productReq") productReq: ProductRequestModel?)

    @Query(value = "select product_code from m_product where delete_flag = 0 order by product_code ", nativeQuery = true)
    fun selectAllProductCode(): List<String>?

    @Query(value = "select product_name from m_product where delete_flag = 0 order by product_code ", nativeQuery = true)
    fun selectAllProductName(): List<String>?

    @Query(value = "select product_code ||' '||product_name from m_product where delete_flag = 0 order by product_code ", nativeQuery = true)
    fun getNotSelectedProductName(): List<String>?

}