# Build stage
FROM maven:3.9.6-amazoncorretto-21 AS build
COPY ./src /home/app/src
COPY pom.xml /home/app

# Specify the variable you need
ARG POSTGRES_HOST
ARG POSTGRES_DATABASE
ARG POSTGRES_USERNAME
ARG POSTGRES_PASSWORD

#COPY dev-install.sh /home/app
#
#RUN chmod +x /home/app/dev-install.sh
#RUN /home/app/dev-install.sh

RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM openjdk:21
COPY --from=build /home/app/target/platform-training-1.0.0.jar /usr/local/lib/platform-training.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/platform-training.jar"]