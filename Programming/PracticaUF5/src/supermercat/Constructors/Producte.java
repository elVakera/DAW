package supermercat.Constructors;

import java.text.ParseException;

public abstract class Producte {
    float preu;
    String nom, codiBarres;

    public Producte(float preu, String nom, String codiBarres){
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;

    }
    public abstract float getPreu() throws ParseException;

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodiBarres() {
        return codiBarres;
    }

    public void setCodiBarres(String codiBarres) {
        this.codiBarres = codiBarres;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
