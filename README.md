# Rettiwt Social Media Service

## Assumptions

- Sorry I had to provide a user endpoint for handling user creation. I know it is not part of the task but for me it doesn't make sense to have to provide the full user information as part of the request body when all we want is to create new posts for a given user. Otherwise I would have to create dummy users, with dummy names for the posts in case the user was not found or pass the full user information as params so I decided to split user creation from post creation and provide separated endpoints.

- There are some data (Users and Posts) which gets added to the persistence layer by default. I did that for convinience and to make testing easier, in an ideal production world that wouldn't be there.

- Added tests that use karate, however for now in order to run them you need the application to be up on port 8100. Ideally it would be better to have a WireMock instance mocking the services and then run karate against that, but due to time constraints I was not able to do it. Maybe in the future I will extend it to do that.

## Additional instructions

- To execute the app, you can either download it from this repository and do:
  - java -jar rettiwt-service-0.0.1-SNAPSHOT or
  - ./mvnw spring-boot:run from the root maven directory

- Default port: 8100 (if you can't run it here you can use a different one by passing -Dserver.port)
- Service definition and documentation can be found in swagger ui: http://localhost:8100/swagger-ui.html
