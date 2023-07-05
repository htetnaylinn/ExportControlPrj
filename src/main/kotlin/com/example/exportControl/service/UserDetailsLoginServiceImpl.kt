package com.example.exportControl.service

import com.example.exportControl.dao.AppUserDao
import com.example.exportControl.model.Muser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsLoginServiceImpl : UserDetailsService {
    @Autowired
    private val appUserDAO: AppUserDao? = null

//    @Autowired
//    private val appRoleDao: AppRoleDao? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userName: String): UserDetails {
        val appUser: Muser? = this.appUserDAO?.findUserAccount(userName)
        if (appUser == null) {
            println("User not found! $userName was not found in the database111")
            throw UsernameNotFoundException("User $userName was not found in the database")
        }

        // [ROLE_USER, ROLE_ADMIN,..]
//        val roleNames = appRoleDao!!.getRoleNames(appUser.getUserId()!!)
//        println("Role Name: $roleNames")
        val grantList: MutableList<GrantedAuthority> = ArrayList()
//        if (roleNames != null) {
//            for (role in roleNames) {
//                // ROLE_USER, ROLE_ADMIN,..
//                val authority: GrantedAuthority = SimpleGrantedAuthority(role)
//                grantList.add(authority)
//            }
//        }

        return User(appUser.userId, appUser.password ,grantList
        )
    }
}