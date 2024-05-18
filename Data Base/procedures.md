# Procedures

Ex 1
~~~ mysql
DELIMITER //
CREATE PROCEDURE spObtenirDataUsuari ()
BEGIN
    SELECT NOW(),USER()
END
// DELIMITER ;
~~~ 
Ex 2
~~~ mysql
DROP PROCEDURE IF EXISTS spSwapSou;
DELIMITER //
CREATE PROCEDURE spSwapSou (IN pEmpId1 INT ,IN pEmpId2 INT)
BEGIN
    DECLARE vT DECIMAL(8,2);
    SELECT salari INTO vT
        FROM empleats
    WHERE empleat_id=pEmpId1;

    UPDATE empleats SET salari = (SELECT salari FROM empleats WHERE empleat_id=pEmpId2)
         WHERE empleat_id=pEmpId1;
    UPDATE empleats SET salari = vT
         WHERE empleat_id=pEmpId2;
END
// DELIMITER ;
~~~ 
Ex 3
~~~ mysql
DROP PROCEDURE IF EXISTS spSwapDep;
DELIMITER //
CREATE PROCEDURE spSwapDep (IN pEmpId1 INT ,IN pEmpId2 INT)
BEGIN

    DECLARE vT INT;

    SELECT departament_id INTO vT 
        FROM empleats 
    WHERE empleat_id=pEmpId1;

   UPDATE empleats
        departament_id=vT;
    WHERE empleat_id =pEmpId2;
END
// DELIMITER ;
~~~ 
Ex 4
~~~ mysql
DROP PROCEDURE IF EXISTS spReasignarEmpleats;
DELIMITER //
CREATE PROCEDURE spReasignarEmpleats (IN pdep1 INT ,IN pdep2 INT)
BEGIN
    UPDATE empleats
        SET departament_id = pdep1
    WHERE departament_id = pdep2;
END
// DELIMITER ;
~~~ 
Ex 5
~~~ mysql
DROP PROCEDURE IF EXISTS spLlista;
DELIMITER //
CREATE PROCEDURE spLlista ()
BEGIN
    SELECT e.empleat_id as id_empleat,e.nom as nom_empleat,d.nom as nom_departament,l.ciutat as localitzacio_dept
        FROM empleats e
        LEFT JOIN departaments d ON e.departament_id = d.departament_id
        LEFT JOIN localitzacions l ON d.localitzacio_id = l.localitzacio_id;

END
// DELIMITER ;
~~~ 
Ex 6
~~~ mysql
DELIMITER //
CREATE PROCEDURE spInfoEmp (IN pEmpcodi INT)
BEGIN
    SELECT *
        FROM empleats
    WHERE empleat_id = pEmpcodi;
END
// DELIMITER ;
~~~
Ex 7
~~~ mysql
CREATE TABLE
    logs_usuaris (
        usuari VARCHAR(100),
        data DATETIME,
    );
~~~
Ex 8
~~~ mysql
DELIMITER //
CREATE PROCEDURE  spInserirlogs()
BEGIN
    INSERT INTO logs_usuaris
    SELECT CURRENT_USER(), NOW();
END
// DELIMITER ;
~~~ 
Ex 9
~~~ mysql
DELIMITER //
CREATE PROCEDURE  spAfegirDep(pCodiDep INT,pNomDep VARCHAR(30),pCodiLoc INT)
BEGIN
    IF (spComprovarLoc(pCodiLoc)) THEN
        INSERT INTO departaments(departament_id,nom,localitzacio_id)
        VALUES(pCodiDep,pNomDep,pCodiLoc);
    ELSE
        INSERT INTO departaments(departament_id,nom,localitzacio_id)
        VALUES(pCodiDep,pNomDep,NULL);
    END IF;

END
// DELIMITER ;

CALL spAfegirDep(1,'sapa',1);

DELIMITER //
CREATE FUNCTION spComprovarLoc(pLocId INT) RETURNS BOOLEAN
NOT DETERMINISTIC READS SQL DATA
BEGIN
    DECLARE locExisteix BOOLEAN DEFAULT FALSE;

    SELECT EXISTS(SELECT * FROM localitzacions WHERE localitzacio_id = pLocId) INTO locExisteix;

    RETURN locExisteix;

END
// DELIMITER ;
~~~
Ex 10
~~~ mysql
DROP PROCEDURE IF EXISTS spNomCognom;
DELIMITER //
CREATE PROCEDURE spNomCognom (IN pEmpcodi INT,OUT vNom VARCHAR(20),OUT vCognom VARCHAR(25))
BEGIN
    SELECT nom,cognoms INTO vNom,vCognom 
        FROM empleats
    WHERE empleat_id=pEmpcodi;

END
// DELIMITER ;

CALL spNomCognom(100)
~~~
~~~ mysql
DROP PROCEDURE IF EXISTS spModNomCognom;
DELIMITER //
CREATE PROCEDURE spModNomCognom (IN pEmpcodi INT,IN pNom VARCHAR(20),IN pCognom VARCHAR(25))
BEGIN

    IF (pNom IS NOT NULL AND pCognom IS NOT NULL) THEN
        UPDATE empleats
            SET nom = pNom,
                cognoms = pCognom
        WHERE empleat_id=pEmpcodi;
    END IF;

END
// DELIMITER ;
~~~~
~~~ mysql
DROP PROCEDURE IF EXISTS spRegLog;
DELIMITER //
CREATE PROCEDURE spRegLog (IN pEmpcodi INT,IN ptaula VARCHAR(20),IN pVpk VARCHAR(25))
BEGIN

    INSERT INTO logs_usuaris(usuari,data,taula,valor_pk)
        VALUES(user(),now(),ptaula,pVpk);
END
// DELIMITER ;
~~~
~~~ mysql
DROP PROCEDURE IF EXISTS spEliminarDeps;
DELIMITER //
CREATE PROCEDURE spEliminarDeps (IN pCodi INT)
BEGIN

     DELETE FROM departaments
     WHERE departament_id=pCodi;

     IF (!spComprovarDep(pCodi)) THEN 
         CALL spRegLog ('departaments', 'ELIMINAR', pCodi);
     END IF;

     UPDATE empleats
         SET id_cap=NULL
     WHERE empleat_id IN (SELECT empleat_id FROM empleats WHERE departament_id=pCodi)

 END
 // DELIMITER ;
~~~ 
