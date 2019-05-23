CREATE TABLE Coffees (
    name VARCHAR(20) PRIMARY KEY,
    manufacturer VARCHAR(20) NOT NULL
);

CREATE TABLE Coffeehouses (
    name VARCHAR(20) PRIMARY KEY,
    address VARCHAR(20) NOT NULL,
    license VARCHAR(15) NOT NULL UNIQUE
);


CREATE TABLE Drinkers (
    name VARCHAR(20) PRIMARY KEY,
    address VARCHAR(20),
    phone VARCHAR(7) UNIQUE
);

CREATE TABLE Likes (
    drinker VARCHAR(20),
    coffee VARCHAR(20),
    PRIMARY KEY (drinker, coffee),
    FOREIGN KEY (drinker) REFERENCES Drinkers(name),
    FOREIGN KEY (coffee) REFERENCES Coffees(name)
);


CREATE TABLE Frequents (
    drinker VARCHAR(20),
    coffeehouse VARCHAR(20),
    PRIMARY KEY (drinker, coffeehouse),
    FOREIGN KEY (drinker) REFERENCES Drinkers(name),
    FOREIGN KEY (coffeehouse) REFERENCES Coffeehouses(name)
);

CREATE TABLE Sells (
    coffeehouse VARCHAR(20),
    coffee VARCHAR(20),
    price INTEGER NOT NULL,
    PRIMARY KEY (coffeehouse, coffee),
    FOREIGN KEY (coffeehouse) REFERENCES Coffeehouses(name),
    FOREIGN KEY (coffee) REFERENCES Coffees(name)
);