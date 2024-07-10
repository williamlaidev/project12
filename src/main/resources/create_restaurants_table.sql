-- create_tables.sql

-- Table: restaurants
CREATE TABLE IF NOT EXISTS restaurants (
                                           restaurantId INTEGER PRIMARY KEY AUTOINCREMENT,
                                           name TEXT NOT NULL,
                                           latitude REAL,
                                           longitude REAL,
                                           address TEXT,
                                           dishType TEXT,
                                           averageRating REAL,
                                           photoUrl TEXT,
                                           summarizedReview TEXT
);

-- Add more CREATE TABLE statements for other tables if needed