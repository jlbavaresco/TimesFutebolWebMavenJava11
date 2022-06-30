@echo off
call mvn clean package
call docker build -t br.edu.ifsul/TimesFutebolWebMavenJava11 .
call docker rm -f TimesFutebolWebMavenJava11
call docker run -d -p 9080:9080 -p 9443:9443 --name TimesFutebolWebMavenJava11 br.edu.ifsul/TimesFutebolWebMavenJava11