Weather API
================
Overview
------------
Weather API project

A weather API that fetches and returns weather data from a 3rd party API, utilizing caching and environment variables.

Features
------------
Fetches weather data from Visual Crossing's API (or API of your choice)
Implements in-memory caching using Redis for efficient data retrieval
Utilizes environment variables for secure API key storage
Automatic cache expiration (5-hour default)

Requirements
---------------
Java 21
Maven
Redis
Visual Crossing API key

Installation
---------------
Steps
Clone the repository: git clone https://github.com/your-username/weatherAPI.git
Install dependencies: mvn clean install (Maven) 

Set environment variables:
API_KEY : Your Visual Crossing API key <br>
REDIS_HOST : Your Redis host URL <br>
REDIS_PORT : Your Redis port <br>
REDIS_TIME_TO_LIVE : Your expiration time <br>
Run the application: mvn spring-boot:run (Maven) <br>

Usage
---------
API Endpoints
Get Weather Data

GET /weather/forecast/:city: Fetch weather data for a specific city
Example Request
```bash
GET /weather/forecast/Uttarakhand
Example Response
JSON
{
    "country": "Uttarakhand",
    "temperature": "22.4",
    "description": "Clear conditions throughout the day.",
    "dateTime": "2024-10-19"
}
```

Caching
----------
City names are used as cache keys
Cache expiration: 5 hours (configurable) via application.properties

Contributing
---------------
Contributions are welcome! Please submit a pull request with your changes.

Acknowledgments
----------------
Visual Crossing API for providing free and easy-to-use weather data
Redis for efficient in-memory caching
