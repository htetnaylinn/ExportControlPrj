package com.example.exportControl.repository

import com.example.exportControl.model.Muser
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

//call m user table (MainGrade)
interface LoginRepository : CrudRepository<Muser, String> {

    @Query(value = "SELECT role FROM m_user WHERE user_id =:user_id", nativeQuery = true)
    fun selectRoleFromMuser(@Param("user_id") user_id: String?): String

    @Query(value = "SELECT grade FROM m_user where employee_cd=:employee_cd", nativeQuery = true)
    fun getGradeByEmpCd(@Param("employee_cd") employee_cd: String?): String

}