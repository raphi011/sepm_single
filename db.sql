-- Table: Customer
CREATE TABLE Customer (
    CustomerId integer NOT NULL  PRIMARY KEY,
    Name varchar(255) NOT NULL,
    IsDeleted boolean NOT NULL
);

-- Table: Horse
CREATE TABLE Horse (
    HorseId integer NOT NULL  PRIMARY KEY,
    Name varchar(255) NOT NULL,
    BirthDate date,
    Weight float,
    Height integer,
    Image blob,
    Created datetime NOT NULL,
    IsDeleted boolean NOT NULL
);

-- tables
-- Table: Booking
CREATE TABLE Booking (
    BookingId integer NOT NULL  PRIMARY KEY,
    "From" datetime NOT NULL,
    "To" datetime NOT NULL,
    Horse_HorseId integer NOT NULL,
    Customer_CustomerId integer NOT NULL,
    FOREIGN KEY (Horse_HorseId) REFERENCES Horse (HorseId),
    FOREIGN KEY (Customer_CustomerId) REFERENCES Customer (CustomerId)
);

INSERT INTO Customer VALUES (1, 'Raphael', FALSE);
INSERT INTO Customer VALUES (2, 'Barbara', FALSE);
INSERT INTO Customer VALUES (3, 'Lisa', FALSE);
INSERT INTO Customer VALUES (4, 'Martin', FALSE);

INSERT INTO Horse VALUES (1, 'Abaccus', parsedatetime('17-09-2012', 'dd-MM-yyyy'), 150, 120, null, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), FALSE);
INSERT INTO Horse VALUES (2, 'Azur', parsedatetime('15-03-2005', 'dd-MM-yyyy'), 220, 180, null, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), FALSE);
INSERT INTO Horse VALUES (3, 'Kahli', parsedatetime('24-12-209', 'dd-MM-yyyy'), 90, 100, null, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), FALSE);
INSERT INTO Horse VALUES (4, 'Sambuca', parsedatetime('22-01-2014', 'dd-MM-yyyy'), 150, 130, null, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), FALSE);

INSERT INTO Booking VALUES (1, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('18-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 1, 1);
INSERT INTO Booking VALUES (2, parsedatetime('02-05-2013 12:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('02-05-2013 15:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 3, 2);