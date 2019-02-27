package com.pinecone.eventsapp.repository

import com.pinecone.eventsapp.entity.Attendee
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AttendeeRepository : MongoRepository<Attendee, String>{

    fun findByEmail(email: String): Optional<Attendee>

}