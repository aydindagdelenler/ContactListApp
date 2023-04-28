FROM openjdk:17
COPY target/contact-0.0.1-SNAPSHOT.jar contact-list-1.0.0.jar
ENTRYPOINT ["java","-jar","/contact-list-1.0.0.jar"]