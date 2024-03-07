FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar supplier-service.jar
ENTRYPOINT ["java","-jar","/supplier-service.jar"]