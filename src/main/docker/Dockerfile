FROM java:8
VOLUME /tmp
ADD todo-list-project-0.0.1-SNAPSHOT.jar /notebook.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/notebook.jar"]