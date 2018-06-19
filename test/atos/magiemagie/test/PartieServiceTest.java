/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Carte.TypeCarte;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.PartieService;
import java.util.Random;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author quico
 */
public class PartieServiceTest {
    
    private PartieService service = new PartieService();
    
    @Test
    public void testRandomCarte(){
        
        TypeCarte[] tabTypesCartes = Carte.TypeCarte.values();
        
        Random r = new Random();
        int n = r.nextInt( tabTypesCartes.length );
        
        Carte c = new Carte();
        c.setTypeCarte( tabTypesCartes[n] );
    }
    
//    @Test
    public void creerNouvellePartieOK() {
        Partie p = service.creerNouvellePartie("blabla1");
        assertNotNull(p.getId());
    }
    
}
