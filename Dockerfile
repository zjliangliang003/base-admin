FROM java:8

MAINTAINER zzz

EXPOSE 8080

RUN mkdir -p /baseAdmin

WORKDIR /baseAdmin

ADD *.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

CMD ["--spring.profiles.active=dev"]

