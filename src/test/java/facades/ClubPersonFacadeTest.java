package facades;

import dtos.PersonDTO;
import entities.ClubMessage;
import entities.ClubPerson;
import java.util.ArrayList;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class ClubPersonFacadeTest {

    private static EntityManagerFactory emf;
    private static ClubPersonFacade facade;

    public ClubPersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = ClubPersonFacade.getFacadeExample(emf);
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("ClubPerson.deleteAllRows").executeUpdate();
            em.persist(new ClubPerson("Tobias", "Ey", 22, "321312"));
            em.persist(new ClubPerson("Bob", "Ey", 22, "321312"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void createPerson() {
        ClubPerson cp = new ClubPerson("Bob", "Thebuilder", 34, "21121");
        ClubPerson clubPerson = facade.createPerson(cp);
        PersonDTO personDTO = facade.getPersonById(clubPerson.getId());
        assertEquals(clubPerson.getId(), personDTO.getId());
    }

    @Test
    void getPersonById() {
        ClubPerson cp = new ClubPerson("Bob", "Thebuilder", 34, "21121");
        PersonDTO personDTO = facade.getPersonById(1);
        assertEquals(1, personDTO.getId());
    }

    @Test
    void getAllPeopleDTO() {
        List<PersonDTO> personDTO = facade.getAllPeopleDTO();
        assertEquals(2, personDTO.toArray().length);
    }

    @Test
    void getPeopleByName() {
        List<PersonDTO> personDTO = facade.getPeopleByName("Tobias");

        assertEquals(1, personDTO.toArray().length);

        personDTO.forEach(p -> {
            assertEquals("Tobias Ey", p.getName());
        });
    }
}
