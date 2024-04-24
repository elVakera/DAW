package supermercat.model;
import supermercat.Constructors.Alimentacio;
import supermercat.Constructors.Electronica;
import supermercat.Constructors.Producte;
import supermercat.Constructors.Textil;
import supermercat.vista.Vista;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Model {
    private static boolean carroComplet;
    private static final Scanner SCN = new Scanner(System.in);
    private static final int CAPACITAT_MAXIMA = 100;
    private static ArrayList<Producte> carro = new ArrayList<>();

    public static boolean comprobarCarro(){
        if(carroComplet){
            return true;
        }else{
            carroComplet = true;
            return false;
        }
    }
    public static void afegirProducte(String tipus){
        switch (tipus) {
            case "0":
                carroComplet = true;
                comprobarCarro();
                break;
            case "1":
                if(comprovarSiHaAcabat()){
                    String nom, codiBarres, dataCaducitat;
                    float preu; 
                    Vista.mostrarMisatge("Afegir aliment");
                    Vista.mostrarIntroduccioDades("Nom producte: ");
                    nom = SCN.nextLine();
                    Vista.mostrarIntroduccioDades("Preu: ");
                    preu = SCN.nextFloat();
                    Vista.mostrarIntroduccioDades("Data de caducitat (dd/mm/yyyy): ");
                    dataCaducitat = SCN.nextLine();
                    Vista.mostrarIntroduccioDades("Codi de barres: ");
                    codiBarres = SCN.nextLine();
                    carro.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
                    comprobarCarro();
                }
               
                break;
            case "2":
                if(comprovarSiHaAcabat()){
                    String nom, codiBarres, Composicio;
                    float preu;
                    Vista.mostrarMisatge("Afegir textil");
                    Vista.mostrarIntroduccioDades("Nom producte: ");
                    nom = SCN.nextLine();
                    Vista.mostrarIntroduccioDades("Preu: ");
                    preu = SCN.nextFloat();
                    Vista.mostrarIntroduccioDades("Composicio: ");
                    Composicio = SCN.nextLine();
                    Vista.mostrarIntroduccioDades("Codi de barres: ");
                    codiBarres = SCN.nextLine();
                    carro.add(new Textil(preu, nom, codiBarres, Composicio));
                    comprobarCarro();
                }
                
                break;
            case "3":
                if (comprovarSiHaAcabat()) {
                    String nom, codiBarres;
                    int diesGarantia;
                    float preu;
                    Vista.mostrarMisatge("Afegir electronica");
                    Vista.mostrarIntroduccioDades("Nom producte: ");
                    nom = SCN.nextLine();
                    Vista.mostrarIntroduccioDades("Preu: ");
                    preu = SCN.nextFloat();
                    Vista.mostrarIntroduccioDades("Garantia (dies): ");
                    diesGarantia = SCN.nextInt();
                    Vista.mostrarIntroduccioDades("Codi de barres: ");
                    codiBarres = SCN.nextLine();
                    carro.add(new Electronica(preu, nom, codiBarres, diesGarantia));
                    comprobarCarro();
                }
                
                break;
            default:
            Vista.mostrarMisatge(tipus + "No es valid com a parametre");

                break;
        }
    }
    private static boolean comprovarSiHaAcabat(){
        boolean acabat = true;
        if(carro.size() != CAPACITAT_MAXIMA){
            acabat = false;
        }
        return acabat;
    }
    public static String mostrarDataActual(){
        LocalDateTime avui = LocalDateTime.now();  
        return avui.getDayOfMonth() + "/" + avui.getMonthValue() + "/" + avui.getYear() + "\t" + avui.getHour() + ":" + avui.getMinute() + ":" + avui.getSecond();
    }
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
