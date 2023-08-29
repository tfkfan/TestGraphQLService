FROM maven:3.8.3-openjdk-17 as build
ADD . /build
WORKDIR /build
RUN mvn clean -Pprod verify && ls -lh target/

#################################################################

FROM openjdk:17

WORKDIR /opt/app

COPY --from=build /build/target/test-rest-service-0.0.1-SNAPSHOT.jar /opt/app/app.jar
COPY --from=build /build/src/main/resources/logback-spring.xml /opt/app/logback.xml
COPY --from=build /build/src/main/resources/config/* /opt/app/config/

EXPOSE 8081

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/app/app.jar --logging.config=logback.xml
