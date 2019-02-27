package com.pinecone.eventsapp

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.util.ResourceUtils

@SpringBootApplication
class EventsAppApplication

fun main(args: Array<String>) {
    runApplication<EventsAppApplication>(*args)

    val file = ResourceUtils.getFile("classpath:serviceAccountKey.json")
    val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(file.inputStream()))
            .setDatabaseUrl("https://events-app-492a4.firebaseio.com")
            .build()
    FirebaseApp.initializeApp(options)
}
