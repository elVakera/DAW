package supermercat.Constructors;
import supermercat.model.Model;
import java.util.Date;

/**
 * Clase especifica per la creacio d'un producte alimentacio, mante les propietats d'un objecte producte a mes de les
 * distintives de la seva clase
 */
public class Alimentacio extends Producte{
    //variables locals
    Date dataCaducitat;

    /**
     * Constructor de Alimentacio, a partir d'uns parametres d'entrada es genera un objecte alimentacio a partir de les
     * bases de producte i les seves propies
     * @param preu Paramentre d'entrada que defineix el preu segons els criteris del constructor Producte (la classe pare)
     * @param nom Paramentre d'entrada que defineix el nom segons els criteris del constructor Producte (la classe pare)
     * @param codiBarres Paramentre d'entrada que defineix el codi de barres segons els criteris del constructor
     *                   Producte (la classe pare)
     * @param dataCaducitat Parametre d'entrada que defineix la data de caducitat d'un producte d'alimentacio a traves
     *                      de la funcio setDataCaducitat
     */
    public Alimentacio(float preu, String nom, String codiBarres, Date dataCaducitat){
        super(preu, nom, codiBarres);
        setDataCaducitat(dataCaducitat);
    }

    /**
     * Funcio per modificar la data de caducitat d'un producte d'alimentacio, a partir d'un parametre d'entrada modifica
     * el valor que defineix la data de caducitat d'un producte d'alimentacio
     * @param dataCaducitat Parametre rebut per el constructor i que a traves d'auesta funcio definira la data de
     *                      caducitat d'un producte d'alimentacio
     */
    public void setDataCaducitat(Date dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

    /**
     * Funcio per extreure la data de caducitat, funcio generica que retorna la data de caducitat d'un producte
     * d'alimentacio
     * @return Retorna el valor Date de la variable dataCaducitat
     */
    public Date getDataCaducitat() {
        return dataCaducitat;
    }

    /**
     * Funcio per extreure el preu, funcio que sobrescric getPreu() de Producte (la clase pare) que retorna el preu d'un
     * producte d'alimentacio
     * @return Retorna el valor de la variable preu definida per la funcio preuAlimentacio de la clase Model, utilitzant
     * com a parametre el preu del producte d'alimentacio definit segons els criteris del constructor Producte (clase
     * pare) i la funcio getDataCaducitat
     */
    @Override
    public float getPreu(){
        return this.preu = Model.preuAlimentacio(super.preu, getDataCaducitat());
    }

    /**
     * Funcio per mostrar l'objecte alimentacio com un String sobrescribint la toString generica
     * @return Retorna l'objecte alimentacio amb un format determinat
     */
    @Override
    public String toString() {
        if(getPreu() <= 0){
            return String.format("\t%-15s\t%-12s\t%-8s\n", super.getNom(), super.getCodiBarres(), 0);
        }else {
            return String.format("\t%-15s\t%-12s\t%-8.2f\n", super.getNom(), super.getCodiBarres(), getPreu());
        }
    }
}