package supermercat.Constructors;

import supermercat.model.Model;

public class Electronica extends  Producte{
    int diesGarantia;
    public Electronica(float preu, String nom, String codiBarres, int diesGarantia){
        super(preu, nom, codiBarres);
        this.diesGarantia = diesGarantia;
    }

    @Override
    public float getPreu() {
        super.preu = Model.preuElectronica(super.preu,diesGarantia);
        return super.preu;
    }
}
