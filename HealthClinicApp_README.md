# Health Clinic Management System

A **console-based Health Clinic Management System** built with **Java 21, JDBC, Maven, and MySQL**. The application follows a layered architecture with separate DTO, DAO, Service, Configuration, and UI components.

The project is designed to manage core clinic operations such as **patients, doctors, appointments, billing, and visit history**, while using MySQL for persistent data storage.

---

## 📌 Project Overview

The Health Clinic Management System provides a structured way to maintain clinic records through a Java console application.

The project demonstrates:

- Java 21 development
- Maven project management
- JDBC-based database connectivity
- MySQL persistence
- CRUD operations
- DAO (Data Access Object) pattern
- Service layer abstraction
- DTOs for transferring application data
- Prepared statements for database operations
- Generated database keys
- Console-based user interaction
- Layered application architecture

The current console UI exposes **Patient Management** directly through the main menu. The codebase also contains DAO/service components for **Doctors, Appointments, Billing, and Visit History**, providing the foundation for extending the console menu to those modules.

---

## ✨ Features

### 1. Patient Management

The application currently provides the following patient operations through the console:

- Register a new patient
- View a patient by ID
- View all patients
- Update patient information
- Delete a patient
- Store patient date of birth
- Store gender, phone number, and email
- Maintain active/inactive status
- Store registration timestamp in the database

### 2. Doctor Management

The project contains a complete DAO and service implementation for:

- Registering doctors
- Finding a doctor by ID
- Listing all doctors
- Updating doctor details
- Deleting doctors
- Maintaining doctor active status

### 3. Appointment Management

Appointment persistence is implemented through JDBC:

- Book an appointment
- Find an appointment by ID
- List all appointments
- Update appointment details
- Cancel/delete an appointment
- Associate an appointment with a patient
- Associate an appointment with a doctor
- Store appointment date/time
- Maintain appointment status

### 4. Billing Management

The billing DAO supports:

- Creating a bill
- Finding a bill by ID
- Listing all bills
- Updating billing information
- Deleting a bill
- Associating a bill with an appointment
- Storing bill amount using `BigDecimal`
- Tracking payment status
- Storing billing date/time

### 5. Visit History

The visit history DAO supports:

- Creating visit records
- Finding visit history by ID
- Listing all visit records
- Updating visit information
- Deleting visit history
- Linking visit history to an appointment
- Storing diagnosis
- Storing prescription
- Storing visit notes

---

## 🏗️ Architecture

The project follows a layered architecture:

```text
                    +----------------------+
                    |      Console UI      |
                    |    ConsoleMenu       |
                    +----------+-----------+
                               |
                               v
                    +----------------------+
                    |    Service Layer     |
                    | PatientService       |
                    | DoctorService        |
                    | AppointmentService   |
                    +----------+-----------+
                               |
                               v
                    +----------------------+
                    |      DAO Layer       |
                    | PatientDAO           |
                    | DoctorDAO            |
                    | AppointmentDAO       |
                    | BillingDAO           |
                    | VisitHistoryDAO      |
                    +----------+-----------+
                               |
                               v
                    +----------------------+
                    |   JDBC / Connection  |
                    | DatabaseConnection   |
                    +----------+-----------+
                               |
                               v
                    +----------------------+
                    |        MySQL         |
                    |  health_clinic_db    |
                    +----------------------+
```

### Layer Responsibilities

#### UI Layer

Responsible for:

- Reading input from the user
- Displaying the console menu
- Calling service methods
- Displaying success/failure messages

Main class:

```text
com.clinic.ui.ConsoleMenu
```

#### Service Layer

Responsible for:

- Providing business-facing operations
- Connecting the UI/application flow with DAO implementations
- Keeping database-access details outside the UI

Examples:

```text
PatientService
PatientServiceImpl
DoctorService
DoctorServiceImpl
AppointmentService
```

#### DAO Layer

Responsible for:

- Executing SQL queries
- Performing CRUD operations
- Mapping `ResultSet` records to Java objects
- Returning generated database IDs

Examples:

```text
PatientDAO
PatientDAOImpl
DoctorDAO
DoctorDAOImpl
AppointmentDAO
AppointmentDAOImpl
BillingDAO
BillingDAOImpl
VisitHistoryDAO
VisitHistoryDAOImpl
```

