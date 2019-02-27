package com.pinecone.eventsapp.session

import com.pinecone.eventsapp.entity.User
import com.pinecone.eventsapp.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserSession(val userRepository: UserRepository):Session() {

    fun add(user: User) = userRepository.save(user)

    fun findUserById(id: String): User {
        val user = userRepository.findById(id)
        when {
            user.isPresent -> return user.get()
            else -> throw Exception(error("error.user-not-found"))
        }
    }

    fun getUsers(): List<User> = userRepository.findAll()

}