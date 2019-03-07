package com.pinecone.eventsapp.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
open class User(var firebaseId: String, var name: String, var email: String) {
    @Id
    var id: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (firebaseId != other.firebaseId) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firebaseId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }


}