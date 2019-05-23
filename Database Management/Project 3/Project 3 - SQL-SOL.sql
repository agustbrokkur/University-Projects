-- [ Dæmi 1 ] --
--Create a trigger on the Bookings table that throws a descriptive error
--if the given seat number does not exist in the corresponding venue.
DROP TRIGGER IF EXISTS CheckSeat ON Bookings;
DROP FUNCTION IF EXISTS CheckSeat();

CREATE OR REPLACE FUNCTION CheckSeat()
RETURNS TRIGGER
AS
$$
BEGIN
	IF (NEW.seat_id > (	SELECT V.number_of_seats FROM Venues V
							JOIN EventSchedules ES ON NEW.schedule_id = ES.id
							JOIN Events E ON E.id = ES.event_id
						WHERE V.id = ES.venue_id)) OR (NEW.seat_id < 1)
		THEN RAISE EXCEPTION 'Function CheckSeat: Seat number does not exist in the corresponding venue.' USING errcode = '45000';
	END IF;
	
	RETURN NEW;
END
$$
LANGUAGE plpgsql;

CREATE trigger CheckSeat
BEFORE UPDATE OR INSERT ON Bookings
FOR EACH ROW EXECUTE PROCEDURE CheckSeat();

-- [ Dæmi 2 ] --
--Create a trigger on the EventSchedules table that makes sure that
--(i) each venue can only be booked once a day, and
--(ii) each event can only be scheduled once a day.
--If those rules are violated the trigger should throw a descriptive error and cancel the insertion.
DROP TRIGGER IF EXISTS CheckEvent ON EventSchedules;
DROP FUNCTION IF EXISTS CheckEvent();

CREATE OR REPLACE FUNCTION CheckEvent()
RETURNS TRIGGER
AS
$$
BEGIN
	IF EXISTS (SELECT * FROM EventSchedules ES
				WHERE NEW.venue_id = ES.venue_id
				AND to_char(ES.event_time, 'YYYY-MM-DD') = to_char(NEW.event_time, 'YYYY-MM-DD')
				AND ES.id != NEW.id)
		THEN RAISE EXCEPTION 'Function CheckEvent: Venue is already booked that day.' USING errcode = '45000';
	END IF;
	
	IF EXISTS (SELECT * FROM EventSchedules ES
				WHERE NEW.event_id = ES.event_id
				AND to_char(ES.event_time, 'YYYY-MM-DD') = to_char(NEW.event_time, 'YYYY-MM-DD')
				AND ES.id != NEW.id)
		THEN RAISE EXCEPTION 'Function CheckEvent: Event is already booked that day.' USING errcode = '45000';
	END IF;
	
	RETURN NEW;
END
$$
LANGUAGE plpgsql;

CREATE trigger CheckEvent
BEFORE UPDATE OR INSERT ON EventSchedules
FOR EACH ROW EXECUTE PROCEDURE CheckEvent();

-- [ Dæmi 3 ] --
--Create a function fGetNextSeatAvailable that takes a schedule ID as input parameter and returns
--the next available seat number on that event.
DROP FUNCTION IF EXISTS fGetNextSeatAvailable(IN iSchedule_id INTEGER);

CREATE OR REPLACE FUNCTION fGetNextSeatAvailable(IN iSchedule_id INTEGER)
RETURNS INTEGER
AS
$$
DECLARE
	nextSeat INTEGER := 1;
BEGIN
	LOOP
		IF (iSchedule_id < 1)
			THEN RAISE EXCEPTION 'Function fGetNextSeatAvailable: Sorry, but that is not a valid number' USING errcode = '45000';
		END IF;
		IF (nextSeat > (SELECT V.number_of_seats
						FROM Venues V
							JOIN EventSchedules ES ON V.id = ES.venue_id
						WHERE ES.id = iSchedule_id))
			THEN RAISE EXCEPTION 'Function fGetNextSeatAvailable: Sorry, no seat is available for this venue' USING errcode = '45000'
			EXIT;
		END IF;
		
		IF EXISTS (SELECT * FROM Bookings B
						WHERE B.schedule_id = iSchedule_id
						AND nextSeat = B.seat_id)
			THEN nextSeat := nextSeat + 1;
		ELSE
			RETURN nextSeat;
        END IF;
	END LOOP;
