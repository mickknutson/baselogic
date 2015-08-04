-- connect 'jdbc:derby://localhost:50505/tutorials;create=true;user=sa;password=password';
--

DROP TABLE IF EXISTS CUSTOMER;

create table CUSTOMER (
  ID integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  FIRST_NAME varchar(25),
  LAST_NAME varchar(25),
  EMAIL varchar(25),
  primary key (ID));



-- CREATE VIEW airport_locations AS
--   select location.LOCATION_ID AS LOCATION_ID,
--   location.AIRPORT_CODE AS AIRPORT_CODE,
--   airport.AIRPORT_NAME AS AIRPORT_NAME,
--   airport.CITY AS CITY,airport.COUNTRY AS COUNTRY,
--   location.TERMINAL AS TERMINAL,
--   location.LOCATION_INFORMATION AS LOCATION_INFORMATION
--   from (location join airport on((location.AIRPORT_CODE = airport.AIRPORT_CODE))
-- );


-- disconnect;
-- exit;
