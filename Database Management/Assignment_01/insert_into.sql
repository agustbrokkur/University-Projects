INSERT INTO Coffees (name, manufacturer)
VALUES ('Herbal', 'Herbal Inc.'),
		('100 Coffee', 'Hundred Cups');

INSERT INTO Coffeehouses (name, address, license)
VALUES ('Starbucks', 'Some-Street 12', 'UJL-3435'),
		('Jon''s Coffee', 'Garfield Lane 74', 'ODD-1982');

INSERT INTO Drinkers (name, address, phone)
VALUES ('Hermes', 'Jam Street 34', '1111111'),
		('Fry', 'Express Lane', '5555555');

INSERT INTO Likes (drinker, coffee)
VALUES ('Hermes', 'Herbal'),
		('Fry', '100 Coffee');

INSERT INTO Frequents (drinker, coffeehouse)
VALUES ('Hermes', 'Jon''s Coffee'),
		('Fry', 'Starbucks');

INSERT INTO Sells (coffeehouse, coffee, price)
VALUES ('Starbucks', 'Herbal', 5),
		('Starbucks', '100 Coffee', 2),
        ('Jon''s Coffee', 'Herbal', 3),
		('Jon''s Coffee', '100 Coffee', 1);