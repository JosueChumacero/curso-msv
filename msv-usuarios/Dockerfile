FROM openjdk:17-jdk-alpine as builder
ARG MSV_NAME=msv-usuarios
WORKDIR /app/$MSV_NAME

COPY ./pom.xml /app
COPY ./$MSV_NAME/.mvn ./.mvn
COPY ./$MSV_NAME/mvnw .
COPY ./$MSV_NAME/pom.xml .

RUN   sed -i -e 's/\r$//' ./mvnw
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./$MSV_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN mkdir ./logs
ARG MSV_NAME=msv-usuarios
ARG TARGET_FOLDER=app/$MSV_NAME/target
COPY --from=builder $TARGET_FOLDER/msv-usuarios-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=8001
ENV PORT $PORT_APP
EXPOSE $PORT

CMD ["java", "-jar", "msv-usuarios-0.0.1-SNAPSHOT.jar"]