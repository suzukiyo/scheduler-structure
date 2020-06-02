# Dockerfile

FROM alpine:3.10.1

ENV TZ Asia/Tokyo

RUN apk update
RUN apk --update add tzdata && \
    apk --no-cache add openjdk11-jre-headless \
    rm -rf /var/cache/apk/* && \
    ./gradlew clean build jar
CMD ["java","-jar","cancel/target/cancel-1.0-SNAPSHOT.jar"]