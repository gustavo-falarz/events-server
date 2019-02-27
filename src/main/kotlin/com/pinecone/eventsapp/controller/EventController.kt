package com.pinecone.eventsapp.controller

import com.pinecone.eventsapp.entity.Event
import com.pinecone.eventsapp.session.EventSession
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/event")
class EventController(val eventSession: EventSession) {

    @PutMapping
    fun addEvent(@RequestParam("userId") userId: String,
                 @RequestParam("placeId") placeId: String,
                 @RequestParam("dateTime") dateTime: Long) = eventSession.addEvent(userId, placeId, dateTime)

    @GetMapping
    fun getEvents(): MutableList<Event> = eventSession.getEvents()

    @GetMapping("/register")
    fun register(@RequestParam("attendeeId") attendeeId: String,
                 @RequestParam("eventId") eventId: String) = eventSession.registerAttendee(attendeeId, eventId)

    @GetMapping("/leave-event")
    fun removeAttendee(@RequestParam("attendeeId") attendeeId: String,
                       @RequestParam("eventId") eventId: String) = eventSession.removeAttendee(attendeeId, eventId)

}