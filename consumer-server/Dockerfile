FROM bellsoft/liberica-openjdk-alpine as build

WORKDIR /source

COPY /gradle /source/gradle
COPY /src /source/src
COPY build.gradle.kts /source/
COPY gradlew /source
COPY settings.gradle.kts /source
COPY .env /source

RUN source /source/.env && ./gradlew build

#--------------------------------------------------------------------------#

FROM bellsoft/liberica-openjdk-alpine

COPY --from=build /source/build/libs/consumer-server.jar /consumer-server.jar
COPY --from=build /source/.env /tmp/.env
CMD source /tmp/.env && java -jar consumer-server.jar