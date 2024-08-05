# Project 12

Project 12 is a Java-based software application developed for the CSC207 Software Design course. It allows users to search for highly-rated restaurants, view their locations on a map, and read summarized reviews. The application integrates Google Maps and Gemini APIs, featuring an interactive map interface, summarized restaurant reviews, the ability to save unlimited restaurant lists, and support for multiple users.

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
python3 -m venv myenv
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
3. If you're encountering any problem on getting these api keys, please contact us and we will provide working api keys.

## Usage

Here's how to use the program, along with common use cases:

### Show the Map View of Your Current Position

1. Run src/main/java/app/Start.java
2. The map view of your current position will be shown in the java swing window.
3. The default map size will be about 700 meters away from your current location. The exact size is depending on your current latitude.

### Choose a Point in the Map View as the Restaurant Searching Center
1. Run src/main/java/app/Start.java
2. Left click in the map view to choose a point that will become the center of searching restaurant.
3. If you don't choose the point, the default search center is the center of the map

Note;
- The size of the view will be larger in future update.
- The map will be movalbe like google map in the future.

### Search Restaurants by Distance

1. Run src/main/java/app/Start.java
2. In the Choose distance text field, enter a distance in meter(numbers only). The restaurant result will be printed out.
3. Only the restaurants within this distance to the searching center will be shown. The maximum search result is 10 restaurants.
4. If you don't enter the distance and left it empty, the default distance is equal to the size of the map.
5. If you enter a double, it will be rounded to an int.
6. If you enter something that is not an int or a double, an error message will be print out.

Note:
- The view size will be larger in future updates. 
- The map will be movable like Google Maps in the future.

### Search Restaurants by Dish Type

1. Run src/main/java/app/Start.java
2. In the Choose dish type box, choose your desired dish type. The restaurant result will be printed out. 
3. Only the restaurants that belong to your chosen dish type will be shown. The maximum search result is 10 restaurants.

Note:
- The program might search for more than ten places, but at most 10 restaurants will be displayed for now.

### Get Restaurant Reviews (Partial)

1. Run src/main/java/ReviewApp.java. 
2. Enter the restaurant ID from the results of the previous restaurant search. 
3. The terminal will print out up to six of the most relevant reviews of the restaurant gathered from Google Maps.

Note:
- This function is not integrated with the restaurant search due to incomplete local data access integration. Currently, as long as the restaurant ID is valid on Google Maps, it will help the user get up to six of the most relevant reviews.
- Future improvements will focus on integrating this feature with restaurant search by distance and dish type so that reviews can be viewed immediately after a search.

### Get Summarized Restaurant Reviews (Partial)
1. Run test/integration/ReviewSummarizeServiceIntegrationTest. 
2. The default test case will use four fake reviews with fake restaurant data. The program will generate a summarized review based on the fake reviews. 
3. You can modify the content of the reviews in the test cases to test the generation and summarization feature.

Note:
- This function is not integrated with the restaurant search and review features due to incomplete local data integration.
- In future updates, when users check restaurant reviews for a restaurant searched on the map, a generated review will also be shown immediately.

## Contributing

For guidelines on contributing to the project, please refer to [CONTRIBUTING.md](CONTRIBUTING.md).

## Credits

Contributors to this project, listed in alphabetical order:

**Brynn**
***; UofT email: brynn.jeon@mail.utoronto.ca ;***
***github id : briskbyte***
- Project Design
- Entity Implementation
- Data Access Integration

**Frank**
***; UofT email: frankd.fu@mail.utoronto.ca ;***
***github id : whyimhere***
- Project Design
- Documentation
- Entity Implementation
- Google Maps Map Image API Integration
- Java Swing View Module

**Kera**
***; UofT email: kera.kim@mail.utoronto.ca ;***
***github id : keraakim***
- Project Design
- Entity Implementation
- Google Maps Geolocation API Integration
- Google Maps Places API Integration

**William**
***; UofT email: weiren.lai@mail.utoronto.ca ;***
***github id : williamlaitpe***
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
