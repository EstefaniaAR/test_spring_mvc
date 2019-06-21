INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (1,'Jonh','Smith','jonh@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (2,'Jane','Tarzan','jane@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (3,'Jonathan','Joestar','jojo@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (4,'Dio','Brando','dio@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (5,'Eirina','Pendenthon','erip@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (6,'Ash','Ketchum','pocket@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (7,'Jonny','Storm','jtf4@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (8,'Peter','Parker','pksp@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (9,'Bruce','Wayne','bruce_wayne@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (10,'Clark','Kent','sp@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (11,'Naruto','Uzumaki','kuy_9@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (12,'Sasuke','Uchiha','chidori@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (13,'Selene','kyle','cat_wom@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (14,'Harvey','Dent','2face@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (15,'Jean','Grey','phoenix@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (16,'Bruce','Banner','hulk@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (17,'Jotaro','Kujoh','jojo3@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (18,'Josep','Joestar','jojo2@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (19,'Caesar','Zeppelin','shiza@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (20,'Diana','Prince','ww@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (21,'Barbara','Gordon','batgirl@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (22,'Steven','Rogers','cap@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (23,'Tony','Stark','cap@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (24,'Jonh','Snow','jonhs@ht.com',SYSDATE(),'');
INSERT INTO Clients (id,name,last_name,email,creation_date,image) values (25,'Sansa','Stark','@ht.com',SYSDATE(),'');

INSERT INTO Products (id,description,price,creation_date) values (1,'Apple',5,SYSDATE());
INSERT INTO Products (id,description,price,creation_date) values (2,'Cabbage',10,SYSDATE());
INSERT INTO Products (id,description,price,creation_date) values (3,'Banana',2,SYSDATE());
INSERT INTO Products (id,description,price,creation_date) values (4,'Leek',30,SYSDATE());
INSERT INTO Products (id,description,price,creation_date) values (5,'Celery',5,SYSDATE());
INSERT INTO Products (id,description,price,creation_date) values (6,'Onion',5,SYSDATE());
INSERT INTO Products (id,description,price,creation_date) values (7,'Raddish',10,SYSDATE());
INSERT INTO Products (id,description,price,creation_date) values (8,'seaweed',5,SYSDATE());
INSERT INTO Products (id,description,price,creation_date) values (9,'Carrot',5,SYSDATE());
INSERT INTO Products (id,description,price,creation_date) values (10,'Peach',10,SYSDATE());

INSERT INTO Bills (description, observation,client_id, creation_date) values ('bill 1',null, 1,SYSDATE());
INSERT INTO bill_item (quantity, bill_id,product_id) values (4,1,1);
INSERT INTO bill_item (quantity, bill_id,product_id) values (2,1,10);
INSERT INTO bill_item (quantity, bill_id,product_id) values (10,1,3);

INSERT INTO Bills (description, observation,client_id, creation_date) values ('bill 2',null, 2,SYSDATE());
INSERT INTO bill_item (quantity, bill_id,product_id) values (1,2,3);
INSERT INTO bill_item (quantity, bill_id,product_id) values (2,2,4);
INSERT INTO bill_item (quantity, bill_id,product_id) values (3,2,8);