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

/**
 * Clase de manipulacio de dades, aquesta clase s'encarrega de gestionar les dades del programa per a tal de fer
 * el que calgui per modificarles segons les indicacions del controlador
 */
public class Model {
    //variables constants
    private static final int CAPACITAT_MAXIMA = 100;
    private static final ArrayList<Producte> CARRO = new ArrayList<>();
    private static final HashMap<String,Integer> HASH_CARRO = new HashMap<>();

    private static boolean comprovaCapacitat(){
        return CARRO.size() == CAPACITAT_MAXIMA;
    }

    /**
     * Funcio per comprovar data, a partir d'un parametre d'entrada es comprovara si es pot parsejar la data amb un
     * format determinat
     * @param dataIn Parametre d'entrada de tipus String que volem comprovar
     * @return Retorna el parametre d'entrada parsejat a tipus Date
     * @throws ParseException Si no es pot parsejar perque no te el format d'String correcte es llençara una excepcio
     */
    public  static Date comprovarData(String dataIn) throws ParseException{
        Pattern format = Pattern.compile("dd/MM/yyyy");
        SimpleDateFormat formato = new SimpleDateFormat(format.pattern());
        return formato.parse(dataIn);
    }

    /**
     * Funcio per afegir un aliment, a partir d'uns parametres d'entrada es creara i s'incloura un aliment a un
     * arraylist, aixo nomes sera posible si la capacitat de l'arraylist determinada per comprovarCapacitat ho permet,
     * tan si es pot com si no es mostrara un misatge amb el resultat de l'operacio
     * @param preu Parametre d'entradade tipus float que permetra la construccio d'un producte alimentacio
     * @param nom Parametre d'entradade tipus String que permetra la construccio d'un producte alimentacio
     * @param codiBarres Parametre d'entradade tipus String que permetra la construccio d'un producte alimentacio
     * @param dataCaducitat Parametre d'entradade tipus Date que permetra la construccio d'un producte alimentacio
     */
    public static void afegirAliment(float preu, String nom, String codiBarres, Date dataCaducitat){
        if(!comprovaCapacitat()){
            CARRO.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
            Vista.mostrarMisatge("Aliment afegit al carro");
        }else {
            Vista.mostrarMisatge("Carro complet");
        }
    }

    /**
     * Funcio per afegir un textil, a partir d'uns parametres d'entrada es creara i s'incloura un textil a un
     * arraylist, aixo nomes sera posible si la capacitat de l'arraylist determinada per comprovarCapacitat ho permet,
     * tan si es pot com si no es mostrara un misatge amb el resultat de l'operacio
     * @param preu Parametre d'entradade tipus float que permetra la construccio d'un producte textil
     * @param nom Parametre d'entradade tipus String que permetra la construccio d'un producte textil
     * @param codiBarres Parametre d'entradade tipus String que permetra la construccio d'un producte textil
     * @param composicio Parametre d'entradade tipus String que permetra la construccio d'un producte textil
     */
    public static void afegirTextil(float preu, String nom, String codiBarres, String composicio){
        if(!comprovaCapacitat()){
            CARRO.add(new Textil(preu, nom, codiBarres, composicio));
            Vista.mostrarMisatge("Textil afegit al carro");
        }else {
            Vista.mostrarMisatge("Carro complet");
        }
    }

    /**
     * Funcio per afegir electonica, a partir d'uns parametres d'entrada es creara i s'incloura electronica a un
     * arraylist, aixo nomes sera posible si la capacitat de l'arraylist determinada per comprovarCapacitat ho permet,
     * tan si es pot com si no es mostrara un misatge amb el resultat de l'operacio
     * @param preu Parametre d'entradade tipus float que permetra la construccio d'un producte electronica
     * @param nom Parametre d'entradade tipus String que permetra la construccio d'un producte electronica
     * @param codiBarres Parametre d'entradade tipus String que permetra la construccio d'un producte electronica
     * @param diesGarantia Parametre d'entradade tipus int que permetra la construccio d'un producte electronica
     */
    public static void afegirElectronica(float preu, String nom, String codiBarres, int diesGarantia){
        if (!comprovaCapacitat()) {
            CARRO.add(new Electronica(preu, nom, codiBarres, diesGarantia));
            Vista.mostrarMisatge("Electronica afegida al carro");
        }else {
            Vista.mostrarMisatge("Carro complet");
        }
    }

    /**
     * Funcio per mostrar la data actual, a partir de un LocalDateTime es calcula la data actual amb la funcio interna
     * now i retornata la data actual en un format determinat
     * @return Retorna la data actual formatada (dd/MM/yyyy hh:mm:ss)
     */
    public static String mostrarDataActual(){
        LocalDateTime avui = LocalDateTime.now();
        return avui.getDayOfMonth() + "/" + avui.getMonthValue() + "/" + avui.getYear() + "\t" + avui.getHour() + ":" + avui.getMinute() + ":" + avui.getSecond();
    }

