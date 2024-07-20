# Contributing

## Table of Contents
- [Getting Started](#getting-started)
- [Standards and Guidelines](#standards-and-guidelines)
    - [Commit & Pull Request Naming](#commit--pull-request-naming)
    - [SOLID Principles](#solid-principles)
    - [Clean Architecture](#clean-architecture)

## Getting Started

Project12 mainly uses Java, with some Python for the Gemini API integration. We utilize the following technologies and tools:

- **APIs**: Gemini API, Yelp API, Google Places API, GeoLocation API
- **Database**: SQLite
- **View**: Java Swing

## Standards and Guidelines

Below are some standards and guidelines we encourage you to follow.

### Commit & Pull Request Naming

We encourage using Conventional Commits for commit messages and pull request titles. Please follow these guidelines:

The format for Conventional Commits is:

```plaintext
<type>[optional scope]: <description>

[optional body]
```

- **type**: The type of change being made. Use the following types:

    - `feat`: A new feature
    - `impr`: An improvement to an existing feature
    - `refactor`: A code change that fixes a bug, makes the code easier to read, understand, or involves other changes
    - `docs`: Documentation only changes
    - `test`: Adding missing tests or correcting existing tests

- **scope**: The scope of the commit (e.g., package, class, or other relevant areas).
- **description**: A short description of the commit.
- **optional body**: A detailed description of the commit, presented in bullet points.

### SOLID Principles

When working on new features, keep these principles in mind to ensure clean and maintainable code:

- **Single Responsibility Principle (SRP)**: Each class should have just one reason to change. In other words, it should only have one job or responsibility.
- **Open/Closed Principle (OCP)**: You should be able to add new features without changing existing code. This means classes or modules should be open for new functionality but closed for modifications.
- **Liskov Substitution Principle (LSP)**: Subtypes should be able to replace their base types without causing errors or unexpected behavior.
- **Interface Segregation Principle (ISP)**: Avoid forcing clients to use interfaces they don’t need. Instead, create smaller, specific interfaces.
- **Dependency Inversion Principle (DIP)**: High-level code should not depend on low-level code. Both should depend on abstractions (like interfaces). This makes your code more flexible and easier to manage.

### Clean Architecture

Clean Architecture divides a software system into layers to keep it organized and easy to maintain:

1. **Entities**: Core parts of your application, like "User" or "Restaurant," holding the main business rules and independent of other parts of the system.
2. **Use Cases**: Define specific actions users can take, like "Create User" or "Search Restaurant," using entities to carry out these actions.
3. **Interface Adapters**: Connect your application's core with the outside world:
    
    - **Controllers**: Manage user input.
    - **Gateways**: Handle connections to databases or external services.
    - **Presenters**: Prepare data to be shown to users.
   
4. **External Interfaces**: Where your app interacts with external systems like databases, APIs, or user interfaces, translating data back and forth between your app and these systems.

To achieve this, Clean Architecture provides a set of key rules:

- **Dependency Rule**: Your core business logic should not depend on external parts like user interfaces or databases. Instead, these external parts should depend on your core logic. This makes your core logic stable and easier to change without affecting other parts of the system.
- **Use of Interfaces**: Use interfaces to define how different parts of the system communicate without depending on each other directly. This allows you to swap out parts of the system (like changing a database) without breaking the whole system.
- **Separation of Concerns**: Keep different parts of your system focused on specific tasks. For example, your business logic should handle business rules, and your user interface should handle displaying information. This makes your system easier to understand and maintain because each part does one thing well.
- **Domain Models**: Use models to represent the core business logic and rules of your application. This keeps your business logic clean and separate from other concerns like data storage or user interfaces, making it more flexible and easier to maintain.
- **Testing**: Write automated tests to check that your system works as expected and continues to work after changes. Testing helps catch bugs early and ensures that changes do not break existing functionality, making your system more reliable.