package com.bikeservice.data

import com.bikeservice.data.tables.BikeTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object DataInitializer {
    fun populateSampleData() {
        transaction {
            // Only add sample data if the table is empty
            if (BikeTable.selectAll().count() == 0L) {
                println("Adding sample bike data...")

                // Add some bikes
                BikeTable.insert {
                    it[id] = UUID.randomUUID().toString()
                    it[name] = "Mountain Explorer"
                    it[model] = "Trek Fuel EX 8"
                    it[color] = "Red"
                    it[mileage] = 125.5.toBigDecimal()
                }

                BikeTable.insert {
                    it[id] = UUID.randomUUID().toString()
                    it[name] = "Road Warrior"
                    it[model] = "Specialized Tarmac"
                    it[color] = "Blue"
                    it[mileage] = 350.75.toBigDecimal()
                }

                BikeTable.insert {
                    it[id] = UUID.randomUUID().toString()
                    it[name] = "City Commuter"
                    it[model] = "Giant Escape"
                    it[color] = "Black"
                    it[mileage] = 750.25.toBigDecimal()
                }

                println("Sample bike data added successfully")
            } else {
                println("Database already contains bike data, skipping initialization")
            }
        }
    }
}