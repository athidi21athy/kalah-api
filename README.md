# kalah-api
Mankalah REST API with Springboot

6 Stone Mankalah Rest Game implementation. Allowing 2 players to play the game in turns.
In this implementation we are not creating games. We can provide them with an integer id
Instruction & Game rules  - https://en.wikipedia.org/wiki/Kalah
Example of 6 Stone game - https://www.youtube.com/watch?v=iSJk6CYsf6c 

<B>Getting Started</B> </br>
Please Follow the below Instruction to get started.


<B>Prerequists</B> </br>
Java Version 1.8+ Apache Maven </br>

<B>Installation </B> </br>

If you have been provided with a compiled .jar file, named something like manKalah-x.x Release.jar you can skip the next step, otherwise:
Unzip the source code to your chosen directory or retrieve from github via git clone <B>GIT CLONE URL</B> the command prompt type mvn clean install. This will build the project and place a compiled .jar file into the /target directiory. I recommend this step as it will also generate some useful test documentation. Navigate to that directory and type java -jar "generated jarfile name". Alternatively you can navigate to the base directory (which contains the pom.xml file) and type: mvn spring-boot:run.
When the application is Started

The endpoints available are as follows:

<B>Create game API </B></br>
http://localhost:8080/games </br>

<B>Play game API</B> </br>
http://localhost:8080/games/1/pits/4 </br>

Once the game is created you can go ahead and start playing the game using Play game API url.
Once the game is created, in order to make a move uoi need make a post request to games/{gameId}/pits/{pitId} passing the unique identifier of the game you want to make a move on,and the pit id the player is moving from. </br>


<B>Built With</B></br>
[Maven](https://maven.apache.org/) - Dependency Management </br>
[SpringBoot](https://spring.io/projects/spring-boot)

<B>Author</B></br>
Arathy Krishna
