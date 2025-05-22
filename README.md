# Book-Review-System
A Spring Boot-based RESTful API that allows users to register, log in, browse books, and post reviews. The API features JWT authentication, role-based access control, and secure data handling, demonstrating strong backend programming, clean architecture, and security practices.
🚀 Features
✅ User registration & login with JWT Authentication

🔐 Role-based access control: ADMIN and USER

📚 CRUD operations for Books (Admin only)

✍️ Review system for Users (one review per book per user)

🧾 Secure password storage with BCrypt

🧱 Layered architecture: Controller, Service, Repository, DTOs

📦 Built with Spring Boot, Spring Security, JPA, and MySQL/PostgreSQL

🛠️ Technologies Used
Java 17

Spring Boot

Spring Data JPA

Spring Security

JWT (JSON Web Tokens)

Lombok

Maven

MySQL/PostgreSQL

Swagger (optional)

📁 Project Structure
nginx
Copy
Edit
src
├── config            # Security configuration (JWT, filters)
├── controller        # REST API endpoints
├── dto               # Request/response data transfer objects
├── entity            # JPA entities (User, Book, Review, Role)
├── repository        # JPA repositories
├── service           # Business logic
├── exception         # Custom exception handling
└── util              # JWT utility classes
🔐 Authentication & Authorization
JWT is used for stateless user authentication.

Endpoints like /api/books (POST, PUT, DELETE) require ADMIN role.

Users with the USER role can:

View books

Post one review per book

Tokens must be sent via the Authorization header as:

makefile
Copy
Edit
Authorization: Bearer <your_token_here>
📦 API Endpoints Summary
🔑 Auth
POST /api/auth/register — Register new user

POST /api/auth/login — Login and receive JWT token

📚 Books
POST /api/books — Create book (Admin only)

GET /api/books — View all books

PUT /api/books/{id} — Update book (Admin only)

DELETE /api/books/{id} — Delete book (Admin only)

✍️ Reviews
POST /api/reviews/{bookId} — Add review (User only)

GET /api/reviews — Get all reviews

DELETE /api/reviews/{id} — Delete review (User only)

⚙️ Getting Started
Prerequisites
Java 17+

Maven

MySQL or PostgreSQL

(Optional) Postman for testing

Setup
bash
Copy
Edit
# Clone the repo
git clone https://github.com/your-username/book-review-api.git
cd book-review-api

# Configure database in application.properties

# Run the project
mvn spring-boot:run
📄 Sample .application.properties
properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/book_review_db
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

app.jwt.secret=your_jwt_secret
app.jwt.expiration=3600000
🧪 Testing the API
Use Postman or Swagger UI to test endpoints.

Register a user

Log in and get a JWT token

Use token to access protected routes

Try out book creation (admin) and posting reviews

💡 Future Enhancements
Add pagination and sorting

Add Swagger API documentation

Deploy on AWS/GCP/Render

Add email verification and password reset

🧑‍💻 Author
Mayank — Backend Developer | Spring Boot & Java Enthusiast
Passionate about building secure and scalable REST APIs

🤝 Contributing
Feel free to fork the project, raise issues or suggest features via Pull Requests.

📜 License
This project is open-source and available under the MIT License.

