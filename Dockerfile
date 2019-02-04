FROM openjdk:8u191-jre-alpine3.8

ADD target/website-gateway.jar /app/dist/website-gateway.jar

EXPOSE 8999

VOLUME /tmp

ENTRYPOINT java -Dspring.profiles.active=$BOOTIFUL_MICRO_PIZZA_ENV -Djava.security.egd=file:/dev/./urandom -jar /app/dist/website-gateway.jar
