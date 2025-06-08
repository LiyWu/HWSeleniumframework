FROM selenium/standalone-chrome:4.33.0-20250606

WORKDIR /usr/src/app

COPY pom.xml /usr/src/app

COPY ./src/test/java /usr/src/app/src/test/java
