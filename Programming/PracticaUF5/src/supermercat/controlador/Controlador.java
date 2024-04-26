package supermercat.controlador;
import supermercat.vista.Vista;
import supermercat.model.Model;

import java.text.ParseException;
import java.util.InputMismatchException;
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
                                try{
                                    String nomA, codiBarresA, dataCaducitatA;
                                    float preuA;

                                    Vista.mostrarMisatge("Afegir aliment");
                                    Vista.mostrarIntroduccioDades("Nom producte: ");
                                    nomA = SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Preu: ");
                                    preuA = SCN.nextFloat();
                                    SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Data de caducitat (dd/mm/YYYY): ");
                                    dataCaducitatA = SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Codi de barres: ");
                                    codiBarresA = SCN.nextLine();

                                    if(nomA.length() > 15){
                                        Vista.mostrarMisatge("nom reduit a 15 caracters");
                                        nomA = nomA.substring(15);
                                    }

                                    Model.afegirAliment(preuA, nomA, codiBarresA, Model.comprovarData(dataCaducitatA));

                                }catch (ParseException e){
                                    Vista.mostrarMisatge("Error al introduir la data");

                                }catch(InputMismatchException e){
                                    Vista.mostrarMisatge("Error al introduir el preu");

                                }catch (Exception e){
                                    Vista.mostrarMisatge("FATAL ERROR!");

                                }finally {
                                    fiProducte = true;
                                }
                                break;

                            case "2":
                                try {
                                    String nomT, codiBarresT, composicioT;
                                    float preuT;

                                    Vista.mostrarMisatge("Afegir textil");
                                    Vista.mostrarIntroduccioDades("Nom producte: ");
                                    nomT = SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Preu: ");
                                    preuT = SCN.nextFloat();
                                    SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Composicio: ");
                                    composicioT = SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Codi de barres: ");
                                    codiBarresT = SCN.nextLine();

                                    if(nomT.length() > 15){
                                        Vista.mostrarMisatge("nom reduit a 15 caracters");
                                        nomT = nomT.substring(15);
                                    }

                                    Model.afegirTextil(preuT, nomT, codiBarresT, composicioT);

                                }catch(InputMismatchException e){
                                    Vista.mostrarMisatge("Error al introduir el preu");

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
                                    Vista.mostrarIntroduccioDades("Nom producte: ");
                                    nomE = SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Preu: ");
                                    preuE = SCN.nextFloat();
                                    SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Garantia (nº dies): ");
                                    diesGarantiaE = SCN.nextInt();
                                    SCN.nextLine();
                                    Vista.mostrarIntroduccioDades("Codi de barres: ");
                                    codiBarresE = SCN.nextLine();

                                    if(nomE.length() > 15){
                                        Vista.mostrarMisatge("nom reduit a 15 caracters");
                                        nomE = nomE.substring(15);
                                    }

                                    Model.afegirElectronica(preuE, nomE, codiBarresE, diesGarantiaE);

                                }catch(InputMismatchException e){
                                    Vista.mostrarMisatge("Error al introduir el preu");

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
                    Model.pasarPerCaixa();
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
