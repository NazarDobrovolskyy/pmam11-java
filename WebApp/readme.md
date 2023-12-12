# Simple Java Web Application with Servlets and JDBC

This is a simple Java web application that demonstrates the use of Servlets and JDBC for basic user management. The application allows you to perform CRUD operations on user data, with basic authentication implemented using a custom filter.

## Features

- **User Management**: Perform CRUD operations on user data (Create, Read, Update, Delete).
- **Authentication**: Secure certain endpoints with basic authentication.
- **Redirects**: Demonstrates how to handle redirects after creating a new user.
- **Serve Static Files**: Serves static files, such as HTML, CSS, and JS.

## Getting Started

1. Open the project in your favorite Java IDE (in my case, IntelliJ IDEA).
2. Set up a database and update the JDBC connection details in `UserDao.java`.
3. Run the application on a servlet container (e.g., Apache Tomcat).
4. Access the application

## Dependencies

- Servlet API (`javax.servlet-api`): Version 3.1.0
- Apache Commons Codec (`commons-codec`): Version 1.15
- MySQL Connector/J (`mysql-connector-java`): Version 8.0.23 (or your preferred version)
