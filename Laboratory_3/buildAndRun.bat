@echo off
call mvn clean package
call docker build -t com.fii/Laboratory_3 .
call docker rm -f Laboratory_3
call docker run -d -p 9080:9080 -p 9443:9443 --name Laboratory_3 com.fii/Laboratory_3