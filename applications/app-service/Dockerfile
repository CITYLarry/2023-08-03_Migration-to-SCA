FROM amazoncorretto:17-alpine
RUN apk add --no-cache bash
COPY build/libs/SCA_PostsAndComments.jar SCA_PostsAndComments.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
EXPOSE 8080
ENTRYPOINT ["./wait-for-it.sh", "mongodb:27017", "--", "./wait-for-it.sh", "rabbitmq:5672", "--", "java", "-jar", "SCA_PostsAndComments.jar"]