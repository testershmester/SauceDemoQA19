FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/testautomation/src
COPY pom.xml /home/testautomation
RUN mvn -f /home/testautomation/pom.xml clean test