#### DTO Layer

DTO classes represent clinic domain data.

```text
Patient
Doctor
Appointment
Billing
Specialization
VisitHistory
```

#### Configuration Layer

Responsible for database connectivity.

```text
DatabaseConnection
TestConnection
```

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| Java 21 | Application development |
| Maven | Build and dependency management |
| JDBC | Database communication |
| MySQL | Persistent database |
| MySQL Connector/J 9.4.0 | JDBC driver |
| IntelliJ IDEA | Development environment |
| Git | Version control |

---

## 📂 Project Structure

```text
HealthClinicApp/
│
├── .github/
│   └── modernize/
│
├── .gitignore
├── pom.xml
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── clinic/
│                   │
│                   ├── Main.java
│                   │
│                   ├── config/
│                   │   ├── DatabaseConnection.java
│                   │   └── TestConnection.java
│                   │
│                   ├── dao/
│                   │   ├── AppointmentDAO.java
│                   │   ├── AppointmentDAOImpl.java
│                   │   ├── BillingDAO.java
│                   │   ├── BillingDAOImpl.java
│                   │   ├── DoctorDAO.java
│                   │   ├── DoctorDAOImpl.java
│                   │   ├── PatientDAO.java
│                   │   ├── PatientDAOImpl.java
│                   │   ├── VisitHistoryDAO.java
│                   │   └── VisitHistoryDAOImpl.java
│                   │
│                   ├── dto/
│                   │   ├── Appointment.java
│                   │   ├── Billing.java
│                   │   ├── Doctor.java
│                   │   ├── Patient.java
│                   │   ├── Specialization.java
│                   │   └── VisitHistory.java
│                   │
│                   ├── service/
│                   │   ├── AppointmentService.java
│                   │   ├── DoctorService.java
│                   │   ├── DoctorServiceImpl.java
│                   │   ├── PatientService.java
│                   │   └── PatientServiceImpl.java
│                   │
│                   └── ui/
│                       └── ConsoleMenu.java
│
└── target/
    └── classes/
```

> `target/` contains generated Maven build output and does not need to be maintained manually.

---

## 🗄️ Database

The application uses MySQL with the following database:

```text
health_clinic_db
```

The JDBC connection currently points to:

```text
jdbc:mysql://localhost:3306/health_clinic_db
```

The application expects the MySQL server to be running locally.

### Main Database Tables

Based on the DAO SQL queries, the application works with these tables:

```text
patients
doctors
appointments
billing
visit_history
```

The domain model also contains:

```text
Specialization
```

but the current codebase does not contain a DAO implementation for a specialization table.

---

## 🔐 Database Configuration

The current `DatabaseConnection` class contains:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/health_clinic_db";

private static final String USER = "root";

private static final String PASSWORD = "root";
```

Before running the application, update these values according to your local MySQL configuration.

### Recommended Production Approach

For a real production application, credentials should **not** be hard-coded in source code.

Instead, use:

- Environment variables
- `.properties` configuration
- `.env`/secret management
- Docker secrets
- Cloud secret managers

For example:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

---

## 📋 Expected Database Columns

The Java DAO implementations reference the following columns.

### Patients

```text
patient_id
first_name
last_name
date_of_birth
gender
phone_number
email
is_active
registered_on
```

### Doctors

```text
doctor_id
first_name
last_name
phone_number
email
is_active
```

### Appointments

```text
appointment_id
patient_id
doctor_id
appointment_date
status
```

### Billing

```text
bill_id
appointment_id
amount
payment_status
billing_date
```

### Visit History

```text
visit_id
appointment_id
diagnosis
prescription
visit_notes
```

---

## 🚀 Getting Started

### Prerequisites

Install the following before running the project:

1. Java Development Kit (JDK) 21
2. Maven
3. MySQL Server
4. Git
5. An IDE such as IntelliJ IDEA or Eclipse

Verify Java:

```bash
java -version
```

Expected major version:

```text
21
```

Verify Maven:

```bash
mvn -version
```

---

## 1. Clone the Repository

```bash
git clone <your-repository-url>
```

Move into the project directory:

```bash
cd HealthClinicApp
```

---

## 2. Create the MySQL Database

Open MySQL:

```sql
CREATE DATABASE health_clinic_db;
```

Then select it:

```sql
USE health_clinic_db;
```

Create the tables according to the columns used by the application's DAO layer.

Example structure:

```sql
CREATE TABLE patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    date_of_birth DATE,
    gender VARCHAR(30),
    phone_number VARCHAR(20),
    email VARCHAR(150),
    is_active BOOLEAN DEFAULT TRUE,
    registered_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

