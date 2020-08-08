FROM maven:3.6.3-jdk-11-slim AS packager

RUN mkdir -p /callprice-api

WORKDIR /callprice-api

ADD ./pom.xml .
ADD ./docker-entrypoint.sh /

RUN mvn install -B -f ./pom.xml

COPY ./ .

RUN mvn package -Dmaven.test.skip=true && \
    mv ./target/*.jar /run/callprice-api.jar

FROM openjdk:11-slim

COPY --from=packager /run/callprice-api.jar /var/com/br/callpriceapi/callprice-api.jar
COPY --from=packager /docker-entrypoint.sh /docker-entrypoint.sh

RUN chmod +x /docker-entrypoint.sh

EXPOSE 8080

ENTRYPOINT [ "bash", "/docker-entrypoint.sh" ]
