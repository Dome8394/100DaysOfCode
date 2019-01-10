FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ./build/libs/spring-data-0.0.1-SNAPSHOT.jar /usr/src/customer/
WORKDIR /usr/src/customer
RUN sh -c 'touch /spring-data-0.0.1-SNAPSHOT.jar'
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongo/customers","/spring-data-0.0.1-SNAPSHOT.jar"]