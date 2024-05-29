1. Volem comparar l'alçada dels dos germans Gasol. El noms curts són "Pau Gasol" i
"Marc Gasol".
~~~ javascript
db.getCollection('jugadors').aggregate(
  [
    {
      $match: {
        $or: [
          { nom_curt: 'Pau Gasol' },
          { nom_curt: 'Marc Gasol' }
        ]
      }
    },
    {
      $project: { _id: 0, nom_curt: 1, alcada: 1 }
    }
  ]
)
~~~
2. L’entrenador “Pedro Martínez” és un dels entrenadors més veterans. Quants partits
ha participat com a entrenador. Independentment de si ho ha fet com a local o com
a visitant. Restringeix la consulta als partits de lla Lliga Regular de la temporada
2023-2024.
~~~ javascript
db.getCollection('partits').aggregate(
  [
    {
      $match: {
        temporada: '2023-2024',
        competicio: 'Lliga Regular'
      }
    },
    {
      $addFields: {
        entrenador: '$equip_local.entrenadors.nom'
      }
    },
    { $unwind: '$entrenador' },
    { $match: { entrenador: 'Pedro Martínez' } },
    { $count: 'entrenador' }
  ]
)
~~~
3. La llicència "JFL" indica que és un jugador de formació. Quants jugadors tenim amb
aquesta llicència?.
~~~ javascript

~~~
4. Passa a Decimal el camp alcada de la col·lecció jugadors.
(https://www.mongodb.com/docs/manual/reference/operator/aggregation/convert/)
~~~ javascript

~~~
5. Quins jugadors tenim el compte d'Instagram? Mostra el nom_curt del jugador i
l'usuari d'Instragram.
~~~ javascript
$match
$unwind
~~~
6. Dona el número total de punts de l'equip local del partit amb codi_acb :"103778"
~~~ javascript
$addFields
$toDecimal
~~~
7. Obtenir els punts de cada equip (local i visitant) del partit amb codi_acb: "103778"
~~~ javascript
$match:{"codi_acb":"1023131"},
$unwind: "$equip_local.jugadors"},
$group: : "local", "total_punts": {"$sum":"equip_local.jugadors.estadistic.punts"
~~~
