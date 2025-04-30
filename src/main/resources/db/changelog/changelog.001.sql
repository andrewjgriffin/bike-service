--liquibase formatted sql
--changeset author:initial-schema

-- Create bikes table
CREATE TABLE bikes (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    model VARCHAR(100),
    color VARCHAR(50),
    mileage DECIMAL(10, 2) DEFAULT 0.0
);