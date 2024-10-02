FROM openjdk:17
WORKDIR /app
COPY ./target/distributed-kv-store-1.0-SNAPSHOT.jar /app/kvstore.jar
ENTRYPOINT ["java", "-jar", "/app/kvstore.jar"]
