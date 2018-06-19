/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author quico
 */
public class JoueurServiceTest {
    
    private JoueurService joueurService = new JoueurService();
    private PartieService partieService = new PartieService();
    
    @Test
    public void ordreJoueursOK(){
        
        Partie nouvellePartie = partieService.creerNouvellePartie("ordreJoueursOK");
        
        joueurService.rejoindrePartie("A", "A", nouvellePartie.getId());
        joueurService.rejoindrePartie("B", "B", nouvellePartie.getId());
        Joueur j = joueurService.rejoindrePartie("C", "C", nouvellePartie.getId());
        
        assertEquals(3L, (long) j.getOrdre());
    }
    
    @Test
    public void rejoindrePartieOK(){
        
        Partie nouvellePartie = partieService.creerNouvellePartie("partie 1");
        
        joueurService.rejoindrePartie("thomas", "blabla", nouvellePartie.getId());
    }
}
