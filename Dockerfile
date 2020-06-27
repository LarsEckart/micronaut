FROM openjdk:14-alpine
COPY build/libs/micronaut-*-all.jar micronaut.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "micronaut.jar"]
