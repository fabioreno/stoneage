INSERT INTO USER (ID, NAME) VALUES ('1', 'Fabio');
INSERT INTO USER (ID, NAME) VALUES ('2', 'Sam');
INSERT INTO USER (ID, NAME) VALUES ('3', 'Thomas');

INSERT INTO POST (ID, MESSAGE, USER_ID) VALUES ('1', 'Where is my beer?', '1');
INSERT INTO POST (ID, MESSAGE, USER_ID) VALUES ('2', 'Here it is found it!', '1');
INSERT INTO POST (ID, MESSAGE, USER_ID) VALUES ('3', 'It is freezing outside.', '2');
INSERT INTO POST (ID, MESSAGE, USER_ID) VALUES ('4', 'I wish I was at the beach right now :(', '3');

INSERT INTO USER_FOLLOWERS (USER_ID, FOLLOWED_ID) VALUES ('1', '2');
INSERT INTO USER_FOLLOWERS (USER_ID, FOLLOWED_ID) VALUES ('1', '3');
INSERT INTO USER_FOLLOWERS (USER_ID, FOLLOWED_ID) VALUES ('2', '3');