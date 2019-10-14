--names of all persons that are NOT in the company with id = 5
SELECT name FROM person
WHERE company_id <> 5;

--company name for each person
SELECT company.name as company, person.name as name
FROM person LEFT JOIN company ON person.company_id = company.id

-- Retrieve in a single query:
-- names of all persons that are NOT in the company with id = 5
-- company name for each person
SELECT company.name as company, person.name as name
FROM person LEFT JOIN company ON person.company_id = company.id
WHERE company_id <> 5;

--Select the name of the company with the maximum number of persons + number of persons in this company
SELECT company.name as company, count(person.id) as employees
FROM person LEFT JOIN company ON person.company_id = company.id
GROUP BY company
ORDER BY employees DESC LIMIT 1