/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;

/**
 *
 * @author quico
 */
public class JoueurService {
    
    private JoueurDAO dao = new JoueurDAO();
    private PartieDAO partieDAO = new PartieDAO();
    
    public void rejoindrePartie(String pseudo, String avatar, long idPartie){
        
        // Recherche si joueur existe déjà
        Joueur joueur = dao.rechercherParPseudo(pseudo);
        if( joueur==null ){
            
            // le joueur n'existe pasd encore
            
            joueur = new Joueur();
            joueur.setPseudo(pseudo);
            joueur.setNbPartiesJouees(0L);
            joueur.setNbPartiesGagnees(0L);
        }
        
        joueur.setAvatar(avatar);
        joueur.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
        long ordre = dao.rechercheOrdreNouveauJoueurPourPartieId(idPartie);
        joueur.setOrdre( ordre );
        
        // Associe le joueur à la partie et vice-versa (JPA relations bidirectionnelles)
        Partie partie = partieDAO.rechercherParId(idPartie);
        joueur.setPartie(partie);
        List<Joueur> listeJoueurs = partie.getJoueurs();
        listeJoueurs.add(joueur);
        
        if( joueur.getId()==null ){// Nouveau
            dao.ajouter(joueur);
        }else{// Existe déjà
            dao.modifier(joueur);
        }
        
    }
}
