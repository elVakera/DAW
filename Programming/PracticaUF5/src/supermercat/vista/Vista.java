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

    public static void mostrarProductes(String producte){
        System.out.println(producte + "\t->\t");
    }

    /**
     * Funcio per generar una capçalera de compra en un format determinat
     */
    public static void mostrarCapcaleraTicket(){
        System.out.printf("\t%-15s\t%-12s\t%-8s\t%s\n", "Nom", "Unitats", "Preu Unitari", "Preu Total");
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
        System.out.printf("\t%-15s\t%-12s\n", "Nom", "Unitats");
    }
}
