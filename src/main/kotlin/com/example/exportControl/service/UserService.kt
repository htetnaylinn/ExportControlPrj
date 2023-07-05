package com.example.exportControl.service

import com.example.exportControl.model.Muser
import com.example.exportControl.repository.UserMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private val userMasterRepo: UserMasterRepository? = null

    /**
     * search user information with company code
     */
    fun searchUserWithCompanyCode(@Param("companyCode") companyCode: String?): List<Muser>? {

       return userMasterRepo?.searchUserWithCompanyCode(companyCode)
    }

    /**
     * search user information with same company Code
     */
    fun searchUserWithCompanyCodeNotIncludedSingedUser(@Param("companyCode") companyCode: String?,@Param("userCode") userCode: String?): List<Muser>? {

        return userMasterRepo?.searchUserWithCompanyCodeNotIncludedSingedUser(companyCode,userCode)
    }
}