package com.honda_logi.fpCoreSystem.config

import org.springframework.context.annotation.Bean
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {


    val REDIRECT_URL_SESSION_ATTRIBUTE_NAME = "REDIRECT_URL"


    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {


        val session = request.session
        val authUser: User = SecurityContextHolder.getContext().authentication.principal as User
        session.setAttribute("user", authUser)
        session.setAttribute("username", authUser.username)
        println("authentication are ${authentication.authorities}")
        session.setAttribute("authorities", authentication.authorities)
        if (request.session.getAttribute("previousURL") != null) {
            val requestUrl: String = request.session.getAttribute("previousURL") as String
            println("URL****************$requestUrl")
        }
        val redirectURLObject = request.session.getAttribute(REDIRECT_URL_SESSION_ATTRIBUTE_NAME)


        if (redirectURLObject !== null && redirectURLObject.toString().isNotEmpty()) {

            if (redirectURLObject.toString().contains("/login")) {

                defaultTargetUrl = "/scheduleOne"
            } else
                response.sendRedirect(redirectURLObject.toString())
        } else {
            defaultTargetUrl = "/scheduleOne"

        }

        request.session.removeAttribute(REDIRECT_URL_SESSION_ATTRIBUTE_NAME)
        super.onAuthenticationSuccess(request, response, authentication)


    }

    @Bean
    fun authSuccessHandler(): AuthSuccessHandler? {
        return AuthSuccessHandler()
    }
}


