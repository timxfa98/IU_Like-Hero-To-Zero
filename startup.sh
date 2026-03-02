#!/bin/bash

# Start WildFly in background
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 &

# Wait for WildFly to start
sleep 15

# Configure datasource
/opt/jboss/wildfly/bin/jboss-cli.sh --connect --command="data-source add \
  --name=HeroToZeroDS \
  --jndi-name=java:jboss/datasources/HeroToZeroDS \
  --driver-name=mysql-connector-j-8.0.33.jar \
  --connection-url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8 \
  --user-name=${DB_USER} \
  --password=${DB_PASSWORD} \
  --enabled=true"

# Keep container running
wait
