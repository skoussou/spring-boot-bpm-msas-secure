FROM registry.redhat.io/redhat-openjdk-18/openjdk18-openshift
ENV JAVA_OPTIONS="-Dorg.drools.server.filter.classes=true -Dspring.profiles.active=openshift -Dorg.guvnor.m2repo.dir=/opt/jboss/.m2/repository -Dapplied=dockerfile"
EXPOSE 8090

COPY target/pam-client-service-1.0.0.jar /deployments/
COPY ./src/main/docker/settings.xml /opt/jboss/.m2/settings.xml

#COPY maven/pam-client-service-1.0.0.jar /deployments/
#COPY maven/settings.xml /opt/jboss/.m2/settings.xml


USER root

RUN chgrp -Rf root /opt/jboss && chmod -Rf g+w /opt/jboss
RUN chgrp -Rf root /deployments && chmod -Rf g+w /deployments

USER jboss
