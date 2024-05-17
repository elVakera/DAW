# Base de dades edx – col·lecció students
1. Busca els estudiants de gènere masculí
~~~~javascript
db.students.find({"gender":{$eq:"H"}})
~~~~ 
2. Digues quina és la quantitat d’estudiants de gènere femení.
~~~~javascript
db.students.find({"gender":{$eq:"M"}}).count()
~~~~ 
3. Busca els estudiants nascuts l’any 1993
~~~~javascript
db.students.find({"birth_year":{$eq:1993}}).count()
~~~~ 
4. Busca els estudiants de gènere masculí i nascuts l’any 1993
~~~~javascript
db.students.find({"birth_year":{$eq:1993},"gender":{$eq:"H"}}).count()
~~~~ 
5. Busca els estudiants nascuts després de l’any 1990
~~~~javascript
db.students.find({"birth_year":{$gt:1990}})
~~~~ 
6. Busca els estudiants nascuts abans o durant l’any 1990
~~~~javascript
db.students.find({"birth_year":{$lte:1990}})
~~~~ 
7. Busca els estudiants nascuts a la dècada dels 90s
~~~~javascript
db.students.find({$and:[{"birth_year":{$gte:1990}},{"birth_year":{$lte:1999}}]})
~~~~ 
8. Busca els estudiants de gènere femani nascus a la dècada dels 90s
~~~~javascript
db.students.find({$and:[{"birth_year":{$gte:1990}},{"birth_year":{$lte:2000}},{"gender":{$eq:"M"}}]})
~~~~ 
9. Busca els estudiants que no han nascut a l’any 1985
~~~~javascript
db.students.find({"birth_year":{$not:{$eq:1985}}})
~~~~ 
10. Busca aquells estudiants que han nascut l’any 1970,1980 o 1990
~~~~javascript
db.students.find({$or:[{"birth_year":{$eq:1970}},{"birth_year":{$eq:1980}},{"birth_year":{$eq:1990}}]})
~~~~ 
11. Busca aquells estudiants que no han nascut l’any 1970,1980 o 1990
~~~~javascript
db.students.find({$and:[{"birth_year":{$not:{$eq:1970}}},{"birth_year":{$not:{$eq:1980}}},{"birth_year":{$not:{$eq:1990}}}]})
~~~~ 
12. Busca aquells estudiants nascuts en any parell.
~~~~javascript
db.students.find({"birth_year":{$mod:[2, 0]}})
~~~~ 
13. Busca els estudiants que tinguin telèfon auxiliar
~~~~javascript
db.students.find({"phone_aux":{$exists : true}})
~~~~ 
14. Busca els estudiants que no tinguin telèfon auxiliar
~~~~javascript
db.students.find({"phone_aux":{$exists : false}})
~~~~ 
15. Busca els estudiants que no tinguin segon cognom
~~~~javascript
db.students.find({"lastname2":{$exists : false}})
~~~~ 
16. Busca els estudiants que tinguin telèfon auxiliar i només el primer cognom
~~~~javascript
db.students.find({$and:[{"phone_aux":{$exists : true}},{"lastname2":{$exists : false}}]})
~~~~ 
17. Busca els estudiants que el seu correu electrònic acabi en .net
~~~~javascript
db.students.find({"email": /\.net$/})
~~~~ 
18. Busca els estudiants que el seu telèfon comenci per 622
~~~~javascript
db.students.find({"phone": /^622/})
~~~~ 
19. Busca els estudiants que el seu dni comenci i acabi amb una lletra
~~~~javascript
db.students.find({"dni": /^[a-z].+[a-z]$/i})
~~~~ 
20. Busca els estudiants que el seu nom comenci per una vocal
~~~~javascript
db.students.find({"name": /^[aeiou]/i})
~~~~ 
21. Busca els estudiants que el seu nom sigui compost
~~~~javascript
db.students.find({"name": /\s/)
~~~~ 
22. Busca els estudiants amb un nom més llarg de 13 caràcters
~~~~javascript
db.students.find({$where: "(this.name.length > 13)"})
~~~~ 
23. Busca els estudiants amb 3 o més vocals en el seu nom
~~~~javascript
db.students.find({"name":/.*[aeiouàáèéíóòú]{3,}.*/i})
~~~~ 
# Base de dades edx – col·lecció bios
24. Busca aquells desenvolupadors que han realitzat contribucions en OOP
~~~~javascript

~~~~ 
25. Busca aquells desenvolupadors que han realitzat contribucions en OOP o Java
~~~~javascript

~~~~ 
26. Busca aquells desenvolupadors que han realitzat contribucions en OOP i Simula
~~~~javascript

~~~~ 
27. Busca aquells desenvolupadors que siguin vius
~~~~javascript

~~~~ 
28. Busca aquells desenvolupadors que siguin morts
~~~~javascript

~~~~ 
29. Busca aquells desenvolupadors que han obtingut un premi a l’any 2002
~~~~javascript

~~~~ 
30. Busca aquells desenvolupadors que han obtingut exactament 3 premis.
~~~~javascript

~~~~ 
# Base de dades imdb – col·lecció people
31. Buscar les persones que només han actuat. No han dirigit
~~~~javascript

~~~~ 
32. Busca les persones que només an dirigit. No han actuat
~~~~javascript

~~~~ 
33. Buscar les persones que han actuat i dirigit
~~~~javascript

~~~~ 
34. Buscar les persones que ni han actuat ni dirigit.
~~~~javascript

~~~~ 
35. Buscar les pel·lícules protagonitzades per Penelope Cruz
~~~~javascript

~~~~ 
# Base de dades edx – col·lecció books
36. Buscar aquells llibres que han estat escrits per Martin Fowloer i Kent Beck
~~~~javascript

~~~~ 
37. Buscar els llibres que tinguin el tag ‘programming’ i ‘agile’
~~~~javascript

~~~~ 
38. Buscar els llibres amb el tag ‘html’, ‘html5’, ‘css’ o ‘css3’
~~~~javascript

~~~~ 
39. Buscar els llibres que no tinguin el tag ‘html’, ‘html5’, ‘css’ o ‘css3’
~~~~javascript

~~~~ 
