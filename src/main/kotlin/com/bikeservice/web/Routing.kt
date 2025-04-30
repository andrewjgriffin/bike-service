package com.bikeservice.web

import com.bikeservice.web.controllers.BikeController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/api") {
            bikeRoutes()
        }
    }
}

fun Route.bikeRoutes() {
    val controller = BikeController()

    route("/bikes") {
        // Get all bikes
        get {
            controller.getAllBikes(this.context)
        }

        // Get bike by id
        get("/{id}") {
            controller.getBikeById(this.context)
        }

        // Create a new bike
        post {
            controller.createBike(this.context)
        }

        // Update a bike
        put("/{id}") {
            controller.updateBike(this.context)
        }

        // Ride a bike
        post("/{id}/ride") {
            controller.rideBike(this.context)
        }
    }
}