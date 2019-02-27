package com.pinecone.eventsapp.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class Event(@DBRef
            var organizer: User,
            var start: Date,
            @DBRef
            var place: Place,
            @DBRef
            var attendees: MutableList<Attendee>) {

    @Id
    var id: String? = null
    var setup: Setup? = null
}