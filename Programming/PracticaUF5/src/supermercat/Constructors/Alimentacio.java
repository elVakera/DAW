package supermercat.Constructors;
import supermercat.model.Model;
import java.util.Date;

public class Alimentacio extends Producte{
    Date dataCaducitat;
    public Alimentacio(float preu, String nom, String codiBarres, Date dataCaducitat){
        super(preu, nom, codiBarres);
        setDataCaducitat(dataCaducitat);
    }

    public void setDataCaducitat(Date dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

    public Date getDataCaducitat() {
        return dataCaducitat;
    }

    public String getNom(){return nom;}

    public String getCodiBarres(){return codiBarres;}

    @Override
    public float getPreu(){
        return this.preu = Model.preuAlimentacio(super.preu, getDataCaducitat());
    }

    @Override
    public String toString() {
        if(getPreu() < 0){
            return String.format("\t%-15s\t%-15s\t%d\n", getNom(), getCodiBarres(), 0);

        }else {
            return String.format("\t%-15s\t%-15s\t%-15.2f\n", getNom(), getCodiBarres(), getPreu());

        }

    }
}