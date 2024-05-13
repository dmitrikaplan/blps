FROM bellsoft/liberica-openjdk-alpine as build

WORKDIR /source

COPY /gradle /source/gradle
COPY /src /source/src
COPY build.gradle.kts /source/
COPY gradlew /source
COPY settings.gradle.kts /source

RUN ./gradlew build --args=--spring.config.name=application-prod

#--------------------------------------------------------------------------#

FROM bellsoft/liberica-openjdk-alpine

COPY --from=build /source/build/libs/auth-server.jar /auth-server.jar
CMD java -jar auth-server.jar --spring.config.name=application-prod