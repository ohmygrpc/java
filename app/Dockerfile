FROM openjdk:11-jre

ENV ENV=""

#-- For Embedded tomcat, required /tmp
VOLUME /tmp

ADD ./build/libs/*.jar app.jar

ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$ENV -server -Xms3G -Xmx3G -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -XX:+DisableExplicitGC -XX:+ScavengeBeforeFullGC -Xlog:gc*:gc.log -jar app.jar"]
