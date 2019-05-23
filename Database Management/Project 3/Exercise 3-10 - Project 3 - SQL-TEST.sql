-- [ TEST 1 ] --
--Create a trigger on the Bookings table that throws a descriptive error
--if the given seat number does not exist in the corresponding venue.
SELECT '1. View BookingSeats' AS now_testing;

BEGIN TRANSACTION;

SELECT 'Should give error on seat number' AS RESULT;

INSERT INTO Bookings VALUES (1, 4500, '1111748499');

ROLLBACK;

SELECT * FROM Venues;

-- [ TEST 2 ] --
--Create a trigger on the EventSchedules table that makes sure that
--(i) each venue can only be booked once a day, and
--(ii) each event can only be scheduled once a day.
--If those rules are violated the trigger should throw a descriptive error and cancel the insertion.
SELECT '2. Venue Booking Test' AS now_testing;

BEGIN TRANSACTION;

SELECT 'This should give an error on multiple venue bookings on the same day' AS RESULT;

INSERT INTO EventSchedules
VALUES (5, 3, 2, '2017-01-01 16:00:00', 8000, 5);
INSERT INTO EventSchedules
VALUES (6, 1, 2, '2017-01-01 18:00:00', 8000, 5);

ROLLBACK;

BEGIN TRANSACTION;

SELECT 'This should give an error on an event booked more than once a day' AS RESULT;

INSERT INTO EventSchedules
VALUES (5, 1, 2, '2017-01-01 16:00:00', 8000, 5);
INSERT INTO EventSchedules
VALUES (6, 1, 3, '2017-01-01 18:00:00', 8000, 5);

ROLLBACK;

-- [ TEST 3 ] --
--Create a function fGetNextSeatAvailable that takes a schedule ID as input parameter and returns
--the next available seat number on that event.
SELECT '3. Function fGetNextSeatAvailable' AS now_testing;

BEGIN TRANSACTION;

SELECT 'This check should work and return 10' AS RESULT;

INSERT INTO Bookings
VALUES (1, 1, '0707045879');
INSERT INTO Bookings
VALUES (1, 4, '0707045879');
INSERT INTO Bookings
VALUES (1, 5, '0707045879');
INSERT INTO Bookings
VALUES (1, 6, '0707045879');
INSERT INTO Bookings
VALUES (1, 7, '0707045879');
INSERT INTO Bookings
VALUES (1, 8, '0707045879');
INSERT INTO Bookings
VALUES (1, 9, '0707045879');
SELECT fGetNextSeatAvailable(1);

ROLLBACK;

BEGIN TRANSACTION;

SELECT 'This should return an error as there are no available seats' AS RESULT;

INSERT INTO Bookings
VALUES (1, 1, '0707045879');
INSERT INTO Bookings
VALUES (1, 4, '0707045879');
INSERT INTO Bookings
VALUES (1, 5, '0707045879');
INSERT INTO Bookings
VALUES (1, 6, '0707045879');
INSERT INTO Bookings
VALUES (1, 7, '0707045879');
INSERT INTO Bookings
VALUES (1, 8, '0707045879');
INSERT INTO Bookings
VALUES (1, 9, '0707045879');
INSERT INTO Bookings
VALUES (1, 10, '0707045879');
INSERT INTO Bookings
VALUES (1, 11, '0707045879');
INSERT INTO Bookings
VALUES (1, 12, '0707045879');
INSERT INTO Bookings
VALUES (1, 20, '0707045879');
SELECT fGetNextSeatAvailable(1);

ROLLBACK;

BEGIN TRANSACTION;

SELECT 'This should return an error as 0 is not a valid number' AS RESULT;
SELECT fGetNextSeatAvailable(0);

ROLLBACK;

SELECT *
FROM Bookings
ORDER BY seat_id

-- [ TEST 4 ] --
--Create a function fGetNumberOfFreeSeats that takes a schedule ID as input parameter
--and returns the current number of free seats on that scheduled event. 
SELECT '4. Function fGetNumberOfFreeSeats' AS now_testing;

BEGIN TRANSACTION;

SELECT 'This should return the number of seats available at the event, which should be 8.' AS RESULT;

INSERT INTO Bookings
VALUES (1, 1, '0707045879');
INSERT INTO Bookings
VALUES (1, 4, '0707045879');
INSERT INTO Bookings
VALUES (1, 5, '0707045879');

SELECT fGetNumberOfFreeSeats(1)

ROLLBACK;

BEGIN TRANSACTION;

SELECT 'This should return the number of seats available at the event, which should be 0 as there are none available.' AS RESULT;

INSERT INTO Bookings
VALUES (1, 1, '0707045879');
INSERT INTO Bookings
VALUES (1, 4, '0707045879');
INSERT INTO Bookings
VALUES (1, 5, '0707045879');
INSERT INTO Bookings
VALUES (1, 6, '0707045879');
INSERT INTO Bookings
VALUES (1, 7, '0707045879');
INSERT INTO Bookings
VALUES (1, 8, '0707045879');
INSERT INTO Bookings
VALUES (1, 9, '0707045879');
INSERT INTO Bookings
VALUES (1, 10, '0707045879');
INSERT INTO Bookings
VALUES (1, 11, '0707045879');
INSERT INTO Bookings
VALUES (1, 12, '0707045879');
INSERT INTO Bookings
VALUES (1, 20, '0707045879');

