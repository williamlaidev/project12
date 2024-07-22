# Project 12

Project 12 is a Java-based software application developed for the CSC207 Software Design course. It enables users to search for highly-rated restaurants, view their locations on a map, and read summarized reviews. The application integrates Google Maps and Gemini APIs, featuring an interactive map interface, restaurant review summaries, the ability to save unlimited restaurant lists, and support for multiple users.

Currently, users can search for restaurants and view summarized reviews with map visualization. Integration of these features is still in progress. Future updates will focus on finalizing the user interface, implementing saved list functionality, and adding support for multiple users.

## Table of Contents

1. [Installation](#installation)
2. [Configuration](#configuration)
3. [Usage](#usage)
4. [Contributing](#contributing)
5. [Credits](#credits)
6. [Additional Information](#additional-information)

## Installation

To set up Project 12 on your local machine, follow these steps:

### 1. Clone the Repository

Open your terminal or command prompt and run:
```bash
git clone https://github.com/your-repository-url.git
```

### 2. Set Up Python Virtual Environment

**2.1 Navigate to the Project Directory**

Change to the project directory:
```bash
cd /path/to/project12
```

**2.2 Create a Python Virtual Environment**

Create a virtual environment with:
```bash
python -m venv myenv
```

**2.3 Activate the Virtual Environment**

- On Windows:
  ```bash
  myenv\Scripts\activate
  ```
- On Unix or MacOS:
  ```bash
  source myenv/bin/activate
  ```

**2.4 Install Required Python Packages**

Install the necessary Python packages:
```bash
pip install -r requirements.txt
```

## Configuration

Before running the program, configure the following settings:

### Setting Up API Keys

1. Navigate to the `.env` file in the project directory.
2. Obtain API keys from the Gemini API and Google Maps API, and add them to the `.env` file in the following format:
   ```
   GEMINI_API_KEY="your_gemini_api_key"
   GOOGLE_MAPS_API_KEY="your_google_maps_api_key"
   ```

## Usage

Here's how to use the program, along with common use cases:

### Search Restaurants by Distance and Dish Type on Map

1. Navigate to the app package.
2. Run `start.java`.
3. Select a point on the map.
4. Enter the distance range in meters.
5. Select the restaurant type you want to search for, or choose to search for all types.
6. The terminal will display up to ten restaurants that fit the search criteria.
7. Reselect a point on the map and start the search again.

**Description:**
The application integrates map view with geolocation and restaurant searching. It can search for restaurants within a selected range on the map and filter by restaurant type if desired. Unfortunately, saving restaurant data locally is not yet implemented, though JSON file settings have been set up and tested. The map is currently fixed but will be updated to allow movement like Google Maps in the future. The current view is small, but UI design improvements are planned. The maximum of ten results is a default setting and can be adjusted in the future.

### Search Restaurant Reviews

1. Perform a restaurant search by distance and dish type on the map.
2. Note down the restaurant ID of the restaurant you want to know more about and copy it.
3. Go to the app package in the project and run `ReviewApp`.
4. Paste the restaurant ID.
5. The program will generate up to six reviews of that restaurant obtained from Google Maps.

**Description:**
Due to incomplete local data access integration, this review check cannot be merged with the restaurant search functionality in a single program. The reviews are limited to a maximum of six by default but can be adjusted in the future.

### Get Restaurant Summarized Review

1. Navigate to the test and integration directory.
2. Run the review summary service integration test.
3. You will see a new review returned with content summarizing all default review test cases.

**Description:**
Due to incomplete local data access setup, this feature is not integrated with the above functionalities. However, the program runs well, and you can adjust the context in the test cases to view differences in the summarized text.

## Contributing

For guidelines on contributing to the project, please refer to [CONTRIBUTING.md](CONTRIBUTING.md).

## Credits

Contributors to this project, listed in alphabetical order:

**Brynn**
- Project Design
- Entity Implementation
- Data Access Integration

**Frank**
- Project Design
- Entity Implementation
- Google Maps Map Image API Integration
- Java Swing View Module

**Kera**
- Project Design
- Entity Implementation
- Google Maps Geolocation API Integration
- Google Maps Places API Integration

**William**
- Project Management
- Documentation
- Project Design
- Entity Implementation
- Gemini API Integration
- Google Maps Places API Integration
- Data Access Integration

Special thanks to TA Howard and Instructor Yasaman for their guidance.

## Additional Information

### UML Diagrams

Visual representations of class and sequence diagrams are available [here](https://lucid.app/lucidchart/a25d3238-67e7-49fb-b829-9c842485de22/edit?invitationId=inv_403f0f3f-3919-487a-9133-a82ce071034f).
