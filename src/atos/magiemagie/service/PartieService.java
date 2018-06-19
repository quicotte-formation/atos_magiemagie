/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Partie;
import java.util.List;

/**
 *
 * @author quico
 */
public class PartieService {
    
    private PartieDAO dao = new PartieDAO();
    
    public Partie creerNouvellePartie(String nom){
        
        Partie p = new Partie();
        p.setNom(nom);
        dao.ajouter(p);
        
        return p;
    }
    
    /**
     * Liste les parties dont aucun joueur n'est à l'état A_LA_MAIN
     * ou GAGNE.
     * @return 
     */
    public List<Partie> listerPartiesNonDemarrees(){
        
        return dao.listerPartiesNonDemarrees();
    }
}
