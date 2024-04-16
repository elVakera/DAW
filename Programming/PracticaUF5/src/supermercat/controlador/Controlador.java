package supermercat.controlador;
import supermercat.vista.Vista;
import supermercat.model.Model;
import java.util.Scanner;

public class Controlador {
    private static final Scanner SCN = new Scanner(System.in);
    public static void supermercat(){
        String opSuper;
        boolean fiSupermercat;

        do{
            Vista.mostrarMenuSuper();
            opSuper = SCN.nextLine();

            switch (opSuper){
                case "0":
                    Vista.mostrarMisatge("Fins aviat!");
                    fiSupermercat = false;
                    break;
                case "1":
                    boolean fiProducte;
                    String opProducte;
                    do{
                        Vista.mostrarMenuProducte();
                        opProducte = SCN.nextLine();

                        switch (opProducte){
                            case "0":
                                Vista.mostrarMisatge("Tornant...");
                                fiProducte = false;
                                break;
                            case "1":
                                Vista.mostrarMisatge("Afegir aliment");
                                Vista.mostrarIntroduccioDades("Nom producte: ");
                                Vista.mostrarIntroduccioDades("Preu: ");
                                Vista.mostrarIntroduccioDades("Composicio: ");
                                Vista.mostrarIntroduccioDades("Codi de barres: ");
                                fiProducte = true;
                                break;
                            case "2":
                                Vista.mostrarMisatge("Afegir textil");
                                Vista.mostrarIntroduccioDades("Nom producte: ");
                                Vista.mostrarIntroduccioDades("Preu: ");
                                Vista.mostrarIntroduccioDades("Data de caducitat (dd/mm/aaaa): ");
                                Vista.mostrarIntroduccioDades("Codi de barres: ");
                                fiProducte = true;
                                break;
                            case "3":
                                Vista.mostrarMisatge("Afegir electronica");
                                Vista.mostrarIntroduccioDades("Nom producte: ");
                                Vista.mostrarIntroduccioDades("Preu: ");
                                Vista.mostrarIntroduccioDades("Garantia (dies): ");
                                Vista.mostrarIntroduccioDades("Codi de barres: ");
                                fiProducte = true;
                                break;
                            default:
                                Vista.mostrarMisatge("Error al introduir opcio");
                                fiProducte = true;
                        }
                    }while (!fiProducte);

                    fiSupermercat = true;
                    break;
                case "2":

                    fiSupermercat = true;
                    break;
                case "3":

                    fiSupermercat = true;
                    break;
                default:
                    Vista.mostrarMisatge("Error al introduir opcio");
                    fiSupermercat = true;
            }
        }while (!fiSupermercat);

    }
}