END
$$
LANGUAGE plpgsql;

-- [ Dæmi 4 ] --
--Create a function fGetNumberOfFreeSeats that takes a schedule ID as input parameter
--and returns the current number of free seats on that scheduled event. 
DROP FUNCTION IF EXISTS fGetNumberOfFreeSeats(IN iSchedule_id INTEGER);

CREATE OR REPLACE FUNCTION fGetNumberOfFreeSeats(IN iSchedule_id INTEGER)
RETURNS INTEGER
AS
$$
DECLARE
	availableSeats INTEGER;
BEGIN
	availableSeats := (SELECT V.number_of_seats
						FROM Venues V
							JOIN EventSchedules ES ON V.id = ES.venue_id
						WHERE ES.id = iSchedule_id);
	availableSeats := availableSeats - (SELECT COUNT(*) FROM Bookings B
										WHERE B.schedule_id = iSchedule_id);
	RETURN availableSeats;
END
$$
LANGUAGE plpgsql;

-- [ Dæmi 5 ] --
--Create a trigger on the Bookings table that maintains the number_of_bookedSeats counter
--in the EventSchedules table. This counter shows how many seats have been booked
--at that given event at each time. 
DROP TRIGGER IF EXISTS number_of_bookedSeats ON Bookings;
DROP FUNCTION IF EXISTS number_of_bookedSeats();

CREATE OR REPLACE FUNCTION number_of_bookedSeats()
RETURNS TRIGGER
AS
$$
DECLARE
	seatNumbers INTEGER;
BEGIN
	seatNumbers := (SELECT COUNT(*) FROM Bookings B
					WHERE B.schedule_id = NEW.schedule_id);
	
	UPDATE EventSchedules
		SET 	number_of_bookedSeats = seatNumbers
	WHERE id = NEW.schedule_id;
	
	RETURN NEW;
END
$$
LANGUAGE plpgsql;

CREATE trigger number_of_bookedSeats
AFTER UPDATE OR INSERT OR DELETE ON Bookings
FOR EACH ROW EXECUTE PROCEDURE number_of_bookedSeats();

-- [ Dæmi 6 ] --
--Create a function fFindConsecutiveSeats that takes as input parameters a
--scheduled event ID and the number of consecutive seats it should find.
--The function returns the first (lowest) seat number where there are
--sufficiently many free seats in a row.
--If no sequence of sufficiently many adjacent free seats exists, the function
--should throw a descriptive error.
DROP FUNCTION IF EXISTS fFindConsecutiveSeats(IN xID INTEGER, IN conSeats INTEGER);

CREATE OR REPLACE FUNCTION fFindConsecutiveSeats(IN xID INTEGER, IN conSeats INTEGER)
RETURNS INTEGER
AS
$$
DECLARE
	availableSeats INTEGER := 0;
	counter INTEGER := 1;
BEGIN
	LOOP
		IF(conSeats < 1)
			THEN RAISE EXCEPTION 'Function fFindConsecutiveSeats: Sorry, but that is not a valid number of seats' USING errcode = '45000';
			EXIT;
		END IF;
	
        IF (availableSeats = conSeats)
			THEN RETURN counter - availableSeats;
		END IF;
		
		IF (counter > (SELECT V.number_of_seats
						FROM Venues V
							JOIN EventSchedules ES ON V.id = ES.venue_id
						WHERE ES.id = xID))
			THEN RAISE EXCEPTION 'Function fFindConsecutiveSeats: Sorry, no consecutive number of seats available for that event' USING errcode = '45000';
			EXIT;
		END IF;
		
		IF EXISTS (SELECT * FROM Bookings B
					WHERE B.schedule_id = xID
					AND counter = B.seat_id)
			THEN availableSeats := 0;
		ELSE
			availableSeats := availableSeats + 1;
		END IF;
		
		counter := counter + 1;
	END LOOP;
