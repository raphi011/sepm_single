CREATE TABLE Horse (
	HorseId integer AUTO_INCREMENT PRIMARY KEY, 
	Name varchar NOT NULL,
	MaxSpeed real NOT NULL CHECK (MaxSpeed >= 50 and MaxSpeed <= 100),
	MinSpeed real NOT NULL CHECK (MinSpeed >= 50 and MinSpeed <= 100),
	Image blob,
	IsDeleted boolean NOT NULL DEFAULT false
);

ALTER TABLE Horse ADD CHECK (MinSpeed <= MaxSpeed);

CREATE TABLE Jockey (
	JockeyId integer AUTO_INCREMENT PRIMARY KEY, 
	Name varchar NOT NULL,
	BirthDate date,
	IsDeleted boolean NOT NULL DEFAULT false
);

CREATE TABLE Race (
	RaceId integer,
	JockeyId integer REFERENCES Jockey (JockeyId), 
	HorseId integer REFERENCES Horse (HorseId), 
	Luck real NOT NULL, 
	Speed real NOT NULL,
	Rank integer NOT NULL,
	Primary Key (RaceId, JockeyId, HorseId),
	UNIQUE KEY horse_UNIQUE (RaceId,HorseId),
	UNIQUE KEY jockey_UNIQUE (RaceId, JockeyId)
);

CREATE SEQUENCE Seq_Race;

INSERT INTO Jockey (Name, BirthDate) VALUES ('Raphael', '1990-07-11');
INSERT INTO Jockey (Name, BirthDate) VALUES ('Barbara', '1990-07-11');
INSERT INTO Jockey (Name, BirthDate) VALUES ('Lisa', '1990-07-11');
INSERT INTO Jockey (Name, BirthDate) VALUES ('Martin', '1990-07-11');

INSERT INTO Horse (Name, MinSpeed, MaxSpeed) VALUES ('Abaccus', 60, 90);
INSERT INTO Horse (Name, MinSpeed, MaxSpeed) VALUES ('Azur', 50, 51);
INSERT INTO Horse (Name, MinSpeed, MaxSpeed) VALUES ('Kahli', 66, 85);
INSERT INTO Horse (Name, MinSpeed, MaxSpeed) VALUES ('Sambuca', 80,95);

INSERT INTO Race (RaceId, JockeyId, HorseId, Luck, Speed, Rank) VALUES (1, 1, 1, 0.95, 70, 8);
INSERT INTO Race (RaceId, JockeyId, HorseId, Luck, Speed, Rank) VALUES (1, 2, 2, 0.95, 70, 8);
INSERT INTO Race (RaceId, JockeyId, HorseId, Luck, Speed, Rank) VALUES (1, 3, 3, 0.95, 70, 8);
INSERT INTO Race (RaceId, JockeyId, HorseId, Luck, Speed, Rank) VALUES (1, 4, 4, 0.95, 70, 8);
INSERT INTO Race (RaceId, JockeyId, HorseId, Luck, Speed, Rank) VALUES (2, 1, 4, 0.95, 70, 8);
INSERT INTO Race (RaceId, JockeyId, HorseId, Luck, Speed, Rank) VALUES (2, 2, 1, 0.95, 70, 8);
