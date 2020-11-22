# Simple 3 nodes Cassandra DB 

### What is it?
This is a little POC exercise to show Cassandra DB in Docker and CRUD operations. 

### Instructions to run all applications:
* In the root folder run `docker-compose -f dbnodes.yaml up -d` - this should spin up 3 instances of Cassandra DB - 
Please make sure all 3 running before proceeding to the next step (there is a known problem when trying to stand up all instances at once).
If one of the nodes dies please re-run the above command.
* `gradle build jibDockerBuild` will produce the docker image to your local image repository.
* Run `docker-compose -f capp.yaml up -d` - this creates kafka-producer and the spring cassandra app.

### Http endpoints:
The Spring Boot Cassandra Application exposes on a number of endpoints:

* Adding hero to db at http://localhost:8080/saveHero with body e.g.
`{
     "id":3,
     "nickname":"Tralalam",
     "gender":"male",
     "race":"human",
     "yearCr":1982
 }`

* Querying all existing heroes http://localhost:8080/allHeroes - Responds with all heros stored in db


* Querying for specific hero by Id http://localhost:8080/getHero/{id}