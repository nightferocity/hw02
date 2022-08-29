FROM OPENJDK:11
COPY hw02-docker.jar hw02-docker.jar
EXPOSE 8033
ENTRYPOINT java -jar hw02-docker.jar $ARGS