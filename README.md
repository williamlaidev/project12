# Project 12

**Project 12** is a Java-based software application developed as part of the CSC207 Software Design course. This application allows users to search for top-rated restaurants, view their locations on an interactive map, and access both authentic user reviews and AI-generated summaries. It seamlessly integrates the Google Maps and Gemini APIs, offering an intuitive experience for restaurant discovery and review summarization.

Future enhancements will focus on simplifying the project structure, refining the user interface, introducing a bookmarking feature for saving favorite restaurants, and supporting multi-user functionality.

## Table of Contents

1. [Installation](#installation)
2. [Configuration](#configuration)
3. [Usage](#usage)
4. [Contributing](#contributing)
5. [Credits](#credits)

## Installation

To set up the project on your local machine, follow these steps:

### 1. Clone the Repository

Open your terminal and run:
```bash
git clone https://github.com/your-repository-url.git
```

### 2. Set Up a Python Virtual Environment

**2.1 Navigate to the Project Directory**

```bash
cd /path/to/project12
```

**2.2 Create a Virtual Environment**

```bash
python3 -m venv myenv
```

**2.3 Activate the Virtual Environment**

- On Windows:
  ```bash
  myenv\Scripts\activate
  ```
- On Unix or macOS:
  ```bash
  source myenv/bin/activate
  ```

**2.4 Install Required Packages**

```bash
pip install -r requirements.txt
```

## Configuration

Before running the program, ensure the following configurations:

### API Key Setup

1. Open the `.env` file in the project directory.
2. Add your Gemini API and Google Maps API keys as follows:
   ```
   GEMINI_API_KEY="your_gemini_api_key"
   GOOGLE_MAPS_API_KEY="your_google_maps_api_key"
   ```
3. For assistance in obtaining API keys, please contact support.

## Usage

Ensure that installation and configuration are complete before using the program.

### Displaying the Map View

1. Run `src/main/java/app/Start.java`.
2. A map centered on your current location will appear in a Java Swing window, using data from the API services.
3. The default map view covers an area approximately 700 meters from your location.

### Adjusting the Map View

1. Run `src/main/java/app/Start.java`.
2. Use the plus and minus buttons at the bottom of the map to zoom in or out.

### Setting a Custom Search Center

1. Run `src/main/java/app/Start.java`.
2. Left-click on the map to set a custom center for restaurant searches.
3. If no point is selected, the search will default to the map's center.

### Searching for Nearby Restaurants

1. Run `src/main/java/app/Start.java`.
2. Enter a distance in meters in the "Choose Distance" field below the map. Restaurants within the specified range will be displayed on the right, with a maximum of 10 results.
3. If no distance is entered, the search will default to the map's visible area.
4. Invalid inputs will trigger an error message.

### Viewing Restaurant Reviews

1. Run `src/main/java/ReviewApp.java`.
2. Follow the steps to search for nearby restaurants.
3. If results are found, click on a restaurant name on the right side of the window to view its reviews.
4. A new window will display a list of up to 10 reviews, including an AI-generated summary at the top.

## Contributing

For detailed information on contributing to this project, please refer to the [CONTRIBUTING.md](CONTRIBUTING.md) file.

## Credits

Contributors to this project, listed in alphabetical order:

**Brynn**
***; UofT email: brynn.jeon@mail.utoronto.ca ;***
***github id : briskbyte***
- Co-designed the project
- Implemented part of the data access functions

**Frank**
***; UofT email: frankd.fu@mail.utoronto.ca ;***
***github id : whyimhere***
- Co-designed the project
- Maintained GitHub repository and project documentation
- Developed zoom in and out functionality for the map UI
- Created search view for restaurant search functionality on the UI
- Designed restaurant view to display search results on the UI
- Developed review view to display restaurant reviews on the UI

**Kera**
***; UofT email: kera.kim@mail.utoronto.ca ;***
***github id : keraakim***
- Co-led and co-designed the project
- Assisted with GitHub repository maintenance
- Implemented user location functionality
- Developed search restaurants with criterium function
- Implemented function to fetch restaurant photos
- Applied clean architecture to the project
- Created and managed project tests

**William**
***; UofT email: weiren.lai@mail.utoronto.ca ;***
***github id : williamlaitpe***
**William**
- Led and designed the project
- Maintained GitHub repository and project documentation
- Implemented search restaurant review functionality
- Developed summary generation for reviews
- Integrated database into the project
- Applied clean architecture and design patterns
- Created and managed project tests

Special thanks to TA Howard and Instructor Yasaman for their guidance.
