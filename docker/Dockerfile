FROM adoptopenjdk/openjdk11:alpine-jre
COPY currency-0.0.1-SNAPSHOT.jar app.jar
COPY run.sh .
RUN chmod +x run.sh
ENTRYPOINT ["./run.sh"]