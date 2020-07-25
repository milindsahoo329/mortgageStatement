# mortgageStatement

# For builiding docker image
# go to the project folder

# Build the image
# docker build -t mortage-docker.jar .

# See list of images
# docker image ls

# run the image
# 9090 is the port where image needs to to be run on, while 8080 is the local port
# use docker image name or id to run the docker
# docker run -p 9090:8080 mortage-docker.jar