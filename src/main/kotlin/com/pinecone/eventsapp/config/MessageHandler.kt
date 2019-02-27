package com.pinecone.eventsapp.config

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Component


@Component
class MessageHandler(val messageSource: ResourceBundleMessageSource) {

    fun getMessage(msgCode: String): String {
        val locale = LocaleContextHolder.getLocale()
        return messageSource.getMessage(msgCode, null, locale)
    }

}