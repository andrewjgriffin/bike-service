# Bike Service API

A Kotlin-based microservice for tracking bike mileage and component lifecycle management.

## Overview

This service provides a RESTful API for managing bicycles and their components (wheels, drivetrain, tires), tracking mileage, and monitoring component lifecycle. Built using Domain-Driven Design principles, it demonstrates real-world development patterns for maintaining and evolving a production-ready service.

## Features

- Track total mileage for bicycles
- Manage bike components with lifecycle tracking
- Record individual rides with timestamps
- Component replacement and status tracking
- Migration patterns for schema evolution

## Tech Stack

- **Language**: Kotlin
- **Framework**: Ktor
- **Database**: H2 (file mode)
- **Migration**: Liquibase
- **DB Access**: Exposed
- **Build Tool**: Gradle

## Project Structure

The project follows a Domain-Driven Design architecture:

```
bike-service/
├── api/            # API definitions and documentation
├── web/            # Controllers and HTTP handling
├── service/        # Business logic and service layer
├── domain/         # Domain models and business rules
├── data/           # Data access and repositories
└── resources/      # Configuration and migrations
```

## Running Locally

### Prerequisites
- JDK 11 or higher
- Gradle

### Steps
1. Clone the repository:
   ```
   git clone https://github.com/yourusername/bike-service.git
   cd bike-service
   ```

2. Run the application:
   ```
   ./gradlew run
   ```

3. The API will be available at `http://localhost:8080`

## API Endpoints

### Bikes
- `GET /bikes` - List all bikes
- `POST /bikes` - Create a new bike
- `GET /bikes/{id}` - Get bike details
- `PUT /bikes/{id}` - Update bike details
- `DELETE /bikes/{id}` - Delete a bike

### Rides
- `POST /bikes/{id}/ride` - Record a ride for a bike
- `GET /bikes/{id}/rides` - Get ride history

### Components
- `GET /bikes/{id}/components` - List all components for a bike
- `POST /bikes/{id}/components` - Add a component to a bike
- `PUT /bikes/{id}/components/{id}` - Update component (including status)
- `DELETE /bikes/{id}/components/{id}` - Remove component

## Database Migrations

Migrations are managed by Liquibase and automatically run at startup. Migration scripts are located in `src/main/resources/db/migrations/`.

## Development

### Branching Strategy
- `main` - Stable version of the application
- `demo` - Branch for demo implementation of the component-specific mileage feature

To start implementing a new feature:
```
git checkout -b feature/your-feature-name
```

### Building
```
./gradlew build
```

### Testing
```
./gradlew test
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.