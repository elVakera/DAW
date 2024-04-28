~~~ mysql
# enunciats de funcions

-- Exercici 1
CREATE FUNCTION spData (data DATE) RETURNS char(10)
BEGIN
	RETURN DATE_FORMAT(data,"%d-%m-%Y");
END

-- Exercici 2
DROP FUNCTION IF EXISTS spPotencia;
CREATE FUNCTION spPotencia (pbase INT,pexp INT) RETURNS INT
BEGIN
    DECLARE res INT DEFAULT 1;
    DECLARE cont INT DEFAULT 0;

    IF(pexp!=0) THEN
        WHILE(cont < pexp) DO
            SET res=res*pbase;
            SET cont=cont+1;    
        END WHILE;
    ELSE
        SET res=1;
    END IF;
	RETURN res;
END;

-- Exercici 4
DROP FUNCTION IF EXISTS spPringat;
use rrhh;
DELIMITER //
CREATE FUNCTION spPringat(pdepCodi INT) RETURNS INT
NOT DETERMINISTIC READS SQL DATA
BEGIN
    DECLARE vcodiEmp INT DEFAULT -1;

    IF(spComprovarDep(pdepCodi)) THEN
        SELECT empleat_id INTO vcodiEmp
            FROM empleats
        WHERE departament_id=pdepCodi AND salari=(SELECT MIN(salari)
                                                    FROM empleats 
                                                WHERE departament_id =pdepCodi)
        LIMIT 1;
    END IF;

    RETURN vcodiEmp;
END
// DELIMITER;

SELECT spPringat(20)

DELIMITER //
CREATE FUNCTION spComprovarDep(pdepId INT) RETURNS BOOLEAN
NOT DETERMINISTIC READS SQL DATA
BEGIN
    DECLARE depExisteix BOOLEAN DEFAULT FALSE;

    SELECT EXISTS(SELECT * FROM departaments WHERE departament_id = pdepId) INTO depExisteix;

    RETURN depExisteix;

END
// DELIMITER ;


-- Exercici 6
DROP FUNCTION IF EXISTS spCategoria;

DELIMITER //
CREATE FUNCTION spCategoria(pcodi INT) RETURNS VARCHAR(17)
DETERMINISTIC 
BEGIN
    DECLARE cat VARCHAR(17);
    DECLARE edat INT;
    
    SELECT TIMESTAMPDIFF(YEAR, data_contractacio, CURDATE()) INTO edat
    FROM empleats
    WHERE empleat_id = pcodi;
    
    CASE 
        WHEN edat BETWEEN 0 AND 1 THEN SET cat = " Auxiliar";
        WHEN edat BETWEEN 2 AND 10 THEN SET cat = "Oficial de Segona";
        WHEN edat BETWEEN 11 AND 20 THEN SET cat = "Oficial de Primera";
        ELSE SET cat = "Que es jubili!";
    END CASE;
    
    RETURN cat;
END //
DELIMITER ;

-- Exercici 7
SELECT empleat_id,nom,TIMESTAMPDIFF(YEAR, data_contractacio, CURDATE()) anys_treballats,spCategoria(empleat_id) categoria
	FROM empleats;

-- Exercici 8
use rrhh;
DROP FUNCTION IF EXISTS spEdat;

DELIMITER //
CREATE FUNCTION spEdat(pdata Date) RETURNS INT
DETERMINISTIC 
BEGIN
    DECLARE edat INT;

    IF (pdata > CURDATE()) THEN  
        SET edat = 0;
    ELSE
        SELECT TIMESTAMPDIFF(YEAR, pdata, CURDATE())
        INTO edat;
    END IF;

    RETURN edat;
END //
DELIMITER ;
 
--  Exercici 9 

DROP FUNCTION IF EXISTS spDirectors;

DELIMITER //
CREATE FUNCTION spDirectors()  RETURNS INT
DETERMINISTIC 
BEGIN
    DECLARE num INT;

    SELECT COUNT(DISTINCT id_cap)  INTO num
        FROM empleats;

    RETURN num;
END //
DELIMITER ;

SELECT spDirectors()
~~~
