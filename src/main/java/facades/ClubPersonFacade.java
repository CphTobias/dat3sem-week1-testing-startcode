package facades;

import dtos.PersonDTO;
import entities.ClubPerson;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

public class ClubPersonFacade {

    private static ClubPersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private ClubPersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ClubPersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ClubPersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() { return emf.createEntityManager();
    }
    
    public ClubPerson createPerson(ClubPerson person){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return person;
    }

    public PersonDTO getPersonById(long id){
        EntityManager em = getEntityManager();
        return new PersonDTO(em.find(ClubPerson.class, id));
    }

    public List<PersonDTO> getAllPeopleDTO(){
        EntityManager em = getEntityManager();
        List<ClubPerson> people = em.createNamedQuery("ClubPerson.findAll", ClubPerson.class)
            .getResultList();
        return PersonDTO.getFromList(people);
    }

    public List<ClubPerson> getAllClubPeople(){
        EntityManager em = getEntityManager();
        List<ClubPerson> people = em.createNamedQuery("ClubPerson.findAll", ClubPerson.class)
            .getResultList();
        return people;
    }

    public List<PersonDTO> getPeopleByName(String name) {
        EntityManager em = getEntityManager();
        List<ClubPerson> people = em.createNamedQuery("ClubPerson.findByName", ClubPerson.class).
            setParameter("name", name).
            getResultList();
        return PersonDTO.getFromList(people);
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        ClubMessageFacade clubMessageFacade = ClubMessageFacade.getInstance(emf);
        ClubPersonFacade personFacade = ClubPersonFacade.getFacadeExample(emf);
        try {
            ClubPerson clubPerson = em.createQuery("SELECT cp FROM ClubPerson cp WHERE cp.id = 1", ClubPerson.class).getSingleResult();
            em.getTransaction().begin();
            clubMessageFacade.addMessage("This is a new message", clubPerson);
            clubMessageFacade.addMessage("Hello world", clubPerson);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
