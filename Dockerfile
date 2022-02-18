<<<<<<< HEAD
FROM openjdk:17
=======
FROM adoptopenjdk/openjdk15
>>>>>>> 4e652d1 (Add handlers to make a response to a mention event)

ARG JAR_FILE=JAR_FILE_MUST_BE_SPECIFIED_AS_BUILD_ARG
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom","-jar","/app.jar"]