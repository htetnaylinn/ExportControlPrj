package com.example.exportControl.config

import com.example.exportControl.service.UserDetailsLoginServiceImpl
import com.example.exportControl.utils.AESUtils
import com.honda_logi.fpCoreSystem.config.AuthSuccessHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.security.web.session.SessionManagementFilter
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy

//@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    var userDetailsService: UserDetailsLoginServiceImpl? = null

    @Autowired
    var AuthSuccessHandler: AuthSuccessHandler? = null

    @Bean
    fun passwordEncoder(): AESUtils {
        return AESUtils
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        /*auth.userDetailsService<UserDetailsService?>(userDetailsService).passwordEncoder(passwordEncoder())*/
        auth.userDetailsService<UserDetailsService?>(userDetailsService).passwordEncoder(passwordEncoder())
    }
    override fun configure(http: HttpSecurity) {

        this.AuthSuccessHandler = AuthSuccessHandler()

        http.csrf().disable()
        http.authorizeRequests().antMatchers("/css/**","/js/**","/image/**").permitAll()
        http.authorizeRequests().antMatchers("/","/adminLogin", "/userLogin", "/login", "/logout").permitAll()
        http.authorizeRequests().antMatchers("/files/{id}/downloadBeforeLogin").permitAll()
        http.authorizeRequests().antMatchers("/inquiryBeforeLogin").permitAll()
        http.authorizeRequests().antMatchers("/sendInquiryBeforeLogin").permitAll()
        http.authorizeRequests().antMatchers("/inquiry").permitAll()
        http.authorizeRequests().antMatchers("/estimateMail").permitAll()
        http.authorizeRequests().antMatchers("/getInquiryErrorMessage").permitAll()
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403")
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic()
        http.headers().frameOptions().sameOrigin().and()
        http.authorizeRequests().anyRequest().authenticated()
            .and().formLogin()
            .loginProcessingUrl("/j_spring_security_check") // Submit URL
            .loginPage("/login")
            .successHandler(this.AuthSuccessHandler)
            .defaultSuccessUrl("/entryPage") //
            .failureUrl("/loginFail") //
            .failureForwardUrl("/loginFail")
            .usernameParameter("username") //
            .passwordParameter("password")
            .and()
            .cors().and()
            .logout().logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/login")
            .invalidateHttpSession(true)


    }

    @Bean
    fun sessionRegistry():SessionRegistry{
        val sessionRegistry: SessionRegistry = SessionRegistryImpl()
        return sessionRegistry
    }

    @Bean
    fun sessionManagementFilter(): SessionManagementFilter? {
        val sessionManagementFilter = SessionManagementFilter(httpSessionSecurityContextRepository())
        sessionManagementFilter.setInvalidSessionStrategy(simpleRedirectInvalidSessionStrategy())
        return sessionManagementFilter
    }

    @Bean
    fun simpleRedirectInvalidSessionStrategy(): SimpleRedirectInvalidSessionStrategy? {
        return SimpleRedirectInvalidSessionStrategy("/login")
    }

    @Bean
    fun httpSessionSecurityContextRepository(): HttpSessionSecurityContextRepository? {
        return HttpSessionSecurityContextRepository()
    }
}