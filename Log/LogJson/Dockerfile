FROM maven:3.8.1-openjdk-11-slim AS build-env
COPY . /app
WORKDIR /app
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:11.0.11-slim
COPY --from=build-env /app/target/*.jar /app.jar
ENV JAVA_OPTS="-Xmx128m"
ENTRYPOINT java -jar  app.jar