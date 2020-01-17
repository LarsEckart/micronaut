FROM bellsoft/liberica-openjdk-alpine-musl:11.0.5
RUN apk --no-cache add curl
COPY build/libs/*-all.jar micronaut.jar
CMD java ${JAVA_OPTS} -jar micronaut.jar
EXPOSE 8080