SELECT fGetNumberOfFreeSeats(1)

ROLLBACK;

-- [ TEST 5 ] --
--Create a trigger on the Bookings table that maintains the number_of_bookedSeats counter
--in the EventSchedules table. This counter shows how many seats have been booked
--at that given event at each time. 
SELECT '5. Trigger number_of_bookedSeats' AS now_testing;

BEGIN TRANSACTION;

SELECT 'These should both return 9' AS RESULT;

SELECT count(*)
FROM Bookings B
WHERE B.schedule_id = 1;

SELECT ES.number_of_bookedSeats FROM EventSchedules ES
WHERE 1 = ES.id

SELECT 'This should increase number_of_bookedSeats to 10' AS RESULT;

INSERT INTO Bookings
VALUES (1, 1, '0707045879');

SELECT 'These should return 10 now' AS RESULT;

SELECT count(*)
FROM Bookings B
WHERE B.schedule_id = 1;

SELECT ES.number_of_bookedSeats FROM EventSchedules ES
WHERE 1 = ES.id;

ROLLBACK;

-- [ TEST 6 ] --
--Create a function fFindConsecutiveSeats that takes as input parameters a
--scheduled event ID and the number of consecutive seats it should find.
--The function returns the first (lowest) seat number where there are
--sufficiently many free seats in a row.
--If no sequence of sufficiently many adjacent free seats exists, the function
--should throw a descriptive error.
SELECT '6. Function fFindConsecutiveSeats' AS now_testing;

BEGIN TRANSACTION;

SELECT 'This should return the first seat number where there are enough seats' AS RESULT;

SELECT fFindConsecutiveSeats(1, 0);
SELECT fFindConsecutiveSeats(1, -1);
SELECT fFindConsecutiveSeats(1, 1);
SELECT fFindConsecutiveSeats(1, 6);
SELECT fFindConsecutiveSeats(1, 9);
SELECT fFindConsecutiveSeats(1, 12);

ROLLBACK;

SELECT *
FROM Bookings
ORDER BY seat_id

-- [ TEST 7 ] --
--Create a procedure, fBookManySeats that takes 4 input parameters
--(schedule ID, customer ssn, first seat number and how many seats it should book)
--and makes a booking for the given number of seats, starting on the
--first seat number provided and booking consecutive seats to the given customer. 
SELECT '7. Procedure fBookManySeats' AS now_testing;

BEGIN TRANSACTION;

SELECT 'This should book many seats' AS now_testing;

SELECT fGetNumberOfFreeSeats(1);

SELECT fBookManySeats(1, '0707045879', 4, 5);

SELECT fGetNumberOfFreeSeats(1);

ROLLBACK;

SELECT *
FROM Bookings
ORDER BY seat_id;

-- [ TEST 8 ] --
--Create a procedure, fFindAndBookSeats that takes number of consecutive seats that
--should be booked, the schedule ID and customer ssn.
--This procedure books the next available seat row with given amount of seats in a row
--to the given schedule to the given person.
--If seat row is not found, a descriptive error should be thrown describing the problem. 

SELECT '8. Procedure fFindAndBookSeats' AS now_testing;

BEGIN TRANSACTION;

SELECT 'This should book many seats' AS now_testing;

SELECT fGetNumberOfFreeSeats(1);

SELECT fBookManySeats(1, '0707045879', 5, 3);
SELECT fFindAndBookSeats(3, 1, '0707045879');

SELECT fGetNumberOfFreeSeats(1);

ROLLBACK;

SELECT *
FROM Bookings
ORDER BY seat_id;

-- [ TEST 9 ] --
--Create a view vGetShowList that returns a list of schedules that have not already passed.
--The list should show ID of schedule, ID of event, time of event,
--name of event, name of venue, total number of seats and how many seats are still available. 
select '9. View vGetShowList' as now_testing;

select 'Should return the available seats for each event/venue' as result;

select available_seats
from vGetShowList;

select 'This should return the name and event time for each event' as result;

select Event_name, event_time
from vGetShowList

-- [ DÆMI 10 ] --
--Create a view vListOfVipPeople that shows ssn, name and email of all people that have
--booked every event that has been scheduled in the current year. 
select '9. View vListOfVipPeople' as now_testing;

select 'Should not return anything as no person qualifies' as result;

select *
from vListOfVipPeople;

BEGIN TRANSACTION;

select 'This should return one name "Susan Bjarnrún Jósúadóttir" as a result' as result;

SELECT fFindAndBookSeats(3, 1, '0707045879');
SELECT fFindAndBookSeats(3, 2, '0707045879');

select Person_Name
from vListOfVipPeople

ROLLBACK;