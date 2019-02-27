package com.pinecone.eventsapp.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
class Attendee(name: String, email: String) : User(name, email) {
}