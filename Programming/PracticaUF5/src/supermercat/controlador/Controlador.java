package supermercat.controlador;
import supermercat.vista.Vista;
import supermercat.model.Model;

import java.text.ParseException;
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
                                String nomA, codiBarresA, dataCaducitatA;
                                float preuA;

                                Vista.mostrarMisatge("\tAfegir aliment");
                                Vista.mostrarIntroduccioDades("\tNom producte: ");
                                nomA = SCN.nextLine();
                                Vista.mostrarIntroduccioDades("\tPreu: ");
                                preuA = SCN.nextFloat();
                                SCN.nextLine();
                                Vista.mostrarIntroduccioDades("\tData de caducitat (dd/mm/YYYY): ");
                                dataCaducitatA = SCN.nextLine();
                                Vista.mostrarIntroduccioDades("\tCodi de barres: ");
                                codiBarresA = SCN.nextLine();
                                Model.afegirAliment(preuA, nomA, codiBarresA, dataCaducitatA);
                                fiProducte = true;
                                break;

                            case "2":
                                String nomT, codiBarresT, composicioT;
                                float preuT;

                                Vista.mostrarMisatge("\tAfegir textil");
                                Vista.mostrarIntroduccioDades("\tNom producte: ");
                                nomT = SCN.nextLine();
                                Vista.mostrarIntroduccioDades("\tPreu: ");
                                preuT = SCN.nextFloat();
                                SCN.nextLine();
                                Vista.mostrarIntroduccioDades("\tComposicio: ");
                                composicioT = SCN.nextLine();
                                Vista.mostrarIntroduccioDades("\tCodi de barres: ");
                                codiBarresT = SCN.nextLine();
                                Model.afegirTextil(preuT, nomT, codiBarresT, composicioT);
                                fiProducte = true;
                                break;

                            case "3":
                                String nomE, codiBarresE;
                                int diesGarantiaE;
                                float preuE;

                                Vista.mostrarMisatge("\tAfegir electronica");
                                Vista.mostrarIntroduccioDades("\tNom producte: ");
                                nomE = SCN.nextLine();
                                Vista.mostrarIntroduccioDades("\tPreu: ");
                                preuE = SCN.nextFloat();
                                SCN.nextLine();
                                Vista.mostrarIntroduccioDades("\tGarantia (nº dies): ");
                                diesGarantiaE = SCN.nextInt();
                                SCN.nextLine();
                                Vista.mostrarIntroduccioDades("\tCodi de barres: ");
                                codiBarresE = SCN.nextLine();
                                Model.afegirElectronica(preuE, nomE, codiBarresE, diesGarantiaE);
                                fiProducte = true;
                                break;


                            default:
                                Vista.mostrarMisatge("Error al introduir opcio: " + opProducte + " no es una opcio");
                                fiProducte = true;

                        }
                    }while (fiProducte);

                    fiSupermercat = true;
                    break;

                case "2":
                try {
                    Model.pasarPerCaixa();
                } catch (ParseException e) {
                    Vista.mostrarMisatge("Error al parsejar preu");
                }
                    
                    fiSupermercat = true;
                    break;

                case "3":
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
