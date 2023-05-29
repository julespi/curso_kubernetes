FROM openjdk:17-jdk-alpine as builder

ARG MSVC_NAME=msvc-usuarios

WORKDIR /app/$MSVC_NAME

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

# se limpia, se empaqueta (nada porq no hay sources) y se skipea la compilacion de los tests y
# del main, o sea, solo descarga las dependencias y nada mas
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
# alternantiva RUN ./mvnm dependency:go-offline

COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN mkdir ./logs

ARG MSVC_NAME=msvc-usuarios
ARG TARGET_FOLDER=/app/$MSVC_NAME/target

COPY --from=builder $TARGET_FOLDER/msvc-usuarios-0.0.1-SNAPSHOT.jar .

ARG PORT_APP=8001

ENV PORT $PORT_APP

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

#### pasandole el puerto como una variable de entorno (-e PORT=8001)
# - docker run -p 8001:8001 -d --name msvc-usuarios --network spring -e PORT=8001 usuarios

#### pasandole el puerto como una variable de entorno en un archivo .env (--env-file ./msvc-usuarios/.env)
# - docker run -p 8001:8001 -d --name msvc-usuarios --network spring --env-file ./msvc-usuarios/.env usuarios

#### pasandole el puerto como un argumento en la construccion de la imagenenv (--build-arg PORT_APP=8001)
# -  docker build -t usuarios . -f ./msvc-usuarios/Dockerfile --build-arg PORT_APP=8001