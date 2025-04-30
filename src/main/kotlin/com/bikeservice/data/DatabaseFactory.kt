package com.bikeservice.data

import kotlinx.coroutines.Dispatchers
import liquibase.Liquibase
import liquibase.database.DatabaseFactory as LiquibaseDatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.h2.tools.Server
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.sql.DriverManager

object DatabaseFactory {
    fun init() {
        println("Initializing database...")

        // Start H2 server for console access (optional)
        val server = Server.createTcpServer("-tcpAllowOthers").start()
        println("H2 server started on port ${server.port}")

        // Connect to the database
        val dbUrl = "jdbc:h2:file:./bikedb;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE"
        val driver = "org.h2.Driver"

        try {
            Database.connect(dbUrl, driver)
            println("Database connected")

            // Run Liquibase migrations
            val connection = DriverManager.getConnection(dbUrl)
            val database = LiquibaseDatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(JdbcConnection(connection))

            val liquibase = Liquibase(
                "db/changelog/master.xml",
                ClassLoaderResourceAccessor(),
                database
            )

            try {
                // Use the non-deprecated update method
                liquibase.update("")
                println("Liquibase migrations completed successfully")
            } catch (e: Exception) {
                println("Error running Liquibase migrations: ${e.message}")
                e.printStackTrace()
            }

            // Populate sample bike data if needed
            DataInitializer.populateSampleData()

        } catch (e: Exception) {
            println("Error initializing database: ${e.message}")
            e.printStackTrace()
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}