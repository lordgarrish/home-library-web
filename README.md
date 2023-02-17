## Home library interactive database.
Small CRUD web application to help you create and organize your own personal
library. Made with Spring Framework 5, Thymeleaf and MySQL as database.<br/>
With this app you can view, edit, add and delete books in your library. All changes
you've made are saved in the database. This application implements classical
Model-View-Controller pattern, where data is presented to user through browser,
data processing and business logic are made on the server and information is saved in
persistent storage.
### Build and usage
Build project using Maven:
```
mvn clean package
```
Deploying the WAR to Tomcat:
1. Copy WAR file from `target/home-library-web-0.0.1-SNAPSHOT.war` to the `tomcat/webapps/` folder.
2. From a terminal, navigate to the `tomcat/bin` folder and execute:
- on Windows
```
catalina.bat run
```
- on Unix-based systems
```
catalina.sh run
```
3. Go to http://localhost:8080/books
