FROM frolvlad/alpine-oraclejdk8:slim

ADD rss-service-1.0-SNAPSHOT.jar rss-service.jar

ENTRYPOINT [ "sh", "-c", "java -jar /rss-service.jar" ]