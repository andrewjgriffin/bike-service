package com.bikeservice.web.controllers

import com.bikeservice.domain.Bike
import com.bikeservice.service.BikeService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.util.*

class BikeController {
    private val service = BikeService()

    suspend fun getAllBikes(call: ApplicationCall) {
        val bikes = service.getAllBikes()
        call.respond(HttpStatusCode.OK, bikes)
    }

    suspend fun getBikeById(call: ApplicationCall) {
        val id = call.parameters.getOrFail("id")
        val bike = service.getBikeById(id)

        if (bike != null) {
            call.respond(HttpStatusCode.OK, bike)
        } else {
            call.respond(HttpStatusCode.NotFound, mapOf("error" to "Bike not found"))
        }
    }

    suspend fun createBike(call: ApplicationCall) {
        val bike = call.receive<Bike>()
        val createdBike = service.createBike(bike)
        call.respond(HttpStatusCode.Created, createdBike)
    }

    suspend fun updateBike(call: ApplicationCall) {
        val id = call.parameters.getOrFail("id")
        val bike = call.receive<Bike>()

        val updated = service.updateBike(id, bike)
        if (updated) {
            call.respond(HttpStatusCode.OK, mapOf("message" to "Bike updated successfully"))
        } else {
            call.respond(HttpStatusCode.NotFound, mapOf("error" to "Bike not found"))
        }
    }

    suspend fun rideBike(call: ApplicationCall) {
        val id = call.parameters.getOrFail("id")
        val mileageData = call.receive<Map<String, Double>>()
        val miles = mileageData["miles"] ?: throw IllegalArgumentException("Miles is required")

        val updated = service.addMileage(id, miles)
        if (updated) {
            call.respond(HttpStatusCode.OK, mapOf("message" to "Mileage added successfully"))
        } else {
            call.respond(HttpStatusCode.NotFound, mapOf("error" to "Bike not found"))
        }
    }
}