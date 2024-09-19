# UDP Server


FROM openjdk:17-alpine


COPY UDPServer.java /app/UDPServer.java

WORKDIR /app

RUN javac UDPServer.java


CMD ["java", "UDPServer"]
