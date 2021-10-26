#!/bin/sh
mvn clean package && docker build -t com.fii/Laboratory_3 .
docker rm -f Laboratory_3 || true && docker run -d -p 9080:9080 -p 9443:9443 --name Laboratory_3 com.fii/Laboratory_3