CREATE ALIAS IF NOT EXISTS FT_INIT FOR "org.h2.fulltext.FullText.init";
CALL FT_INIT();

-- Table: Customer
CREATE TABLE Customer (
    CustomerId integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name varchar NOT NULL,
    IsDeleted boolean NOT NULL DEFAULT false
);

-- Table: Horse
CREATE TABLE Horse (
    HorseId integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name varchar NOT NULL,
    BirthDate date,
    Weight float,
    Height integer,
    Image blob,
    Created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    IsDeleted boolean NOT NULL DEFAULT false
);

-- tables
-- Table: Booking
CREATE TABLE Booking (
    BookingId integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    "From" datetime NOT NULL,
    "To" datetime NOT NULL,
    Created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    LastChanged datetime,
    Horse_HorseId integer NOT NULL,
    Customer_CustomerId integer NOT NULL,
    FOREIGN KEY (Horse_HorseId) REFERENCES Horse (HorseId),
    FOREIGN KEY (Customer_CustomerId) REFERENCES Customer (CustomerId)
);

INSERT INTO Customer (Name, IsDeleted) VALUES ('Raphael', FALSE);
INSERT INTO Customer (Name, IsDeleted) VALUES ('Barbara', FALSE);
INSERT INTO Customer (Name, IsDeleted) VALUES ('Lisa', FALSE);
INSERT INTO Customer (Name, IsDeleted) VALUES ('Martin', FALSE);

INSERT INTO Horse (Name, BirthDate, Weight, Height, Image, Created, IsDeleted) VALUES ('Abaccus', parsedatetime('17-09-2012', 'dd-MM-yyyy'), 150, 120, null, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), FALSE);
INSERT INTO Horse (Name, BirthDate, Weight, Height, Image, Created, IsDeleted) VALUES ('Azur', parsedatetime('15-03-2005', 'dd-MM-yyyy'), 220, 180, null, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), FALSE);
INSERT INTO Horse (Name, BirthDate, Weight, Height, Image, Created, IsDeleted) VALUES ('Kahli', parsedatetime('24-12-209', 'dd-MM-yyyy'), 90, 100, null, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), FALSE);
INSERT INTO Horse (Name, BirthDate, Weight, Height, Image, Created, IsDeleted) VALUES ('Sambuca', parsedatetime('22-01-2014', 'dd-MM-yyyy'), 150, 130, null, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), FALSE);

INSERT INTO Booking ("From", "To", Horse_HorseId, Customer_CustomerId) VALUES (parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('18-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 1, 1);
INSERT INTO Booking ("From", "To", Horse_HorseId, Customer_CustomerId) VALUES (parsedatetime('02-05-2013 12:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('02-05-2013 15:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 3, 2);

CALL FT_CREATE_INDEX('PUBLIC', 'CUSTOMER', 'NAME' );
CALL FT_CREATE_INDEX('PUBLIC', 'HORSE', 'NAME,WEIGHT,HEIGHT');