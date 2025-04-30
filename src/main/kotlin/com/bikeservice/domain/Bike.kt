package com.bikeservice.domain

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Bike(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val model: String? = null,
    val color: String? = null,
    val mileage: Double = 0.0
)