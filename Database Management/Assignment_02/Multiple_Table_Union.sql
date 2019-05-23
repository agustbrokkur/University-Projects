SELECT p.name, g.gender, s.record, c.place
FROM People p
JOIN Gender g
	ON p.gender = g.gender
JOIN Results r
	ON p.ID = r.peopleID
JOIN Sports s
	ON s.ID = r.sportID
JOIN Competitions c
	ON c.ID = r.competitionID
LIMIT 30;