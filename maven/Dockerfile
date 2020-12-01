FROM openjdk:13
WORKDIR /

#EXPOSE 8181
EXPOSE ${PORT}

#ADD target/explorecali-3.0.0-SNAPSHOT.jar //
ADD target/${JAR_FILE} //

#ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=docker", "/explorecali-3.0.1-SNAPSHOT.jar"]
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=docker", "${JAR_FILE}"]
