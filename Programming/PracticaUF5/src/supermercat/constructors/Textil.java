package supermercat.constructors;

/**
 * Clase especifica per la creacio d'un producte textil, mante les propietats d'un objecte producte a mes de les
 * distintives de la seva clase
 */
public class Textil extends Producte{
    //variables locals
    String composicioTextil;

    /**
     * Constructor de Textil, a partir d'uns parametres d'entrada es genera un objecte textil a partir de les bases de
     * producte i les seves propies
     * @param preu Paramentre d'entrada que defineix el preu segons els criteris del constructor Producte (la classe pare)
     * @param nom Paramentre d'entrada que defineix el nom segons els criteris del constructor Producte (la classe pare)
     * @param codiBarres Paramentre d'entrada que defineix el codi de barres segons els criteris del constructor
     *                   Producte (la classe pare)
     * @param composicioTextil Parametre d'entrada que defineix la composicio textil d'un producte textil a traves de
     *                         la funcio setComposicioTextil
     */
    public Textil(float preu, String nom, String codiBarres, String composicioTextil){
        super(preu,nom,codiBarres);
        setComposicioTextil(composicioTextil);

    }

    /**
     * Funcio per modificar la composicio textil d'un producte textil, a partir d'un parametre d'entrada modifica el
     * valor que defineix la composicio textil d'un producte textil
     * @param composicioTextil Parametre rebut per el constructor i que a traves d'auesta funcio definira la composicio
     *                        textil d'un producte textil
     */
    public void setComposicioTextil(String composicioTextil) {
        this.composicioTextil = composicioTextil;
    }

    /**
     * Funcio que extrau la composicio textil dun producte textil
     * @return el valor String de la variable composicioTextil d'un producte textil
     */
    public String getComposicioTextil() {
        return composicioTextil;
    }

    /**
     * Funcio que extrau el preu, funcio que sobrescric getPreu() de Producte (la clase pare) que retorna el preu d'un
     * producte textil
     * @return retorna el valor numeric amb decimals de la variable preu
     */
    @Override
    public float getPreu() {
        return preu;
    }

    /**
     * Funcio per mostrar l'objecte textil com un String sobrescribint la toString generica
     * @return Retorna l'objecte textil amb un format determinat
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
