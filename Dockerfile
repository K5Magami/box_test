FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/box-api.jar app.jar
ADD resource/config.json resource/config.json
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar