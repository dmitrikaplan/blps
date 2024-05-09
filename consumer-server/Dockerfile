FROM bellsoft/liberica-openjdk-alpine as build

WORKDIR /source

COPY /gradle /source/gradle
COPY /src /source/src
COPY build.gradle.kts /source/
COPY gradlew /source
COPY settings.gradle.kts /source

RUN ./gradlew build

#--------------------------------------------------------------------------#

FROM bellsoft/liberica-openjdk-alpine

COPY --from=build /source/build/libs/consumer-server.jar /consumer-server.jar
CMD java -jar consumer-server.jar