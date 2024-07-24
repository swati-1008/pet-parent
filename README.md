# Purrs n' Paws

Purrs n' Paws is a comprehensive platform for pet parents, offering a wide range of features including e-commerce shopping, vet consultations, chat rooms, social networking, pet care blogs, and nearby events.

## Features

- **User Authentication**: Secure login/signup with JWT.
- **Landing Page**: Search bar with trending and relevant suggestions, carousel for features and blogs, and more.
- **Social Network**: Instagram-like clone with reels, posts, likes, comments, and more.
- **Future Updates**: E-commerce, vet consultations, chat rooms, and events.

## Technologies Used

- **Backend**: Java, Spring Boot, JPA
- **Frontend**: React, Redux
- **Database**: PostgreSQL
- **Authentication**: JWT

## Installation

### Prerequisites

- Node.js and npm installed
- PostgreSQL installed and running
- Java JDK installed
- Maven installed

### Backend Setup

1. Clone the repository and navigate to the backend directory
   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name/BackEnd
2. Setup environment variables
   Update the `application.properties` file in src/main/resources directory and add your environment variables
   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   jwt.secret=your_jwt_secret
3. Build the project using Maven
   ```bash
   mvn clean install
4. Run the backend server
   ```bash
   mvn spring-boot:run

### Frontend Setup

1. Navigate to the frontend directory
   ```bash
   cd ../FrontEnd
2. Install dependencies
   ```bash
   npm install
3. Set up environment variables
   Create a .env file in the frontend directory and add your environment variables
   ```
   REACT_APP_API_BASE_URL=http://localhost:8080
4. Start the frontend development server
   ```
   npm start

### Usage

1. Open your browser and navigate to http://localhost:3000.
2. Sign up or log in to access the application.
3. Use the application features as intended.

### API Endpoints 

## Authentication
POST /user/signup - Sign up a new user
POST /user/login - Log in a user and receive a JWT token
