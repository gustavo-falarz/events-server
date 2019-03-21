package com.pinecone.eventsapp.controller

import com.pinecone.eventsapp.entity.Attendee
import com.pinecone.eventsapp.session.AttendeeSession
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/attendee")
class AttendeeController(val attendeeSession: AttendeeSession) {

    @PutMapping
    fun add(@RequestBody attendee: Attendee) = attendeeSession.add(attendee)

    @GetMapping
    fun getAttendees() = attendeeSession.getAttendees()

    @GetMapping("/update-role")
    fun updateRole(@RequestParam("userId") userId: String,
                   @RequestParam("role") role: Attendee.Role) =attendeeSession.updateRole(userId, role)

}
