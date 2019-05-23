-- Query 7

EXPLAIN (ANALYZE, COSTS, VERBOSE, BUFFERS, FORMAT JSON)

select distinct P.ID, P.name, P.height, R.result, S.name, case (R.result = S.record) when true then 'Yes' else 'No' end as "record?"
from People P, Results R, Sports S
where P.ID = R.peopleID
  and S.ID = R.sportID
  and R.result = (
    select max(R1.result)
          from Results R1
    where R1.sportID = R.sportID
);
