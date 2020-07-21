FROM openjdk:8-jre-alpine

COPY DateWriter.jar /

RUN ["mkdir", "/data"]

CMD ["java", "-jar", "DateWriter.jar", "/data/date.txt"]