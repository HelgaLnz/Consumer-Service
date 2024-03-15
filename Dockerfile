FROM openjdk:17-slim-buster
ADD /target/consumer-service-0.0.1-SNAPSHOT.jar consumer-service.jar
ENTRYPOINT ["java", "-jar", "consumer-service.jar"]