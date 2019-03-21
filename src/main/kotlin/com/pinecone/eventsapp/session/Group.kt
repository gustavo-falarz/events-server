package com.pinecone.eventsapp.session

import com.pinecone.eventsapp.entity.Attendee
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Group(var owners: List<Attendee>) {
    var members: List<Attendee> = mutableListOf()
}