# Project 12

Project 12 is a Java-based software project developed as part of the CSC207 Software Design course. This project allows users to search for highly-rated restaurants and view summarized reviews sourced from Yelp. It features a map interface for searching, summarized reviews for each restaurant, and the ability to save unlimited lists of restaurants.

Project 12 aims to provide users with a tool to quickly get a glance at the pros and cons of highly-rated restaurants without having to read multiple reviews.

## Table of Contents
- [Features](#features)
- [Usage](#usage)
- [Roadmap](#roadmap)
- [Technologies Used](#technologies-used)
- [API Documentation](#api-documentation)

## Features
- Displays restaurants with ratings higher than 3 stars.
- Map view for searching restaurants.
- Summarized reviews for each restaurant.
- Customizable saved lists for users.
- Supports multiple user accounts on one device.

## Usage
### Searching Restaurants
1. Select a point on the map.
2. Choose a distance range and cuisine type.
3. The map will display restaurants within the selected range and cuisine type.
4. Click on a restaurant to view more information, including a summarized review.

### Saving Restaurants
1. Users can create multiple lists to save their favorite restaurants.

### User Switch
1. Multiple users can be created and switched between on a single device, allowing multiple people to use the application independently.

## Roadmap
- **Identify and create entities:**
    - Set up entity and attributes for users.
    - Set up entity and attributes for restaurants.
    - Set up entity and attributes for searches.
    - Set up entity and attributes for the map.

- **Set up API and database usage:**
    - Set up API for getting reviews (Yelp).
    - Set up API for summarizing text (ChatGPT).
    - Set up database for storing restaurant information (SQLite).

- **Set up the view window:**
    - Create a search view with map, filter, and result list (Java Swing).
    - Create a view for restaurant information (Java Swing).

- **Set up DAO (Data Access Object):**
    - DAO for connecting entities to the database.
    - DAO for entity and API usage.

- **Implement use cases for Minimum Viable Product:**
    - Implement searching for restaurants with certain criteria from API (Yelp).
    - Implement generating summarized reviews with pros and cons from multiple reviews (ChatGPT).
    - Implement a map view with moving and displaying restaurants.

- **Implement advanced functions:**
    - Add set point on the map for users.
    - Add filtering options (distance, cuisine type, open hours, price).
    - Add saved list function for users.
    - Add multiple users (create and switch between users).

- **Enhance the user interface:**

## Technologies Used
- **Programming Language:** Java
- **Libraries Used:** [List specific Java libraries here]
- **APIs Used:**
    - Yelp API
    - ChatGPT API
- **Database Used:** SQLite

## API Documentation
- **Yelp API:** Used to fetch restaurant data and reviews.
- **ChatGPT API:** Used for generating summarized reviews.
