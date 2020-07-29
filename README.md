# DataLayer

- Runs on 9001
- Spring boot application that handles CRUD operations related to the mortgage data storage
- A limited array holds the records allowing insertion and retrieval of data
- REST endpoints exposed on port 9001 providing the APIs to manipulate data
- Controller, Service, Repository, Model maintained
- Sorting of records achieved using Comparable interface and Comparator function
- Sample Unit Test cases written for the controller
- dockerfile maintained for the microservice
- CustomExceptionHandler added for handling server exceptions
- add a sample RMI that is called by the BusinessLayer


# BusinessLayer

- Runs on 9002
- Spring boot application that does the validations as given in the problem statement
- Consumes REST APIs from DataLayer
- Exposes REST endpoints for the client to perform the required tasks as per problem statement
- Controller, Service, Gateway, DTO maintained
- Validation of RequestBody using javax validation
- Custom Exception Handlers
- dockerfile maintained for the microservice
- openapi-ui dependency added


