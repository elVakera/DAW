package supermercat.Constructors;

public class Textil extends Producte{
    String composicioTextil;
    public Textil(float preu, String nom, String codiBarres, String composicioTextil){
        super(preu,nom,codiBarres);
        this.composicioTextil = composicioTextil;
    }
    public String getComposicioTextil() {
        return composicioTextil;
    }

    @Override
    public float getPreu() {
        return 0;
    }
    @Override
    public String toString() {
        return super.getNom() + " " + super.getCodiBarres() + " " + getPreu() + " " + getComposicioTextil();
    }
}
