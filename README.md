# Retail Store Feedback Dashboard

A Spring Boot application that analyzes customer feedback sentiment, enriches each feedback item with AI-generated category + actionable insight, and visualizes results in a dashboard.

## What You Built

This project currently implements:

- A **sentiment analysis pipeline** using Stanford CoreNLP (`SentimentAnalyzer`) on raw feedback text.
- A **data processing backend** (`FeedbackService`) that parses analyzed feedback and prepares summary stats.
- An **AI enrichment layer** (`GeminiService`) that classifies each feedback item and generates actionable recommendations.
- A **web dashboard UI** (Thymeleaf + Bootstrap + Chart.js) for charts, recent feedback, and full feedback table.
- A **REST endpoint** (`/getfeedback`) used by frontend JavaScript to refresh full feedback data.

## Tech Stack

### Backend

- Java 21 (project config in `pom.xml`)
- Spring Boot 3.4.0
- Spring MVC (`spring-boot-starter-web`)
- Thymeleaf (`spring-boot-starter-thymeleaf`)
- Jackson (JSON parsing)
- RestTemplate (HTTP calls to Gemini API)

### NLP / AI

- Stanford CoreNLP 4.5.5 (+ models)
- Google Gemini API (`gemini-2.0-flash` endpoint in config)

### Frontend

- Thymeleaf templates
- Bootstrap 5.3.2 (CDN)
- Chart.js 4.4.1 (CDN)
- Vanilla JavaScript (`fetch`) for dynamic table updates

### Build and Test

- Maven Wrapper (`./mvnw`)
- JUnit 5 + Spring Boot Test

## Project Flow (End-to-End)

1. Raw feedback is stored in `store_feedaback.txt`.
2. `SentimentAnalyzer` reads that file, runs Stanford CoreNLP sentiment analysis, and writes results to `sentiment_feedback_output.txt`.
3. `FeedbackService` reads `sentiment_feedback_output.txt`, parses entries, and calls `GeminiService` for category + actionable insight.
4. `FeedbackController` exposes:
   - `GET /` and `GET /dashboard` -> returns `dashboard.html` with summary model data.
   - `GET /getfeedback` -> returns all enhanced feedback as JSON.
5. `dashboard.html` renders summary cards/charts and fetches full feedback table data from `/getfeedback`.

## File-by-File Guide

### Root

- `pom.xml`: Maven dependencies and Spring Boot plugin.
- `store_feedaback.txt`: Raw customer feedback source data.
- `sentiment_feedback_output.txt`: Generated sentiment analysis output used by web app.
- `HELP.md`: Default Spring Initializr helper doc.

### Java Source (`src/main/java/com/project/retailstorefeedback`)

- `RetailStoreFeedbackApplication.java`: Spring Boot app entry point.
- `SentimentAnalyzer.java`: Standalone NLP processor that reads raw feedback, predicts sentiment, and writes output file.

#### `Controller/`

- `FeedbackController.java`:
  - Loads dashboard model (`FeedbackSummary`) for server-side rendering.
  - Exposes JSON API for all feedback rows.

#### `Services/`

- `FeedbackService.java`:
  - Parses feedback entries from `sentiment_feedback_output.txt`.
  - Enhances each entry via Gemini.
  - Caches enhanced results in memory.
  - Aggregates sentiment/category/department counts for dashboard charts.

- `GeminiService.java`:
  - Calls Gemini API via HTTP POST.
  - Sends prompt and returns generated text response.

#### `Models/`

- `FeedbackEntry.java`: Base feedback data model.
- `EnhancedFeedback.java`: Extends base model with `category` and `actionableInsight`.
- `FeedbackSummary.java`: Aggregated dashboard model (totals + chart maps + recent feedback).

### Resources (`src/main/resources`)

- `application.properties`: App settings, Gemini key/url, logging, Thymeleaf config.
- `templates/dashboard.html`: Main dashboard UI + Chart.js setup + table fetch logic.
- `templates/error.html`: Error view if feedback data cannot be loaded.
- `logback.xml`: Logging configuration.

### Tests

- `src/test/java/com/project/retailstorefeedback/RetailStoreFeedbackApplicationTests.java`: Basic Spring context load test.

## GitHub-Ready Repository Notes

Use this section directly in your GitHub repo to explain the implementation:

- This app combines **traditional NLP (CoreNLP)** with **LLM enrichment (Gemini)** for richer feedback intelligence.
- It uses **server-side rendering with Thymeleaf** for summary content and **client-side fetch** for live table refresh.
- It includes both:
  - an **offline preprocessing step** (`SentimentAnalyzer`), and
  - an **interactive dashboard app** (Spring Boot web layer).
- The dashboard shows:
  - total feedback,
  - sentiment distribution,
  - category distribution,
  - department distribution,
  - recent and full feedback entries.

## Run Guide

### 1) Run tests

```bash
cd '/Users/macbookpro/Desktop/Coding work and Lectures/Java/Spring work/RetailStore-Feedback'
./mvnw test
```

### 2) Start the Spring Boot app

```bash
cd '/Users/macbookpro/Desktop/Coding work and Lectures/Java/Spring work/RetailStore-Feedback'
./mvnw spring-boot:run
```

Open `http://localhost:8080/dashboard`.

### 3) (Optional) Re-generate sentiment output first

If you want fresh sentiment analysis from `store_feedaback.txt`, run the analyzer class before starting the dashboard.

## Current Gaps / Improvements

- `application.properties` currently contains a real `gemini.api-key`; move this to an environment variable before publishing.
- `SentimentAnalyzer` currently uses a hardcoded absolute input path in `main`; make it configurable for portability.
- `FeedbackService` has an `ObjectMapper` field that should be initialized/injected to avoid runtime null issues during JSON parsing.
- Consider adding service-level tests for parsing and aggregation logic.

## Suggested GitHub Topics

`spring-boot`, `thymeleaf`, `java`, `nlp`, `stanford-corenlp`, `gemini-api`, `chartjs`, `feedback-analysis`

## GitHub Pages Setup

This repo now includes a static GitHub Pages entry file at `docs/index.html`.

- In GitHub, open **Settings -> Pages**.
- Set **Source** to `Deploy from a branch`.
- Select branch `main` and folder `/docs`.
- Save, then wait for deployment.

Your project page will load `docs/index.html` instead of showing only markdown.

> Note: GitHub Pages cannot run the Spring Boot backend. For the live dashboard (`/dashboard` and `/getfeedback`), run the app locally with Maven.
