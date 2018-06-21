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
public class PartieService {
    
    private PartieDAO dao = new PartieDAO();
    private JoueurDAO joueurDAO = new JoueurDAO();
    
    public void passeJoueurSuivant(long idPartie){
        
        // Récupere joueur qui a la main => joueurQuiALaMain
        Joueur joueurQuiALaMain = joueurDAO.rechercheJoueurQuiALaMainPourPartieId(idPartie);
        
        // Détermine si tous autres joueurs ont perdu
        // et passe le joueur à l'éata gagné si c'est le cas
        // puis quitte la fonction.
        if( dao.determineSiPlusQueUnJoueurDansPartie(idPartie) ){
            
            joueurQuiALaMain.setEtat(Joueur.EtatJoueur.GAGNE);
            joueurDAO.modifier(joueurQuiALaMain);
            return;
        }
        
        // La partie n'est pas terminée
        
        // Récup ordre MAX des joueurs de la partie
        long ordreMax = dao.rechercheOrdreMaxJoueurPourPartieId(idPartie);
        
        // joueurEvalue = joueurQuiALaMain
        Joueur joueurEvalue = joueurQuiALaMain;
        
        while(true){// Boucle qui permet de déterminer le joueur qui 'attrape' la main
            
           // Si joueurEvalue est le dernier joueur alors on évaluera le 1er
           if( joueurEvalue.getOrdre()>=ordreMax ){
               joueurEvalue=joueurDAO.rechercheJoueurParPartieIdEtOrdre(idPartie, 1L);
           }else{
               joueurEvalue = joueurDAO.rechercheJoueurParPartieIdEtOrdre(idPartie, joueurEvalue.getOrdre()+1 );
           }
           
           // Return si tout les joueurs non étiminés étaient en sommeil profond ( et qu'on la a juste réveillés )
           if( joueurEvalue.getId()==joueurQuiALaMain.getId() )
               return;
           
           // Si joueurEvalue en sommeil profond => son etat passe à PAS LA MAIN
           if( joueurEvalue.getEtat()==Joueur.EtatJoueur.SOMMEIL_PROFOND ){
               joueurEvalue.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
               joueurDAO.modifier(joueurEvalue);
           }else{
               // N'était pas en sommeil profon
               
               // SI joueurEvaluepas la main ? Alors c'est lui qui prend la main
               if( joueurEvalue.getEtat()==Joueur.EtatJoueur.N_A_PAS_LA_MAIN ){
                   
                   joueurQuiALaMain.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                   joueurDAO.modifier(joueurQuiALaMain);
                   
                   joueurEvalue.setEtat(Joueur.EtatJoueur.A_LA_MAIN);
                   joueurDAO.modifier(joueurEvalue);
                   
                   return;
               }
           }
           
           
        }
    }
    
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
