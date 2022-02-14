FROM gradle:6.8-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
FROM openjdk:11
EXPOSE 8080
ONBUILD COPY /home/gradle/src/build/libs/*.jar /app/norberbot.jar
ENTRYPOINT ["java", "-jar", "/app/norberbot.jar"]