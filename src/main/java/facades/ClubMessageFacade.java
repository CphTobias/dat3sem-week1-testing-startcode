package facades;

import dtos.MessageDTO;
import dtos.PersonDTO;
import entities.ClubMessage;
import entities.ClubPerson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ClubMessageFacade {

    private static EntityManagerFactory emf;
    private static ClubMessageFacade instance;

    private ClubMessageFacade() {
    }

    public static ClubMessageFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new ClubMessageFacade();
            emf = _emf;
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MessageDTO getMessageById(long id) {
        EntityManager em = getEntityManager();
        return new MessageDTO(em.find(ClubMessage.class, id));
    }

    public MessageDTO addMessage(String message, ClubPerson clubPerson) {
        EntityManager em = getEntityManager();
        ClubMessage clubMessage = new ClubMessage(message, clubPerson);
        try {
            em.getTransaction().begin();
            em.persist(clubMessage);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MessageDTO(clubMessage);
    }

    public List<MessageDTO> getAllMessages() {
        EntityManager em = getEntityManager();
        List<ClubMessage> clubMessages = em.createNamedQuery("ClubMessage.findAll", ClubMessage.class).getResultList();
        return MessageDTO.getFromList(clubMessages);
    }

    public List<MessageDTO> getAllMessagesByPerson(long personid) {
        EntityManager em = getEntityManager();
        List<ClubMessage> clubMessages = em.createNamedQuery("ClubMessage.findByPerson", ClubMessage.class)
            .setParameter("person", personid)
            .getResultList();
        return MessageDTO.getFromList(clubMessages);
    }
}
