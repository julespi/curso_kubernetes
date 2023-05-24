FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-cursos

COPY ./pom.xml /app
COPY ./msvc-cursos/.mvn ./.mvn
COPY ./msvc-cursos/mvnw .
COPY ./msvc-cursos/pom.xml .

# se limpia, se empaqueta (nada porq no hay sources) y se skipea la compilacion de los tests y
# del main, o sea, solo descarga las dependencias y nada mas
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
# alternantiva RUN ./mvnm dependency:go-offline

COPY ./msvc-cursos/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/msvc-cursos/target/msvc-cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "./msvc-cursos-0.0.1-SNAPSHOT.jar"]

# 1 - docker build -t cursos . -f ./msvc-cursos/Dockerfile