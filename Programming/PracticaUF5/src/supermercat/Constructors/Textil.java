package supermercat.Constructors;

public class Textil extends Producte{
    String composicioTextil;

    public Textil(float preu, String nom, String codiBarres, String composicioTextil){
        super(preu,nom,codiBarres);
        setComposicioTextil(composicioTextil);

    }

    public void setComposicioTextil(String composicioTextil) {
        this.composicioTextil = composicioTextil;
    }

    public String getComposicioTextil() {
        return composicioTextil;
    }

    public String getNom() {
        return super.getNom();
    }

    public String getCodiBarres() {
        return super.getCodiBarres();
    }

    @Override
    public float getPreu() {
        return preu;
    }

    @Override
    public String toString() {
        if(getPreu() <= 0){
            return String.format("\t%-15s\t%-12s\t%-8s\n", getNom(), getCodiBarres(), 0);
        }else {
            return String.format("\t%-15s\t%-12s\t%-8.2f\n", getNom(), getCodiBarres(), getPreu());
        }
    }

}
