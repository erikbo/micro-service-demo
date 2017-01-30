FROM frolvlad/alpine-oraclejdk8:slim

ADD user-service-1.0-SNAPSHOT.jar user-service.jar

ENTRYPOINT [ "sh", "-c", "java -jar /user-service.jar" ]