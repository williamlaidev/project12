-- initialize_data.sql

-- Insert sample data into restaurants table
INSERT INTO restaurants (name, latitude, longitude, address, dishType, averageRating, photoUrl, summarizedReview)
VALUES
    ('Restaurant A', 40.7128, -74.0060, '123 Main St, New York, NY', 'Italian', 4.5, 'http://example.com/photo1.jpg', 'Great Italian cuisine.'),
    ('Restaurant B', 34.0522, -118.2437, '456 Oak Ave, Los Angeles, CA', 'Mexican', 4.2, 'http://example.com/photo2.jpg', 'Authentic Mexican flavors.');