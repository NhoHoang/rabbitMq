# API for Mobile Developer Test 

**Javis Hoang - CMC Global**

#Table of contents

* [Overview](#overview)
* [Technologies](#technologies)
* [Setup](#setup)
* [Check URL](#url)
* [Explanation](#explanation)



## Overview
This MVP aims to building services for new user registration to integrate with mobile application via API.
Building with Microservice Architecture using Kafka to communication between microservices. 
To speed up the setup of the development environment, a docker-compose is provided to provision the necessary 
infrastructure, i.e. Kafka and Zookeeper. 

## Technologies
- Java 11
- Spring Boot 2.7.1
- Kafka

## Setup
### Run kafka in docker
Run Compose file located at ./docker-compose/docker-compose.yml using docker

``````
docker-compose up -d
``````

#### Service Discovery (Eureka)
- Build/Run
    - mvn clean install
  
#### Gateway Service
- Run
  - mvn clean install
  
#### User Service
- Run
    - mvn clean install
   
- Check
    - localhost:8881/api/v1/user -- expect 0 records returned
#### Notification service
- Run
    - mvn clean install

**Run:**

DiscoveryService, port : 8761

GatewayService, port : 8000

UseService, port : 8881

NotificationService, port : 8882

## Check URL

API Documentation can be found at : http://localhost:8881/swagger-ui/

- Import cURL into Postman for testing. (Call API to gateway at port 8000)
``````
curl --location --request POST 'localhost:8000/api/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "javis123@yopmail.com",
    "password": "5555ffff",
    "salary": 40000
}'
``````

    - Check email received at javis123@yopmail.com
    - Or input your own email in email field

- Validation for the request body:
  - email must be of valid format
  - password must be at least of 8 character in length
  - salary must be 15000 above

## Explanation

1. Design pattern and frameworks: 
  
     This MVP is built with Spring Framework (Spring Boot) & microservice architecture. There are 4 microservices running:
   - Discovery/Service registry (Eureka) – Where all services will register themselves.
   - Gateway -  That will route the requests to the needed microservice.
   - User service – New users will register in here and the record will store in in-memory database H2. 
   - Notification service - Notify the registration successfully to user by sending email.


    * Note: 
      - User Service acts as producer, will send registered user's information into Kafka topic.
      - Notification Service is comsummer, subscribing the topic and will get the user information to sending email.
2. REST API development:
   - Using RestController to consume JSON request body and returning the HttpStatus properly.

3. Data validation and exception handling
   - Using Validator library for validation input of user at Controller level.
   - Besides, Rest Controller Advice is also used to handle the exceptions separately like: 
   - MethodArgumentNotValidException, SQLException, NullPointerException...
  

