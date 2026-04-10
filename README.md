# Retail Store Feedback Dashboard

This is a Spring Boot application that analyzes retail customer feedback, adds AI-generated insights, and displays the results in a simple dashboard.

## Overview

The project takes feedback from a text file, runs sentiment analysis with Stanford CoreNLP, enriches each item with Google Gemini, and shows the final results in a web dashboard.

It includes:

- sentiment analysis for customer comments
- AI-generated feedback categories and recommendations
- a Spring Boot web dashboard
- a JSON endpoint for loading feedback dynamically

## Tech Stack

- Java 21
- Spring Boot 3.4.0
- Spring MVC
- Thymeleaf
- Stanford CoreNLP 4.5.5
- Google Gemini API
- Jackson for JSON parsing
- Bootstrap and Chart.js on the frontend

## How It Works

1. Raw feedback is stored in `store_feedaback.txt`.
2. `SentimentAnalyzer` reads the file and generates `sentiment_feedback_output.txt`.
3. `FeedbackService` reads the generated file and prepares the dashboard data.
4. `GeminiService` adds a category and actionable insight for each feedback item.
5. `FeedbackController` shows the dashboard and exposes `/getfeedback` as JSON.

## Main Pages and Endpoints

- `GET /` or `GET /dashboard` — opens the feedback dashboard
- `GET /getfeedback` — returns all feedback data as JSON

## Project Structure

- `src/main/java/com/project/retailstorefeedback/RetailStoreFeedbackApplication.java` — main Spring Boot entry point
- `src/main/java/com/project/retailstorefeedback/SentimentAnalyzer.java` — sentiment analysis processor
- `src/main/java/com/project/retailstorefeedback/Controller/FeedbackController.java` — web controller
- `src/main/java/com/project/retailstorefeedback/Services/FeedbackService.java` — parses and prepares feedback data
- `src/main/java/com/project/retailstorefeedback/Services/GeminiService.java` — calls the Gemini API
- `src/main/java/com/project/retailstorefeedback/Models/` — data models used by the app
- `src/main/resources/templates/dashboard.html` — dashboard UI
- `src/main/resources/templates/error.html` — error page

## How to Run

### 1. Run the sentiment analysis step

Make sure the input feedback file exists, then run the analyzer so it creates `sentiment_feedback_output.txt`.

### 2. Start the Spring Boot app

```bash
cd '/Users/macbookpro/Desktop/Coding work and Lectures/Java/Spring work/RetailStore-Feedback'
./mvnw spring-boot:run
```

### 3. Open the dashboard

Go to:

```text
http://localhost:8080/dashboard
```

## Notes

- The project depends on `sentiment_feedback_output.txt`, so that file should be generated before opening the dashboard.
- The sentiment analyzer currently uses a local file path, so it may need to be updated if you move the project to another machine.
- Before publishing publicly, make sure any API keys in `application.properties` are removed or replaced with environment variables.

## Short GitHub Description

Retail Store Feedback Dashboard is a Spring Boot project that analyzes customer feedback with CoreNLP and Gemini, then shows the results in a simple web dashboard.
