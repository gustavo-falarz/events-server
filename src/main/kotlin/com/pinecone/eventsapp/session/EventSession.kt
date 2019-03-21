package com.pinecone.eventsapp.session

import com.pinecone.eventsapp.entity.Attendee
import com.pinecone.eventsapp.entity.Event
import com.pinecone.eventsapp.repository.EventRepository
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Hours
import org.springframework.stereotype.Component
import java.util.*

@Component
class EventSession(val eventRepository: EventRepository,
                   val attendeeSession: AttendeeSession,
                   val placeSession: PlaceSession) : Session() {

    fun addEvent(userId: String,
                 placeId: String,
                 dateTime: Long) {

        checkTime(dateTime)

        val organizer = attendeeSession.findAttendeeById(userId)

        when {
            organizer.role != Attendee.Role.ORGANIZER -> throw Exception(error("error.role-incompatible"))
        }

        val place = placeSession.findPlaceById(placeId)
        val date = Date(dateTime)
        val event = Event(organizer, date, place, mutableListOf())
        eventRepository.save(event)
    }

    fun getEvents(): MutableList<Event> = eventRepository.findAll()

    fun getEventById(id: String): Event {
        val event = eventRepository.findById(id)
        when {
            event.isPresent -> return event.get()
            else -> throw Exception(error("error.event-not-found"))
        }
    }

    fun registerAttendee(attendeeId: String, eventId: String): Event {
        val event = getEventById(eventId)
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
        val event = getEventById(eventId)
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

    fun checkTime(time: Long) {
        val now = DateTime(DateTimeZone.forID("America/Sao_Paulo")).toLocalDateTime().toDateTime(DateTimeZone.UTC)
        val event = DateTime(time, DateTimeZone.UTC)

        if (Hours.hoursBetween(now, event)
                        .isLessThan(Hours.TWO)) {
            throw Exception(error("error.too-late-to-create"))
        }
    }
}