package supermercat.model;
import supermercat.Constructors.*;
import supermercat.vista.Vista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Model {
    private static final int CAPACITAT_MAXIMA = 100;
    private static final ArrayList<Producte> CARRO = new ArrayList<>();
    private static final HashMap<String,Integer> HASH_CARRO = new HashMap<>();

    private static boolean comprovaCapacitat(){
        return CARRO.size() == CAPACITAT_MAXIMA;
    }

    public  static Date comprovarData(String dataIn) throws ParseException{
        Pattern format = Pattern.compile("dd/MM/yyyy");
        SimpleDateFormat formato = new SimpleDateFormat(format.pattern());
        return formato.parse(dataIn);
    }

    public static void afegirAliment(float preu, String nom, String codiBarres, Date dataCaducitat){
        if(!comprovaCapacitat()){
            CARRO.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
            Vista.mostrarMisatge("Aliment afegit al carro");
        }else {
            Vista.mostrarMisatge("Carro complet");
        }
    }

    public static void afegirTextil(float preu, String nom, String codiBarres, String composicio){
        if(!comprovaCapacitat()){
            CARRO.add(new Textil(preu, nom, codiBarres, composicio));
            Vista.mostrarMisatge("Textil afegit al carro");
        }else {
            Vista.mostrarMisatge("Carro complet");
        }
    }

    public static void afegirElectronica(float preu, String nom, String codiBarres, int diesGarantia){
        if (!comprovaCapacitat()) {
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
    public static void inicialitzaSuper(){
        final String ARREL = "./PracticaUF5";
        File carpetaUpdate = new File(ARREL + "/Updates");
        File carpetaLogs = new File(ARREL + "/Logs");
        File fitxerUpdate = new File(ARREL + "/Updates/UpdateTextilPrices.dat");
        File fitxerLogs = new File(ARREL + "/Logs/Exceptions.dat");

        try {
            creaCarpeta(carpetaLogs);
            creaCarpeta(carpetaUpdate);
            creaFitxers(fitxerLogs);
            creaFitxers(fitxerUpdate);

        }catch (Exception e){
            Vista.mostrarMisatge(e.getMessage());
        }

    }
    public static void omplenaRegistreErrors(Exception eIn){
        try {
            final String ARREL_LOGS = "./PracticaUF5/Logs/Exceptions.dat";
            File rutaLogs = new File(ARREL_LOGS);
            FileOutputStream obrirAlFinal = new FileOutputStream(rutaLogs, true);
            PrintStream writerLogs = new PrintStream(obrirAlFinal);

            writerLogs.println("Exception trobada a " + mostrarDataActual() + ": " + eIn.getMessage());
            writerLogs.close();

        }catch (FileNotFoundException e){
            Vista.mostrarMisatge("No s'ha trobat l'arxiu/carpeta");
            omplenaRegistreErrors(e);
        }
    }
    public static void creaCarpeta(File carpeta) throws Exception{
        if(carpeta.mkdir()){
            Vista.mostrarMisatge("La carpeta " + carpeta.getName() + " creada correctament");
        }else {
            Vista.mostrarMisatge("La carpeta " + carpeta.getName() + " ja existeix");
        }
    }
    public static void creaFitxers(File arxiu)throws Exception{
        if(arxiu.createNewFile()){
            Vista.mostrarMisatge("Arxiu " + arxiu.getName() + " creat correctament");
        }else {
            Vista.mostrarMisatge("L'arxiu " + arxiu.getName() + " ja existeix");
        }
    }
}
