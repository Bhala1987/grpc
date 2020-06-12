1. git clone https://github.com/Bhala1987/grpc.git
2. Once cloned locally in your system, open the project in IntelliJ IDEA, or your preferred interface (Eclipse for example)
3. Click on 'Gradle' icon in the top right corner
4. Click on 'Reimport All Gradle Projects' icon
5. Double click on 'generateProto' task under grpc --> Tasks --> other
6. Finally, on the project pane which is on the left-hand side, navigate to any java test class under grpc --> src --> test --> java --> tests
7. Run it!!!

(P.S.: To have new .proto files, place them under src --> main --> proto and then follow the above steps)

##### To get the Serenity report, use the following configuration gradle tasks:
```clean build test```

Once the above gradle configuration ran & completes its activities, you should be able to see the Serenity report under target --> site --> serenity --> index.html 

NOTE: This whole project has been built using java8 version as newer versions are having some problems, hence highly recommended to use Java8 open source JDK.

#### Commits:
1. Don't commit the following files/directories (.gitignore file will take care of):
        .gradle/
        .idea/
        build/
        target/


### How to run the hello server locally
1. Open the ~/docs folder in terminal
2. Run the command ./scripts/runserver.sh