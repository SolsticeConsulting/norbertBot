FROM openjdk:11
ARG JAR_FILE=JAR_FILE_MUST_BE_SPECIFIED_AS_BUILD_ARG
COPY ${JAR_FILE} norberbot.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom","-jar","/norberbot${COMMIT_SHA}.jar"]