#!/bin/sh
mvn clean 
mvn spring-boot:run &
"mvn test"
