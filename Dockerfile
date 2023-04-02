FROM openjdk:11
ADD /target/Socks-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT "java", "-jar","backend.jar"