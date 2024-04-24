package supermercat.Constructors;

public class Textil extends Producte{
    String composicioTextil;
    public Textil(float preu, String nom, String codiBarres, String composicioTextil){
        super(preu,nom,codiBarres);
        this.composicioTextil = composicioTextil;
    }
    public void setComposicioTextil(String composicioTextil) {
        this.composicioTextil = composicioTextil;
    }
    public String getComposicioTextil() {
        return composicioTextil;
    }
    public String getNom(){return nom;}
    public String getCodiBarres(){return codiBarres;}

    @Override
    public float getPreu() {
        return preu;
    }
    @Override
    public String toString() {
        return String.format("\t%7s\t%12s\t%8.2f\n", getNom(), getCodiBarres(), getPreu());
    }
}
