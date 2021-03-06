/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.ClubMessage;
import entities.ClubPerson;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        ClubPerson cp = new ClubPerson("Tobias", "Z", 22, "31312331");
        try {
            em.getTransaction().begin();
            em.persist(cp);
            em.persist(new ClubMessage("Hello there this is a test", cp));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        populate();
    }
}
