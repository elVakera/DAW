package supermercat.Constructors;
import supermercat.model.Model;
import java.text.ParseException;

public class Alimentacio extends Producte{
    String dataCaducitat;
    public Alimentacio(float preu, String nom, String codiBarres, String dataCaducitat){
        super(preu, nom, codiBarres);
        setDataCaducitat(dataCaducitat);
    }
    public void setDataCaducitat(String dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }
    public String getDataCaducitat() {
        return dataCaducitat;
    }
    public String getNom(){return nom;}
    public String getCodiBarres(){return codiBarres;}
    @Override
    public float getPreu() throws ParseException {
        return this.preu = Model.preuAlimentacio(super.preu, getDataCaducitat());
    }

    @Override
    public String toString() {
        try {
            return String.format("\t%7s\t%12s\t%8.2f\n", getNom(), getCodiBarres(), getPreu());
        } catch (ParseException e) {
            return ("Data de caducitat (" + getDataCaducitat() + ") en format incorrecte");
        }
    }
}