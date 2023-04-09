FROM maven:3.8.1-openjdk-17-slim AS build-env
COPY . /app
WORKDIR /app
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:17-slim
COPY --from=build-env /app/target/*.jar /app.jar
COPY --from=build-env /app/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar
ENV JAVA_OPTS="-Xmx128m"
ENTRYPOINT java -jar -javaagent:/opentelemetry-javaagent.jar app.jar