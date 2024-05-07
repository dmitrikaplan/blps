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

EXPOSE 8080
COPY --from=build /source/build/libs/api.jar /api.jar
COPY --from=build /source/.env /.env
CMD source .env && java -jar api.jar