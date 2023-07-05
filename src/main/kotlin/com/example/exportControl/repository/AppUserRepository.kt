//package com.example.exportControl.repository
//
//import com.example.exportControl.model.AppUser
//import org.springframework.data.jpa.repository.Query
//import org.springframework.data.repository.CrudRepository
//import org.springframework.data.repository.query.Param
//
//interface AppUserRepository : CrudRepository<AppUser, Long> {
//    @Query(value = "SELECT name FROM password WHERE employee_cd = :employeeCd", nativeQuery = true)
//    fun selectNameEng(@Param("employeeCd") employeeCd: String?): String?
//
//    @Query(
//        value = "SELECT * FROM password WHERE employee_cd =:employee_cd",
//        nativeQuery = true
//    )
//    fun selectEmpCd(@Param("employee_cd") employee_cd: String?): AppUser
//
//    @Query(value = "SELECT user_id FROM password ORDER BY user_id DESC LIMIT 1", nativeQuery = true)
//    fun selectLastRecordPassId(): String?
//
//    @Query(value = "SELECT * FROM password order by CAST(SUBSTRING(employee_cd FROM '([0-9]{1,10})') AS INTEGER)", nativeQuery = true)
//    fun selectAll(): List<AppUser?>?
//
//    abstract fun save(editPass: AppUser?)
//
//    //EKP
//    @Query(value = "SELECT * FROM password WHERE employee_cd =:userCd", nativeQuery = true)
//    fun searchAppUser(@Param("userCd") userCd: String?): AppUser
//
//    @Query(value = "SELECT * FROM password EXCEPT SELECT * FROM password WHERE employee_cd =:userCd OR name =:userCd ORDER BY employee_cd", nativeQuery = true)
//    fun noSearch(@Param("userCd") userCd: String?): List<AppUser?>?
//
//    @Query(value = "SELECT COUNT(*) FROM password WHERE employee_cd =:userCd OR name =:userCd", nativeQuery = true)
//    fun search(@Param("userCd") userCd: String?): Int
//}