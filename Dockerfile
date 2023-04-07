FROM openjdk:11
ADD /target/Socks-0.0.1-SNAPSHOT.jar sock.jar
ENTRYPOINT "java", "-jar","sock.jar"