//package com.example.exportControl.model
//
//import javax.persistence.*
//
//@Entity
//@Table(name = "password")
//class AppUser {
//
//    @Id
//    @Column(nullable = false, unique = true, length = 45, name = "user_id")
//    var userId: String? = null
//
//    @Column(nullable = false, length = 64, name = "decrype_password")
//    var decryptPassword: String? = null
//
//    @Column(nullable = false, length = 64, name = "password")
//    var encryptPassword: String? = null
//
//    @Column(name = "user_name")
//    var nameEng: String? = null
//
//    fun AppUser(userId: String?, decryptPassword: String?, encryptPassword: String?) {
//        this.userId = userId
//        this.decryptPassword = decryptPassword
//        this.encryptPassword = encryptPassword
//    }
//
//    @JvmName("getUserId1")
//    fun getUserId(): String? {
//        return userId
//    }
//
//    @JvmName("setUserId1")
//    fun setUserId(userId: String?) {
//        this.userId = userId
//    }
//
//    @JvmName("getUserName1")
//    fun getUserName(): String? {
//        return nameEng
//    }
//
//    @JvmName("setUserName1")
//    fun setUserName(nameEng: String?) {
//        this.nameEng = nameEng
//    }
//
//
//
//    @JvmName("getDecryptPassword1")
//    fun getDecryptPassword(): String? {
//        return decryptPassword
//    }
//
//    @JvmName("setDecryptPassword1")
//    fun setDecryptPassword(decryptPassword: String?) {
//        this.decryptPassword = decryptPassword
//    }
//
//    @JvmName("getEncrytedPassword1")
//    fun getEncrytedPassword(): String? {
//        return encryptPassword
//    }
//
//    @JvmName("setEncrytedPassword1")
//    fun setEncryptPassword(encryptPassword: String?) {
//        this.encryptPassword = encryptPassword
//    }
//
//    constructor(userId: String?, encryptPassword: String?) : super() {
//        this.userId = userId
//        this.encryptPassword = encryptPassword
//    }
//
//    constructor(userId: String?, decryptedPassword : String?, encryptPassword: String?, nameEng: String?) : super() {
//        this.userId = userId
//
//        this.decryptPassword = decryptedPassword
//        this.encryptPassword = encryptPassword
//        this.nameEng = nameEng
//    }
//
//    constructor(userId: String?) : super() {
//        this.userId = userId
//    }
//
//    constructor(userId: String?, userName : String?, encryptPassword: String?) : super() {
//        this.userId = userId
//        this.nameEng = userName
//        this.encryptPassword = encryptPassword
//
//    }
//
//    constructor() : super() {        // TODO Auto-generated constructor stub
//    }
//
//}