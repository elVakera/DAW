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
