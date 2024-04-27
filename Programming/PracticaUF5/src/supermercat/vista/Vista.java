package supermercat.vista;
import supermercat.model.Model;

/**
 * Clase principal de mostrar dades, aquesta clase es l'encarregada de mostrar a l'usuari tot allo que necesita
 * per utilizar l'aplicacio
 */
public class Vista {
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

    public static void mostrarMisatge(String msg){
        System.out.println(msg);
    }

    public static void mostrarIntroduccioDades(String msg){
        System.out.print(msg);
    }

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
        System.out.println("\t->\t" + producte);
    }

    public static void mostrarCapcalera(){
        System.out.printf("\t%-15s\t%-12s\t%-8s\t%s\n", "Nom", "Unitats", "Preu Unitari", "Preu Total");
    }

    public static void mostrarCompra(){
        System.out.println("\t-------------------------");
        System.out.println("\t------SUPERMERCAT--------");
        System.out.println("\t-------------------------");
        System.out.println("\tData: " + Model.mostrarDataActual());
        System.out.println("\t-------------------------");
        Model.carroActual();
        System.out.println();
    }
}
