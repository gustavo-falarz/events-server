package com.pinecone.eventsapp.session

import com.pinecone.eventsapp.config.MessageHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Session {
    @Autowired
    lateinit var messageHandler: MessageHandler

    fun error(code: String) = messageHandler.getMessage(code)

    fun message(code: String) = messageHandler.getMessage(code)
}