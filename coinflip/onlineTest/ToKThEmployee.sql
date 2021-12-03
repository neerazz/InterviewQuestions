SELECT id, name, divisionID, managerId, salary FROM maintable_C4MZO ORDER BY Salary DESC LIMIT 3;

select id, name, divisionID, managerId, salary
from (SELECT id, name, divisionID, managerId, salary FROM maintable_C4MZO ORDER BY Salary DESC LIMIT 3) emp
order by salary LIMIT 1;

select id, name,
       (select division.name from cb_companydivisions division where division.id = emp.divisionID) as DivisionName,
       (select manager.name from maintable_C4MZO manager where manager.id = emp.managerId)        as ManagerName,
       salary
from (SELECT id, name, divisionID, managerId, salary FROM maintable_C4MZO ORDER BY Salary DESC LIMIT 3) emp
order by salary LIMIT 1;