FROM registry.redhat.io/redhat-openjdk-18/openjdk18-openshift

ENV JAVA_OPTIONS="-Dorg.drools.server.filter.classes=true -Dspring.profiles.active=openshift -Dorg.kie.server.startup.strategy=LocalContainersStartupStrategy -Dorg.kie.server.mode=PRODUCTION -Dkie.maven.settings.custom=/opt/jboss/.m2/settings.xml -Dorg.guvnor.m2repo.dir=/opt/jboss/.m2/repository -Dapplied=dockerfile -DSERVICE_NAME=$SERVICE_NAME -DSERVICE_USERNAME=$SERVICE_USERNAME -DSERVICE_PASSWORD=$SERVICE_PASSWORD"
EXPOSE 8090

#COPY target/hr-expenses-business-application-service-1.1.0-SNAPSHOT.jar /deployments/
#COPY hr-expenses-business-application-service.xml /deployments/
#COPY ./src/main/docker/settings.xml /opt/jboss/.m2/settings.xml

COPY maven/hr-expenses-business-application-service-1.2.0-SNAPSHOT.jar /deployments/
COPY maven/hr-expenses-business-application-service.xml /deployments/
COPY maven/settings.xml /opt/jboss/.m2/settings.xml



USER root

RUN chgrp -Rf root /opt/jboss/.m2 && chmod -Rf g+w /opt/jboss/.m2
RUN chgrp -Rf root /deployments && chmod -Rf g+w /deployments

USER jboss
