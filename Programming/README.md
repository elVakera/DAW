# Main
~~~ java
package supermercat;
import supermercat.controlador.Controlador;

/**
 * Clase principal de l'aplicacio, inicia el supermercat a traves de la clase controlador
 */
public class Main {
    public static void main(String[] args) {
        Controlador.supermercat();
    }
}
~~~
# Programa Base
## Controlador
~~~ java
package supermercat.controlador;
import supermercat.vista.Vista;
import supermercat.model.Model;

import java.io.File;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase de control sobre les decision de l'usuari, aquesta clase s'encarrega d'enregistrar i gestionar les
 * decisions preses per l'usuari
 */
public class Controlador {
    //variables constants
    private static final Scanner SCN = new Scanner(System.in);

    /**
     * Funcio supermercat, funcio interactiva amb l'usuari que mostra els diferents menus amb els cual l'usuari
     * interactuara amb l'aplicacio, les diferents excepcions es tractaran a cada try_catch i s'inclouran al document
     * d'excepcions
     */
    public static void supermercat(){
        String opSuper;
        boolean fiSupermercat;

        //inicia creacio d'arxius i demes
        try {
            Model.inicialitzaSuper();

        }catch (RuntimeException e){
            Vista.mostrarMisatge(e.getMessage());

        } catch (StackOverflowError error){
            Vista.mostrarMisatge("Se lio parda");
        }

        //mostra en bucle un menu am el que pot escullir si afegir un producte, pasar per caixa, mostrar carro o acabar
        do{
            Vista.mostrarMenuSuper();
            opSuper = SCN.nextLine();       //escull la opcio

            switch (opSuper){
                case "0":
                    Vista.mostrarMisatge("Fins aviat!");
                    fiSupermercat = false;
                    break;

                case "1":
                    boolean fiProducte;
                    String opProducte;

                    /*mostra en bucle el menu per escullir quin producte vol afegir al carro amb les opcions alient,
                    textil electronica o si vol tornar*/
                    do{
                        Vista.mostrarMenuProducte();
                        opProducte = SCN.nextLine();        //escull la opcio

                        switch (opProducte){
                            case "0":
                                Vista.mostrarMisatge("Tornant...");
                                fiProducte = false;
                                break;

                            case "1":
                                try{
                                    String nomA, codiBarresA, dataCaducitatA;
                                    float preuA;

                                    Vista.mostrarMisatge("Afegir aliment");
                                    Vista.mostrarIntroduccioDades("(15 Digits Max) Nom producte: ");
                                    nomA = SCN.nextLine();

                                    if(nomA.length() > 15){
                                        Vista.mostrarMisatge("nom reduit a 15 caracters");
                                        nomA = nomA.substring(15);
                                    }

                                    Vista.mostrarIntroduccioDades("Preu: ");
                                    preuA = SCN.nextFloat();
                                    SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Data de caducitat (dd/mm/YYYY): ");
                                    dataCaducitatA = SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Codi de barres: ");
                                    codiBarresA = SCN.nextLine();

                                    if(!codiBarresA.matches("\\d{1,4}")){
                                        Vista.mostrarMisatge("El codi de barres ha de ser numeric i maxim 4 digits");

                                    }else {
                                        //afegir aliment
                                        Model.afegirAliment(preuA, nomA, codiBarresA, Model.comprovarData(dataCaducitatA));
                                    }

                                }catch (ParseException e){
                                    Vista.mostrarMisatge("Error al introduir la data");
                                    Model.omplenaRegistreExcepcions(e);

                                }catch(InputMismatchException e){
                                    Vista.mostrarMisatge("Error al introduir el preu");
                                    Model.omplenaRegistreExcepcions(e);

                                }catch (Exception e){
                                    Vista.mostrarMisatge("FATAL ERROR!");
                                    Model.omplenaRegistreExcepcions(e);

                                }finally {
                                    fiProducte = true;
                                }
                                break;

                            case "2":
                                try {
                                    String nomT, codiBarresT, composicioT;
                                    float preuT;

                                    Vista.mostrarMisatge("Afegir textil");
                                    Vista.mostrarIntroduccioDades("(15 Digits Max) Nom producte: ");
                                    nomT = SCN.nextLine();

                                    if(nomT.length() > 15){
                                        Vista.mostrarMisatge("nom reduit a 15 caracters");
                                        nomT = nomT.substring(15);
                                    }

                                    Vista.mostrarIntroduccioDades("Preu: ");
                                    preuT = SCN.nextFloat();
                                    SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Composicio: ");
                                    composicioT = SCN.nextLine();

                                    if (!composicioT.matches("^[a-zA-Z]+$")){
                                        throw new IllegalArgumentException("La composicio no pot tenir numeros");
                                    }
                                    Vista.mostrarIntroduccioDades("Codi de barres: ");
                                    codiBarresT = SCN.nextLine();

                                    //comprova que el codi de barres sigui numeric y que no es repeteixi
                                    if(!codiBarresT.matches("\\d{1,4}")){
                                        Vista.mostrarMisatge("El codi de barres ha de ser numeric i maxim 4 digits");

                                    }else {
                                        if(!Model.comprovarCodiBarres(codiBarresT)){
                                            Model.afegirTextil(preuT, nomT, codiBarresT, composicioT);  //afegir textil

                                        }else {

                                            Vista.mostrarMisatge("Dos productes textils no poden tenir el mateix codi de barres");
                                            Boolean codiBo = false;

                                            do{
                                                String codiProva;

                                                Vista.mostrarIntroduccioDades("Introdueix codi de barres que vulguis comprovar: ");
                                                codiProva =SCN.nextLine();
                                                if(!codiBarresT.matches("\\d{1,4}")){
                                                    Vista.mostrarMisatge("El codi de barres ha de ser numeric i maxim 4 digits");

                                                }else {
                                                    codiBo = Model.trobarProducte(codiProva);
                                                }

                                            }while (codiBo);
                                            Model.afegirTextil(preuT, nomT, codiBarresT, composicioT);  //afegir textil
                                        }
                                    }

                                }catch(InputMismatchException e){
                                    Vista.mostrarMisatge("Error al introduir el preu");
                                    Model.omplenaRegistreExcepcions(e);

                                }catch (Exception e){
                                    Vista.mostrarMisatge(e.getMessage());
                                    Model.omplenaRegistreExcepcions(e);

                                }finally {
                                    fiProducte = true;
                                }
                                break;

                            case "3":
                                try{
                                    String nomE, codiBarresE;
                                    int diesGarantiaE;
                                    float preuE;

                                    Vista.mostrarMisatge("Afegir electronica");
                                    Vista.mostrarIntroduccioDades("(15 Digits Max) Nom producte: ");
                                    nomE = SCN.nextLine();

                                    if(nomE.length() > 15){
                                        Vista.mostrarMisatge("nom reduit a 15 caracters");
                                        nomE = nomE.substring(15);
                                    }

                                    Vista.mostrarIntroduccioDades("Preu: ");
                                    preuE = SCN.nextFloat();
                                    SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Garantia (nº dies): ");
                                    diesGarantiaE = SCN.nextInt();
                                    SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Codi de barres: ");
                                    codiBarresE = SCN.nextLine();

                                    if(!codiBarresE.matches("\\d{1,4}")){
                                        Vista.mostrarMisatge("El codi de barres ha de ser numeric i maxim 4 digits");

                                    }else {
                                        //afegir electronica
                                        Model.afegirElectronica(preuE, nomE, codiBarresE, diesGarantiaE);
                                    }

                                }catch(InputMismatchException e){
                                    Vista.mostrarMisatge("Error al introduir el dies de garantia o preu");
                                    Model.omplenaRegistreExcepcions(e);

                                }catch (Exception e){
                                    Vista.mostrarMisatge(e.getMessage());
                                    Model.omplenaRegistreExcepcions(e);

                                }finally {
                                    fiProducte = true;
                                }
                                break;

                            default:
                                Vista.mostrarMisatge("Error al introduir opcio: " + opProducte + " no es una opcio");
                                fiProducte = true;

                        }
                    }while (fiProducte);

                    fiSupermercat = true;
                    break;

                case "2":
                    //genera ticket de compra i neteja el carro
                    Model.pasarPerCaixa();
                    fiSupermercat = true;
                    break;

                case "3":
                    //mostra el contingut del carro
                    Model.carroActual();
                    fiSupermercat = true;
                    break;

                default:
                    Vista.mostrarMisatge("Error al introduir opcio: " + opSuper + " no es una opcio");
                    fiSupermercat = true;

            }
        }while (fiSupermercat);
    }
}

