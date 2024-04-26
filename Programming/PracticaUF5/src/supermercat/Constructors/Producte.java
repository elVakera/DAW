package supermercat.Constructors;
import java.text.ParseException;

public abstract class Producte{
    float preu;
    String nom, codiBarres;

    public Producte(float preu, String nom, String codiBarres){
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;

    }
    public abstract float getPreu() throws ParseException;
    public String getNom() {
        return nom;
    }
    public String getCodiBarres() {
        return codiBarres;
    }

}
