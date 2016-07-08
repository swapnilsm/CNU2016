FROM maven:3-jdk-8
VOLUME /tmp
ADD . /
RUN apt-get update && apt-get install -y python-pip && pip install awscli && mvn package
CMD aws s3 cp $S3_LOC/Assignment04/application.properties target/application.properties && java -jar target/SpringHello-1.0-SNAPSHOT.jar
