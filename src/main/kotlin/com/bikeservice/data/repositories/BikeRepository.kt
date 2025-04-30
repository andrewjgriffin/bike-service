package com.bikeservice.data.repositories

import com.bikeservice.data.DatabaseFactory.dbQuery
import com.bikeservice.data.tables.BikeTable
import com.bikeservice.domain.Bike
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import java.util.UUID

class BikeRepository {

    suspend fun getAllBikes(): List<Bike> = dbQuery {
        BikeTable.selectAll()
            .map { row -> rowToBike(row) }
    }

    suspend fun getBikeById(id: String): Bike? = dbQuery {
        BikeTable.select { BikeTable.id eq id }
            .mapNotNull { row -> rowToBike(row) }
            .singleOrNull()
    }

    suspend fun createBike(bike: Bike): Bike = dbQuery {
        val id = bike.id.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()

        BikeTable.insert {
            it[BikeTable.id] = id
            it[name] = bike.name
            it[model] = bike.model
            it[color] = bike.color
            it[mileage] = bike.mileage.toBigDecimal()
        }

        bike.copy(id = id)
    }

    suspend fun updateBike(id: String, bike: Bike): Boolean = dbQuery {
        val updatedRows = BikeTable.update({ BikeTable.id eq id }) {
            it[name] = bike.name
            it[model] = bike.model
            it[color] = bike.color
            it[mileage] = bike.mileage.toBigDecimal()
        }

        updatedRows > 0
    }

    private fun rowToBike(row: ResultRow): Bike {
        return Bike(
            id = row[BikeTable.id],
            name = row[BikeTable.name],
            model = row[BikeTable.model],
            color = row[BikeTable.color],
            mileage = row[BikeTable.mileage].toDouble()
        )
    }
}