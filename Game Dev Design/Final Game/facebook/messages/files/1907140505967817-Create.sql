----------------------------------------------------
-- Agnar Ísaksson, Ágúst Helgi Árnason
-- School: Reykjavik University
-- Course: Databases 1 (T-202-GAG1) 2017
-- Project: 4
----------------------------------------------------


DROP TABLE IF EXISTS People CASCADE;
DROP TABLE IF EXISTS Linkings CASCADE;
DROP TABLE IF EXISTS Opponents CASCADE;
DROP TABLE IF EXISTS Sponsor CASCADE;
DROP TABLE IF EXISTS Assets CASCADE;
DROP TABLE IF EXISTS Record CASCADE;
DROP TABLE IF EXISTS Parties CASCADE;
DROP TABLE IF EXISTS Members CASCADE;
DROP TABLE IF EXISTS Roles CASCADE;
DROP TABLE IF EXISTS Grants CASCADE;


CREATE TABLE People(
	ID SERIAL					-- ID field
	, name VARCHAR(255)			-- Name of person
	, address VARCHAR(255)		-- Address of person
	, phone VARCHAR(15)			-- Phone number
	, dob DATE					-- Date of birth
	, dod DATE					-- When user was created
	, PRIMARY KEY (ID)
);

CREATE TABLE Linkings(
	ID SERIAL					-- ID if linkings
	, name VARCHAR(255)			-- Name of the link
	, type VARCHAR(255)			-- Name of the type of link between people (enemy, marriage, partner etc.)
	, description VARCHAR(15)	-- Description
	, PID SERIAL				-- ID of the person
	, FOREIGN KEY (PID) REFERENCES People(ID)
	, PRIMARY KEY (ID)
);

CREATE TABLE Opponents(
	  startDate DATE			-- Start date
	, endDate DATE				-- End date
	, memberID SERIAL			-- ID of member
	, enemyID SERIAL			-- ID of enemy
	, FOREIGN KEY (memberID) REFERENCES Members(PID)
	, FOREIGN KEY (enemyID) REFERENCES People(ID)
);

CREATE TABLE Sponsors(
	  ID SERIAL					-- ID of Sponsor
	  name VARCHAR(255)			-- Name of sponsor
	, address VARCHAR(255)		-- Address of sponsor
	, industry VARCHAR(255)		-- Name of the industry the sponsor is in
	, PRIMARY KEY (ID)
);

CREATE TABLE Assets(
	  name VARCHAR(255)			-- Name of the asset
	, details VARCHAR(255)		-- Details about the asset
	, useage VARCHAR(255)		-- What the asset can be used for
	, MID SERIAL				-- ID of the member that owns the asset
	, FOREIGN KEY (MID) REFERENCES Members(PID)
);

CREATE TABLE Record(
	  startDate DATE			-- Start date
	, endDate DATE				-- End date
	, salary INTEGER			-- Salary of the position in roles
	, MID SERIAL				-- ID of the member
	, RID SERIAL				-- ID of the role
	, FOREIGN KEY (MID) REFERENCES Members(PID)
	, FOREIGN KEY (RID) REFERENCES Roles(ID)
);

CREATE TABLE Parties(
	  ID SERIAL					-- ID of the party
	, country VARCHAR(255)		-- Country of the party
	, name VARCHAR(255)			-- Name of the party
	, MID SERIAL				-- ID of the member
	, FOREIGN KEY (MID) REFERENCES Members(PID)
	, PRIMARY KEY (ID)
);

CREATE TABLE Members(
	  startDate DATE			-- Date of membership
	, PID SERIAL				-- ID of the member
	, FOREIGN KEY (PID) REFERENCES People(ID)
);

CREATE TABLE Roles(
	  ID SERIAL					-- ID of the role
	, title VARCHAR(255)		-- Title of the role
	, PRIMARY KEY (ID)
);

CREATE TABLE Grants(
	  date DATE					-- Date of the grant
	, amount INTEGER			-- Amount of the grant
	, payback VARCHAR(255)		-- What the sponsor wants in return for the grant
	, reviewDate DATE			-- Date of the review
	, grade INTEGER				-- How successful the payback was, graded from 1-10
	, reviewID SERIAL			-- ID of the member tasked to review the grant
	, SID SERIAL				-- ID of the sponsor
	, MID SERIAL				-- ID of the member receiving the grant
	, FOREIGN KEY (reviewID) REFERENCES Members(PID)
	, FOREIGN KEY (SID) REFERENCES Sponsors(ID)
	, FOREIGN KEY (MID) REFERENCES Members(PID)
);






