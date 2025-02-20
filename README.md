# Journal Scribe

A Spring Boot application for managing and sharing travel memories and experiences.

## Description

Traveller Blog is a web application that allows users to create, manage, and share their travel experiences. Users can write blog posts about their journeys, add photos, and share their adventures with others.

## Features

- Create and manage travel blog posts
- Store travel memories with details
- RESTful API endpoints for blog operations
- Database persistence using MySQL

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL Database
- Maven

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.6 or later
- MySQL 8.0 or later

### Installation

1. Clone the repository

  bash
  git clone https://github.com/Mavihsg/traveller-blog.git

2. Navigate to the project directory

  bash
  cd traveller-blog

3. Configure the database
   - Create a MySQL database named `blog_db`
   - Update `application.properties` with your database credentials

4. Build the project

  bash
  mvn clean install

5. Run the application

  bash
  mvn spring-boot:run

----------------------------------------------

The application will be available at `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/blogs | Get all blog posts |
| GET    | /api/blogs/{id} | Get a specific blog post |
| POST   | /api/blogs | Create a new blog post |
| PUT    | /api/blogs/{id} | Update a blog post |
| DELETE | /api/blogs/{id} | Delete a blog post |

## Database Schema

The application uses a MySQL database with the following main table:

- `memory` - Stores blog post information
  Refer Project report for more details

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Contact

Shivam Gupta - shivamgupta16p80@gmail.com

Project Link: [https://github.com/Mavihsg/traveller-blog](https://github.com/Mavihsg/traveller-blog)
