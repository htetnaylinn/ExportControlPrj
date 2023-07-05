package com.example.exportControl.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object EncrytedPasswordUtils {

    fun encrytePassword(password: String?): String {
        val encoder = BCryptPasswordEncoder()
        return encoder.encode(password)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val password = "123"
        val encrytedPassword = encrytePassword(password)
        println("Encryted Password: $encrytedPassword")
    }
}