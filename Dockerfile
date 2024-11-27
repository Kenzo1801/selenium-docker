FROM bellsoft/liberica-openjdk-alpine:17.0.8

# Install curl jq
RUN apk add curl jq

WORKDIR /home/selenium-docker

# Add required files
ADD target/docker-resources ./
ADD runner.sh               runner.sh

RUN dos2unix runner.sh


# Start the runner.sh
ENTRYPOINT sh runner.sh
