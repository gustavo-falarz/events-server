package com.pinecone.eventsapp.session

import com.pinecone.eventsapp.entity.Event
import com.pinecone.eventsapp.repository.EventRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class EventSession(val eventRepository: EventRepository,
                   val attendeeSession: AttendeeSession,
                   val placeSession: PlaceSession,
                   val userSession: UserSession) : Session() {

    fun addEvent(userId: String,
                 placeId: String,
                 dateTime: Long) {
        val organizer = userSession.findUserById(userId)
        val place = placeSession.findPlaceById(placeId)
        val date = Date(dateTime)
        val event = Event(organizer, date, place,mutableListOf())
        eventRepository.save(event)
    }

    fun getEvents(): MutableList<Event> = eventRepository.findAll()

    fun getEventById(id: String): Optional<Event> {
        val event = eventRepository.findById(id)
        when {
            event.isPresent -> return event
            else -> throw Exception(message("error.event-not-found"))
        }
    }

    fun registerAttendee(attendeeId: String, eventId: String): Event {
        val event = getEventById(eventId).get()
        val attendee = attendeeSession.findAttendeeById(attendeeId)
        when {
            event.attendees.contains(attendee) -> {
                throw Exception(message("error.attendee-already-registered-to-attend"))
            }
            else -> {
                event.attendees.add(attendee)
                return eventRepository.save(event)
            }
        }
    }

    fun removeAttendee(attendeeId: String, eventId: String): Event {
        val event = getEventById(eventId).get()
        val attendee = attendeeSession.findAttendeeById(attendeeId)
        when {
            event.attendees.contains(attendee) -> {
                event.attendees.remove(attendee)
                return eventRepository.save(event)
            }
            else -> {
                throw Exception(message("error.attendee-not-registered-to-attend"))
            }
        }
    }
}