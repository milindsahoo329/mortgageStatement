# business layer app

# For building docker image
# go to the project folder

# Build the image
docker build -t data-layer-docker.jar .

# See list of images
docker image ls

# run the image
# 8002 is the port where image needs to be run on, while 9002 is the local port for the application
# use docker image name or id to run the docker
docker run -p 8002:9002 data-layer-docker.jar