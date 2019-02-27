package com.pinecone.eventsapp.controller

import com.pinecone.eventsapp.entity.Place
import com.pinecone.eventsapp.session.PlaceSession
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/place")
class PlaceController(val placeSession: PlaceSession) {

    @GetMapping
    fun getPlaces(): MutableList<Place> = placeSession.getPlaces()

    @PutMapping
    fun addPlace (@RequestBody place: Place) = placeSession.addPlace(place)

}