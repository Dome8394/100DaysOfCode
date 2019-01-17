FROM openjdk:8-jdk-alpine
MAINTAINER Dominik Kesim <dominik.kesim@gmail.com>
ARG JAR_FILE
COPY ./build/libs/spring-data-0.0.1-SNAPSHOT.jar /usr/src/customer/
WORKDIR /usr/src/customer
EXPOSE 8080
CMD ["java", "-Dspring.data.mongodb.uri=mongodb://mongo/test", "-jar", "spring-data-0.0.1-SNAPSHOT.jar"]