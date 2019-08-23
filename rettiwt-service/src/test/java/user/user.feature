Feature: Rettiwt Social Media Application Testing

Background:
* url 'http://localhost:8100'

* def validGetUserId = 1
* def validPostUserId = 2
* def validFollowedUserId = 3
* def invalidUserId = 10000

Scenario: Testing valid GET /users status code and response
  Given path '/rettiwt-service/users'
  When method GET
  Then status 200
  And match $ contains {"id":1,"name":"Fabio","posts":[{"id":2,"message":"Here it is found it!"},{"id":1,"message":"Where is my beer?"}]}

Scenario: Testing valid GET /users/{userId} status code and response
	Given path '/rettiwt-service/users/' + validGetUserId
	When method GET
  Then status 200
  And match $ contains {"id":1,"name":"Fabio","posts":[{"id":2,"message":"Here it is found it!"},{"id":1,"message":"Where is my beer?"}]}

Scenario: Testing invalid GET /users/{userId} user not found
	Given path '/rettiwt-service/users/' + invalidUserId
  When method GET
  Then status 404

Scenario: Testing valid POST /users status code and response
  Given path '/rettiwt-service/users'
  And request {name: 'Sandra'}
  When method POST
  Then status 201
  And match header Location contains 'http://localhost:8100/rettiwt-service/users/'

Scenario: Testing invalid POST /users name is too short
  Given path '/rettiwt-service/users'
  And request {name: 'S'}
  When method POST
  Then status 400
  
Scenario: Testing valid GET /users/{userId}/posts status code and response
  Given path '/rettiwt-service/users/' + validGetUserId + '/posts'
  When method GET
  Then status 200
  And match $ contains [{"id":2,"message":"Here it is found it!"},{"id":1,"message":"Where is my beer?"}]

Scenario: Testing invalid GET /users/{userId}/posts user not found
  Given path '/rettiwt-service/users/' + invalidUserId + '/posts'
  When method GET
  Then status 404

Scenario: Testing valid POST /users/{userId}/posts status code and response
  Given path '/rettiwt-service/users/' + validPostUserId + '/posts'
  And request {message: 'My new post!'}
  When method POST
  Then status 201
  And match header Location contains 'http://localhost:8100/rettiwt-service/users/' + validPostUserId + '/posts'

Scenario: Testing invalid POST /users/{userId}/posts user not found
  Given path '/rettiwt-service/users/' + invalidUserId + '/posts'
  And request {message: 'My new post!'}
  When method POST
  Then status 404
  
Scenario: Testing invalid POST /users/{userId}/posts message is too big
  Given path '/rettiwt-service/users/' + validPostUserId + '/posts'
  And request {message: 'I was thinking and thinking and thinking and thinking and thinking and thinking and thinking and thinking and thinking and thinking and thinking'}
  When method POST
  Then status 400
  
Scenario: Testing valid GET /users/{userId}/follows/posts status code and response
  Given path '/rettiwt-service/users/' + validGetUserId + '/follows/posts'
  When method GET
  Then status 200
  And match $ contains [{"id":4,"message":"I wish I was at the beach right now :("},{"id":3,"message":"It is freezing outside."}]
  
Scenario: Testing valid GET /users/{userId}/follows/posts user not found
  Given path '/rettiwt-service/users/' + invalidUserId + '/follows/posts'
  When method GET
  Then status 404

Scenario: Testing valid POST /users/{userId}/follows/{followed} status code and response
  Given path '/rettiwt-service/users/' + validPostUserId + '/follows/' + validFollowedUserId
  And request {}
  When method POST
  Then status 201
  And match header Location contains 'http://localhost:8100/rettiwt-service/users/' + validPostUserId + '/follows/' + validFollowedUserId

Scenario: Testing invalid POST /users/{userId}/follows/{followed} user not found
  Given path '/rettiwt-service/users/' + invalidUserId + '/follows/' + validFollowedUserId
  And request {}
  When method POST
  Then status 404
  
Scenario: Testing invalid POST /users/{userId}/follows/{followed} followed user not found
  Given path '/rettiwt-service/users/' + validPostUserId + '/follows/' + invalidUserId
  And request {}
  When method POST
  Then status 404
  
Scenario: Testing invalid POST /users/{userId}/follows/{followed} users are the same
  Given path '/rettiwt-service/users/' + validPostUserId + '/follows/' + validPostUserId
  And request {}
  When method POST
  Then status 400

Scenario: Testing valid DELETE /users/{userId}/follows/{followed} status code and response
  Given path '/rettiwt-service/users/' + validPostUserId + '/follows/' + validFollowedUserId
  And request {}
  When method DELETE
  Then status 200

Scenario: Testing invalid DELETE /users/{userId}/follows/{followed} user not found
  Given path '/rettiwt-service/users/' + invalidUserId + '/follows/' + validFollowedUserId
  And request {}
  When method DELETE
  Then status 404
  
Scenario: Testing invalid DELETE /users/{userId}/follows/{followed} followed user not found
  Given path '/rettiwt-service/users/' + validPostUserId + '/follows/' + invalidUserId
  And request {}
  When method DELETE
  Then status 404
  
Scenario: Testing invalid DELETE /users/{userId}/follows/{followed} users are the same
  Given path '/rettiwt-service/users/' + validPostUserId + '/follows/' + validPostUserId
  And request {}
  When method DELETE
  Then status 400
 
  