~~~
## Model
~~~ java
package supermercat.model;
import supermercat.constructors.*;
import supermercat.vista.Vista;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
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
    private static final ArrayList<Producte> CARRO_TEXTIL = new ArrayList<>();
    private static final HashMap<String, String> DOCUMENT = new HashMap<>();
    private static final LinkedHashMap<String,String[]> HASH_CARRO = new LinkedHashMap<>();
    private static final LinkedHashMap<String,String[]> HASH_CARRO_CAIXA = new LinkedHashMap<>();

    /**
     * Funcion comprovarCapacitat de carro, comproba si el carro ha arribat a la seva maxima capacitat
     * @return Retorna cert o fals segons si la capacitat es la maxima
     */
    private static boolean comprovaCapacitat(){
        return CARRO.size() == CAPACITAT_MAXIMA;
    }

    /**
     * Funcio per comprovar el codi de barres, aquesta funcio ens dira si existeix el codi de barres al carro
     * @param codiBarres Parametre d'entrada de tipus String que compararem
     * @return retornara true si existeix al carro o false si no
     */
    public static boolean comprovarCodiBarres(String codiBarres){
        boolean existeix = false;
        for (Producte p : CARRO_TEXTIL){
            if(p.getCodiBarres().matches(codiBarres)){
                existeix = true;
                break;
            }
        }
        return existeix;
    }

    /**
     * Funcio per comprovar data, a partir d'un parametre d'entrada es comprovara si es pot parsejar la data amb un
     * format determinat
     * @param dataIn Parametre d'entrada de tipus String que volem comprovar
     * @return Retorna el parametre d'entrada parsejat a tipus Date
     * @throws ParseException Si no es pot parsejar perque no te el format d'String correcte es llençara una excepcio
     */
    public static Date comprovarData(String dataIn) throws ParseException{
        Pattern format = Pattern.compile("dd/MM/yyyy");
        SimpleDateFormat formato = new SimpleDateFormat(format.pattern());
        return formato.parse(dataIn);
    }

    /**
     * Funcio busca producte, a partir d'un parametre d'entrada comprovarem si el carro es buit, si no ho esta
     * comprovara el codi de barres de tots els productes amb el parametre d'entrada, si el troba et dira el seu nom i
     * sino avisara que no ho ha trobat
     * @param codiBarres Parametre d'entrada de tipus String que es comparara amb els codis de barres del carro
     * @return Retorna verdader o fals depenent de si el troba o no
     */
    public static Boolean trobarProducte(String codiBarres){
        boolean existeix = false;

        if (!(CARRO.isEmpty())){
            Optional<Producte> producte = CARRO.stream().filter(finder -> codiBarres.equals(finder.getCodiBarres()))
                                                                                    .findFirst();                       //buscardins del carro si existeix

            if (producte.isPresent()) {
                Vista.mostrarMisatge("El producte existeix i es: " + producte.get().getNom());
                existeix = true;

            } else {
                System.out.println("El codi introduït no existeix al carro");
                existeix = false;
            }
        } else {
            System.out.println("El carro és buit");

        }

        return existeix;
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
            CARRO_TEXTIL.add(new Textil(preu, nom, codiBarres, composicio));
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
     * Funcio per afegir al carro els productes per mostrar
     * @param p Parametre d'entrada de tipus Producte que volem afegir a un HashMap
     */
    public static void afegirAlHash(Producte p){
        int unitats = 1;
        String key = p.getCodiBarres() + p.getPreu();

        if(!(HASH_CARRO_CAIXA.containsKey(key))){
            String[] valueCaixa = new String[3];
            valueCaixa[0] = p.getNom();
            valueCaixa[1] = unitats + "";
            valueCaixa[2] = p.getPreu() + "";

            //si pertenece a textil
            if(p instanceof Textil){
                for(Map.Entry<String, String> doc : DOCUMENT.entrySet()){
                    String valorDoc = doc.getValue();   //valor preu
                    String valorKey = doc.getKey();     //valor codi barres

                    //si coinciden los codigos de barras pero no el precio
                    if(valorKey.equals(p.getCodiBarres())){
                        if(!(p.getPreu() + "").equals(valorDoc)){
                            valueCaixa[2] = valorDoc;       //modifica el valor al del doc

                        }else {
                            valueCaixa[2] = p.getPreu() + "";   //modifica el valor al del preu
                        }
                    }
                }
            }

            HASH_CARRO_CAIXA.put(key, valueCaixa);

        }else {
            String[] valueHash = new String[3];
            valueHash[0] = HASH_CARRO_CAIXA.get(key)[0];
            valueHash[1] = (Integer.parseInt(HASH_CARRO_CAIXA.get(key)[1]) + unitats) + "";
            valueHash[2] = p.getPreu() + "";

            HASH_CARRO_CAIXA.replace(key, valueHash);
        }
        if(!(HASH_CARRO.containsKey(p.getCodiBarres()))){
            String[] valueHash = new String[2];
            valueHash[0] = p.getNom();
            valueHash[1] = unitats + "";

            HASH_CARRO.put(p.getCodiBarres(), valueHash);

        }else {
            String[] valueHash = new String[2];
            valueHash[0] = HASH_CARRO.get(p.getCodiBarres())[0];
            valueHash[1] = (Integer.parseInt(HASH_CARRO.get(p.getCodiBarres())[1]) + unitats) + "";

            HASH_CARRO.replace(p.getCodiBarres(), valueHash);
        }
    }

    /**
     * Funcio per mostrar la data actual, a partir de un LocalDateTime es calcula la data actual amb la funcio interna
     * now i retornata la data actual en un format determinat
     * @return Retorna la data actual formatada (dd/MM/yyyy hh:mm:ss)
     */
    public static String mostrarDataActual(){
        LocalDateTime avui = LocalDateTime.now();
        return avui.getDayOfMonth() + "/" + avui.getMonthValue() + "/" + avui.getYear() + "\t" + avui.getHour() +
                                      ":" + avui.getMinute() + ":" + avui.getSecond();
    }

    /**
     * Funcio calcula el preu total, aquesta funcio calcula el preu total a partir de la multiplicacio del preu i les
     * unitats de tots eles productes i despres suma aquest resultat i el guarda en una variable de referencia atomica
     * @return Retorna el valor de la variable String total que te el resultat del calcul del preu total
     */
    private static String calcularPreuTotal(){
        String total;
        AtomicReference<Float> totalPagar = new AtomicReference<>(0f);
        HASH_CARRO_CAIXA.forEach((key, nomUniPre) -> totalPagar.updateAndGet(suma -> suma + (Float.parseFloat(nomUniPre[2]) *
                                                                                        Float.parseFloat(nomUniPre[1]))));
        total = totalPagar + " €";
        return total;
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
     * @return Retorna el resultat numeric amb decimals que definira el preu d'un producte d'alimentacio
     */
    public static float preuAlimentacio(float preu, Date dataCaducitat){
        Date actual = new Date();
        long milisegonsDif = dataCaducitat.getTime() - actual.getTime();
        int dataDif = (int) TimeUnit.MILLISECONDS.toDays(milisegonsDif);

        return (float) (preu - preu * (1 / (dataDif + 1)) - (preu * 0.1));
    }

    /**
     * Mostra el contingut del carro, si el carro no es buit mostra una capçalera i tots els productes del carro
     * formatats, si el carro es buit mostrara un misatge
     */
    public static void carroActual(){
        Collections.sort(CARRO);

        for(Producte p: CARRO){
            if(p instanceof Textil){

            }
            afegirAlHash(p);
        }

        if(!HASH_CARRO.isEmpty()){
            Vista.mostrarCapcaleraCarro();
            HASH_CARRO.forEach((codiBarres, nomUnitats) -> Vista.mostrarCarro(nomUnitats[0], nomUnitats[1]));

        }else {
            Vista.mostrarMisatge("El carro es buit");
        }
        HASH_CARRO_CAIXA.clear();
        HASH_CARRO.clear();
    }

    /**
     * Mostra un menu en forma de ticket amb totes les dades de la compra, am totes les modificacions pertinents i
     * esborra totes les llistes
     */
    public static void pasarPerCaixa(){
        Collections.sort(CARRO);

        for(Producte p: CARRO){
            afegirAlHash(p);
        }

        Vista.mostrarCompra();
        HASH_CARRO_CAIXA.forEach((key, nomUniPre) -> Vista.mostrarCaixa(nomUniPre[0] ,
                                                                         nomUniPre[1] ,
                                                                         nomUniPre[2] ,
                                                                         ((Float.parseFloat(nomUniPre[2]) *
                                                                                 Float.parseFloat(nomUniPre[1])) + "")));
        Vista.mostrarPreuFinal(calcularPreuTotal());
        Vista.mostrarMisatge(" ");
        CARRO.clear();
        CARRO_TEXTIL.clear();
        HASH_CARRO_CAIXA.clear();
        HASH_CARRO.clear();
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
            guardarDocument();

        }catch (IOException e){
            Vista.mostrarMisatge(e.getMessage());
            omplenaRegistreExcepcions(e);

        }catch (Exception e) {
            omplenaRegistreExcepcions(e);
            throw new RuntimeException(e);

        }
    }

    /**
     * Funcio per crear una carpeta, a partir d'una ruta es creara una carpeta, nomes sera posible si la carpeta no
     * existeix, tan si es pot com si no es mostrara un misatge el resultat de l'oeracio
     * @param carpeta Parametre d'entrada de tipus File que defineix el desti i nom de la carpeta
     * @throws FileNotFoundException Si no troba la ruta es pot produir una excepcio
     */
    private static void creaCarpeta(File carpeta) throws FileNotFoundException{
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
    private static void creaFitxers(File arxiu)throws Exception{
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

    /**
     * Funcion guarda un document, aquesta funcio llegeix i guarda un document dins d'un HashMap
     * @throws IOException Aquesta funcio por llençar una Excepcio si no troba el fitxer o no pot llegir una linea
     */
    private static void guardarDocument() throws IOException{
        File rutaFitxer = new File("./Updates/UpdateTextilPrices.dat");
        String linea, separador = ";";
        FileReader rdr = new FileReader(rutaFitxer);
        BufferedReader brdr = new BufferedReader(rdr);

        while ((linea = brdr.readLine()) != null){
            String[] valor = linea.split(separador);
            DOCUMENT.put(valor[0], valor[1]);
        }
    }
}

~~~
## Vista
~~~ java
package supermercat.vista;
import supermercat.model.Model;

/**
 * Clase principal de mostrar dades, aquesta clase es l'encarregada de mostrar a l'usuari tot allo que necesita
 * per utilizar l'aplicacio
 */
public class Vista {

    /**
     * Funcio de mostra menu del super, mostra diferents lineas en forma de menu per escullir entre les diferents
     * opcions de l'aplicacio
     */
    public static void mostrarMenuSuper(){
        System.out.println("\tBENVINGUT AL SUPERMERCAT");
        System.out.println("\t------------------------");
        System.out.println("\t---------INICI----------");
        System.out.println("\t------------------------");
        System.out.println("\t1) Introduir producte");
        System.out.println("\t2) Passar per caixa");
        System.out.println("\t3) Mostrar carret de la compra");
        System.out.println("\t0) Acabar");
        System.out.println();
    }

    /**
     * Funcio per mostrar misatges, a partir d'un parametre d'entrada mostra un misatge per pantalla
     * @param msg Parametre d'entrada de tipus String que es mostrara com a misatge
     */
    public static void mostrarMisatge(String msg){
        System.out.println(msg);
    }

    /**
     * Funcio per mostrar demanda de dades, a partir dun parametre d'entrada mostra un misatge per pantalla per demanar
     * introduccio de dades
     * @param msg Parametre d'entrada de tipus String que es mostrara com a misatge
     */
    public static void mostrarIntroduccioDades(String msg){
        System.out.print(msg);
    }

    /**
     * Funcio de mostra de menu de pruductes, mostra diferents lineas en forma de menu per escullir el tipus de producte
     */
    public static void mostrarMenuProducte(){
        System.out.println("\t-------------------------");
        System.out.println("\t--------PRODUCTE---------");
        System.out.println("\t-------------------------");
        System.out.println("\t1) Alimentació");
        System.out.println("\t2) Tèxtil");
        System.out.println("\t3) Electrònica");
        System.out.println("\t0) Tornar");
        System.out.println();
    }
    public static void mostrarPreuFinal(String totalCaixa){
        System.out.println();
        System.out.println();
        System.out.printf("\t%-20s %s", "Total a pagar: ",totalCaixa);
        System.out.println();
    }
    /**
     * Funcio per generar una capçalera de compra en un format determinat
     */
    public static void mostrarCapcaleraTicket(){
        System.out.printf("\t%-15s\t%-12s\t%-8s\t%-8s\n", "Nom", "Unitats", "Preu Unitari", "Preu Total");
    }

    /**
     * Funcio mostra producte a caixa, a partir d'uns parametres d'entrada es mostrara un misatge formatat de una manera
     * determinada amb ells
     * @param nom Parametre d'entrada de tipus String que volem printar
     * @param unitats Parametre d'entrada de tipus String que volem printar
     * @param preuU Parametre d'entrada de tipus String que volem printar
     * @param preuT Parametre d'entrada de tipus String que volem printar
     */
    public static void mostrarCaixa(String nom, String unitats, String preuU, String preuT){
        System.out.printf("\t%-15s\t%-12s\t%-12s\t%-8s\n", nom, unitats, preuU, preuT);
    }

    /**
     * Funcio mostra carro, a partir d'uns parametres d'entrada mostrara un misatge formatat d'una manera determinada
     * @param nom Parametre d'entrada de tipus String que volem printar
     * @param unitats Parametre d'entrada de tipus String que volem printar
     */
    public static void mostrarCarro(String nom, String unitats){
        String separador = "->";
        System.out.printf("\t%-15s\t%-4s\t%-10s\n", nom, separador, unitats);
    }

    /**
     * Funcio que mostra la compra, aquesta funcio dona forma a un tiquet de compra per mostrar la compra realitzada
     */
    public static void mostrarCompra(){
        System.out.println("\t-------------------------");
        System.out.println("\t------SUPERMERCAT--------");
        System.out.println("\t-------------------------");
        System.out.println("\tData: " + Model.mostrarDataActual());
        System.out.println("\t-------------------------");
        mostrarCapcaleraTicket();
        System.out.println();
    }

    /**
     * Funcio que genera una capçalera per a mostrar el carro en un format determinat
     */
    public static void mostrarCapcaleraCarro(){
        System.out.printf("\t%-21s\t%-18s\n", "Nom", "Unitats");
    }
}

~~~
# Constructors
## Producte
~~~ java
package supermercat.constructors;
import java.util.Comparator;

/**
 * Clase general del tipus de productes, aquesta clase abstracte es la base per a crear cualsevol tipus de
 * producte en especific
 */
public abstract class Producte  implements Comparable <Producte>, Comparator<Producte> {
    //variables locals
    float preu;
    String nom, codiBarres;

    /**
     * Constructor de producte, a partir d'uns paramentes d'entrada es genera un objecte producte
     * @param preu parametre d'entrada per definir el preu d'un producte
     * @param nom parametre d'entrada per definir el nom d'un producte
     * @param codiBarres parametre d'entrada per definir el codi de barres d'un producte
     */
    public Producte(float preu, String nom, String codiBarres){
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;

    }

    /**
     * Funcio per extreure el preu d'un producte, una funcio generica de forma abstracte que retorna el preu d'un
     * producte que es podra modificar segons les especificacions de cualsevol producte a la seva clase
     * @return Retorna el valor numeric amb decimals de la variable preu
     */
    public abstract float getPreu();

    /**
     * Funcior per extreure el nom d'un producte, una funcio generica per retornar l'String del nom dun producte
     * @return Retorna l'String del nom de la variable nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Foncio per extreure el codi de barres d'un producte, una funcio generica que retorna el codi de barres d'un
     * producte
     * @return Retorna l'String del codi de la variable codiBarres
     */
    public String getCodiBarres() {
        return codiBarres;
    }

    /**
     * Funcio comparadora, aquesta funcio compara els codis de barres per poder ordenar
     * @param o Producte que volem comparar.
     * @return Retornara positiu o negatiu o 0 segon si el Producte es mes gran mes petit o igual
     */
    public int compareTo(Producte o) {
        return Integer.parseInt(codiBarres) - Integer.parseInt(o.getCodiBarres());
    }

    /**
     * Funcio comparadora, aquesta funcio comparara el preu de 2 objectes per ordenarlos
     * @param o1 Parametre d'entrada de tipus Producte el cual compararem en primer lloc
     * @param o2 Parametre d'entrada de tipus Producte el cual el amb el del primer lloc
     * @return
     */
    public int compare(Producte o1, Producte o2) {
        return (int) (o1.getPreu() - o2.getPreu());
    }
}


~~~
## Alimentacio
~~~ java
package supermercat.constructors;
import supermercat.model.Model;
import java.util.Date;

/**
 * Clase especifica per la creacio d'un producte alimentacio, mante les propietats d'un objecte producte a mes de les
 * distintives de la seva clase
 */
public class Alimentacio extends Producte{
    //variables locals
    Date dataCaducitat;

    /**
     * Constructor de Alimentacio, a partir d'uns parametres d'entrada es genera un objecte alimentacio a partir de les
     * bases de producte i les seves propies
     * @param preu Paramentre d'entrada que defineix el preu segons els criteris del constructor Producte (la classe pare)
     * @param nom Paramentre d'entrada que defineix el nom segons els criteris del constructor Producte (la classe pare)
     * @param codiBarres Paramentre d'entrada que defineix el codi de barres segons els criteris del constructor
     *                   Producte (la classe pare)
     * @param dataCaducitat Parametre d'entrada que defineix la data de caducitat d'un producte d'alimentacio a traves
     *                      de la funcio setDataCaducitat
     */
    public Alimentacio(float preu, String nom, String codiBarres, Date dataCaducitat){
        super(preu, nom, codiBarres);
        setDataCaducitat(dataCaducitat);
    }

    /**
     * Funcio per modificar la data de caducitat d'un producte d'alimentacio, a partir d'un parametre d'entrada modifica
     * el valor que defineix la data de caducitat d'un producte d'alimentacio
     * @param dataCaducitat Parametre rebut per el constructor i que a traves d'auesta funcio definira la data de
     *                      caducitat d'un producte d'alimentacio
     */
    public void setDataCaducitat(Date dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

    /**
     * Funcio per extreure la data de caducitat, funcio generica que retorna la data de caducitat d'un producte
     * d'alimentacio
     * @return Retorna el valor Date de la variable dataCaducitat
     */
    public Date getDataCaducitat() {
        return dataCaducitat;
    }

    /**
     * Funcio per extreure el preu, funcio que sobrescric getPreu() de Producte (la clase pare) que retorna el preu d'un
     * producte d'alimentacio
     * @return Retorna el valor de la variable preu definida per la funcio preuAlimentacio de la clase Model, utilitzant
     * com a parametre el preu del producte d'alimentacio definit segons els criteris del constructor Producte (clase
     * pare) i la funcio getDataCaducitat
     */
    @Override
    public float getPreu(){
        return this.preu = Model.preuAlimentacio(super.preu, getDataCaducitat());
    }

    /**
     * Funcio per mostrar l'objecte alimentacio com un String sobrescribint la toString generica
     * @return Retorna l'objecte alimentacio amb un format determinat
     */
    @Override
    public String toString() {
        //si el preu es inferior o igual a 0 sera 0
        if(getPreu() <= 0){
            return String.format("\t%-15s\t%-12s\t%-8s\t%-15s\n", super.getNom(), super.getCodiBarres(), 0, (getDataCaducitat() + ""));
        }else {
            return String.format("\t%-15s\t%-12s\t%-8.2f\t%-15s\n", super.getNom(), super.getCodiBarres(), getPreu(), (getDataCaducitat() + ""));
        }
    }
}
~~~
## Electronica
~~~ java
package supermercat.constructors;
import supermercat.model.Model;

/**
 * Clase especifica per la creacio d'un producte electronic, mante les propietats d'un objecte producte a mes de les
 * distintives de la seva clase
 */
public class Electronica extends  Producte{
    //variables locals
    int diesGarantia;

    /**
     * Constructor de Electronica, a partir d'uns parametres d'entrada es genera un objecte electronica a partir de les
     * bases de producte i les seves propies
     * @param preu Paramentre d'entrada que defineix el preu segons els criteris del constructor Producte (la classe pare)
     * @param nom Paramentre d'entrada que defineix el nom segons els criteris del constructor Producte (la classe pare)
     * @param codiBarres Paramentre d'entrada que defineix el codi de barres segons els criteris del constructor
     *                   Producte (la classe pare)
     * @param diesGarantia Parametre d'entrada que defineix els dies de garantia d'un producte electronic
     */
    public Electronica(float preu, String nom, String codiBarres, int diesGarantia){
        super(preu, nom, codiBarres);
        setDiesGarantia(diesGarantia);
    }

    /**
     * Funcio per modificar els dies de garantia d'un producte d'electronica, a partir d'un parametre d'entrada modifica
     * el valor que defineix els dies de garantia d'un producte d'electronica
     * @param diesGarantia Parametre rebut per el constructor i que a traves d'auesta funcio definira els dies de
     *                     garantia d'un producte d'electronica
     */
    public void setDiesGarantia(int diesGarantia) {
        this.diesGarantia = diesGarantia;
    }

    /**
     * Funcio per extreure els dies de garantia, funcio generica que retorna el valor numeric dels dies de garantia d'un
     * producte d'electronica
     * @return Retorna el valor numeric enter de la variable diesGarantia
     */
    public int getDiesGarantia(){
        return diesGarantia;
    }

    /**
     * Funcio per extreure el preu, funcio que sobrescric getPreu() de Producte (la clase pare) que retorna el preu d'un
     * producte d'electronica
     * @return Retorna el valor de la variable preu definida per la funcio preuElectronica de la clase Model, utilitzant
     * com a parametre el preu del producte d'electronica definit segons els criteris del constructor Producte (clase
     * pare) i la funcio getDiesGarantia
     */
    @Override
    public float getPreu() {
        return this.preu = Model.preuElectronica(super.preu, getDiesGarantia());
    }

    /**
     * Funcio per mostrar l'objecte electronica com un String sobrescribint la toString generica
     * @return Retorna l'objecte electronica amb un format determinat
     */
    @Override
    public String toString() {
        //si el preu es inferior o igual a 0 sera 0
        if(getPreu() <= 0){
            return String.format("\t%-15s\t%-12s\t%-8s\t-15%d\n", super.getNom(), super.getCodiBarres(), 0, getDiesGarantia());

        }else {
            return String.format("\t%-15s\t%-12s\t%-8.2f\t%-15d\n", super.getNom(), super.getCodiBarres(), getPreu(), getDiesGarantia());
        }
    }
}


~~~
## Textil
~~~ java
package supermercat.constructors;

/**
 * Clase especifica per la creacio d'un producte textil, mante les propietats d'un objecte producte a mes de les
 * distintives de la seva clase
 */
public class Textil extends Producte{
    //variables locals
    String composicioTextil;

    /**
     * Constructor de Textil, a partir d'uns parametres d'entrada es genera un objecte textil a partir de les bases de
     * producte i les seves propies
     * @param preu Paramentre d'entrada que defineix el preu segons els criteris del constructor Producte (la classe pare)
     * @param nom Paramentre d'entrada que defineix el nom segons els criteris del constructor Producte (la classe pare)
     * @param codiBarres Paramentre d'entrada que defineix el codi de barres segons els criteris del constructor
     *                   Producte (la classe pare)
     * @param composicioTextil Parametre d'entrada que defineix la composicio textil d'un producte textil a traves de
     *                         la funcio setComposicioTextil
     */
    public Textil(float preu, String nom, String codiBarres, String composicioTextil){
        super(preu,nom,codiBarres);
        setComposicioTextil(composicioTextil);

    }

    /**
     * Funcio per modificar la composicio textil d'un producte textil, a partir d'un parametre d'entrada modifica el
     * valor que defineix la composicio textil d'un producte textil
     * @param composicioTextil Parametre rebut per el constructor i que a traves d'auesta funcio definira la composicio
     *                        textil d'un producte textil
     */
    public void setComposicioTextil(String composicioTextil) {
        this.composicioTextil = composicioTextil;
    }

    /**
     * Funcio que extrau la composicio textil dun producte textil
     * @return el valor String de la variable composicioTextil d'un producte textil
     */
    public String getComposicioTextil() {
        return composicioTextil;
    }

    /**
     * Funcio que extrau el preu, funcio que sobrescric getPreu() de Producte (la clase pare) que retorna el preu d'un
     * producte textil
     * @return retorna el valor numeric amb decimals de la variable preu
     */
    @Override
    public float getPreu() {
        return preu;
    }

    /**
     * Funcio per mostrar l'objecte textil com un String sobrescribint la toString generica
     * @return Retorna l'objecte textil amb un format determinat
     */
    @Override
    public String toString() {
        //si el preu es inferior o igual a 0 sera 0
        if(getPreu() <= 0){
            return String.format("\t%-15s\t%-12s\t%-8s\t%-15s\n", super.getNom(), super.getCodiBarres(), 0, getComposicioTextil());

        }else {
            return String.format("\t%-15s\t%-12s\t%-8.2f\t%-15s\n", super.getNom(), super.getCodiBarres(), getPreu(), getComposicioTextil());

        }
    }
}

~~~

