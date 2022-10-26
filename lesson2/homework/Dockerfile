FROM amazoncorretto:17-alpine-jdk

RUN addgroup -S app && adduser -S app -G app

WORKDIR /home/app
COPY src/com/hsai2023/lesson2/Main.java Main.java
COPY entrypoint.sh entrypoint.sh

RUN chown -R app /home/app

ENV DEBUG_PORT=5005

USER app
ENTRYPOINT ["./entrypoint.sh"]