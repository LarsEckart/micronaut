FROM bellsoft/liberica-openjdk-alpine-musl:11.0.5
RUN apk --no-cache add curl
COPY build/libs/*-all.jar micronaut.jar
RUN adduser -D myuser
USER myuser
CMD java ${JAVA_OPTS} -Dmicronaut.environments=heroku -jar micronaut.jar
EXPOSE 8080