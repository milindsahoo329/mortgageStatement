FROM openjdk:11
EXPOSE 8080
ADD target/mortgage-docker.jar mortgage-docker.jar
ENTRYPOINT ["java","-jar","mortgage-docker.jar"]