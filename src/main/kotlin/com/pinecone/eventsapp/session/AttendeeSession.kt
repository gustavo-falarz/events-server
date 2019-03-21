package com.pinecone.eventsapp.session

import com.pinecone.eventsapp.entity.Attendee
import com.pinecone.eventsapp.repository.AttendeeRepository
import org.springframework.stereotype.Component

@Component
class AttendeeSession(val attendeeRepository: AttendeeRepository) : Session() {

    fun add(attendee: Attendee) {
        val result = attendeeRepository.findByEmail(attendee.email)
        when {
            !result.isPresent -> attendeeRepository.save(attendee)
            else -> throw Exception(error("error.attendee-registered"))
        }
    }

    fun getAttendees(): List<Attendee> = attendeeRepository.findAll()

    fun updateRole(userId: String, role: Attendee.Role) {
        val attendee = findAttendeeById(userId)
        attendee.role = role
        attendeeRepository.save(attendee)
    }

    fun findAttendeeById(attendeeId: String): Attendee {
        val attendee = attendeeRepository.findById(attendeeId)
        return when {
            attendee.isPresent -> attendee.get()
            else -> throw Exception(error("error.attendee-not-found"))
        }
    }

}