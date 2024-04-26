package supermercat.model;
import supermercat.Constructors.*;
import supermercat.vista.Vista;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Model {
    private static final int CAPACITAT_MAXIMA = 100;
    private static final ArrayList<Producte> CARRO = new ArrayList<>();

    private static boolean comprovarSiHaAcabat(){
        return CARRO.size() >= CAPACITAT_MAXIMA;
    }

    public static Date comprovarData(String dataCaducitat)throws ParseException{
        Pattern format = Pattern.compile("dd/MM/yyyy");
        SimpleDateFormat formato = new SimpleDateFormat(format.pattern());

        return formato.parse(dataCaducitat);
    }

    public static String comprovarLlrgada(String nom){
        final int MAX_CAR = 15;

        if(nom.length() <= MAX_CAR){
            return nom;
        }else {
            return nom.substring(15);
        }
    }

    public static void afegirAliment(float preu, String nom, String codiBarres, Date dataCaducitat){
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
    }

    public static String mostrarDataActual(){
        LocalDateTime avui = LocalDateTime.now();
        return avui.getDayOfMonth() + "/" + avui.getMonthValue() + "/" + avui.getYear() + "\t" + avui.getHour() + ":" + avui.getMinute() + ":" + avui.getSecond();
    }

    public static float preuElectronica(float preu, int diesGarantia){
        return (float) (preu + preu * (diesGarantia  / 365) * 0.1);
    }

    public static float preuAlimentacio(float preu, Date dataCaducitat){
        Date actual = new Date();

        long milisegonsDif = dataCaducitat.getTime() - actual.getTime();
        int dataDif = (int) TimeUnit.MILLISECONDS.toDays(milisegonsDif);

        return (float) (preu - preu * (1 / (dataDif + 1)) - (preu * 0.1));
    }

    public static void carroActual(){
        if(!CARRO.isEmpty()){
            Vista.mostrarCapcalera();
            System.out.println();

            for(Producte p : CARRO){
                Vista.mostrarProductes(p.toString());
            }
        }else {
            Vista.mostrarMisatge("El carro es buit");

        }
    }
    public static void pasarPerCaixa(){
        //String op = "caixa";

        Vista.mostrarCompra();
       //Vista.mostrarProductes(filtradoHashMap(op).toString());
        CARRO.clear();

    }
/*
    private static HashMap<String,String> filtradoHashMap(String op){
        HashMap<String, String> map = new HashMap<>();
        String key;
        final String SEPARADOR = "     /      ";
        final int UNITATS = 1;

        if(op.equals("caixa")){
            for(Producte p : CARRO){
                key = p.getCodiBarres() + p.getPreu();

                if(!map.containsKey(key)){
                    map.put(key, p.getNom() + SEPARADOR + UNITATS);
                }
                else{
                    map.replace(key, map.get(key).split(SEPARADOR)[0] + SEPARADOR + Integer.parseInt(map.get(key).split(SEPARADOR)[1]) + 1); //Reemplazo el value manteniendo la primera posicion(getnom) y le sumo 1 a unidad
                }
            }
        }else{
            for(Producte p : CARRO){
                map.put(p.getCodiBarres() + p.getPreu(), p.getNom() + SEPARADOR + UNITATS);
            }
        }
        return map;
    }*/
}
