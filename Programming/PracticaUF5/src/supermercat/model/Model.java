package supermercat.model;
import supermercat.Constructors.*;
import supermercat.vista.Vista;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Model {
    private static final int CAPACITAT_MAXIMA = 100;
    private static final ArrayList<Producte> CARRO = new ArrayList<>();

    private static boolean comprovarSiHaAcabat(){
        boolean acabat = true;
        if(CARRO.size() != CAPACITAT_MAXIMA){
            acabat = false;
        }
        return acabat;
    }
    public static void afegirAliment(float preu, String nom, String codiBarres, String dataCaducitat){
        if(!comprovarSiHaAcabat()){
            CARRO.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
            Vista.mostrarMisatge("Aliment afegit al carro");
        }else {
            Vista.mostrarMisatge("Carro complet");
        }
    }
    public static void afegirTextil(float preu, String nom, String codiBarres, String composicio){
        if(!comprovarSiHaAcabat()){
            CARRO.add(new Textil(preu, nom, codiBarres, composicio));
            Vista.mostrarMisatge("Textil afegit al carro");
        }else {
            Vista.mostrarMisatge("Carro complet");
        }
    }
    public static void afegirElectronica(float preu, String nom, String codiBarres, int diesGarantia){
        if (!comprovarSiHaAcabat()) {
            CARRO.add(new Electronica(preu, nom, codiBarres, diesGarantia));
            Vista.mostrarMisatge("Electronica afegida al carro");
        }else {
            Vista.mostrarMisatge("Carro complet");
        }
    }public static String mostrarDataActual(){
        LocalDateTime avui = LocalDateTime.now();
        return avui.getDayOfMonth() + "/" + avui.getMonthValue() + "/" + avui.getYear() + "\t" + avui.getHour() + ":" + avui.getMinute() + ":" + avui.getSecond();
    }
    public static float preuElectronica(float preu, int diesGarantia){
        return (float) (preu + preu * (diesGarantia  / 365) * 0.1);
    }
    public static float preuAlimentacio(float preu, String dataCaducitat) throws ParseException {

        Pattern format = Pattern.compile("dd/MM/yyyy");
        SimpleDateFormat formato = new SimpleDateFormat(format.pattern());
        Date caducitat = formato.parse(dataCaducitat);
        Date actual = new Date();
        long milisegonsDif = caducitat.getTime() - actual.getTime();
        int dataDif = (int) TimeUnit.MILLISECONDS.toDays(milisegonsDif);

        return (float) (preu - preu * (1 / (dataDif + 1)) - (preu * 0.1));
    }

    public static void carroActual(){
        if(CARRO.isEmpty()){
            Vista.mostrarCapcalera();
            for(Producte p : CARRO){
                Vista.mostrarProductes(p.toString());
            }
        }else {
            Vista.mostrarMisatge("El carro es buit");
        }
    }
    public static void pasarPerCaixa(){
        Vista.mostrarCompra();
        CARRO.clear();
    }
}
