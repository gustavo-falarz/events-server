package com.pinecone.eventsapp.session

import com.pinecone.eventsapp.entity.Place
import com.pinecone.eventsapp.repository.PlaceRepository
import org.springframework.stereotype.Component

@Component
class PlaceSession(val placeRepository: PlaceRepository): Session() {

    fun getPlaces(): MutableList<Place> = placeRepository.findAll()

    fun findPlaceById(id: String): Place {
        val placeDb = placeRepository.findById(id)
        return when {
            placeDb.isPresent -> placeDb.get()
            else -> throw Exception(error("error.place-not-found"))
        }
    }

    fun addPlace(place: Place) {
        val placeDb = placeRepository.findByName(place.name)
        when {
            !placeDb.isPresent -> {
                placeRepository.save(place)
            }
            else -> {
                throw Exception(error("error.place-already-registered"))
            }
        }

    }
}