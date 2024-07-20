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
   GEMINI_API_KEY=your_gemini_api_key
   GOOGLE_MAPS_API_KEY=your_google_maps_api_key
   ```

## Usage

Here's how to use the program, along with common use cases:

### Search Restaurants by Distance and Dish Type

1. Navigate to the use case package in your IDE.
2. Run the relevant class for searching restaurants based on distance and dish type.

### Search Restaurants by Distance and Nearest/Top Rated

1. Navigate to the use case package in your IDE.
2. Run the relevant class for searching restaurants based on distance and rating.

### Get Restaurant Summarized Review

1. Navigate to the use case package in your IDE.
2. Run the relevant class to get summarized reviews for a restaurant.

### Navigate on Map View

1. Navigate to the use case package in your IDE.
2. Run the relevant class to view restaurant locations on the map.

## Contributing

For guidelines on contributing to the project, please refer to [CONTRIBUTING.md](CONTRIBUTING.md).

## Credits

Contributors to this project, listed in alphabetical order:

**Brynn**
- Project Design
- Entity Implementation
- OpenAI API Integration

**Frank**
- Project Design
- Entity Implementation
- Google Maps API Integration
- Java Swing View Module

**Kera**
- Project Design
- Entity Implementation
- Google Maps API Integration

**William**
- Project Management
- Documentation
- Project Design
- Entity Implementation
- Gemini API Integration
- SQLite Database Integration

Special thanks to TA Howard and Instructor Yasaman for their guidance.

## Additional Information

### UML Diagrams

Visual representations of class and sequence diagrams are available [here](https://lucid.app/lucidchart/a25d3238-67e7-49fb-b829-9c842485de22/edit?invitationId=inv_403f0f3f-3919-487a-9133-a82ce071034f).
