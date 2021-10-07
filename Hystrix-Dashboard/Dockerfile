FROM openjdk:8
ADD target/hystrix-dashboard.jar hystrix-dashboard.jar
EXPOSE 9195
ENTRYPOINT ["java","-jar","hystrix-dashboard.jar"]