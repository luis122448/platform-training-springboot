# Build stage
FROM gradle:jdk21 AS build

# Create a directory to work in
WORKDIR /home/app

COPY ./src /home/app/src
COPY build.gradle /home/app
COPY settings.gradle /home/app

# Specify the variable you need
ARG POSTGRES_HOST
ARG POSTGRES_DATABASE
ARG POSTGRES_USERNAME
ARG POSTGRES_PASSWORD

COPY deploy.sh /home/app
#
RUN chmod +x /home/app/deploy.sh
RUN /home/app/deploy.sh

# Build the project
RUN gradle clean build --no-daemon

# Package stage
FROM openjdk:21
COPY --from=build /home/app/build/libs/platform-training-1.0.0.jar /usr/local/lib/platform-training.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/platform-training.jar"]