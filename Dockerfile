FROM maven:3.9.6-amazoncorretto-21

WORKDIR /app

COPY . .

RUN mvn clean package

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/cadastro-profissionais.jar"]