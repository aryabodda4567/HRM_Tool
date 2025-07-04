# HRM_Tool

HRM_Tool is a Human Resource Management (HRM) application designed to help organizations efficiently manage their departments, employees, and administrative functions. Built with Java and Spring Boot, this tool streamlines HR operations such as department and employee management, admin account creation, and utility operations like encoding, hashing, and date calculations.

## Features

- **Department Management:**  
  - Add, update, delete, and retrieve departments.
  - Manage department codes, names, descriptions, budgets, managers, and shifts.
  - Retrieve lists of department codes and names.

- **Employee Management:**  
  - Update employee records including name, surname, email, phone, address, gender, designation, department, job title, job description, job status, and salary.

- **Admin Management:**  
  - Create admin accounts with secure password hashing.
  - Login functionality for admins with email and password validation.

- **Utility Endpoints:**  
  - Encode/decode strings using Base64.
  - Generate random numbers and strings.
  - Calculate date and time differences.
  - Generate SHA-256 hashes.

## Technologies Used

- Java
- Spring Boot (REST API)
- JPA/Hibernate (Data Access)
- Standard Java Libraries (Base64, Security, Date/Time)

## Getting Started

1. **Clone the repository:**
    ```sh
    git clone git@github.com:aryabodda4567/HRM_Tool.git
    cd HRM_Tool
    ```

2. **Build and Run the Application:**
    - Make sure you have Java and Maven installed.
    - Configure your database (see `application.properties` for settings).
    - Build and run:
      ```sh
      mvn clean install
      mvn spring-boot:run
      ```

3. **API Usage:**
    - The application exposes REST endpoints for managing departments, employees, and admins.
    - Example endpoint:  
      `POST /hrm/admin/create` — Create a new admin  
      `POST /hrm/departments/` — Add a new department  
      `PUT /hrm/departments/` — Update a department  
      `DELETE /hrm/departments/` — Delete a department by code  
      `GET /hrm/utility/get-department-codes-and-names` — Get department codes and names

## Project Structure

- `src/main/java/org/project/hrm_tool/`
  - `admin/` — Admin creation, login, and management
  - `departments/` — Department CRUD operations and queries
  - `employee/` — Employee update and management
  - `utility/` — Helper functions (encoding, hashing, date calculations)
  - `utilityendpoints/` — Utility-related REST endpoints

## License

This project is licensed under the MIT License.

---
*HRM_Tool — Streamline your organization's HR operations with ease!*
