FROM openjdk:17-ea-11-jdk-slim

WORKDIR /server

COPY ../zola-messaging-server/build/libs/zola-messaging-server-1.0.0.jar server.jar

EXPOSE 8291

ENTRYPOINT ["java", "-jar", "server.jar"]
