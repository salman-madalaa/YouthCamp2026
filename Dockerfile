FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/youthcamp-0.0.1-SNAPSHOT.war app.war

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.war"]