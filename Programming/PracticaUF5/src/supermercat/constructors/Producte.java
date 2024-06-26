package supermercat.constructors;
import java.util.Comparator;

/**
 * Clase general del tipus de productes, aquesta clase abstracte es la base per a crear cualsevol tipus de
 * producte en especific
 */
public abstract class Producte  implements Comparable <Producte>, Comparator<Producte> {
    //variables locals
    float preu;
    String nom, codiBarres;

    /**
     * Constructor de producte, a partir d'uns paramentes d'entrada es genera un objecte producte
     * @param preu parametre d'entrada per definir el preu d'un producte
     * @param nom parametre d'entrada per definir el nom d'un producte
     * @param codiBarres parametre d'entrada per definir el codi de barres d'un producte
     */
    public Producte(float preu, String nom, String codiBarres){
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;

    }

    /**
     * Funcio per extreure el preu d'un producte, una funcio generica de forma abstracte que retorna el preu d'un
     * producte que es podra modificar segons les especificacions de cualsevol producte a la seva clase
     * @return Retorna el valor numeric amb decimals de la variable preu
     */
    public abstract float getPreu();

    /**
     * Funcior per extreure el nom d'un producte, una funcio generica per retornar l'String del nom dun producte
     * @return Retorna l'String del nom de la variable nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Foncio per extreure el codi de barres d'un producte, una funcio generica que retorna el codi de barres d'un
     * producte
     * @return Retorna l'String del codi de la variable codiBarres
     */
    public String getCodiBarres() {
        return codiBarres;
    }

    /**
     * Funcio comparadora, aquesta funcio compara els codis de barres per poder ordenar
     * @param o Producte que volem comparar.
     * @return Retornara positiu o negatiu o 0 segon si el Producte es mes gran mes petit o igual
     */
    public int compareTo(Producte o) {
        return Integer.parseInt(codiBarres) - Integer.parseInt(o.getCodiBarres());
    }

    /**
     * Funcio comparadora, aquesta funcio comparara el preu de 2 objectes per ordenarlos
     * @param o1 Parametre d'entrada de tipus Producte el cual compararem en primer lloc
     * @param o2 Parametre d'entrada de tipus Producte el cual el amb el del primer lloc
     * @return
     */
    public int compare(Producte o1, Producte o2) {
        return (int) (o1.getPreu() - o2.getPreu());
    }
}

