# CQRS & Event sourcing pattern
This project is created for practicing CQRS (Command and Query Responsibility Segregation) and Event sourcing pattern. 

## Technology Stack
* [Axon Framework](https://developer.axoniq.io/axon-framework/overview)
* [Axon Server](https://developer.axoniq.io/axon-server/overview)
* [Spring Boot](https://spring.io/projects/spring-boot)

## How to Run

* Download the [Axon server jar](https://download.axoniq.io/axonserver/AxonServer-4.5.12.zip)
* Start the Axon server 
 `java -jar axonserver.jar`
* Run the spring boot application

## Exposed endpoints

* /shipOrder - POST - to create a order and change the state to shipped
* /orders - GET - to list all the available orders
