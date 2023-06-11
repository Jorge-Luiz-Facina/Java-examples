FROM openjdk:17-jdk-slim
RUN apt-get update -y && apt-get install -y wget
VOLUME /tmp
ADD external/target/*.jar app.jar
ENV JAVA_OPTS="-Xmx128m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]