FROM openjdk:11
EXPOSE 9002
ADD target/business-layer-docker.jar business-layer-docker.jar
ENTRYPOINT ["java","-jar","business-layer-docker.jar"]