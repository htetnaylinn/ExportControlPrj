package com.example.exportControl.controller

import com.example.exportControl.model.Product
import com.example.exportControl.model.requestModel.ProductRequestModel
import com.example.exportControl.repository.ProductMasterRepository
import com.example.exportControl.repository.UserMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMethod
import org.thymeleaf.util.ListUtils.isEmpty
import java.security.Principal
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.servlet.http.HttpServletResponse

/**
 * ProductMasterController
 *
 */
@Controller
class ProductMasterController : MenuController(){
    @Autowired
    private val productMasterRepo: ProductMasterRepository? = null

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    /**
     * 初期表示処理
     * @param model
     * @param principal
     */
    @RequestMapping("productMaster")
    fun productMaster(model: Model,principal: Principal): String {
        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)
        model.addAttribute("SearchList", productMasterRepo?.selectAllProduct())
        model.addAttribute("ProductList", productMasterRepo?.selectAllProduct())
        model.addAttribute("proNameList", productMasterRepo?.getNotSelectedProductName())

        return "productMaster"
    }

    /**
     * 検索処理
     * @param model
     * @param principal
     */
    @RequestMapping("searchProductMaster")
    fun searchProductMaster(
        model: Model,
        @Param("productCode") productCode: String?,
        @Param("productName") productName: String?,
        principal: Principal,
        response: HttpServletResponse
    ): String {
        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

        model.addAttribute("loginCompany",loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)

        val productList = productMasterRepo?.searchProductCodeAndName(productCode, productName)

        var productList2: String = ""

        if(isEmpty(productList)){
           productList2 = "empty"
        }

        println(productList)

        println(productList2)
        model.addAttribute("productCode", productCode)
        model.addAttribute("productName", productName)
        model.addAttribute("SearchList", productMasterRepo?.selectAllProduct())
        model.addAttribute("ProductList",productList)
        model.addAttribute("ProductList2", productList2)

        response.setHeader("Cache-Control", "no-cache");

        return "productMaster"
    }

    /**
     *
     * @param model
     * @param principal
     */
    @RequestMapping("newProductMaster")
    fun newProductMaster(model: Model,principal: Principal): String {
        val userName = principal.name
        val muser = usermasterRepo?.selectUserByUserCode(userName)
        val loginCompany = companyMasterRepo?.selectCompanyByCompanyCode(muser?.companyCode)

        model.addAttribute("loginCompany", loginCompany)
        model.addAttribute("languageOption",webConfig?.messageSourcePath)

        val productCodeDf0 = productMasterRepo?.productCode_df_0()
        val productCodeDf1 = productMasterRepo?.productCode_df_1()
        val productNameDf0 = productMasterRepo?.selectAllProductName()

        model.addAttribute("productCode_df_0",productCodeDf0)
        model.addAttribute("productCode_df_1",productCodeDf1)
        model.addAttribute("productNameDf0",productNameDf0)

        return "productMasterDetail"
    }

    /**
     *　削除処理
     * @param productCode
     */
    @RequestMapping("/deleteProductMaster/{productCode}", method = [RequestMethod.GET])
    fun deleteProductMaster(@PathVariable("productCode") productCode: String,principal: Principal): String {
        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        var userCode = principal.name
        productMasterRepo?.deleteProductMaster(productCode, currentTime,userCode)
        return "redirect:/productMaster"
    }

    /**
     *　編集処理
     * @param productCode
     * @param　model
     */
    @RequestMapping("/editProductMaster/{productCode}", method = [RequestMethod.GET])
    fun editProductMaster(@PathVariable("productCode") productCode: String, model: Model): String {
        model.addAttribute("productMaster", productMasterRepo?.selectProductByProductCode(productCode))
        return "productMasterDetailEdit"
    }

    /**
     *　更新処理
     * @param prodouctReq
     * @param　model
     */
    @RequestMapping("/updateProductMaster")
    fun updateProductMaster(@ModelAttribute("product") productReq: ProductRequestModel?,principal: Principal): String {
        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        var userCode = principal.name
        productReq?.updatedDt = currentTime
        productReq?.updatedBy = userCode
        productMasterRepo?.updateProductMaster(productReq)
        return "redirect:/productMaster"
    }


    @RequestMapping("/saveProductMaster")
    fun saveProductMaster(
        @ModelAttribute("product") productReq: ProductRequestModel?,
        @Param("updateCheck") updateCheck: String?,
        model: Model,principal: Principal
    ): String {
        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        var userCode = principal.name
        if(updateCheck == "false"){
            val saveProductMaster = Product(
                productReq?.productCode,
                productReq?.productName,
                userCode,
                currentTime,
                null,
                null,
                0
            )
            productMasterRepo?.save(saveProductMaster)
        }else{
            productReq?.updatedBy = userCode
            productReq?.updatedDt = currentTime
            productMasterRepo?.updateProductMaster(productReq)

        }


        return "redirect:/productMaster"
    }
}
