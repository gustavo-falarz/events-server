package com.pinecone.eventsapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*
import java.util.Locale.US
import javax.servlet.http.HttpServletRequest


@Configuration
class LocaleConfig: AcceptHeaderLocaleResolver(), WebMvcConfigurer {

    val locales = listOf(Locale("en"), Locale("fr"), Locale("pt"))

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val headerLang = request.getHeader("Accept-Language")
        return when {
            headerLang == null || headerLang.isEmpty() -> Locale.getDefault()
            else -> Locale.lookup(Locale.LanguageRange.parse(headerLang), locales)
        }
    }



    @Bean
    fun messageSource(): ResourceBundleMessageSource {
        val rs = ResourceBundleMessageSource()
        rs.setBasename("messages")
        rs.setDefaultEncoding("ISO-8859-1")
        rs.setUseCodeAsDefaultMessage(true)
        return rs
    }

}