Exercici 0 – Importació de la BD
Importa els fitxers .json per la BD de rrhh proporcionada per el professor
(https://github.com/robertventura/databases/blob/master/mongodb/rrhh/)
Passos:
 Crea la BD rrhh
 Importa el fitxer departaments.json  col·lecció departaments
 Importa el fitxer empleats.json  col·lecció empleats
Exercicis $group
1. Mostra la quantitat d’empleats per cada departament. Mostra id de departament i la
quantitat.
~~~ javascript
db.empleats.aggregate(
  [
    {
      $group: {
        _id: "$departament.codi",
        empleats: {$sum: 1}
      }
    }
  ]
)
~~~
2. Si no ho has tingut en compte en l’exercici anterior. Només tingues en compte
aquells empleats que estiguin en un departament.
~~~ javascript
db.getCollection('empleats').aggregate(
  [
    {
      $group: {
        _id:'$departament.codi',
        empleats:{$sum:1}
      }
    },
    {
      $match: {
        _id:{$not:{$eq:null}}
      }
    }
  ]
)
~~~
3. Ordena el resultat anterior per els departament de més a menys nombre
d’empleats.
~~~ javascript
db.getCollection('empleats').aggregate(
  [
    {
      $group: {
        _id: '$departament.codi',
        empleats: { $sum: 1 }
      }
    },
    { $match: { _id: { $not: { $eq: null } } } },
    { $sort: { empleats: -1 } }
  ]
)
~~~
4. De cada departament mostra el salari més alt. Mostra id de departament i el salari
més alt.
~~~ javascript
db.getCollection('empleats').aggregate(
  [
    {
      $group: {
        _id: '$departament.nom',
        empleats: { $sum: 1 },
        maxSalari: { $max: '$salari' }
      }
    },
    { $match: { _id: { $not: { $eq: null } } } },
    { $sort: { empleats: -1 } }
  ]
)
~~~
5. Quina és la massa salarial de cada departament? Mostra id de departament i la
massa salarial.
~~~ javascript
db.getCollection('empleats').aggregate(
  [
    {
      $group: {
        _id: '$departament.nom',
        empleats: { $sum: 1 },
        massaSalarial: { $sum: '$salari' }
      }
    },
    { $match: { _id: { $not: { $eq: null } } } },
    { $sort: { empleats: -1 } }
  ]
)
~~~
6. Només mostra aquells departaments que tinguin una massa salarial igual o
superior a 19000
~~~ javascript
db.getCollection('empleats').aggregate(
  [
    {
      $group: {
        _id: '$departament.nom',
        empleats: { $sum: 1 },
        massaSalarial: { $sum: '$salari' }
      }
    },
    {
      $match: {
        _id: { $not: { $eq: null } },
        massaSalarial: { $gte: 19000 }
      }
    },
    { $sort: { empleats: -1 } }
  ]
)
~~~
7. Redefineix el camp “historial_feines” perquè tingui el número de feines i no el detall
de les feines que ha tingut cada empleat. Si l’empleat no ha tingut cap treure el
camp “historial_feines”.
~~~ javascript
db.getCollection('empleats').aggregate(
  [
    {
      $addFields: {
        historial_feines: {
          $cond: {
            if: { $isArray: '$historial_feines' },
            then: { $size: '$historial_feines' },
            else: '$$REMOVE'
          }
        }
      }
    }
  ]
)
~~~
8. Com que tot empleat té una feina incorpora la feina actual com una feina més.
~~~ javascript
db.getCollection('empleats').aggregate(
  [
    {
      $addFields: {
        historial_feines: {
          $cond: {
            if: { $isArray: '$historial_feines' },
            then: { $size: '$historial_feines' },
            else: '$$REMOVE'
          }
        }
      }
    },
    {
      $addFields: {
        historial_feines: {
          $add: ['$historial_feines', 1]
        }
      }
    }
  ]
)
~~~    
9. Mostra aquells empleats que han tingut 2 feines o més.
~~~ javascript
db.getCollection('empleats').aggregate(
  [
    {
      $addFields: {
        historial_feines: {
          $cond: {
            if: { $isArray: '$historial_feines' },
            then: { $size: '$historial_feines' },
            else: '$$REMOVE'
          }
        }
      }
    },
    {
      $addFields: {
        historial_feines: {
          $add: ['$historial_feines', 1]
        }
      }
    },
    {
      $match: {
        historial_feines: {$gte:2}
      }
    }
  ]
)
~~~    
10.Per cada empleat mostra les dades del seu departament. Redefineix el mateix
camp departament del document empleats.
~~~ javascript
db.getCollection('empleats').aggregate(
  [
    {
      $lookup: {
        from: 'epartaments',
        localField: 'departament.coi',
        foreignField: 'codi',
        as: 'departament'
      }
    }
  ]
)
~~~
11.Si has utilitzat $join a l’exercici anterior veuràs que el camp departament és un
array. Per tant fés el que necessitis perquè aquest camp sigui un objecte a on hi
hagi la informació del departament i no un array.
~~~ javascript
db.getCollection('empleats').aggregate(
  [
    {
      $lookup: {
        from: 'epartaments',
        localField: 'departament.coi',
        foreignField: 'codi',
        as: 'departament'
      }
    },
    {
      $addFields: {
        departament: {
          $arrayElemAt: ['$departament', 0]
        }
      }
    }
  ]
)
~~~
