package supermercat.Constructors;
import supermercat.model.Model;

public class Electronica extends  Producte{
    int diesGarantia;
    public Electronica(float preu, String nom, String codiBarres, int diesGarantia){
        super(preu, nom, codiBarres);
        setDiesGarantia(diesGarantia);
    }
    public void setDiesGarantia(int diesGarantia) {
        this.diesGarantia = diesGarantia;
    }
    public int getDiesGarantia(){
        return diesGarantia;
    }
    @Override
    public float getPreu() {
        return this.preu = Model.preuElectronica(super.preu, getDiesGarantia());
    }
    @Override
    public String toString() {
        if(getPreu() <= 0){
            return String.format("\t%7s\t%12s\t%8s\n", getNom(), getCodiBarres(), "Gratis");

        }else {
            return String.format("\t%7s\t%12s\t%8.2f\n", getNom(), getCodiBarres(), getPreu());
        }
    }
}
