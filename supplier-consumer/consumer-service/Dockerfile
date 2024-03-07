FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar consumer-service.jar
ENTRYPOINT ["java","-jar","/consumer-service.jar"]