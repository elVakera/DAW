package supermercat.Constructors;

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

    private float preuCaducitat(float preu, String dataCaducitat){

     return 0;
    }
}