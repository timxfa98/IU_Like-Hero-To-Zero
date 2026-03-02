FROM maven:3.8-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM jboss/wildfly:latest
USER root
RUN curl -o /opt/jboss/wildfly/standalone/deployments/mysql-connector-j-8.0.33.jar \
  https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar
COPY --from=build /app/target/like-hero-to-zero.war /opt/jboss/wildfly/standalone/deployments/
COPY startup.sh /opt/jboss/startup.sh
RUN chmod +x /opt/jboss/startup.sh && chown jboss:jboss /opt/jboss/startup.sh
USER jboss
CMD ["/opt/jboss/startup.sh"]
