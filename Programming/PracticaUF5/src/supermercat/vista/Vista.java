package supermercat.vista;
import supermercat.model.Model;
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
    }

}
