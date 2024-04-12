package Codi;

/**
 * @description Aquest programa mostra un menu amb 4 progrmes diferents que inclouen crear un vector, generar numeros
 * ranom i trobarne els numeros enars i parells, trobar un numero parell dins d'un array i tambe mostrar el contingut
 * @date 05/04/2024
 * @version 1.1
 */

import java.util.Scanner;
import java.lang.Math;

public class Menu {
    private Scanner in = new Scanner(System.in);
    private int opcio;
    private int [] vector = new int[10];
    private int [][] matriu = new int[10][10];

    /**
     * @description Aquesta funcio mostra un menu selector de les diferents funcionalitats del programa,
     * et pregunta quina opcio entre la (0-4) vols entrar per teclat i executara la que li correspon segons el menu
     * en cas de introduirla malamente surtir un avis d'atencio i tornara a preguntarte fins que escullis una opcio
     * correcta
     */
    public void menuPrincipal() {
        do {
            System.out.println("1. Crear un vector");
            System.out.println("2. Parells i Senars");
            System.out.println("3. Trobar un número parell");
            System.out.println("4. Mostrar contingut");
            System.out.println("0. Acabar");
            System.out.print("Entra una opció(0-4): ");
            opcio = in.nextInt();
            in.nextLine();

            switch (opcio) {
                case 1:
                    //crear un vector
                    System.out.print("Entra la llargada del vector: ");
                    int n = in.nextInt();
                    crearVector(vector,n);
                    break;
                case 2:
                    //comprovar si hi ha més parells o senars dins d'una matriu
                    parellsoSenars(matriu);

                    break;
                case 3:
                    //cercar números parells dins d'un vector
                    if (hihaParells(vector)) {
                        System.out.println("s'ha trobat números parells al vector");
                    }
                    else System.out.println("No s'ha trobat números parells!!!");

                    break;
                case 4:
                    //mostrar el contingut del vector
                    mostrarContingut(vector);
                    break;
                case 0:
                    System.out.println("Gràcies. Fins la propera!");
                    break;
                default:
                    System.out.println("ATENCIÓ!!! \nHa de ser un valor entre 0 i 4");

            }
        }while(opcio != 0);
    }

    /**
     * @description Aquesta funcio crea un vector a partir d'un array d'enters "vector" i un enter "n"
     * @param vector Es l'array dd'enters que volem omplir
     * @param n Es el tamany de l'array
     */
    public static void crearVector(int []vector,int n){
        for (int i = 0; i < n; i++) { //omplim vector
            vector[i] = (int)Math.floor(Math.random()*10);
            System.out.print(vector[i]);
        }
    }

    /**
     * @description Aquesta funcio genera un numero random el 0 al 9 i el guarda en un array doble que entra per
     * parametre, comprova que es senar o parell i el contabilitza cuants hi ha i finalment motra si hi ha mes o
     * menys parells o iguals
     * @date 5/04/2024
     * @param matriu Es la taula on guardarem els valors que volem tractar
     */
    public static void parellsoSenars(int [][]matriu){
        //ToDo: aquesta funció ha de retornar el total de números parells o senars segons qui guanyi
        int parell=0,senar=0;

        for (int i = 0; i < matriu.length; i++) {
            for (int j = 0; j < matriu.length; j++){
                matriu[i][j] = (int)Math.floor(Math.random()*10);
                if (matriu[i][j]% 2 == 0) {
                    parell = parell + 1;
                } else {
                    senar = senar + 1;
                }
            }
        }
        if (parell > senar){
            System.out.println("hi ha més parells que senars");
        }
        else if (senar > parell){
            System.out.println("hi ha més senars que parells");
        }
        else System.out.println("empat!");
    }

    /**
     * @description Aquesta funcio ens troba y mostra si hi ha o no un numero parell ins d'un arrai que entra per
     * parametre y retornara true o false segons si troba un o no
     * @param vector Es el vector que volem comprovar per saber si conte un parell
     * @return retornara true o false segons trobi o no un parell
     */
    public static boolean hihaParells(int [] vector){
        int i=0;
        while ((i < vector.length) && (vector[i]%2 != 0)){  //v.length ens diu la longitud del vector
            i=i+1;
        }
        if (i != vector.length){
            System.out.println("hi ha almenys un número parell");
        }
        else System.out.println("no s'ha trobat cap número parell");
        return true;
    }

    /**
     * @description Aquesta funcio mostra mostra el contingut d'un arrai que entra per parametre
     * @param vector Es l'array que volem mostrar
     * @date 12/04/2024
     */
    public static void mostrarContingut(int [] vector){
        //PENDENT: aquesta funció està condicionada a l'execució d'alguna  de les funcions anteriors
        int i=0;
        while(vector[i]!='\0'){
            System.out.print(vector[i]);
            i++;
        }
    }

}