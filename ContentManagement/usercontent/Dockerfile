FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE=musixdb
ENV MONGO_URL=mongodb://localhost:27017/musixdb
ADD ./target/usercontent-0.0.1-SNAPSHOT.jar  /usr/lib/usercontent-0.0.1-SNAPSHOT.jar
#run the jar file
ENTRYPOINT ["java","-jar","usercontent-0.0.1-SNAPSHOT.jar"]