# Build stage
FROM gradle:jdk21 AS build

WORKDIR /home/app

COPY ./src /home/app/src
COPY build.gradle /home/app
COPY settings.gradle /home/app
COPY deploy.sh /home/app

# Set build arguments
ARG POSTGRES_HOST
ARG POSTGRES_PORT
ARG POSTGRES_DATABASE
ARG POSTGRES_USERNAME
ARG POSTGRES_PASSWORD

# Set environment variables for the build process
ENV POSTGRES_HOST $POSTGRES_HOST
ENV POSTGRES_PORT $POSTGRES_PORT
ENV POSTGRES_DATABASE $POSTGRES_DATABASE
ENV POSTGRES_USERNAME $POSTGRES_USERNAME
ENV POSTGRES_PASSWORD $POSTGRES_PASSWORD

# Build
RUN gradle clean build --no-daemon

# Package stage
FROM openjdk:21
COPY --from=build /home/app/build/libs/platform-training-1.0.0.jar /usr/local/lib/platform-training.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/usr/local/lib/platform-training.jar"]