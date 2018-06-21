/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author quico
 */
public class PartieDAO {
    
    public long rechercheOrdreMaxJoueurPourPartieId(long partieId){
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query q = em.createQuery("SELECT MAX(j.ordre) FROM Joueur j JOIN j.partie p WHERE p.id=:id");
        q.setParameter("id", partieId);
        
        return (long) q.getSingleResult();
    }
    
    public boolean determineSiPlusQueUnJoueurDansPartie(long partieId){
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query q = em.createQuery(""
                + "SELECT   j "
                + "FROM     Joueur j "
                + "         JOIN j.partie p "
                + "WHERE    p.id=:idPartie"
                + "EXCEPT "
                + "SELECT   j "
                + "FROM     Joueur j "
                + "         JOIN j.partie p "
                + "WHERE    p.id=:idPartie "
                + "         AND j.etat=:etatPerdu");
        q.setParameter("idPartie", partieId);
        q.setParameter("etatPerdu", Joueur.EtatJoueur.PERDU);
        List res = q.getResultList();
        
        return res.size()==1;
    }
    
    public void ajouter(Partie p){
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }
    
    public List<Partie> listerPartiesNonDemarrees(){
        
        EntityManager em =Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Query query = em.createQuery(""
                + "SELECT p "
                + "FROM Partie p "
                + "EXCEPT "
                + "SELECT p "
                + "FROM Partie p "
                + "     JOIN p.joueurs j "
                + "WHERE j.etat IN (:etat_gagne, :etat_alamain)");
        query.setParameter("etat_gagne", Joueur.EtatJoueur.GAGNE);
        query.setParameter("etat_alamain", Joueur.EtatJoueur.A_LA_MAIN);
        
        return query.getResultList();
    }

    public Partie rechercherParId(long idPartie) {
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        return em.find(Partie.class, idPartie);
    }
}
