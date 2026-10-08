FROM openjdk:8-jdk AS builder
RUN apt-get update && apt-get install -y ant
WORKDIR /app
COPY . .
RUN ant dist || ant compile

FROM tomcat:9.0-jdk8-openjdk-slim

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=builder /app/dist/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
