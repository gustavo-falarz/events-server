package com.pinecone.eventsapp.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
class Attendee(firebaseId: String, name: String, email: String) : User(firebaseId, name, email)