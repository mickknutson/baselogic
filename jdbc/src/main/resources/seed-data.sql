--connect 'jdbc:derby://localhost:50505/tutorials;create=true;user=sa;password=password';

INSERT INTO CUSTOMER (ID, FIRST_NAME, LAST_NAME, EMAIL)
VALUES
 ('1','Mick','Knutson','mickknutson@gmail.com'),
 ('2','Mick','Knutson2','mickknutson@yahoo.com'),
 ('3','Mick','Knutson3','mickknutson@hotmail.com'),
 ('4','Mick','Knutson4','mickknutson@live.com');
