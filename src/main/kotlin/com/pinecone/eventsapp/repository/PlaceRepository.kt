package com.pinecone.eventsapp.repository

import com.pinecone.eventsapp.entity.Place
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlaceRepository : MongoRepository<Place, String>{

    fun findByName(name: String): Optional<Place>
}