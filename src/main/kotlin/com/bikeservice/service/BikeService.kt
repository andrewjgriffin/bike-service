package com.bikeservice.service

import com.bikeservice.data.repositories.BikeRepository
import com.bikeservice.domain.Bike

class BikeService {
    private val repository = BikeRepository()

    suspend fun getAllBikes(): List<Bike> {
        return repository.getAllBikes()
    }

    suspend fun getBikeById(id: String): Bike? {
        return repository.getBikeById(id)
    }

    suspend fun createBike(bike: Bike): Bike {
        return repository.createBike(bike)
    }

    suspend fun updateBike(id: String, bike: Bike): Boolean {
        return repository.updateBike(id, bike)
    }

    suspend fun addMileage(id: String, miles: Double): Boolean {
        val bike = repository.getBikeById(id) ?: return false
        val updatedBike = bike.copy(mileage = bike.mileage + miles)
        return repository.updateBike(id, updatedBike)
    }
}