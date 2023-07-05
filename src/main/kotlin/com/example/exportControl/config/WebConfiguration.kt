package com.example.exportControl.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.config.annotation.*
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor


@Configuration
@EnableWebMvc
class WebConfiguration : WebMvcConfigurer {
    val messageSource = ReloadableResourceBundleMessageSource()
    var messageSourcePath  = 0//editedlast

    @Bean
    fun messageSource(): MessageSource {
        messageSource.setBasename("classpath:messages_jp")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**").addResourceLocations(
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"
        )
    }

    fun langChange(): MessageSource {
        messageSource.clearCache()
        if (messageSourcePath == 0) {
            messageSource.setBasename("classpath:messages_jp")
        } else if (messageSourcePath == 1) {
            messageSource.setBasename("classpath:messages_en")
        }
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    /*@Bean
    fun localeResolver(): LocaleResolver {
        val localeResolver = CookieLocaleResolver()
        val locale = LocaleContextHolder.getLocale()
        localeResolver.setDefaultLocale(locale)
        return localeResolver
    }*/

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val localeChangeInterceptor = LocaleChangeInterceptor()
        localeChangeInterceptor.paramName = "lang"
        return localeChangeInterceptor
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }

//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**")
//            .allowedOriginPatterns("*")
//            .allowedMethods("*")
//            .maxAge(3600L)
//            .allowedHeaders("*")
//            .exposedHeaders("Authorization","X-Auth-Token")
//            .allowCredentials(true);
//    }

//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**")
////            .allowedOrigins("*")
//            .allowedOriginPatterns("*")
//            .allowCredentials(true)
//            .maxAge(3600)
//            .allowedHeaders(
//                "Accept", "Content-Type", "Origin",
//                "Authorization", "X-Auth-Token"
//            )
//            .exposedHeaders("X-Auth-Token", "Authorization")
//            .allowedMethods("POST", "GET", "DELETE", "PUT", "OPTIONS")
//    }
}