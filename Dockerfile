FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 5500
ADD target/Transfer_Money_REST_API-0.0.1-SNAPSHOT.jar TransferMoneyRestAPI.jar
#CMD ["java", "java", "-jar", "TransferMoneyRestAPI.jar"]
ENTRYPOINT ["java", "-jar", "TransferMoneyRestAPI.jar"]