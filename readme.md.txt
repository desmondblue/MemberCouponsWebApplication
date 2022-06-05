Member Coupons Web Application

This is a lightweight web application showcasing REST API exposure which can be used as base for developing web-application for any scale.

Technology Stack used: Java, SpringBoot, Postgres

Approach: 
1. Setting up relational database in Postgresql as for the implementation mostly data was relational and easily conforms to the capabilities of RDBMS.
2. Creating a backend using Spring Framework capabilities to expose REST APIs whilst being secured by Basic Authentication.
What else could be used?
The same application could also be made in Flask Python framework since this is at present a lightweight spring boot application.

Why this approach?
1. Developing applicaitons with Java SpringBoot provide portability and since SpringBoot is an intelligent framework it addresses security, functionality and integration.
2. Scalability - A very robust framework which is easily capable of handling Production level requirements with neccessary security measures. 
Also allows easy integration with a wide array of Storage tools which might be needed.
3. Loosely coupled approach - Spring boot allows us to develop web application in a microservices framework which provides high availability

Data Initialization:
I have used a SQL script for initializing dataset/database -path challenge\src\main\java\com\coding\SqlScripts.
The sql query can be run to instantiate dataset

Unit Tests:
1. Testing DAO class to check if the data returned is sorted by valid_until descending
2. Testing Service class to check if data from DAO class is sorted by nearest distance

Trigger REST API:
Use the below curl with basic authentication to trigger the REST API
basic auth credentials : admin/strongpassword123
curl --location --request POST 'http://localhost:8081/coupons/get-member-coupons' \
--header 'Authorization: Basic YWRtaW46c3Ryb25ncGFzc3dvcmQxMjM=' \
--header 'Content-Type: application/json' \
--data-raw '{

    "memberId":"3",
    "longitude":50,
    "latitude":80
}'
