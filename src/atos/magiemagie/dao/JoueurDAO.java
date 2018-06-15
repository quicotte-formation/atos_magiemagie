/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Joueur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author quico
 */
public class JoueurDAO {
    
    public long rechercheOrdreNouveauJoueurPourPartieId(long partieId){
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Query query = em.createQuery("SELECT MAX(j.ordre)+1 FROM Joueur j JOIN j.partie p WHERE p.id=:idPartie");
        query.setParameter("idPartie", partieId);
        
        Object res = query.getSingleResult();
        if( res==null ){
            return 1;
        }
        
        return (long) res;
    }
    
    /**
     * Renvoie le Joueur dont le pseudo existe en BD, ou renvoie null si pas trouvé.
     * @param pseudo Le pseudo à rechercher.
     * @return 
     */
    public Joueur rechercherParPseudo(String pseudo){
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Query query = em.createQuery("SELECT j FROM Joueur j WHERE j.pseudo=:lePseudo");
        query.setParameter("lePseudo", pseudo);
        
        List<Joueur> joueursTrouves = query.getResultList();
        
        if( joueursTrouves.isEmpty() )
            return null;
        
        return joueursTrouves.get(0);
    }

    public void ajouter(Joueur joueur) {
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        em.persist(joueur);
        em.getTransaction().commit();
    }

    public void modifier(Joueur joueur) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        em.merge(joueur);
        em.getTransaction().commit();
    }

    
}