END
$$
LANGUAGE plpgsql;

-- [ Dæmi 7 ] --
--Create a procedure, fBookManySeats that takes 4 input parameters
--(schedule ID, customer ssn, first seat number and how many seats it should book)
--and makes a booking for the given number of seats, starting on the
--first seat number provided and booking consecutive seats to the given customer. 
DROP FUNCTION IF EXISTS fBookManySeats( IN xID INTEGER, IN cSSN CHAR(10), IN seatNumber INTEGER, IN numberOfSeats INTEGER );

CREATE OR REPLACE FUNCTION fBookManySeats( IN xID INTEGER, IN cSSN CHAR(10), IN seatNumber INTEGER, IN numberOfSeats INTEGER )
RETURNS VOID 
AS 
$$
DECLARE
	consecutive INTEGER := numberOfSeats;
	selectedSeat INTEGER := seatNumber;
BEGIN
	LOOP
    	IF (consecutive < 1)
        	THEN EXIT;
        END IF;
		INSERT INTO Bookings (schedule_id, seat_id, people_ssn)
		VALUES (xID, selectedSeat, cSSN);
		selectedSeat := selectedSeat + 1;
		consecutive := consecutive - 1;
	END LOOP;
END;
$$ 
LANGUAGE plpgsql;

-- [ DÆMI 8 ] --
--Create a procedure, fFindAndBookSeats that takes number of consecutive seats that
--should be booked, the schedule ID and customer ssn.
--This procedure books the next available seat row with given amount of seats in a row
--to the given schedule to the given person.
--If seat row is not found, a descriptive error should be thrown describing the problem. 
DROP FUNCTION IF EXISTS fFindAndBookSeats( IN numberOfSeats INTEGER, IN xID INTEGER, IN cSSN CHAR(10) );

CREATE OR REPLACE FUNCTION fFindAndBookSeats( IN numberOfSeats INTEGER, IN xID INTEGER, IN cSSN CHAR(10) )
RETURNS VOID 
AS 
$$
DECLARE
	selectedSeat INTEGER;
BEGIN
	selectedSeat := fFindConsecutiveSeats(xID, numberOfSeats);
	
	PERFORM fBookManySeats(xID, cSSN, selectedSeat, numberOfSeats);
	RAISE NOTICE 'Seats booked';
END;
$$ 
LANGUAGE plpgsql;

-- [ DÆMI 9 ] --
--Create a view vGetShowList that returns a list of schedules that have not already passed.
--The list should show ID of schedule, ID of event, time of event,
--name of event, name of venue, total number of seats and how many seats are still available. 
DROP VIEW IF EXISTS vGetShowList;

CREATE OR REPLACE VIEW vGetShowList
AS
SELECT ES.id AS EvenSchedule_ID, E.id AS Event_ID, ES.event_time, E.name AS Event_Name, V.name AS Venue_Name, V.number_of_seats, (V.number_of_seats - ES.number_of_bookedSeats) AS available_seats
FROM EventSchedules ES
	JOIN Events E ON E.id = ES.event_id
	JOIN Venues V ON V.id = ES.venue_id
WHERE ES.event_time > CURRENT_TIMESTAMP
	
-- [ DÆMI 10 ] --
--Create a view vListOfVipPeople that shows ssn, name and email of all people that have
--booked every event that has been scheduled in the current year. 
DROP VIEW IF EXISTS vListOfVipPeople;

CREATE OR REPLACE VIEW vListOfVipPeople
AS
SELECT P.ssn AS Person_SSN, P.name AS Person_Name, P.email AS Person_Email
FROM People P
	JOIN Bookings B ON B.people_ssn = P.ssn
	JOIN EventSchedules ES ON ES.id = B.schedule_id
WHERE date_part('year', ES.event_time) = date_part('year', CURRENT_DATE)
GROUP BY P.ssn
HAVING COUNT(DISTINCT ES.event_id) = (SELECT COUNT(DISTINCT ES1.event_id)
                                     	FROM EventSchedules ES1);