```sql
CREATE TABLE doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    phone_number VARCHAR(20),
    email VARCHAR(150),
    is_active BOOLEAN DEFAULT TRUE
);
```

```sql
CREATE TABLE appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date TIMESTAMP NOT NULL,
    status VARCHAR(50),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);
```

```sql
CREATE TABLE billing (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id INT NOT NULL,
    amount DECIMAL(10,2),
    payment_status VARCHAR(50),
    billing_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id)
);
```

```sql
CREATE TABLE visit_history (
    visit_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id INT NOT NULL,
    diagnosis TEXT,
    prescription TEXT,
    visit_notes TEXT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id)
);
```

> Adjust constraints and column lengths according to your clinic's actual database requirements.

---

## 3. Configure Database Credentials

Open:

```text
src/main/java/com/clinic/config/DatabaseConnection.java
```

Update:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/health_clinic_db";

private static final String USER = "root";

private static final String PASSWORD = "root";
```

For example:

```java
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";
```

---

## 4. Build the Project

From the project root:

```bash
mvn clean install
```

If the build completes successfully, Maven will compile the Java source code and create the build output under:

```text
target/
```

---

## 5. Test the Database Connection

The project contains:

```text
com.clinic.config.TestConnection
```

Run this class from your IDE.

If the connection succeeds, it prints:

```text
Database Connected Successfully!
```

If the connection fails, verify:

- MySQL server is running
- Database name is correct
- Username is correct
- Password is correct
- MySQL port is correct
- JDBC URL is correct
- MySQL Connector/J dependency is available

---

## 6. Run the Application

Run:

```text
com.clinic.Main
```

The `Main` class starts:

```java
new ConsoleMenu().start();
```

The application opens the clinic console menu.

---

## 🖥️ Console Menu

The current application menu is:

```text
========== HEALTH CLINIC ==========
1. Register Patient
2. View Patient
3. View All Patients
4. Update Patient
5. Delete Patient
6. Exit
```

---

## 👤 Register Patient

Select:

```text
1
```

The application asks for:

```text
First Name
Last Name
DOB
Gender
Phone
Email
```

Example:

```text
First Name : Priyanka
Last Name : Yadav
DOB (yyyy-mm-dd): 2004-03-04
Gender : Female
Phone : 9876543210
Email : example@gmail.com
```

A new patient is inserted into MySQL and the generated patient ID is displayed.

Example:

```text
Patient Registered Successfully. ID : 1
```

---

## 🔎 View Patient

Select:

```text
2
```

Enter the patient ID:

```text
Enter Patient ID : 1
```

The application retrieves the patient using a parameterized SQL query and prints the corresponding Java object.

If the patient does not exist:

```text
Patient Not Found
```

---

## 📋 View All Patients

Select:

```text
3
```

The application executes a query equivalent to:

```sql
SELECT * FROM patients;
```

Each database row is mapped into a `Patient` object and displayed.

---

## ✏️ Update Patient

Select:

```text
4
```

Enter the patient ID.

The application first retrieves the existing patient record.

Currently, the console flow allows updating:

- Phone number
- Email

The DAO itself supports updating the complete patient record, including:

- First name
- Last name
- Date of birth
- Gender
- Phone number
- Email
- Active status

---

## 🗑️ Delete Patient

Select:

```text
5
```

Enter the patient ID.

The application executes a parameterized delete operation:

```sql
DELETE FROM patients WHERE patient_id = ?;
```

If successful:

```text
Deleted Successfully
```

---

## 🧩 DAO Pattern

One of the important architectural concepts demonstrated in this project is the **DAO Pattern**.

For example:

```text
PatientDAO
      |
      v
PatientDAOImpl
      |
      v
MySQL
```

The interface defines the operations:

```java
int insertPatient(Patient patient);

Patient getPatientById(int patientId);

List<Patient> getAllPatients();

boolean updatePatient(Patient patient);

