FROM openjdk:11

EXPOSE 3080

ADD target/svcs-login-0.0.1.jar svcs-login-0.0.1.jar

ENTRYPOINT ["java","-jar","svcs-login-0.0.1.jar"]