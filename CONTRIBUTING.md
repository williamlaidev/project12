# Contributing

## Table of Contents
- [Getting Started](#getting-started)
    - [Main Resources and Packages](#main-resources-and-packages)
    - [Architecture Design](#architecture-design)
- [Standards and Guidelines](#standards-and-guidelines)
    - [Commit & Pull Request Naming](#commit--pull-request-naming)
    - [SOLID Principles](#solid-principles)
    - [Clean Architecture](#clean-architecture)

## Getting Started

Project12 is primarily developed in Java, with some Python scripts used for integrating the Gemini API. This section covers the key resources, packages, and architectural design used in the project.

### Main Resources and Packages

- **External APIs**:
    - **Google Gemini API**: Provides access to summarization features.
    - **Google Places API**: Offers information about places, including geographical data.
    - **GeoLocation API**: Facilitates location-based services and data.
    - **Google Static Map API**: Get map image with a given location and zoom level.
- **Local Repository**:
    - **JSON Files**: Used for local data storage.
- **Database**:
    - **SQLite**: A disk-based database used for storing project data.
- **View**:
    - **Java Swing**: A toolkit for building graphical user interfaces in Java.

### Architecture Design

- **Enterprise Rules**
    - **Entity**: Represents key business components like users or products within the system.
    - **Domain**: Includes the rules and logic that govern these business components.

- **Application Rules**
    - **Use Case**: Specifies the main features and functions provided by the application.

- **Interface Adapters**
    - **Interface Adapter**: Connects the core business logic with external systems or services, transforming data and commands for processing by these external components.
    - **Data Access**: Define the process of retrieving and storing data from databases or other sources.

- **Framework**
    - **API**: Handles interactions with external services and manages request and response flows.
    - **Data Writing**: Manages the processes for saving and updating data within the application.
    - **Database**: Manages the connection to and interaction with the SQLite database.

## Standards and Guidelines

Below are some standards and guidelines to follow:

### Commit & Pull Request Naming

We recommend using Conventional Commits for commit messages and pull request titles. Please adhere to the following format:

```plaintext
<type>[optional scope]: <description>

[optional body]
```

- **type**: The type of change being made. Use the following types:
    - `feat`: A new feature
    - `impr`: An improvement to an existing feature
    - `refactor`: A code change that fixes a bug, improves readability, or involves other changes
    - `docs`: Documentation changes
    - `test`: Adding or correcting tests

- **scope**: The scope of the commit (e.g., package, class, or relevant area).
- **description**: A brief description of the commit.
- **optional body**: A detailed description of the commit, if necessary, using bullet points.

### SOLID Principles

When adding new features, adhere to these principles for clean and maintainable code:

- **Single Responsibility Principle (SRP)**: Each class should have one reason to change, meaning it should only have one job or responsibility.
- **Open/Closed Principle (OCP)**: You should be able to add new features without modifying existing code. Classes or modules should be open for extension but closed for modification.
- **Liskov Substitution Principle (LSP)**: Subtypes should be replaceable with their base types without causing errors or unexpected behavior.
- **Interface Segregation Principle (ISP)**: Avoid forcing clients to use interfaces they don't need. Create smaller, specific interfaces.
- **Dependency Inversion Principle (DIP)**: High-level modules should not depend on low-level modules. Both should depend on abstractions (like interfaces).

### Clean Architecture

Clean Architecture organizes a software system into layers to maintain structure and ease of maintenance:

1. **Entities**: Core components like "User" or "Restaurant," holding the main business rules and independent of other system parts.
2. **Use Cases**: Define specific actions users can perform, like "Create User" or "Search Restaurant," utilizing entities.
3. **Interface Adapters**: Connect your application's core with external systems:
    - **Controllers**: Manage user input.
    - **Gateways**: Handle database or external service connections.
    - **Presenters**: Prepare data for user presentation.
4. **External Interfaces**: Interactions with external systems like databases or APIs, translating data between your app and these systems.

Key rules for Clean Architecture include:

- **Dependency Rule**: Core business logic should not depend on external components like user interfaces or databases. External components should depend on core logic, ensuring stability and easier changes.
- **Use of Interfaces**: Define communication between different system parts using interfaces to avoid direct dependencies, allowing for easier swapping of components.
- **Separation of Concerns**: Keep different system parts focused on specific tasks, making the system easier to understand and maintain.
- **Domain Models**: Use models to represent core business logic and rules, keeping business logic separate from concerns like data storage or user interfaces.
- **Testing**: Write automated tests to ensure your system works as expected and remains functional after changes. Testing helps catch bugs early and ensures reliability.
