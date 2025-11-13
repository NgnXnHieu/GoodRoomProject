# 🏡 Homestay Booking Platform

A full-stack application that enables users to search, book, and manage homestay accommodations across various locations. The platform offers a mobile application for users and a web-based dashboard for administrators.

## 📌 Features

### User Application (Mobile)
- 🔍 Search for homestays by location and preferences
- 🗕️ Book and cancel reservations
- ❤️ View and manage favorite listings
- 🔐 Secure user authentication and profile management

### Admin Dashboard (Web)
- 🏠 Manage homestay listings and details
- 👥 Oversee user accounts and bookings
- 📊 Monitor booking statuses and analytics
- 🔐 Admin authentication and authorization

## 🛠️ Technologies Used

- **Frontend (Admin Dashboard):**
  - HTML, CSS, JavaScript

- **Backend:**
  - Java with Spring Boot
  - Spring Security for authentication
  - Hibernate and JPA for ORM

- **Database:**
  - MySQL

- **Tools & Utilities:**
  - Postman for API testing
  - Maven for project management

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK)
- MySQL Database
- Maven

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/NguyenDuyHungIT17/homestay.git
   cd homestay
   ```

2. **Set up the database:**
   - Create a MySQL database named `homestay`.
   - Import the provided `db.sql` file to set up the schema.

3. **Configure the application:**
   - Update the database connection settings in the `application.properties` file located in the `BE` directory.

4. **Build and run the backend:**
   ```bash
   cd BE
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access the admin dashboard:**
   - Open the `dangnhap.html` file located in the `FE_ADMIN_12/FE/admin` directory using a web browser.

## 📁 Project Structure

```
homestay/
├── BE/                      # Backend source code
├── FE/                      # Frontend source code for user application
├── FE_ADMIN_12/FE/admin/    # Frontend source code for admin dashboard
├── db.sql                   # Database schema
├── Note.txt                 # Additional notes
├── giải thích db.docx       # Database explanation document
└── testapi.txt              # API testing notes
```


## 📬 Contact

For any inquiries or feedback, please reach out to [nguyenduyhungit17@gmail.com](mailto:nguyenduyhungit17@gmail.com).
# GoodRoomProject
