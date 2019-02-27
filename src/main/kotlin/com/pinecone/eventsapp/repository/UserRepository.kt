package com.pinecone.eventsapp.repository

import com.pinecone.eventsapp.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String>