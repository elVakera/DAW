package supermercat.controlador;
import supermercat.vista.Vista;
import supermercat.model.Model;
import java.util.Scanner;

public class Controlador {
    private static final Scanner SCN = new Scanner(System.in);
    public static void supermercat(){
        String op;

        Vista.mostrarMenuSuper();
    }
}
