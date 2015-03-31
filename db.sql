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
	Skill real NOT NULL,
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