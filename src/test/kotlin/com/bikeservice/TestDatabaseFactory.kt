package com.bikeservice.test

import com.bikeservice.data.DatabaseFactory
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import com.bikeservice.data.tables.BikeTable

object TestDatabaseFactory {
    fun init() {
        // Use in-memory H2 database for tests
        val dbUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
        val driver = "org.h2.Driver"

        Database.connect(dbUrl, driver)

        // Create schema directly instead of using Liquibase in tests
        transaction {
            SchemaUtils.create(BikeTable)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    // Clean database between tests
    fun cleanup() {
        transaction {
            SchemaUtils.drop(BikeTable)
            SchemaUtils.create(BikeTable)
        }
    }
}