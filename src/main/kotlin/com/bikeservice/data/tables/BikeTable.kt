package com.bikeservice.data.tables

import org.jetbrains.exposed.sql.Table

object BikeTable : Table("bikes") {
    val id = varchar("id", 36)
    val name = varchar("name", 100)
    val model = varchar("model", 100).nullable()
    val color = varchar("color", 50).nullable()
    val mileage = decimal("mileage", 10, 2).default(0.0.toBigDecimal())

    override val primaryKey = PrimaryKey(id)
}