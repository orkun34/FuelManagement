FROM java:8
EXPOSE 8282
ADD ./target/FuelManagement-1.0-SNAPSHOT.jar FuelManagement-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-Dspring.profiles.active=container","-jar","FuelManagement-1.0-SNAPSHOT.jar"]