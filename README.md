💰 Smart Expense Tracker

A full-stack personal finance management application built with Java, Spring Boot, React, and MySQL. It helps users manage their income, expenses, budgets, and financial activities through an interactive dashboard and detailed financial reports.

The application provides secure authentication, Google OAuth 2.0 login, transaction management, budget tracking, and financial analytics through a responsive user interface.

<p align="center">
  <strong>Track Smarter. Manage Better. Save More.</strong>
</p>---

🌐 Live Demo: https://smart-expense-tracker-frontend-5z24.vercel.app

- API Documentation: https://smart-expense-tracker-backend-nwj5.onrender.com/swagger-ui/index.html#/

📸 Application Preview


<img width="1220" height="2712" alt="Screenshot_20261113-180527_Photos" src="https://github.com/user-attachments/assets/8f150f0b-b1d6-4683-8f55-8b13a4d90882" />

---

🚀 Key Features

🔐 Authentication & Security

- User registration and login.
- JWT-based authentication.
- Google OAuth 2.0 login.
- Password encryption using BCrypt.
- Protected API endpoints.
- Secure user-specific financial data access.

📊 Dashboard

- Overview of total income and expenses.
- Current balance calculation.
- Budget utilization and remaining budget.
- Monthly financial summaries.
- Interactive financial charts.
- Recent transaction history.

💸 Expense Management

- Add, view, update, and delete expenses.
- Categorize expenses.
- Track payment methods.
- Filter expenses by category and date.
- Search and sort transactions.
- Pagination for efficient data retrieval.

💵 Income Management

- Record and manage income sources.
- Update and delete income records.
- View income transaction history.

🎯 Budget Management

- Create monthly budgets.
- Assign budgets to expense categories.
- Track spending against budget limits.
- Monitor remaining budget.
- View budget utilization.

📈 Financial Reports

- Monthly income and expense reports.
- Category-wise expense analysis.
- Budget utilization reports.
- Financial summaries for better money management.

👤 Profile Management

- View and update profile information.
- Change account password.
- Password change confirmation and notification email.

📧 Email Integration

- Email notifications for supported authentication and account activities.
- Password-related email notifications.

📱 Responsive Interface

- Responsive layouts for desktop, tablet, and mobile devices.
- Interactive charts and data visualization.
- User-friendly navigation and forms.

---

🛠️ Technology Stack

Frontend

- React.js
- Vite
- JavaScript
- HTML5
- CSS3
- Axios
- React Router
- Recharts

Backend

- Java 21
- Spring Boot 3.5.x
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- OAuth 2.0
- Maven
- RESTful APIs

Database & Caching

- MySQL
- Redis (planned / include only after implementation and verification)

Development Tools & Deployment

- Git
- GitHub
- IntelliJ IDEA
- Visual Studio Code
- Postman
- Docker
- Vercel (Frontend)
- Render (Backend)
- Aiven (MySQL Database)

---

🏗️ System Architecture

The application follows a layered backend architecture with a separate React frontend.

                 CLIENT
                   |
                   v
             React + Vite
                   |
              Axios / HTTP
                   |
                   v
          Spring Boot REST API
                   |
          Spring Security + JWT
                   |
                   v
              Controllers
                   |
                   v
                Services
                   |
                   v
              Repositories
                   |
                   v
              JPA / Hibernate
                   |
                   v
                MySQL

Application Deployment Architecture

             GitHub Monorepo
                    |
          +---------+---------+
          |                   |
          v                   v
      frontend/            backend/
          |                   |
          v                   v
       Vercel               Render
          |                   |
          |              JDBC Connection
          |                   |
          |                   v
          |                Aiven
          |                 MySQL
          |
          +---- HTTP API ----+

---

📁 Project Structure

smart-expense-tracker/
│
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── hooks/
│   │   ├── context/
│   │   ├── routes/
│   │   └── App.jsx
│   ├── package.json
│   ├── vite.config.js
│   └── .gitignore
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/expensetracker/
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── dto/
│   │   │   │   ├── entity/
│   │   │   │   ├── repository/
│   │   │   │   ├── service/
│   │   │   │   └── security/
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── test/
│   ├── pom.xml
│   ├── Dockerfile
│   └── .gitignore
│
├── .gitignore
└── README.md

Note: Adjust the folder structure above if your actual project uses different package or directory names.

---

⚙️ Getting Started

Follow these instructions to run the application locally.

Prerequisites

Install the following:

- Java 21 or later
- Node.js and npm
- MySQL 8.x
- Maven (or use the Maven wrapper)
- Git

1. Clone the repository

git clone https://github.com/YOUR_USERNAME/smart-expense-tracker.git

cd smart-expense-tracker

2. Backend Setup

Navigate to the backend directory:

cd backend

Configure your environment variables.

