# Internship-Project-Web-Department

Description: Application built on Spring Core to keep track of employees and their workload by department.

Technologies:

Java - 11
Security - Basic Authorization.
Database - PostgreSql.
Entities - User (employee), Department, Project, ProjectPosition
ORM: Hibernate
Spring Data JPA
API Documentation: Open API (former Swagger)
Checkstyle/Formatter: Google formatter (https://github.com/google/google-java-format)
Containerize the app with Docker.
DB migrations: Liquibase
Spring Scheduler
Tests - Mockito, JUnit5

Required functionality:
Rest API - CRUD operations for the entities.
Exception handler
API to retrieve list of available employees for assigning onto the new project. Employee is available if he/she doesn’t have active projects. API should accept period (integer, days) as a parameter and return list of employees available now or within the period. If parameter not provided - return only available on the current moment. The response should contain user details, available_from date (by default = current_date for available on the current moment) and available_to date (if user is available on the moment but has planned position within the period).
API for loading users from the CSV file
A job, collecting the statistics about employees workload by department on monthly basis. The statistics must be printed to the Excel-report.
API for the report downloading
Report structure: Employee (FullName) | Department | Project | Occupation
A job, collecting the statistics about employees who will be available within the next 30 days. The statistics must be printed to the Excel-report.
API for the report downloading
Report structure: Employee (FullName) | Department | Project | Project position end date

Models:
User should contain: first name, last name, email, password, job_title, department
Project should contain: title, start date, end date
Department should contain: title
ProjectPosition should contain: user_id, project_id, position start date, position end date, position_title, occupation
Git flow

Branches: master, develop, <feature branch>

Branch naming: feature/step-<step-number>-<short-desc>

Flow: develop -> feature -> PR feature to develop (review step) -> master

PR Title: STEP-<step-number>: <Short description of what has been done>





Development steps:
Step 0 - :
Check development steps and provide estimates for each step
Look through requirements (technologies, functionality, models), write down questions if have some
Create database schema (e.g. with draw.io)
Init git repository
Step 1 - Base App Config:
Create basic spring application pom.xml skeleton
Configure Spring web-application context
Configure database connection
Step 2 - Models and database schema creation:
Create required entity models
Configure database schema creation with Liquibase
Create JPA repositories
Step 3 - Provide CRUD operations:
Create service with CRUD operations and unit tests.
Create spring controllers for CRUD operations and integrations tests
Step 4 - Securing Application:
Configure Spring Security with basic authorization
Auth integration tests
Step 5 - API evolving:
Create service and API for retrieving list of available employees. Cover with tests
Create service and API for loading users from CSV file. Cover with tests.
Step 6 - Reports:
Create services to collect data and print Excel reports per requirements.
Configure scheduled job for reports generating.
Create an API to download last generated report.






