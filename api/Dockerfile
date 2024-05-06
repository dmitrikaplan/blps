FROM bellsoft/liberica-openjdk-alpine as build

COPY . /source
WORKDIR /source
RUN source /source/.env && ./gradlew build

#--------------------------------------------------------------------------#

FROM bellsoft/liberica-openjdk-alpine

EXPOSE 8080
COPY --from=build /source/build/libs/api.jar /api.jar
COPY --from=build /source/.env /.env
CMD source .env && java -jar api.jar