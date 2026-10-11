# News App

A Java Spring Boot application that pulls top headlines from NewsAPI.org and displays them in a simple web interface.

## Features
- Fetch current top headlines from NewsAPI.org
- Filter by category such as general, business, technology, sports, health, science, entertainment
- Search headlines by keyword
- Responsive UI built with Thymeleaf

## Prerequisites
- Java 17+
- Maven 3.9+
- A NewsAPI.org API key

## Setup
1. Get a free API key from NewsAPI.org: https://newsapi.org/
2. Export the key:
   ```bash
   export NEWS_API_KEY=your_api_key_here
   ```
3. Run the app:
   ```bash
   mvn spring-boot:run
   ```
4. Open the app in a browser:
   ```text
   http://localhost:8080
   ```

## Notes
The app expects the API key in the `NEWS_API_KEY` environment variable or `newsapi.api.key` property.
