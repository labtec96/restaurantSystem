INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_MANAGER');

INSERT INTO user (first_name,last_name,email,password,enabled) VALUES ('ch','dd','op@op.pp','ch',false);

INSERT INTO restaurant_table (Floor,max_number_of_people,number) VALUES (2,3,21);
INSERT INTO restaurant_table (Floor,max_number_of_people,number) VALUES (2,3,12);
INSERT INTO restaurant_table (Floor,max_number_of_people,number) VALUES (2,3,13);
INSERT INTO restaurant_table (Floor,max_number_of_people,number) VALUES (2,3,14);
INSERT INTO restaurant_table (Floor,max_number_of_people,number) VALUES (2,3,41);

INSERT INTO reservation (date,end_hour,start_hour,persons,status,restaurant_table_id,user_id) VALUES ('2020-05-19','12:00','13:00',5,'WERYFIKOWANA',1,1);
INSERT INTO reservation (date,end_hour,start_hour,persons,status,restaurant_table_id,user_id) VALUES ('2020-05-18','12:00','13:00',5,'POTWIERDZONA',1,1);


INSERT INTO meal (description,name,price,weight) VALUES ('Wątróbka z cebulą z sosem bawarskim','Polska kałamażyca',100,200);
INSERT INTO meal (description,name,price,weight) VALUES ('Wątróbka z cebulą z sosem bawarskim','Polska kałamażyca',123,123);