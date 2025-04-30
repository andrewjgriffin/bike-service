# Bike Service API Test Commands

This file contains sample curl commands to test the API endpoints, which will be useful during the demo.

## Get All Bikes

```bash
curl -X GET http://localhost:8080/api/bikes | json_pp
```

## Get Bike by ID

Note: The actual ID will be returned from the getAllBikes endpoint. Replace the placeholder below with the actual ID.

```bash
# Replace BIKE_ID with an actual ID from the getAllBikes response
curl -X GET http://localhost:8080/api/bikes/BIKE_ID | json_pp
```

## Create a New Bike

```bash
curl -X POST http://localhost:8080/api/bikes \
  -H "Content-Type: application/json" \
  -d '{"name": "Gravel King", "model": "Cannondale Topstone", "color": "Green"}' | json_pp
```

## Update a Bike

```bash
# Replace BIKE_ID with an actual ID
curl -X PUT http://localhost:8080/api/bikes/BIKE_ID \
  -H "Content-Type: application/json" \
  -d '{"name": "Gravel King Pro", "model": "Cannondale Topstone Carbon", "color": "Green", "mileage": 0.0}' | json_pp
```

## Add Mileage to a Bike

```bash
# Replace BIKE_ID with an actual ID
curl -X POST http://localhost:8080/api/bikes/BIKE_ID/ride \
  -H "Content-Type: application/json" \
  -d '{"miles": 15.5}' | json_pp
```

## For Demo Tasks - Future Implementation

During the demo, we'll implement these additional features:

### 1. Add Component Management

```bash
# Get components for a bike
curl -X GET http://localhost:8080/api/bikes/BIKE_ID/components | json_pp

# Add a component to a bike
curl -X POST http://localhost:8080/api/bikes/BIKE_ID/components \
  -H "Content-Type: application/json" \
  -d '{"name": "Continental GP5000", "type": "TIRE"}' | json_pp

# Replace a component
curl -X PUT http://localhost:8080/api/bikes/BIKE_ID/components/COMPONENT_ID \
  -H "Content-Type: application/json" \
  -d '{"isActive": false}' | json_pp
```

### 2. Add Component Mileage Tracking

```bash
# Get component mileage
curl -X GET http://localhost:8080/api/components/COMPONENT_ID/mileage | json_pp
```