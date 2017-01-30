FROM frolvlad/alpine-oraclejdk8:slim

ADD orchestrator-service-1.0-SNAPSHOT.jar orchestrator-service.jar

ENTRYPOINT [ "sh", "-c", "java -jar /orchestrator-service.jar" ]