#!/bin/sh
mvn clean package && docker build -t br.edu.ifsul/TimesFutebolWebMavenJava11 .
docker rm -f TimesFutebolWebMavenJava11 || true && docker run -d -p 9080:9080 -p 9443:9443 --name TimesFutebolWebMavenJava11 br.edu.ifsul/TimesFutebolWebMavenJava11