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
