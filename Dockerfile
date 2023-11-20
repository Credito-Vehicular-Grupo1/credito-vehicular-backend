FROM openjdk:17
VOLUME /tmp
EXPOSE 8090
ARG JAR_FILE=target/creditoVehicular-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} creditoVehicular.jar
ENTRYPOINT ["java","-jar","/creditoVehicular.jar"]