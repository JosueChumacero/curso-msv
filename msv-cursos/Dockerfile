FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msv-cursos

COPY ./pom.xml /app
COPY ./msv-cursos/.mvn ./.mvn
COPY ./msv-cursos/mvnw .
COPY ./msv-cursos/pom.xml .

RUN   sed -i -e 's/\r$//' ./mvnw
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./msv-cursos/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/msv-cursos/target/msv-cursos-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

CMD ["java", "-jar", "msv-cursos-0.0.1-SNAPSHOT.jar"]