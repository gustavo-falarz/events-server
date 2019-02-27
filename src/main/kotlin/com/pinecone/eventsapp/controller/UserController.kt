package com.pinecone.eventsapp.controller

import com.pinecone.eventsapp.entity.User
import com.pinecone.eventsapp.session.UserSession
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class UserController(val userSession: UserSession) {

    @PutMapping
    fun add(@RequestBody user: User) = userSession.add(user)

    @GetMapping
    fun getUsers() = userSession.getUsers()

    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: String) = userSession.findUserById(id)

}
