package com.bikeservice.service

import com.bikeservice.domain.Bike
import com.bikeservice.test.TestDatabaseFactory
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BikeServiceTest {

    private val service = BikeService()

    companion object {
        @BeforeAll
        @JvmStatic
        fun setupDatabase() {
            TestDatabaseFactory.init()
        }
    }

    @AfterEach
    fun tearDown() {
        TestDatabaseFactory.cleanup()
    }

    @Test
    fun `test create and retrieve bike`() = runBlocking {
        // Create a bike
        val bike = Bike(
            name = "Test Bike",
            model = "Test Model",
            color = "Red"
        )

        val createdBike = service.createBike(bike)
        assertNotNull(createdBike.id)

        // Retrieve the bike
        val retrievedBike = service.getBikeById(createdBike.id)
        assertNotNull(retrievedBike)
        assertEquals("Test Bike", retrievedBike.name)
        assertEquals("Test Model", retrievedBike.model)
        assertEquals("Red", retrievedBike.color)
    }

    @Test
    fun `test update bike`() = runBlocking {
        // Create a bike
        val bike = Bike(
            name = "Original Bike",
            model = "Original Model",
            color = "Blue"
        )

        val createdBike = service.createBike(bike)

        // Update the bike
        val updatedBike = createdBike.copy(
            name = "Updated Bike",
            model = "Updated Model"
        )

        val result = service.updateBike(createdBike.id, updatedBike)
        assertTrue(result)

        // Verify the update
        val retrievedBike = service.getBikeById(createdBike.id)
        assertNotNull(retrievedBike)
        assertEquals("Updated Bike", retrievedBike.name)
        assertEquals("Updated Model", retrievedBike.model)
    }

    // TODO: Add test for addMileage
}