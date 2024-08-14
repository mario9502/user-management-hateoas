# User Management

User management is a simple REST-API with implemented HATEOAS using the standard HTTP methods –  GET, POST, PUT, DELETE.

## Description

It was build with Spring Boot with Maven on Java 17. For database was used MySQL so if not present MySQL server needs to be instelled. The app is runnunig on port 8080.
For database properties check the "application.yaml' file in the "resources" folder.

## Getting Started

### Dependencies

* ModelMapper was used for converting POJO to DTO and vice versa

### Executing program

Run the "UserManagementAppTestApplication"

### Database Requirements

Its support one User Entity with the following fields:
* First Name
* Last Name
* Date of Birth
* Phone Number
* E-mail Address

## Testing Functionality

To test the functionality you need to use Postman and send POST Request on http://localhost:8080/users/add - Add user
With body: 

{
    "firstName": "First",
    "lastName": "Last",
    "dateOfBirth": "1991-11-02",
    "phoneNumber": "0878286577",
    "email": "mail@mail"
}

And after that follow the integrated links

