FROM openjdk:17

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} /app/api.jar

EXPOSE 8091

CMD ["java", "-Dspring.profiles.active=development", "-Dserver.port=8091", "-jar", "api.jar"]