<h4>Steps to Setup</h4>

1. Clone the application
git clone https://github.com/stutirastogi/scheduler.git

2. Create Mysql database
create database task_manager

3. Change mysql username and password as per your installation
open src/main/resources/application.properties
change spring.datasource.username and spring.datasource.password as per your mysql installation

4. Build and run the app 
mvn spring-boot:run

  The app will start running at http://localhost:8080.

<h4>Explore Rest APIs</h4>
The documentation for rest apis can be found at http://localhost:8080/swagger-ui.html