    /**
     * Funcio que calcula el preu de l'electronica, a partir d'uns parametres d'entrada es calculara seguint una funcio
     * el preu que tindra un producte d'electronica
     * @param preu Parametre d'entrada definit al constructor d'electronica per definir el preu d'un producte
     *             d'electronica
     * @param diesGarantia Parametre d'entrada definit al constructor d'electronica per definir els dies de garantia
     *                    d'un producte d'electronica
     * @return Retorna el resultat numeric am decimals del que definira el preu d'un producte d'electronica
     */
    public static float preuElectronica(float preu, int diesGarantia){
        return (float) (preu + preu * (diesGarantia  / 365) * 0.1);
    }

    /**
     * Funcio que calcula el preu de l'laliment, a partir d'uns parametres d'entrada es calculara seguint una funcio el
     * preu que tindra un producte d'alimentacio
     * @param preu Parametre d'entrada definit al constructor d'alimentacio per definir el preu d'un producte
     *             d'alimentacio
     * @param dataCaducitat Parametre d'entrada definit al constructor d'alimentacio per definir la data de caducitat
     *                     d'un producte d'alimentacio
     * @return
     */
    public static float preuAlimentacio(float preu, Date dataCaducitat){
        Date actual = new Date();
        long milisegonsDif = dataCaducitat.getTime() - actual.getTime();
        int dataDif = (int) TimeUnit.MILLISECONDS.toDays(milisegonsDif);

        return (float) (preu - preu * (1 / (dataDif + 1)) - (preu * 0.1));
    }

    public static void carroActual(){
        if(!CARRO.isEmpty()){
            Vista.mostrarCapcaleraCarro();
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

    /**
     * Funcio que inicialitza el super, a partir dunes rutes definides dins del programa es generan carpetes i fitxers
     * per tractar eventualitats del supermercat
     */
    public static void inicialitzaSuper(){
        final String ARREL = "./";
        File carpetaUpdate = new File(ARREL + "Updates");
        File carpetaLogs = new File(ARREL + "Logs");
        File fitxerUpdate = new File(ARREL + "Updates/UpdateTextilPrices.dat");
        File fitxerLogs = new File(ARREL + "Logs/Exceptions.dat");

        try {
            creaCarpeta(carpetaLogs);
            creaCarpeta(carpetaUpdate);
            creaFitxers(fitxerLogs);
            creaFitxers(fitxerUpdate);

        }catch (FileNotFoundException e){
            Vista.mostrarMisatge(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Funcio per crear una carpeta, a partir d'una ruta es creara una carpeta, nomes sera posible si la carpeta no
     * existeix, tan si es pot com si no es mostrara un misatge el resultat de l'oeracio
     * @param carpeta Parametre d'entrada de tipus File que defineix el desti i nom de la carpeta
     * @throws FileNotFoundException Si no troba la ruta es pot produir una excepcio
     */
    public static void creaCarpeta(File carpeta) throws FileNotFoundException{
        if(carpeta.mkdirs()){
            Vista.mostrarMisatge("La carpeta " + carpeta.getName() + " creada correctament");
        }else {
            Vista.mostrarMisatge("La carpeta " + carpeta.getName() + " ja existeix");
        }
    }

    /**
     * Funcio per a crear un fitxer, a partir d'una ruta es crea un fitxer, nomes sera posible si el fixer no existeix,
     * tan si es pot com si no es mostrara un misatge am el resultat de l'operacio
     * @param arxiu Parametre d'entrada de tipus File que defineix el desti i nom del fixer
     * @throws Exception Es pot produir una excepcio a l'hora de crear el fitxer amb la ruta
     */
    public static void creaFitxers(File arxiu)throws Exception{
        if(arxiu.createNewFile()){
            Vista.mostrarMisatge("Arxiu " + arxiu.getName() + " creat correctament");
        }else {
            Vista.mostrarMisatge("L'arxiu " + arxiu.getName() + " ja existeix");
        }
    }

    /**
     * Funcio per omplenar el registre d'excepcions, a partir d'un parametre d'entrada s'omplira un document
     * d'excepcions
     * @param eIn Parametre d'entrada de tipus Exception que s'utilitzara per escriure el document
     */
    public static void omplenaRegistreExcepcions(Exception eIn){
        try {
            final String ARREL_LOGS = "./Logs/Exceptions.dat";
            File rutaLogs = new File(ARREL_LOGS);
            FileOutputStream obrirAlFinal = new FileOutputStream(rutaLogs, true);   //evitar sobreescritura de l'arxiu
            PrintStream writerLogs = new PrintStream(obrirAlFinal);

            writerLogs.println("Exception trobada a " + mostrarDataActual() + ": " + eIn.getMessage());
            writerLogs.close();

        }catch (FileNotFoundException e){
            Vista.mostrarMisatge("No s'ha trobat l'arxiu/carpeta");
            omplenaRegistreExcepcions(e);
        }
    }

}
