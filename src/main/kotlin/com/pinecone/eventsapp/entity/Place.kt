package com.pinecone.eventsapp.entity

import org.springframework.data.annotation.Id
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Place(var name: String,
            var address: Address,
            var point: Point) {
    @Id
    var id: String? = null
}