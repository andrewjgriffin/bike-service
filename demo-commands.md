# Bike Service API Test Commands

This file contains sample curl commands to test the API endpoints, which will be useful during the demo.

## Get All Bikes

```bash
curl -X GET http://localhost:8080/api/bikes | json_pp
```

## Create a New Bike

```bash
curl -X POST http://localhost:8080/api/bikes \
  -H "Content-Type: application/json" \
  -d '{"name": "Windsurfer", "model": "Specialized Aethos", "color": "Teal"}' | json_pp
```

## Get All Bikes

```bash
curl -X GET http://localhost:8080/api/bikes | json_pp
```

## Get Bike by ID

Note: The actual ID will be returned from the getAllBikes endpoint. Replace the placeholder below with the actual ID.

```bash
# Replace BIKE_ID with an actual ID from the getAllBikes response
curl -X GET http://localhost:8080/api/bikes/{BIKE_ID} | json_pp
```

## Update a Bike

```bash
# Replace BIKE_ID with an actual ID
curl -X PUT http://localhost:8080/api/bikes/{BIKE_ID} \
  -H "Content-Type: application/json" \
  -d '{"name": "Windsurfer", "model": "Specialized Aethos", "color": "Teal"}' | json_pp
```

## Add Mileage to a Bike

```bash
# Replace BIKE_ID with an actual ID
curl -X POST http://localhost:8080/api/bikes/{BIKE_ID}/ride \
  -H "Content-Type: application/json" \
  -d '{"miles": 15.5}' | json_pp
```

## For Demo Tasks - Future Implementation

1. Check out new branch, ask windsurf to run through the repo architecture
   2. I am new to the bike service team, can you explain this repo to me so that I can have an idea of the architecture and I cam come in and make changes?
   3. Great, that is very helpful - is there information in the readme or configuration that can help me to access the database locally to see what models exist?
   4. nice, that would be helpful - how do i set up a local h2 database viewer?

2. Ask windsurf to implement a service test for addMileage
   3.
Ope, looks like my colleagues left one of the service methods untested in @BikeService.kt . Can you implement a test in @BikeServiceTest.kt for the @addMileage method?

