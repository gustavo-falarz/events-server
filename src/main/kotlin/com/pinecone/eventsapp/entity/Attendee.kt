package com.pinecone.eventsapp.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
class Attendee(id: String, name: String, email: String) : User(id, name, email){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}