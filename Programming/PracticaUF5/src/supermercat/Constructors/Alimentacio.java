package supermercat.Constructors;

import supermercat.model.Model;

import java.text.ParseException;

public class Alimentacio extends Producte{
    String dataCaducitat;
    public Alimentacio(float preu, String nom, String codiBarres, String dataCaducitat){
        super(preu, nom, codiBarres);
        this.dataCaducitat = dataCaducitat;
    }
    public String getDataCaducitat() {
        return dataCaducitat;
    }

    public void setDataCaducitat(String dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

    @Override
    public float getPreu() throws ParseException {
        return super.preu = Model.preuAlimentacio(super.preu, getDataCaducitat());
    }

}