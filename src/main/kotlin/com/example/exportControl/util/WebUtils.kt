package com.example.exportControl.util

import org.springframework.security.core.userdetails.User

object WebUtils {
    fun toString(user: User): String {
        val sb = StringBuilder()
        sb.append("UserName:").append(user.username)
        val authorities = user.authorities
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (")
            var first = true
            for (a in authorities) {
                if (first) {
                    sb.append(a.authority)
                    first = false
                } else {
                    sb.append(", ").append(a.authority)
                }
            }
            sb.append(")")
        }
        return sb.toString()
    }
}