boolean deletePatient(int patientId);
```

The implementation contains the actual SQL queries.

This separation makes the application easier to maintain and extend.

---

## 🔄 CRUD Operations

The project implements the standard CRUD operations.

| Operation | Meaning | Example |
|---|---|---|
| Create | Insert new record | Register patient |
| Read | Retrieve record | View patient |
| Update | Modify record | Update patient |
| Delete | Remove record | Delete patient |

For example:

```text
CREATE → INSERT
READ   → SELECT
UPDATE → UPDATE
DELETE → DELETE
```

---

## 🔗 JDBC Workflow

The application uses JDBC to communicate with MySQL.

Typical flow:

```text
Java Object
     |
     v
Service
     |
     v
DAO
     |
     v
DatabaseConnection
     |
     v
JDBC Connection
     |
     v
PreparedStatement
     |
     v
MySQL
```

For example, registering a patient:

```text
ConsoleMenu
    ↓
PatientService
    ↓
PatientServiceImpl
    ↓
PatientDAO
    ↓
PatientDAOImpl
    ↓
PreparedStatement
    ↓
INSERT INTO patients
    ↓
MySQL
    ↓
Generated Patient ID
```

---

## 🔒 Prepared Statements

The application uses `PreparedStatement` for database operations.

Example:

```java
String sql = "SELECT * FROM patients WHERE patient_id = ?";

PreparedStatement statement =
        connection.prepareStatement(sql);

statement.setInt(1, patientId);
```

This is preferable to constructing SQL by concatenating user input and helps protect against SQL injection.

---

## 🔑 Generated Keys

When a new record is inserted, the DAO requests generated database keys:

```java
connection.prepareStatement(
    sql,
    Statement.RETURN_GENERATED_KEYS
);
```

After insertion:

```java
ResultSet rs = statement.getGeneratedKeys();

if (rs.next()) {
    return rs.getInt(1);
}
```

This allows the application to immediately return the generated ID.

---

## 🧱 DTO Design

The project uses simple Java classes as DTO/domain objects.

### Patient

```text
Patient
├── patientId
├── firstName
├── lastName
├── dateOfBirth
├── gender
├── phoneNumber
├── email
├── isActive
└── registeredOn
```

### Doctor

```text
Doctor
├── doctorId
├── firstName
├── lastName
├── phoneNumber
├── email
└── isActive
```

### Appointment

```text
Appointment
├── appointmentId
├── patientId
├── doctorId
├── appointmentDate
└── status
```

### Billing

```text
Billing
├── billId
├── appointmentId
├── amount
├── paymentStatus
└── generatedOn
```

### Visit History

```text
VisitHistory
├── visitId
├── appointmentId
├── diagnosis
├── prescription
├── visitNotes
└── createdOn
```

---

## 📦 Maven Configuration

The project uses Maven.

The main dependency is the MySQL JDBC driver:

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.4.0</version>
</dependency>
```

The project targets Java 21:

```xml
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
```

---

## 🧪 Testing the Application

A simple database connectivity test is included:

```text
TestConnection.java
```

Recommended manual test sequence:

### Patient Registration

```text
1 → Enter patient information → Verify generated ID
```

### Patient Retrieval

```text
2 → Enter generated ID → Verify patient information
```

### List Patients

```text
3 → Verify registered patient appears
```

### Patient Update

```text
4 → Enter patient ID → Update phone/email → Verify changes
```

### Patient Delete

```text
5 → Enter patient ID → Verify deletion
```

### Database Verification

You can also verify records directly in MySQL:

```sql
SELECT * FROM patients;
```

---

## ⚠️ Current Limitations

The repository currently has some areas that can be improved.

### Console UI Coverage

The main console menu currently exposes patient operations only:

```text
Register Patient
View Patient
View All Patients
Update Patient
Delete Patient
```

Although DAO implementations exist for doctors, appointments, billing, and visit history, these modules are not currently exposed through the main `ConsoleMenu`.

### Database Credentials

The database username and password are currently stored directly in:

```text
DatabaseConnection.java
```

For production use, credentials should be moved to environment/configuration management.

### Validation

The current console input handling can be improved with validation for:

- Invalid menu input
- Invalid patient IDs
- Invalid dates
- Invalid email addresses
- Invalid phone numbers
- Empty names
- Database constraint violations

### Error Handling

DAO classes currently print stack traces when database operations fail. A production application should use structured exception handling and logging.

