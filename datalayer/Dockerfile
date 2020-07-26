FROM openjdk:11
EXPOSE 9001
ADD target/data-layer-docker data-layer-docker
ENTRYPOINT ["java","-jar","data-layer-docker.jar"]