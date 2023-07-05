package com.example.exportControl.repository

import com.example.exportControl.model.Muser
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param


interface LoginRepositoryNew : CrudRepository<Muser, String> {

    @Query(value = "SELECT admin_flg FROM m_user WHERE employee_cd =:employee_cd", nativeQuery = true)
    fun selectAdminFlgFromMuser(@Param("employee_cd") employee_cd: String?): Int

    @Query(value = "SELECT grade FROM m_user where employee_cd=:employee_cd", nativeQuery = true)
    fun getGradeByEmpCd(@Param("employee_cd") employee_cd: String?): String

}