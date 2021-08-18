FROM maven:3.8-openjdk-11-slim as build

WORKDIR /app
COPY pom.xml .
COPY src src

RUN MAVEN_OPTS=-Dorg.slf4j.simpleLogger.defaultLogLevel=warn mvn package && \
jlink --add-modules java.base,java.compiler,java.instrument,java.naming,java.rmi,java.security.jgss,java.security.sasl,java.sql,jdk.jconsole,jdk.unsupported \
--compress 2 --no-header-files --no-man-pages --output target/runtime-image

FROM alpine:3.8 as release

COPY --from=build /app/target/runtime-image /app

COPY --from=build /app/target/ping-service-rest-0.0.1-SNAPSHOT.jar \
                  /app/ping-service-rest.jar

ENTRYPOINT ["/app/bin/java", "-jar"]
CMD ["/app/ping-service-rest.jar"]
