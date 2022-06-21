FROM openjdk:11
VOLUME /tmp
EXPOSE 8093
ADD ./target/ws-product-active-0.0.1-SNAPSHOT.jar ws-product-active.jar
ENTRYPOINT ["java","-jar","ws-product-active.jar"]