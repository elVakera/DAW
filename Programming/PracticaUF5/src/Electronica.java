public class Electronica extends  Producte{
    int diesGarantia;
    public Electronica(float preu, String nom, String codiBarres, int diesGarantia){
        super(preu, nom, codiBarres);
        this.diesGarantia = diesGarantia;
    }
}
