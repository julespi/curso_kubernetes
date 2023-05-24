FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-usuarios

COPY ./pom.xml /app
COPY ./msvc-usuarios/.mvn ./.mvn
COPY ./msvc-usuarios/mvnw .
COPY ./msvc-usuarios/pom.xml .

# se limpia, se empaqueta (nada porq no hay sources) y se skipea la compilacion de los tests y
# del main, o sea, solo descarga las dependencias y nada mas
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
# alternantiva RUN ./mvnm dependency:go-offline

COPY ./msvc-usuarios/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/msvc-usuarios/target/msvc-usuarios-0.0.1-SNAPSHOT.jar .

ENV PORT 8001

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "./msvc-usuarios-0.0.1-SNAPSHOT.jar"]

# 1 - docker build -t usuarios . -f ./msvc-usuarios/Dockerfile
# 2 - docker network create spring
# 3 - docker run -p 8001:8001 -d --name msvc-usuarios --network spring usuarios
# -- DDBB --
# 1 - docker pull mysql:8
# 2 - docker run -d -p 3307:3306 --name mysql8 --network spring -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=msvc_usuarios -v db-mysql:/var/lib/mysql mysql:8
# 3 - docker pull postgres:14-alpine
# 4 - docker run -d -p 5433:5432 --name postgres14 --network spring -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=msvc_cursos postgres:14-alpine