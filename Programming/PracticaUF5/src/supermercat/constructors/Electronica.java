package supermercat.constructors;
import supermercat.model.Model;

/**
 * Clase especifica per la creacio d'un producte electronic, mante les propietats d'un objecte producte a mes de les
 * distintives de la seva clase
 */
public class Electronica extends  Producte{
    //variables locals
    int diesGarantia;

    /**
     * Constructor de Electronica, a partir d'uns parametres d'entrada es genera un objecte electronica a partir de les
     * bases de producte i les seves propies
     * @param preu Paramentre d'entrada que defineix el preu segons els criteris del constructor Producte (la classe pare)
     * @param nom Paramentre d'entrada que defineix el nom segons els criteris del constructor Producte (la classe pare)
     * @param codiBarres Paramentre d'entrada que defineix el codi de barres segons els criteris del constructor
     *                   Producte (la classe pare)
     * @param diesGarantia Parametre d'entrada que defineix els dies de garantia d'un producte electronic
     */
    public Electronica(float preu, String nom, String codiBarres, int diesGarantia){
        super(preu, nom, codiBarres);
        setDiesGarantia(diesGarantia);
    }

    /**
     * Funcio per modificar els dies de garantia d'un producte d'electronica, a partir d'un parametre d'entrada modifica
     * el valor que defineix els dies de garantia d'un producte d'electronica
     * @param diesGarantia Parametre rebut per el constructor i que a traves d'auesta funcio definira els dies de
     *                     garantia d'un producte d'electronica
     */
    public void setDiesGarantia(int diesGarantia) {
        this.diesGarantia = diesGarantia;
    }

    /**
     * Funcio per extreure els dies de garantia, funcio generica que retorna el valor numeric dels dies de garantia d'un
     * producte d'electronica
     * @return Retorna el valor numeric enter de la variable diesGarantia
     */
    public int getDiesGarantia(){
        return diesGarantia;
    }

    /**
     * Funcio per extreure el preu, funcio que sobrescric getPreu() de Producte (la clase pare) que retorna el preu d'un
     * producte d'electronica
     * @return Retorna el valor de la variable preu definida per la funcio preuElectronica de la clase Model, utilitzant
     * com a parametre el preu del producte d'electronica definit segons els criteris del constructor Producte (clase
     * pare) i la funcio getDiesGarantia
     */
    @Override
    public float getPreu() {
        return this.preu = Model.preuElectronica(super.preu, getDiesGarantia());
    }

    /**
     * Funcio per mostrar l'objecte electronica com un String sobrescribint la toString generica
     * @return Retorna l'objecte electronica amb un format determinat
     */
    @Override
    public String toString() {
        //si el preu es inferior o igual a 0 sera 0
        if(getPreu() <= 0){
            return String.format("\t%-15s\t%-12s\t%-8s\n", super.getNom(), super.getCodiBarres(), 0);

        }else {
            return String.format("\t%-15s\t%-12s\t%-8.2f\n", super.getNom(), super.getCodiBarres(), getPreu());
        }
    }
}

