# FROM maven:3.6-jdk-12-alpine as build
FROM maven:3.6.3-jdk-11-slim as build
# RUN apk add --no-cache bash maven

WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn package && \
jlink --add-modules java.base,java.compiler,java.instrument,java.naming,java.rmi,java.security.jgss,java.security.sasl,java.sql,jdk.jconsole,jdk.unsupported \
--compress 2 --no-header-files --no-man-pages --output target/runtime-image

# FROM alpine:3.14 as release
FROM debian:stretch-slim as release

WORKDIR /app

COPY --from=build /app/target/runtime-image ./jre

COPY --from=build /app/target/ping-service-rest-0.0.1-SNAPSHOT.jar \
                  ./ping-service-rest.jar

ENTRYPOINT ["/app/jre/bin/java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/ping-service-rest.jar"]
