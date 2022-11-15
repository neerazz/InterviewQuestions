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

select emp.id,
       emp.name,
       (select division.name from cb_companydivisions division where division.id = emp.divisionID) as divisionName,
       managerId,
       case
           when (select manager.name from cb_companymanager manager where manager.id = emp.managerId) is null
               then emp.managerId
           else (select manager.name
                 from cb_companymanager manager
                 where manager.id = emp.managerId)
           end                                                                                     as "Manager Name/ID",
       salary
from (SELECT id, name, divisionID, managerId, salary FROM maintable_c4mzo ORDER BY salary DESC LIMIT 3) emp
order by salary
LIMIT 1;
