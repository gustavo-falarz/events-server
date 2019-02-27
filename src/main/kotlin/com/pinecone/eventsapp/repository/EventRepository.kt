package com.pinecone.eventsapp.repository

import com.pinecone.eventsapp.entity.Event
import org.springframework.data.mongodb.repository.MongoRepository

interface EventRepository: MongoRepository<Event, String>