### Testing

Automated unit/integration tests are not currently included in the project structure. Adding JUnit and Mockito would improve testability.

---

## 🚀 Future Enhancements

Possible improvements include:

### Console Features

Add menus for:

```text
1. Patient Management
2. Doctor Management
3. Appointment Management
4. Billing Management
5. Visit History
6. Exit
```

### Patient Features

- Search patients by name
- Search by phone/email
- Pagination
- Patient medical history
- Patient status management

### Doctor Features

- Doctor specialization
- Doctor availability
- Search doctors
- Doctor schedules

### Appointment Features

- Appointment booking
- Appointment rescheduling
- Appointment cancellation
- Appointment status tracking
- Doctor availability checking

### Billing Features

- Automatic bill generation
- Payment tracking
- Invoice generation
- Pending payment reports

### Visit History

- Diagnosis records
- Prescription records
- Doctor notes
- Complete patient visit timeline

### Technical Improvements

- Add JUnit 5
- Add Mockito
- Add SLF4J/Logback logging
- Move DB credentials to environment variables
- Add connection pooling
- Add transaction management
- Add custom exception classes
- Add input validation
- Add REST API using Spring Boot
- Add web frontend
- Add authentication and authorization
- Add Docker support
- Add CI/CD pipeline

---

## 🔮 Possible REST API Evolution

The existing layered architecture can be migrated to Spring Boot without completely rewriting the domain/DAO concepts.

A future architecture could look like:

```text
React / Web Client
        |
        v
Spring Boot REST Controller
        |
        v
Service Layer
        |
        v
Repository / DAO Layer
        |
        v
MySQL
```

Possible endpoints:

```text
GET    /api/patients
GET    /api/patients/{id}
POST   /api/patients
PUT    /api/patients/{id}
DELETE /api/patients/{id}

GET    /api/doctors
GET    /api/doctors/{id}
POST   /api/doctors
PUT    /api/doctors/{id}
DELETE /api/doctors/{id}

GET    /api/appointments
POST   /api/appointments
PUT    /api/appointments/{id}
DELETE /api/appointments/{id}
```

---

## 🧠 Key Concepts Demonstrated

This project is useful for learning and demonstrating:

- Core Java
- Object-Oriented Programming
- Interfaces
- Encapsulation
- Collections
- Exception handling
- JDBC
- SQL
- MySQL
- CRUD
- DAO Pattern
- Service Layer Pattern
- DTO Pattern
- Layered Architecture
- Maven
- PreparedStatement
- ResultSet mapping
- Generated keys
- Foreign-key relationships
- Console application development

---

## 📊 Entity Relationship Overview

The main business relationships can be represented conceptually as:

```text
Patient
   |
   | 1
   |
   | N
Appointment
   |
   | N
   |
Doctor

Appointment
   |
   | 1
   |
   +---------> Billing

Appointment
   |
   | 1
   |
   +---------> Visit History
```

In other words:

- One patient can have multiple appointments.
- One doctor can have multiple appointments.
- An appointment connects a patient and a doctor.
- An appointment can have associated billing information.
- An appointment can have associated visit history.

---

## 🔧 Build Commands

Clean the project:

```bash
mvn clean
```

Compile:

```bash
mvn compile
```

Run the package phase:

```bash
mvn package
```

Install into the local Maven repository:

```bash
mvn clean install
```

---

## 📝 Git Workflow

Typical workflow:

```bash
git status
```

```bash
git add .
```

```bash
git commit -m "Update clinic management system"
```

```bash
git push origin main
```

Do not commit passwords, API keys, or other secrets to Git.

---

## 👩‍💻 Author

**Priyanka Yadav**

B.Tech Computer Science Engineering

---

## 📄 License

This project can be used for educational and portfolio purposes. Add an explicit open-source license such as MIT if you intend to distribute the project under an open-source license.

---

## ⭐ Project Summary

**Health Clinic Management System** is a Java 21 + JDBC + MySQL console application demonstrating how a real-world clinic management domain can be organized using a layered architecture.

The application currently provides a working console-based patient CRUD workflow while the underlying codebase also includes database-access components for doctors, appointments, billing, and visit history. Its architecture provides a good foundation for extending the application into a complete clinic management platform or migrating it to a Spring Boot REST API in the future.
