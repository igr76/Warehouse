FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
COPY .mvn/  .mvn
COPY mvnw pom.xml ./
RUN ./mvnm  depedensy:resolve

COPY src ./src
CMD ["./mvnm","spring-boot:run"]