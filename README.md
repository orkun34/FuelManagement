# FuelManagement

Project Nebula is a kind of product review check application that handles inappropriate words which comes from client review.

Modules that are used in projects are;
 - spring-boot
 - spring-aop
 - spring-data-jpa
 - h2

##### Brief
  - NamedNativeQueries
  - Using Optional<String> rest controller in order to remove duplicate service and repository definitions
  - N-tier layer
  - Customer exception handling

#### Run the project
Go to project folder and run
```sh
mvn spring-boot:run
```
After that you can run cURL requests
(sample)
```sh
curl 'http://localhost:8282/monthlyExpenses' -X GET
```
According to default application.properties , server will run at 8282 port
In addition, there is also .txt file in order to test bulk insert under /demoRequests/bulkInsert.txt via file upload service
#### Docker usage
Run below commands in order
```sh
mvn clean install
docker-compose up fuelmng-app
```
After container successfully deployed , run below command from shell;
```sh
docker exec -it fuelmng-cont-springboot bash
```
You will be inside of container and you can test;
```sh
curl 'http://localhost:8282/monthlyExpenses' -X POST -d "{"fuelType":"Diesel","fuelType":"Diesel","driverId":"234KK","consumptionDate":"2018-02-21","price":10.3,"volume":77.2}" -H "Content-type:application/json"
```