Create an "application-local.yml" or configure environment variables in your IDE without committing secrets.

Example environment variables:

DB_URL=jdbc:mysql://localhost:3306/smart_expense_tracker
DB_USERNAME=root
DB_PASSWORD=your_database_password

SECRET_KEY=your_secure_jwt_secret
EXPIRATION=86400000

GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret

MAIL_USERNAME=your_email
MAIL_PASSWORD=your_email_app_password

Configure your database and application settings in "application.yml" to reference these values.

Create the database:

CREATE DATABASE smart_expense_tracker;

Run the backend:

./mvnw spring-boot:run

On Windows:

mvnw.cmd spring-boot:run

Backend will run at:

http://localhost:8080

3. Frontend Setup

Open another terminal:

cd frontend

Install dependencies:

npm install

Create a ".env" file:

VITE_API_BASE_URL=http://localhost:8080

Start the frontend:

npm run dev

Frontend will run at:

http://localhost:5173

---

🔑 Google OAuth Configuration

To enable Google login:

1. Open the "Google Cloud Console" (https://console.cloud.google.com/).
2. Create or select a Google Cloud project.
3. Configure the OAuth consent screen.
4. Create OAuth 2.0 credentials.
5. Add your frontend origin to authorized JavaScript origins if required by your OAuth configuration.
6. Add your backend OAuth callback URL to authorized redirect URIs.

For local development, Spring Security's default callback is:

http://localhost:8080/login/oauth2/code/google

For production, register the exact HTTPS callback URL of your deployed backend.

Never commit Google client secrets to GitHub.

---

🔌 API Modules

The backend exposes REST APIs organized around the following modules.

Module| Responsibility
Authentication| Registration, login, and authentication
Expenses| Expense CRUD and filtering
Income| Income CRUD operations
Budget| Monthly budget management
Dashboard| Financial overview and summaries
Reports| Monthly and category-wise analytics
Profile| Profile and password management

Main API Endpoints

HTTP Method| Endpoint| Description
GET| "/api/dashboard"| Retrieve dashboard data
GET| "/api/expenses"| Retrieve expenses
POST| "/api/expenses"| Add an expense
PUT| "/api/expenses/{id}"| Update an expense
DELETE| "/api/expenses/{id}"| Delete an expense
GET| "/api/incomes"| Retrieve income records
GET| "/api/budgets"| Retrieve budgets
GET| "/api/reports/monthly"| Monthly financial report
GET| "/api/reports/category-wise-expense"| Category-wise expense report
GET| "/api/reports/budget"| Budget report
GET| "/api/profile"| Retrieve user profile
PUT| "/api/profile/update-profile"| Update profile
PUT| "/api/profile/change-password"| Change password

Endpoint paths and HTTP methods should be verified against the actual controller mappings before publishing.

---

🗄️ Database Design

The application uses MySQL as its relational database.

The main domain entities include:

- User: Stores account and authentication information.
- Expense: Stores user expense transactions.
- Income: Stores user income transactions.
- Budget: Stores monthly category-based budget information.
- Category: Represents expense or income categories, depending on the implemented schema.

Relationships are managed using JPA/Hibernate.

All financial records are associated with their respective users to maintain data separation.

---

🔒 Security Considerations

- Passwords are hashed using BCrypt.
- Authentication is implemented using JWT and Google OAuth 2.0.
- Protected endpoints require authentication.
- User-specific data access is enforced by the backend.
- Sensitive credentials are managed through environment variables.
- CORS is configured for authorized frontend origins.
- HTTPS should be used for production deployments.

---

🚀 Deployment

The application is deployed using separate services connected through a single GitHub monorepo.

Component| Platform
Frontend| Vercel
Backend| Render
MySQL Database| Aiven

Both frontend and backend have independent deployment configurations and build processes.

---

🎯 Key Engineering Challenges

The project provided practical experience in:

- Designing layered Spring Boot backend architecture.
- Implementing JWT authentication and Google OAuth 2.0.
- Managing relational database entities and relationships using JPA/Hibernate.
- Building secure REST APIs.
- Integrating React with Spring Boot.
- Implementing filtering, sorting, searching, and pagination.
- Generating monthly and category-wise financial analytics.
- Handling frontend-backend integration and deployment across multiple cloud services.

---

👨‍💻 Author

Aayush Sharma

Aspiring Software Engineer | Java Backend Developer | Full-Stack Development Enthusiast

- GitHub: "aayush240805" (https://github.com/aayush240805)
- LinkedIn: "Aayush Sharma" (https://www.linkedin.com/in/aayush-sharma-161a752a5)
- Email: aayush240805@gmail.com

---

📄 License

This project is licensed under the MIT License. See the "LICENSE" file for details.

---

<p align="center">
  <strong>Built with Java, Spring Boot, React, and MySQL </strong>
</p>
