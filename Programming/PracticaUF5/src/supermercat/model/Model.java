package supermercat.model;
import supermercat.vista.Vista;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;


public class Model {
    public static float preuElectronica(float preu, int diesGarantia){
        return (float) (preu + preu * (diesGarantia  /365) * 0.1);
    }
    public static float preuAlimentacio(float preu, String dataCaducitat) throws ParseException {

        Pattern format = Pattern.compile("dd/MM/yyyy");
        SimpleDateFormat formato = new SimpleDateFormat(format.pattern());
        Date caducitat = formato.parse(dataCaducitat);
        Date actual = new Date();
        int dataDif = (int) (caducitat.getTime() - actual.getTime())/(1000/60/60/24);

        return (float)(preu - preu * (1/(dataDif + 1)) - (preu * 0.1));
    }
}
