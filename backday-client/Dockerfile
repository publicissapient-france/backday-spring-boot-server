FROM java:8
VOLUME /tmp
COPY dist/application.yml /
ADD build/libs/backday-spring-boot-client*.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]