DROP TABLE IF EXISTS FUEL_CONSUMPTION;

CREATE TABLE FUEL_CONSUMPTION (
  FUEL_CONSUMPTION_ID INTEGER AUTO_INCREMENT,
  FUEL_TYPE VARCHAR(64) NOT NULL,
  PRICE   FLOAT NOT NULL,
  VOLUME  FLOAT NOT NULL,
  CONSUMPTION_DATE DATE NOT NULL,
  DRIVER_ID VARCHAR(64) NOT NULL,
  PRIMARY KEY(FUEL_CONSUMPTION_ID));