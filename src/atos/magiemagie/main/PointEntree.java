/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.main;

import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.PartieService;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author quico
 */
public class PointEntree {

    private PartieService partieService = new PartieService();
    
    public void menuPrincipal() {

        Scanner scan = new Scanner(System.in);
        String choix;
        
        do {
            System.out.println("Menu principal");
            System.out.println("--------------");
            System.out.println("1. Lister les parties non démarrées");
            System.out.println("2. Créer une partie");
            System.out.println("3. Rejoindre une partie");
            System.out.println("4. Démarrer une partie");
            System.out.println("Q. Quitter");
            System.out.print("Votre choix > ");

            choix = scan.nextLine();
            switch (choix) {
                case "1":
                    List<Partie> parties = partieService.listerPartiesNonDemarrees();
                    System.out.println(parties);
                    break;

                case "2":
                    break;

                case "3":
                    break;

                case "4":
                    break;

                case "Q":
                    break;

                default:
                    System.out.println("Choix inconnu!");
                    break;

            }
        } while ( choix.equals("Q")==false );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PointEntree m = new PointEntree();

        m.menuPrincipal();
    }

}
